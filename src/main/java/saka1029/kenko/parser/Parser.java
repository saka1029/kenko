package saka1029.kenko.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class Parser {

    static final Type ROOT_TYPE = new Type("ROOT", "", n -> "#");
    final Node root;
    final BufferedReader reader;
    final List<Type> types = new ArrayList<>();
    Node node;

    public Parser(BufferedReader reader) {
        this.node = this.root = new Node(ROOT_TYPE, null, null);
        this.reader = reader;
    }

    Node get() {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("#"))
                    continue;
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

    Node eaten;

    boolean eat(Type expected) {
        if (node == null)
            return false;
        if (node.type == expected) {
            eaten = node;
            get();
            return true;
        }
        return false;
    }

    boolean is(Type... expected) {
        return node != null
            && Stream.of(expected).anyMatch(type -> node.type == type);
    }

    Node addEatenTo(Node parent) {
        return parent.addChild(eaten);
    }
}
