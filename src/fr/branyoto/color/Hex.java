package fr.branyoto.color;

import static fr.branyoto.utils.ArgumentsChecker.requirementOnHex;
import static java.lang.Integer.toHexString;

public record Hex(String hex) implements Color {
    public Hex {
        requirementOnHex(hex);
    }

    public static Hex fromRGB(int r, int g, int b, int a) {
        return new Hex("#" + toHex(r) + toHex(g) + toHex(b) + toHex(a));
    }
    private static String toHex(int x) {
        return (x < 16 ? "0" : "") + toHexString(x);
    }
    public static Hex fromHSV(double h, double s, double v, double a) {
        return RGB.fromHSV(h, s, v, a).toHex();
    }

    @Override
    public RGB toRGB() {
        return  RGB.fromHex(hex);
    }
    @Override
    public HSV toHSV() {
        return HSV.fromHex(hex);
    }
    @Override
    public Hex toHex() {
        return new Hex(hex);
    }
    @Override
    public String toString() {
        return hex;
    }
}
