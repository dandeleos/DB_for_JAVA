package edu.uob;

import edu.uob.Tokeniser;

import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Paths;
import java.nio.file.Files;



/** This class implements the DB server. */
public class DBServer {

    private static final char END_OF_TRANSMISSION = 4;
    private String storageFolderPath;
    private File dataBasePath;
    private File useDataBasePath;

    public static void main(String args[]) throws IOException {
        DBServer server = new DBServer();
        server.blockingListenOn(8888);
    }

    /**
    * KEEP this signature otherwise we won't be able to mark your submission correctly.
    */
    public DBServer() {
        storageFolderPath = Paths.get("databases").toAbsolutePath().toString();
        File BasePath = new File(storageFolderPath);
        this.dataBasePath = BasePath;
        this.useDataBasePath = null;
        try {
            // Create the database storage folder if it doesn't already exist !
            Files.createDirectories(Paths.get(storageFolderPath));
        } catch(IOException ioe) {
            System.out.println("Can't seem to create database storage folder " + storageFolderPath);
        }
    }

    /**
    * KEEP this signature (i.e. {@code edu.uob.DBServer.handleCommand(String)}) otherwise we won't be
    * able to mark your submission correctly.
    *
    * <p>This method handles all incoming DB commands and carries out the required actions.
    */
    public String handleCommand(String command){
        // TODO implement your server logic here
        //System.out.println(storageFolderPath);
        //System.out.println(readFile("people.tab"));
        Tokeniser token = new Tokeniser(command);
        System.out.println(token.tokens);
        try {
            Tokeniser token = new Tokeniser();

            if (tokeniser.tokenise(command)){
                Parser parser = new Parser(token);
                DBCmd cmd = parser.parse();
                return cmd.execute(this);
            }
        }

        return "";
    }
    public String readFile(String name) throws FileNotFoundException {
        String result = "firstLine";
        String pathName = storageFolderPath + File.separator + name;
        File fileToOpen = new File(pathName);
        Reader reader = new FileReader(fileToOpen);


        try {

            BufferedReader buffReader = new BufferedReader(reader);
            String firstLine = buffReader.readLine();
            buffReader.close();
            result = firstLine;

        } catch (FileNotFoundException ioe) {
            System.out.println("Can't seem to find the file " + name);
        } catch (IOException ioe) {
            System.out.println("Can't seem to redirect to reader " + name);
        }


        return result;
    }

    //  === Methods below handle networking aspects of the project - you will not need to change these ! ===

    public void blockingListenOn(int portNumber) throws IOException {
        try (ServerSocket s = new ServerSocket(portNumber)) {
            System.out.println("Server listening on port " + portNumber);
            while (!Thread.interrupted()) {
                try {
                    blockingHandleConnection(s);
                } catch (IOException e) {
                    System.err.println("Server encountered a non-fatal IO error:");
                    e.printStackTrace();
                    System.err.println("Continuing...");
                }
            }
        }
    }

    private void blockingHandleConnection(ServerSocket serverSocket) throws IOException {
        try (Socket s = serverSocket.accept();
             BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()))) {

            System.out.println("Connection established: " + serverSocket.getInetAddress());
            while (!Thread.interrupted()) {
                String incomingCommand = reader.readLine();
                System.out.println("Received message: " + incomingCommand);
                String result = handleCommand(incomingCommand);
                writer.write(result);
                writer.write("\n" + END_OF_TRANSMISSION + "\n");
                writer.flush();
            }
        }
    }

    public File getDataBasePath() {
        return dataBasePath;
    }

    public File getUseDataBasePath() {
        return useDataBasePath;
    }

    public void setUseDataBasePath(File useDataBasePath) {
        this.useDataBasePath = useDataBasePath;
    }

    public void setDataBasePath(File dataBasePath) {
        this.dataBasePath = dataBasePath;
    }
}
