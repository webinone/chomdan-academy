package com.chomdan.api.utils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by foresight on 17. 8. 17.
 */
public class StringTest {

    public static void main(String[] args) {

        List<String> programing =
                Arrays.asList("Javascript", "C", "C++", "Nodejs", "Java", "Oracle", "MariaDB", "PHP", "ASP");

        List<Integer> list = Arrays.asList(1, 2, 3);


        long count2 = programing.stream().filter(str -> str.indexOf("Java") > -1).count();

        System.out.println(count2);

    }
}
