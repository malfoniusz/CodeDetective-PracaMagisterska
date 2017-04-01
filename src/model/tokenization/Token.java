package model.tokenization;

public enum Token {
    CLASS,
    CLASS_VAR,
    NEW,
    CONSTRUCTOR_USE,
    VOID,
    NUMBER_DEC,     // int, long, short
    NUMBER_POINT,   // double, float
    TEXT,           // String, char
    BOOLEAN,
    BYTE,
    TABLE,
    OP_RELATION,    // ==, !=, >=, <=, >, <
    OP_LOGIC,       // &&, ||, !
    OP_ASSIGN,      // <<=, >>=, +=, -=, *=, /=, %=, &=, ^=, |=, =
    OP_ARITHMETIC,  // ++, --, +, -, *, /, %
    OP_BITWISE,     // <<, >>>, >>, &, |, ^, ~
    STATEMENT,      // if, else, else if
    LOOP,           // for, while, do
    SWITCH,
    CASE,
    DEFAULT,
    CONTINUE,
    BREAK,
    RETURN,
    TRY,
    CATCH,
    STATIC,
    FINAL,
    EXTENDS,
    IMPLEMENTS,
    FUNCTION_DEF,
}
