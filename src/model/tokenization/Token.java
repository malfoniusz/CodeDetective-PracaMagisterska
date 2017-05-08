package model.tokenization;

public enum Token {
    // Tokeny sa poukladane w kolejnosci ich wykrywania przez proces tokenizacji

    IF_OR_ELSE("IF_OR_ELSE"),  // if, else, else if
    LOOP("LOOP"),   // for, while, do
    FOR("FOR"),
    WHILE("WHILE"),
    DO("DO"),
    SWITCH("SWITCH"),
    TRY("TRY"),
    CATCH("CATCH"),

    FUNCTION_DEF("FUNCTION_DEF"),
    FUNCTION_USE("FUNCTION_USE"),

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
    NUMBER("NUMBER"),               // number_whole, number_decimal
    NUMBER_WHOLE("NUMBER_WHOLE"),   // int, long, short
    INT("INT"),
    LONG("LONG"),
    SHORT("SHORT"),
    NUMBER_DEC("NUMBER_DEC"),       // double, float
    FLOAT("FLOAT"),
    DOUBLE("DOUBLE"),
    TEXT("TEXT"),                   // String, char
    STRING("STRING"),
    CHAR("CHAR"),
    BOOLEAN("BOOLEAN"),
    BYTE("BYTE"),

    OPERATOR("OPERATOR"),           // op_relation, op_logic, op_assign, op_arithmetic, op_bitwise
    OP_RELATION("OP_RELATION"),     // ==, !=, >=, <=, >, <
    OP_LOGIC("OP_LOGIC"),           // &&, ||, !
    OP_ASSIGN("OP_ASSIGN"),         // <<=, >>=, +=, -=, *=, /=, %=, &=, ^=, |=, =
    OP_ARITHMETIC("OP_ARITHMETIC"), // ++, --, +, -, *, /, %
    OP_BITWISE("OP_BITWISE"),       // <<, >>>, >>, &, |, ^, ~

    CLASS_VAR("CLASS_VAR"),

    TABLE("TABLE"),
    GENERIC("GENERIC"),        // <...>
    CASE("CASE"),
    DEFAULT("DEFAULT"),
    CONTINUE("CONTINUE"),
    BREAK("BREAK"),
    CAST("CAST"),        // (Object)

    UNKNOWN_SKIP("UNKNOWN_SKIP"),
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
