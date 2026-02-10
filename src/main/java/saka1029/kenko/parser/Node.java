package saka1029.kenko.parser;

import java.util.ArrayList;
import java.util.List;

public class Node {

    public Node parent;
    public final List<Node> children = new ArrayList<>();

    public final Type type;
    public final String header;
    public final String title;
    public final List<String> text = new ArrayList<>();

    public Node(Type type, String header, String title) {
        this.type = type;
        this.header = header;
        this.title = title;
    }

    public Node addChild(Node child) {
        children.add(child);
        child.parent = this;
        return child;
    }

    public String id() {
        return type.id(this);
    }

    static final String NL = System.lineSeparator();

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(type).append(": ")
            .append(header).append(" ").append(title).append(NL);
        for (String line : text)
            sb.append("    ").append(line).append(NL);
        return sb.toString();
    }
}
