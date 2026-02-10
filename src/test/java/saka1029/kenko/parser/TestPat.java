package saka1029.kenko.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestPat {

    @Test
    public void test漢数字正規化() {
        // assertEquals("0", Pat.漢数字正規化("〇"));
        assertEquals("1", Pat.漢数字正規化("一"));
        assertEquals("2", Pat.漢数字正規化("二"));
        assertEquals("11", Pat.漢数字正規化("十一"));
        assertEquals("12", Pat.漢数字正規化("十二"));
        assertEquals("32", Pat.漢数字正規化("三十二"));
    }
}
