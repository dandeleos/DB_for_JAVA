package edu.uob.Interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Tokeniser {
//    String command;
//    private String fragments[];
//    ArrayList<String> tokens = new ArrayList<>();
//    String[] specialCharacters = {"(",")",",",";"};
    private LinkedList<Token> tokens;
    private LinkedList<TokenInfo> tokenInfos;
    public Tokeniser() {
        tokenInfos = new LinkedList<TokenInfo>();
        tokens = new LinkedList<Token>();

        /* --- define tokens information --- */

        // Command type (a list of special keywords, so have just placed them all into a single regular expression)
        addToken("\\bUSE\\b|\\bCREATE\\b|\\bDROP\\b|\\bALTER\\b|\\bINSERT\\b|\\bSELECT\\b|\\bUPDATE\\b|\\bDELETE\\b" +
                "|\\bJOIN\\b", TokenType.CMD);

        // Keywords (Unsure how extensive this list needs to be, but for now will just keep them all in one regular expression)
        addToken("\\bAND\\b|\\bDROP\\b|\\bDATABASE\\b|\\bDROP\\b|\\bFROM\\b|\\bIN\\b|\\bINTO\\b|\\bNULL\\b|\\bON\\b" +
                "|\\bOR\\b|\\bSET\\b|\\bTABLE\\b|\\bVALUES\\b|\\bWHERE\\b", TokenType.KEYWORD);

        // Operations (in a list)
        addToken(("==|<=|>=|!=|<|>|=|\\bLIKE\\b"), TokenType.OPERATOR); // Operation

        // Literals (expressions for are quite messy so separating into String, number, character and boolean)
        addToken("'([^']|\\.)*'", TokenType.STRING); // String literal \\"([^\\"]|\\.)*\\"
        addToken("[+-]?([0-9]*[.])?[0-9]+", TokenType.NUMBER); // Number literal
        addToken("\\bTRUE\\b|\\bFALSE\\b", TokenType.Boolean); // Boolean literal
        addToken("\\b[a-zA-Z]\\b", TokenType.Character); // Character literal (letters)
        addToken("[!#$%&()*+,-\\./:;<=>?@[/]^_`{~}]", TokenType.Character); // Character literal (special characters)
        // addToken("\\bNULL\\b", TokenType.LIT); // NULL literal

        // Separator
        addToken(("\\s+"), TokenType.Separator);

        // Identifiers (Basically any word, so has the lowest precedence)
        addToken("\\b([a-zA-Z0-9])*\\b", TokenType.IDENTIFIER);

    }

    public void addToken(String regex,TokenType token) {
        tokenInfos.add(
        new TokenInfo(
        Pattern.compile("^("+regex+")",Pattern.CASE_INSENSITIVE),token));
    }


    public boolean tokenize(String command) {
        if(command != null && !command.isEmpty()) {
            String s = command.trim();//.strip();
            while(!s.isEmpty()) {// && !s.equals("")){
                boolean match = false;
                for(TokenInfo info : tokenInfos) {
                    Matcher m = info.regex.matcher(s);
                    if(m.find()) {
                        match = true;
                        String tok = m.group().trim();
                        tokens.add(new Token(info.token,tok));

                        s = m.replaceFirst("").trim();//.strip();
                        if (tokens.size() > command.length()) {
                            return false;
                        }
//                        if(!info.token.equals(TokenType.Separator)) {
//                            tokens.add(new Token(info.token,tok));
//                        }
//                        break;
                    }
                }
                if(!match) return false;
            }
            return true;
        }
    }

    public LinkedList<Token> getTokens(){
        return tokens;
    }

//    public Tokeniser(String command){
//        this.command = command.trim();
//        this.fragments = command.split("'");
//        for (int i=0; i<fragments.length; i++) {
//            // Every odd fragment is a string literal, so just append it without any alterations
//            if (i%2 != 0) tokens.add("'" + fragments[i] + "'");
//                // If it's not a string literal, it must be query characters (which need further processing)
//            else {
//                // Tokenise the fragments into an array of strings
//                String[] nextBatchOfTokens = tokenise(fragments[i]);
//                // Then add these to the "result" array list (needs a bit of conversion)
//                tokens.addAll(Arrays.asList(nextBatchOfTokens));
//
//            }
//        }
//        // Finally, loop through the result array list, printing out each token a line at a time
//        for(int i=0; i<tokens.size(); i++) System.out.println(tokens.get(i));
//
//
//    }
//
//    private String[] tokenise(String input)
//    {
//        // Add in some extra padding spaces around the "special characters"
//        // so we can be sure that they are separated by AT LEAST one space (possibly more)
//        for(int i=0; i<specialCharacters.length ;i++) {
//            input = input.replace(specialCharacters[i], " " + specialCharacters[i] + " ");
//        }
//        // Remove all double spaces (the previous replacements may had added some)
//        // This is "blind" replacement - replacing if they exist, doing nothing if they don't
//        while (input.contains("  ")) input = input.replaceAll("  ", " ");
//        // Again, remove any whitespace from the beginning and end that might have been introduced
//        input = input.trim();
//        // Finally split on the space char (since there will now ALWAYS be a space between tokens)
//        return input.split(" ");
//    }

    private class TokenInfo{
        public final Pattern regex;
        public final TokenType token;

        public TokenInfo(Pattern regex, TokenType token) {
            super();
            this.regex = regex;
            this.token = token;
        }
    }

    }


}
