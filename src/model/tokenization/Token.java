package model.tokenization;

public enum Token {
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
    STATEMENT,      // if, else, else if, switch
    LOOP,           // for, while, do while
    BREAK,
    CONTINUE,
    RETURN,
}
