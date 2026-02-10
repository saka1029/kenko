package saka1029.kenko.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static saka1029.kenko.parser.Pat.*;

import org.junit.Test;

public class TestType {

    @Test
    public void testType() {
        Type 章 = new Type("章", "第(?<H>" + 漢数字 + ")章" + 空白 + "(?<T>.*)");
        Type 節 = new Type("節", "第(?<H>" + 漢数字 + ")節" + 空白 + "(?<T>.*)");
        Type 条 = new Type("条", "第(?<H>" + 漢数字 + ")条" + 空白 + "(?<T>.*)");
        String text = "  第一章　総則(第一条―第三条)  ";
        Node root = Node.createRoot(null);
        Node child = 章.match(root, text);
        assertNotNull(child);
        assertEquals("一", child.header);
        assertEquals("総則(第一条―第三条)", child.title);
    }

}
