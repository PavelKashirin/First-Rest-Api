package com.study.iter;

import org.junit.Assert;
import org.junit.Test;

public class CustomArrayIteratorTest {

    @Test
    public void testNext() {
        String[][] strings = {{"qwer"}, {"uytr", "qwed", "asdf"}, {"zxcv", "nbv"}};
        CustomArrayIterator<String> iter = new CustomArrayIterator<>(strings);

        final String ex = iter.next();
        Assert.assertEquals(ex, "qwer");
    }

    @Test
    public void testHasNext() {
        String[][] strings = {{"qwe"}, {"asd"}};
        CustomArrayIterator<String> iter = new CustomArrayIterator<>(strings);
        Assert.assertTrue(iter.hasNext());
    }
}