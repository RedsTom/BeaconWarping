package me.redstom.beaconwarp.common;

import com.google.inject.Singleton;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.stream.Stream;

@Singleton
public class RandomNameGenerator {

    private static final Random RANDOM = new Random();

    private final String[] names;

    public RandomNameGenerator() {
        InputStream    file = getClass().getClassLoader().getResourceAsStream("names.txt");
        BufferedReader bis  = new BufferedReader(new InputStreamReader(file));
        this.names = bis.lines().toArray(String[]::new);
    }

    public String pickOne() {
        return Stream.of(names).skip(RANDOM.nextInt(names.length)).findFirst().orElseThrow();
    }
}
