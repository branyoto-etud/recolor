package fr.branyoto.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static java.util.Objects.requireNonNull;

/**
 * Little helper to generate path with a base and somme additional things.
 */
public class PathCreator {
    /**
     * Return the first not existing path found, starting with
     * {@code baseName} and using {@code convertor} to create the next trailing element
     * with {@code supplier} as first element.
     *
     * @param baseName the starting path. Cannot be null.
     * @param supplier the first element to add to the base name. Cannot be null.
     * @param convertor a function that generate a new tail based on the previous one. Cannot be null.
     * @return a path free to occupy.
     */
    public static <V> Path getFirstNotExisting(String baseName, Supplier<? extends V> supplier, UnaryOperator<V> convertor) {
        requireNonNull(convertor);
        var tail = supplier.get();
        var path = Paths.get(requireNonNull(baseName));
        while (Files.exists(path)) {
            path = Paths.get(baseName + tail);
            tail = convertor.apply(tail);
        }
        return path;
    }
    /**
     * Return the first not existing path found, starting with
     * {@code baseName} and using {@code generator} to create the next path.
     *
     * @param baseName the starting path. Cannot be null.
     * @param generator the function that convert a path to a new path. Cannot be null.
     * @return a path free to occupy.
     */
    public static Path getFirstNotExisting(String baseName, UnaryOperator<String> generator) {
        requireNonNull(generator);
        var name = requireNonNull(baseName);
        Path path;
        while (Files.exists(path = Paths.get(name))) {
            name = generator.apply(name);
        }
        return path;
    }

    /**
     * Return the first not existing path found, starting with
     * {@code baseName} and adding a trailing serial number (>=0).
     *
     * @param baseName the starting path. Cannot be null.
     * @return a path free to occupy.
     */
    public static Path getFirstNotExisting(String baseName) {
        var path = Paths.get(requireNonNull(baseName));
        int i = 0;
        while (Files.exists(path)) {
            path = Paths.get(baseName + i);
            i++;
        }
        return path;
    }
}
