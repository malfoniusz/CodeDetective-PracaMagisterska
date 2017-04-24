package model.tokenization;

public enum Token {
    CONDITIONAL_STATEMENT("conditional_statment"),  // if, else, else if
    LOOP("loop"),   // for, while, do
    SWITCH("switch"),
    TRY("try"),
    CATCH("catch"),

    FUNCTION_DEF("function_def"),   // function def + constructor def
    FUNCTION_USE("function_use"),
    CONSTRUCTOR_USE("constructor_use"),
    ARG("arg"),

    ENUM("enum"),
    NEW("new"),
    CLASS("class"),
    EXTENDS("extends"),
    IMPLEMENTS("implements"),
    STATIC("static"),
    FINAL("final"),
    THROW("throw"),
    VOID("void"),
    RETURN("return"),
    NUMBER_DEC("number_dec"),       // int, long, short
    NUMBER_POINT("number_point"),   // double, float
    TEXT("text"),                   // String, char
    BOOLEAN("boolean"),
    BYTE("byte"),

    OP_RELATION("op_relation"),    // ==, !=, >=, <=, >, <
    OP_LOGIC("op_logic"),       // &&, ||, !
    OP_ASSIGN("op_assign"),      // <<=, >>=, +=, -=, *=, /=, %=, &=, ^=, |=, =
    OP_ARITHMETIC("op_arithmetic"),  // ++, --, +, -, *, /, %
    OP_BITWISE("op_bitwise"),     // <<, >>>, >>, &, |, ^, ~

    CLASS_VAR("class_var"),

    TABLE("table"),
    GENERIC("generic"),        // <...>
    CASE("case"),
    DEFAULT("default"),
    CONTINUE("continue"),
    BREAK("break"),
    CAST("cast"),        // (Object)

    UNKNOWN("unknown"),
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
