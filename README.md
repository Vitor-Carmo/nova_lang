# 🌟 Nova Lang

<div align="center">

[![GitHub
stars](https://img.shields.io/github/stars/Vitor-Carmo/nova_lang?style=for-the-badge&logo=github)](https://github.com/Vitor-Carmo/nova_lang/stargazers)
[![GitHub
forks](https://img.shields.io/github/forks/Vitor-Carmo/nova_lang?style=for-the-badge&logo=github)](https://github.com/Vitor-Carmo/nova_lang/network)
[![GitHub
issues](https://img.shields.io/github/issues/Vitor-Carmo/nova_lang?style=for-the-badge&logo=github)](https://github.com/Vitor-Carmo/nova_lang/issues)

**A toy programming language built from scratch in Java to explore how
interpreters work internally.**
</div>

------------------------------------------------------------------------

# 📖 Overview

**Nova Lang** is a personal project focused on building a small
programming language from scratch in Java.

The goal of this project is to understand how programming languages work
internally by implementing the core components of an interpreter:

-   Lexical analysis (tokenization)
-   Parsing and AST generation
-   Expression evaluation
-   Tree-walk interpretation

Instead of using existing frameworks, Nova Lang implements these
components manually to demonstrate the internal architecture behind
interpreters.


------------------------------------------------------------------------

# ✨ Current Features

Nova Lang currently supports a minimal but functional set of language
features:

-   Variable declarations
-   Integer values
-   Arithmetic expressions
-   Variable references
-   Print statements

Example Nova Lang program:

    let x = 5
    let y = 10
    print y + x

Output:

    15

------------------------------------------------------------------------

# 🧠 Interpreter Architecture

Nova Lang follows the classic interpreter pipeline:

    Source Code
       ↓
    Lexer
       ↓
    Tokens
       ↓
    Parser
       ↓
    Abstract Syntax Tree (AST)
       ↓
    Interpreter
       ↓
    Program Output

Each stage transforms the program into a more structured representation
until it can be executed.

------------------------------------------------------------------------

# 🔎 Example: Tokenization

Source code:

    let x = 5

Generated tokens:

    LET
    IDENTIFIER(x)
    EQUAL
    NUMBER(5)

------------------------------------------------------------------------

# 🌳 Example: AST Representation

For the code:

    print y + x

The parser builds an AST similar to:

    PrintStatement
     └── BinaryExpression (+)
          ├── VariableExpression (y)
          └── VariableExpression (x)

The interpreter evaluates this tree recursively.

------------------------------------------------------------------------

# 📁 Project Structure

    src
    ├── main
    │   ├── java
    │   │   └── lang
    │   │       ├── ast
    │   │       │   ├── expressions
    │   │       │   │   ├── BinaryExpression.java
    │   │       │   │   ├── Expression.java
    │   │       │   │   ├── NumberExpression.java
    │   │       │   │   └── StringExpression.java
    │   │       │   └── statements
    │   │       │       ├── PrintStatement.java
    │   │       │       ├── VariableDeclaration.java
    │   │       │       └── VariableExpression.java
    │   │       ├── interpreter
    │   │       │   └── Interpreter.java
    │   │       ├── lexer
    │   │       │   ├── Keywords.java
    │   │       │   ├── Lexer.java
    │   │       │   ├── Token.java
    │   │       │   └── TokenType.java
    │   │       ├── Main.java
    │   │       └── parser
    │   │           └── Parser.java
    │   └── resources
    │       ├── hello_world.nova
    │       ├── print.nova
    │       ├── soma.nova
    │       └── subtracao.nova
    └── test
        └── java
            └── lang
------------------------------------------------------------------------

# 🚀 Running the Interpreter

just run:

```bash
make run-file file=src/main/resources/hello_world.nova
```

Example program:

    let x = 5
    let y = 10
    print y + x

Output:

    15

------------------------------------------------------------------------

# 🎯 Goals of the Project

Nova Lang exists primarily as a **learning and experimentation project**
to explore topics such as:

-   Programming language design
-   Abstract Syntax Trees
-   Interpreter architecture
-   Expression parsing
-   Runtime evaluation

------------------------------------------------------------------------

# 🛣️ Roadmap

Future improvements may include:

-   Parentheses in expressions
-   Operator precedence
-   Conditional statements (`if`)
-   Loops (`while`)
-   Functions
-   Error handling improvements
-   Better syntax parsing

------------------------------------------------------------------------

# 📚 Learning Resources

This project was inspired by materials related to language
implementation such as:

-   Crafting Interpreters
-   Lisp interpreter tutorials
-   Compiler design resources

------------------------------------------------------------------------

## 🤝 Contributing

This is a personal learning project, but contributions, suggestions, or bug reports are welcome! If you're interested in improving Nova Lang or fixing an issue:

1.  Fork the repository.
2.  Create your feature branch (`git checkout -b feature/AmazingFeature`).
3.  Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4.  Push to the branch (`git push origin feature/AmazingFeature`).
5.  Open a Pull Request.

### Development Setup for Contributors
The development setup is straightforward: clone the repository and use any Java IDE (like IntelliJ IDEA, Eclipse, or VS Code with Java extensions) to open the project. You can compile and run directly from the IDE.

## 📄 License

This project is currently without a specified license.
**TODO:** Add an appropriate open-source license (e.g., MIT, Apache 2.0) to the repository.

## 🙏 Acknowledgments

-   Inspired by resources on compiler design and interpreter implementation.
-   Special thanks to the open-source community for providing learning materials on programming language theory.

## 📞 Support & Contact

-   🐛 Issues: [GitHub Issues](https://github.com/Vitor-Carmo/nova_lang/issues)

---

<div align="center">

**⭐ Star this repo if you find it helpful or interesting!**

Made with ❤️ by [Vitor Carmo](https://github.com/Vitor-Carmo)

</div>
