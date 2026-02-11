package saka1029.kenko.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Parser {

    final Node root;
    final BufferedReader reader;
    final List<Type> types = new ArrayList<>();
    Node node;

    public Parser(BufferedReader reader) {
        this.node = this.root = new Node(null, null, null);
        this.reader = reader;
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
            return node = null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    abstract void parseMain(Node parent);

    public void parse() {
        get();  // 1トークン先読み
        parseMain(root);
    }

    public Node eaten;

    public boolean eat(Type expected) {
        if (node == null)
            return false;
        if (node.type == expected) {
            eaten = node;
            get();
            return true;
        }
        return false;
    }
}
