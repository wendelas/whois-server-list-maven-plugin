package de.malkusch.whoisServerList.compiler.merger;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringMergerTest {

    @Test
    public void testMerge() {
        StringMerger merger = new StringMerger();

        assertEquals("", merger.merge("", null));
        assertEquals("", merger.merge(null, ""));
        assertEquals("", merger.merge("", ""));
        assertEquals("l", merger.merge("l", "r"));
        assertEquals("l", merger.merge("l", ""));
        assertEquals("r", merger.merge("", "r"));
    }

}
