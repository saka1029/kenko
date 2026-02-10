package saka1029.kenko.parser;

import java.util.ArrayList;
import java.util.List;

public class Node {

    public final Node parent;
    public final List<Node> children = new ArrayList<>();

    public final Type type;
    public final String header;
    public final String title;

    Node(Node parent, Type type, String header, String title) {
        this.parent = parent;
        this.type = type;
        this.header = header;
        this.title = title;
    }

    public static Node createRoot(Type type) {
        return new Node(null, type, null, null);
    }

    public Node addChild(Type type, String header, String title) {
        Node child = new Node(this, type, header, title);
        children.add(child);
        return child;
    }

    public int id() {
        return type.idFunc.apply(this);
    }
}
