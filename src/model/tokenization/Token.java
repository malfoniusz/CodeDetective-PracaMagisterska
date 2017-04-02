package model.tokenization;

public enum Token {
    CONDITIONAL_STATEMENT,  // if, else, else if
    LOOP,                   // for, while, do
    SWITCH,
    TRY,
    CATCH,

    FUNCTION_DEF,
    FUNCTION_USE,
    CONSTRUCTOR_USE,

    ENUM,
    NEW,
    CLASS,
    EXTENDS,
    IMPLEMENTS,
    STATIC,
    FINAL,
    THROW,
    VOID,
    NUMBER_DEC,     // int, long, short
    NUMBER_POINT,   // double, float
    TEXT,           // String, char
    BOOLEAN,
    BYTE,

    OP_RELATION,    // ==, !=, >=, <=, >, <
    OP_LOGIC,       // &&, ||, !
    OP_ASSIGN,      // <<=, >>=, +=, -=, *=, /=, %=, &=, ^=, |=, =
    OP_ARITHMETIC,  // ++, --, +, -, *, /, %
    OP_BITWISE,     // <<, >>>, >>, &, |, ^, ~

    CLASS_VAR,

    TABLE,
    GENERIC,        // <...>
    CASE,
    DEFAULT,
    CONTINUE,
    BREAK,
    RETURN,

    UNKNOWN,
}
