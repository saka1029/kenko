package saka1029.kenko.parser;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    final Node root;
    final BufferedReader reader;
    final List<Type> types = new ArrayList<>();
    Node current;

    public Parser(BufferedReader reader) {
        this.current = this.root = new Node(null, null, null);
        this.reader = reader;
    }

    Node parse() {
        return null;
    }

}
