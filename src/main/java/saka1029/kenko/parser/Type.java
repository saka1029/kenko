package saka1029.kenko.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Type {

    public interface IdFunc { String apply(Node node); }
    public final String name;
    public final Pattern pattern;
    final IdFunc idFunc;

    public Type(String name, String pattern, IdFunc idFunc) {
        this.name = name;
        this.pattern = Pattern.compile("^%s$".formatted(pattern));
        this.idFunc = idFunc;
    }

    public Node createNode(String text) {
        Matcher matcher = pattern.matcher(text.trim());
        if (!matcher.matches())
            return null;
        Node child = new Node(this, matcher.group("H"), matcher.group("T"));
        return child;
    }

    /**
     * idは同一の親にぶら下がる子ノードをユニークに識別する識別子である。
     * 形式は
     * <pre>
     * id     = digits { "-" digits }
     * digits = DIGIT { DIGIT }
     * DIGIT  = "0" | "1" | "2" | "3" | "4" | "5" | "5" | "7" | "8" | "9"
     * </pre>
     * ユニークに識別する情報を持たない場合は"#"とする。
     * @param node
     * @return
     */
    public String id(Node node) {
        return idFunc.apply(node);
    }

    @Override
    public String toString() {
        return "Type:" + name;
    }
}
