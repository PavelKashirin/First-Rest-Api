package com.study.concat;

import java.util.*;

public class Duplicates {

    private <T> boolean hasItem(T t, Collection<T> collection) {
        for (T temp : collection) {
            if (temp.equals(t)) {
                return true;
            }
        }

        return false;
    }

    public <T> List<T> findDuplicatesOne(Collection<T> first, Collection<T> second) {
        List<T> result = new ArrayList<>();
        for (T t : first) {
            if (hasItem(t, second)) {
                if (!hasItem(t, result)) {
                    result.add(t);
                }
            }
        }

        return result;
    }

    public <T> List<T> findDuplicatesTwo(Collection<T> first, List<T> second) {
        Set<T> set1 = new HashSet<>(first);
        Set<T> set2 = new HashSet<>(second);
        List<T> result = new ArrayList<>();

        for (T temp : set1) {
            if (!set2.add(temp)) {
                result.add(temp);
            }
        }

        return result;
    }

    public <T> List<T> findDuplicatesThree(Collection<T> first, List<T> second) {
        List<T> result = new ArrayList<>();
        Map<T, Integer> temp = new HashMap<>();
        Set<T> set = new HashSet<>(second);
        first.forEach(x -> temp.put(x, 1));
        set.forEach(x -> temp.put(x, (temp.get(x) == null) ? 1 : (temp.get(x) + 1)));
        temp.forEach((x,y) -> {
            if (temp.get(x) > 1) {
                result.add(x);
            }
        });

        return result;
    }
}
