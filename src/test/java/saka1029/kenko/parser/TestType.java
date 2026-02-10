package saka1029.kenko.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static saka1029.kenko.parser.Pat.*;

import org.junit.Test;

public class TestType {

    @Test
    public void testType() {
        Type.IdFunc idf = node -> "" + Kan2Int(node.header);
        Type 章 = new Type("章", "第(?<H>" + P漢数字 + ")章" + P空白 + "(?<T>.*)", idf);
        Type 節 = new Type("節", "第(?<H>" + P漢数字 + ")節" + P空白 + "(?<T>.*)", idf);
        Type 条 = new Type("条", "第(?<H>" + P漢数字 + ")条" + P空白 + "(?<T>.*)", idf);
        Node root = Node.createRoot(null);
        Node child1 = 章.createNode(root, "  第一章　総則(第一条―第三条)  ");
        assertNotNull(child1);
        assertEquals("一", child1.header);
        assertEquals("総則(第一条―第三条)", child1.title);
        assertEquals("1", child1.id());
        Node child2 = 節.createNode(root, "  第四節　   補則(第九十八条)  ");
        assertNotNull(child2);
        assertEquals("四", child2.header);
        assertEquals("補則(第九十八条)", child2.title);
        assertEquals("4", child2.id());
        Node child3 = 条.createNode(root, "  第三十三条　    この法律において  ");
        assertNotNull(child3);
        assertEquals("三十三", child3.header);
        assertEquals("この法律において", child3.title);
        assertEquals("33", child3.id());
    }

}
