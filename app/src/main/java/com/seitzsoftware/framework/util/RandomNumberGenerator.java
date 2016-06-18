package com.seitzsoftware.framework.util;

import java.util.Random;

/**
 * Created by Kevin on 12/14/2015.
 */
public class RandomNumberGenerator {

    private static Random rand = new Random();

    public static int getRandIntBetween(int lowerBound, int upperBound) {
        return rand.nextInt(upperBound - lowerBound) + lowerBound;
    }

    public static int getRandInt(int upperBound) {
        return rand.nextInt(upperBound);
    }
}
