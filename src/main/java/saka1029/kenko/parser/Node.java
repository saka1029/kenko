package saka1029.kenko.parser;

import java.util.ArrayList;
import java.util.List;

public class Node {

    interface Visitor {
        void visit(Node node, int depth);
    }

    public Node parent;
    public final List<Node> children = new ArrayList<>();

    public final Type type;
    public final String number;
    public final String header;
    public final List<String> text = new ArrayList<>();

    public Node(Type type, String number, String header) {
        this.type = type;
        this.number = number;
        this.header = header;
    }

    public Node addChild(Node child) {
        children.add(child);
        child.parent = this;
        return child;
    }

    public String id() {
        return type == null ? null : type.id(this);
    }

    public void visit(Visitor visitor, int depth) {
        visitor.visit(this, depth);
        for (Node child : children)
            child.visit(visitor, depth + 1);
    }

    static final String NL = System.lineSeparator();

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(type).append(": ")
            .append("id=").append(id()).append(" ")
            .append(number).append(" ").append(header).append(NL);
        for (String line : text)
            sb.append("    ").append(line).append(NL);
        return sb.toString();
    }
}
