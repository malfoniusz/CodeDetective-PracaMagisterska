package model;

public enum SetttingsTokensRadioGroup {

    FULL("full"),
    NORMAL("normal"),
    BROAD("broad"),
    MINIMALISTIC("minimalistic"),
    CUSTOM("custom"),
    ;

    private final String text;

    private SetttingsTokensRadioGroup(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
