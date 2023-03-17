package edu.uob.Interpreter;

public class Token {
    private TokenType type;
    private String sequence;

    public Token(TokenType type, String sequence) {
        this.type = type;
        this.sequence = sequence;
    }

    public TokenType getType() {
        return type;
    }

    public String getSequence() {
        return sequence;
    }

}
