package edu.uob.Interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Pattern;

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

    }

    public void addToken(String regex,TokenType token) {
        tokenInfos.add(
        new TokenInfo(
        Pattern.compile("^("+regex+")"),token));
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
