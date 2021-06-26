package com.novur.bettertools.framework;

import java.util.Arrays;
import java.util.HashSet;

public class Util {

    public static HashSet<String> asHash(String... strings) {
        return new HashSet<>(Arrays.asList(strings));
    }
}
