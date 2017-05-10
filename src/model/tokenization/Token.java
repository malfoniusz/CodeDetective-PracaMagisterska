package model.tokenization;

public enum Token {
    // Tokeny sa poukladane w kolejnosci ich wykrywania przez proces tokenizacji

    IF_OR_ELSE,  // if, else, else if
    LOOP,   	 // for, while, do
    FOR,
    WHILE,
    DO,
    SWITCH,
    TRY,
    CATCH,

    FUNCTION_DEF,
    FUNCTION_USE,

    ENUM,
    NEW,
    CLASS,
    EXTENDS,
    IMPLEMENTS,
    STATIC,
    FINAL,
    THROW,
    THROWS,
    VOID,
    RETURN,
    NUMBER,         // number_whole, number_decimal
    NUMBER_WHOLE,   // int, long, short
    INT,
    LONG,
    SHORT,
    NUMBER_DEC,		// double, float
    FLOAT,
    DOUBLE,
    TEXT,           // String, char
    STRING,
    CHAR,
    BOOLEAN,
    BYTE,

    OPERATOR,       // op_relation, op_logic, op_assign, op_arithmetic, op_bitwise
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
    CAST,           // (Object)

    UNKNOWN_SKIP,
    ;

}
