package com.restapi.automation;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Random;

public class RandomUtils {

    private static final Random random = new Random();

    public static String randomDateOfBirth(){

        int minDay = (int) LocalDate.of(1950, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2018, 1,1).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);

        return LocalDate.ofEpochDay(randomDay).toString();
    }

    public static String randomString(int length){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static int randomNumber(int inclusiveLimit){
        return random.nextInt(inclusiveLimit) + 1;
    }
}
