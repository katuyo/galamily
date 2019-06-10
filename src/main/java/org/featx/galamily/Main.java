package org.featx.galamily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static org.featx.galamily.Constants.EMPTY_STR;

public class Main {
    public static void main(String[] args) {
        GalamilyApplication application = new GalamilyApplication();

        InputStream is = application.getClass().getResourceAsStream("/origin.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))){
            application.run(bufferedReader.lines().collect(Collectors.toList()))
                    .stream().filter(s->!EMPTY_STR.equals(s)).forEach(System.out::println);
        } catch (IOException ignored) {
        }

        is = application.getClass().getResourceAsStream("/origin_2.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))){
            application.run(bufferedReader.lines().collect(Collectors.toList()))
                    .stream().filter(s->!EMPTY_STR.equals(s)).forEach(System.out::println);
        } catch (IOException ignored) {

        }
    }
}
