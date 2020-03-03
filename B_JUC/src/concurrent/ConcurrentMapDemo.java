package concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentMapDemo {
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
        ConcurrentNavigableMap<String, Integer> listMap = new ConcurrentSkipListMap<>();
        listMap.put("e",34);
        listMap.put("a",34);
        listMap.put("f",34);
        listMap.put("q",34);
        listMap.put("z",34);
        listMap.put("h",34);
        listMap.put("u",34);
        System.out.println(listMap);
        System.out.println(listMap.headMap("f"));
        System.out.println(listMap.tailMap("r"));
        System.out.println(listMap.subMap("a","s"));
        // concurrentHashmap();
        // System.out.println(tableSizeFor(15000));
    }

    private static void concurrentHashmap() {
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>(13);
        map.put("a", 12);
        map.put("b", 12);
        map.put("e", 12);
        map.put("d", 12);
        System.out.println(map);
    }

    private static final int tableSizeFor(int c) {
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
