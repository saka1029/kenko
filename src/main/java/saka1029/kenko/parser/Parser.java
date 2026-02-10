package saka1029.kenko.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    final Node root;
    final BufferedReader reader;
    final List<Type> types = new ArrayList<>();
    Node node;

    public Parser(BufferedReader reader) {
        this.root = new Node(null, null, null);
        this.reader = reader;
        get();
    }

    Node get() {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                for (Type type : types) {
                    Node n = type.createNode(line);
                    if (n != null)
                        return node = n;
                }
                node.text.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return node = null;
    }
}
