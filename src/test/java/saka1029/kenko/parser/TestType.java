package saka1029.kenko.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TestType {

    @Test
    public void testType() {
        Type 章 = new Type("章", "^第(?<H>" + Pat.漢数字 + ")章" + Pat.空白 + "(?<T>.*)$");
        String text = "  第一章　総則(第一条―第三条)  ";
        Node root = Node.createRoot(null);
        Node child = 章.match(root, text);
        assertNotNull(child);
        assertEquals("一", child.header);
        assertEquals("総則(第一条―第三条)", child.title);
    }

}
