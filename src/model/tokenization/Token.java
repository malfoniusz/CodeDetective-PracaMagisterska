package model.tokenization;

public enum Token {
    // Tokeny sa poukladane w kolejnosci ich wykrywania przez proces tokenizacji

    CONDITIONAL_STATEMENT("CONDITIONAL_STATMENT"),  // if, else, else if
    LOOP("LOOP"),   // for, while, do
    SWITCH("SWITCH"),
    TRY("TRY"),
    CATCH("CATCH"),

    FUNCTION_DEF("FUNCTION_DEF"),   // function def + constructor def
    FUNCTION_USE("FUNCTION_USE"),
    CONSTRUCTOR_USE("CONSTRUCTOR_USE"),
    ARG("ARG"),

    ENUM("ENUM"),
    NEW("NEW"),
    CLASS("CLASS"),
    EXTENDS("EXTENDS"),
    IMPLEMENTS("IMPLEMENTS"),
    STATIC("STATIC"),
    FINAL("FINAL"),
    THROW("THROW"),
    THROWS("THROWS"),
    VOID("VOID"),
    RETURN("RETURN"),
    NUMBER_WHOLE("NUMBER_WHOLE"),   // int, long, short
    NUMBER_DEC("NUMBER_DEC"),       // double, float
    TEXT("TEXT"),                   // String, char
    BOOLEAN("BOOLEAN"),
    BYTE("BYTE"),

    OP_RELATION("OP_RELATION"),    // ==, !=, >=, <=, >, <
    OP_LOGIC("OP_LOGIC"),       // &&, ||, !
    OP_ASSIGN("OP_ASSIGN"),      // <<=, >>=, +=, -=, *=, /=, %=, &=, ^=, |=, =
    OP_ARITHMETIC("OP_ARITHMETIC"),  // ++, --, +, -, *, /, %
    OP_BITWISE("OP_BITWISE"),     // <<, >>>, >>, &, |, ^, ~

    CLASS_VAR("CLASS_VAR"),

    TABLE("TABLE"),
    GENERIC("GENERIC"),        // <...>
    CASE("CASE"),
    DEFAULT("DEFAULT"),
    CONTINUE("CONTINUE"),
    BREAK("BREAK"),
    CAST("CAST"),        // (Object)

    UNKNOWN("UNKNOWN"),
    ;

    private final String text;

    private Token(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
