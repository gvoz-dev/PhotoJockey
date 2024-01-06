package org.gvozdev.pj.utils;

import org.junit.jupiter.api.Test;

import static org.gvozdev.pj.utils.CvUtils.ABGR2BGRA;
import static org.gvozdev.pj.utils.CvUtils.BGRA2ABGR;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CvUtilsTest {
    @Test
    void testBGRA2ABGR() {
        assertThrows(IllegalArgumentException.class, () -> BGRA2ABGR(new byte[]{1, 2, 3}));

        byte[] buf = {1, 2, 3, 4};
        BGRA2ABGR(buf);
        assertArrayEquals(new byte[]{4, 1, 2, 3}, buf);
    }

    @Test
    void testABGR2BGRA() {
        assertThrows(IllegalArgumentException.class, () -> ABGR2BGRA(new byte[]{1, 2, 3}));

        byte[] buf = {1, 2, 3, 4};
        ABGR2BGRA(buf);
        assertArrayEquals(new byte[]{2, 3, 4, 1}, buf);
    }
}
