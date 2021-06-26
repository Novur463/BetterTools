package com.novur.bettertools.framework.math;

import java.util.concurrent.ThreadLocalRandom;

public class MathUtil {
    public static ThreadLocalRandom getThreadRandom() {
        return ThreadLocalRandom.current();
    }
}
