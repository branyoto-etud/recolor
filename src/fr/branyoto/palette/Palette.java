package fr.branyoto.palette;

import fr.branyoto.color.BiColor;
import fr.branyoto.color.Hex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class Palette {
    private final ArrayList<BiColor> colors = new ArrayList<>();

    public void savePalette(String fileName) throws IOException {
        Files.write(Paths.get(fileName),
                colors.stream().map(BiColor::toHex).collect(toList()));
    }

    public void loadPalette(String fileName) throws IOException {
        for (var line : Files.readAllLines(Paths.get(fileName))) {
            var tokens = line.split(",");
            try {
                colors.add(new BiColor(new Hex(tokens[0]), new Hex(tokens[1])));
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                throw new IOException("Error during reading of " + fileName);
            }
        }
    }
    public void addColor(BiColor color) {
        colors.add(Objects.requireNonNull(color));
    }
    public void addColor(BiColor... colors) {
        Arrays.stream(colors).forEach(this::addColor);
    }
}
