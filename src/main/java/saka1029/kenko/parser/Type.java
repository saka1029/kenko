package saka1029.kenko.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Type {

    public final String name;
    public final Pattern pattern;

    public Type(String name, String pattern) {
        this.name = name;
        this.pattern = Pattern.compile("^%s$".formatted(pattern));
    }

    public Node createNode(Node parent, String text) {
        Matcher matcher = pattern.matcher(text.trim());
        if (!matcher.matches())
            return null;
        Node child = parent.addChild(this, matcher.group("H"), matcher.group("T"));
        return child;
    }
}
