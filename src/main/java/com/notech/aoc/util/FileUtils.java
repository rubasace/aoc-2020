package com.notech.aoc.util;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.stream.Stream;

public class FileUtils {

    protected static final String BLANK_LINE_REGEX = "\\n\\n";

    public static File getFile(final String fileName) {
        try {
            URL resource = FileUtils.class.getClassLoader().getResource(fileName);
            return new File(resource.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static Path getPath(final String fileName) {
        return getFile(fileName).toPath();
    }

    public static String readFile(final String fileName) {
        try {
            return Files.readString(getPath(fileName));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static <T> Stream<T> executePerLine(final String filename, Function<String, T> function) {
        return getLines(filename)
                .map(function);
    }

    public static Stream<String> getLines(final String filename){
        try {
            return Files.lines(FileUtils.getPath(filename));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static String[] splitByBlankSpace(final String string) {
        return string.split(BLANK_LINE_REGEX);
    }
}
