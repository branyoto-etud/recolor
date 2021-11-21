package fr.branyoto.color;

import static java.util.Objects.requireNonNull;

public record BiColor(Color first, Color second) {
    public BiColor {
        requireNonNull(first);
        requireNonNull(second);
    }
    public String toHex() {
        return first.toHex() + "," + second.toHex();
    }
}
