package model;

public enum ConsecutiveOption {

    LINES("lines"),
    TOKENS("tokens")
    ;

    private final String text;

    private ConsecutiveOption(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
