package com.study.concat;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DuplicatesTest {

    @Test
    public void findDuplicatesOne() {
        List<String> list1 = new ArrayList<>(Arrays.asList("34", "4", "4", "5", "123", "7"));
        List<String> list2 = new ArrayList<>(Arrays.asList("Hello", "Hello", "1", "2", "4", "5", "123", "7", "543"));

        List<String> expected = new Duplicates().findDuplicatesOne(list1, list2);
        Assert.assertEquals(expected, new ArrayList<>(Arrays.asList("4", "5", "123", "7")));
    }

    @Test
    public void findDuplicatesTwo() {
        List<String> list1 = new ArrayList<>(Arrays.asList("34", "4", "4", "5", "123", "7"));
        List<String> list2 = new ArrayList<>(Arrays.asList("Hello", "Hello", "1", "2", "4", "5", "123", "7", "543"));

        List<String> expected = new Duplicates().findDuplicatesTwo(list1, list2);
        Assert.assertEquals(expected, new ArrayList<>(Arrays.asList("123", "4", "5", "7")));
    }

    @Test
    public void findDuplicatesThree() {
        List<String> list1 = new ArrayList<>(Arrays.asList("34", "4", "4", "5", "123", "7"));
        List<String> list2 = new ArrayList<>(Arrays.asList("Hello", "Hello", "1", "2", "4", "5", "123", "7", "543"));

        List<String> expected = new Duplicates().findDuplicatesThree(list1, list2);
        Assert.assertEquals(expected, new ArrayList<>(Arrays.asList("123", "4", "5", "7")));
    }
}