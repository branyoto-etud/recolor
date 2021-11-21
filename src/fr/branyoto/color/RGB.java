package fr.branyoto.color;

import static fr.branyoto.utils.ArgumentsChecker.requirementOnHSV;
import static fr.branyoto.utils.ArgumentsChecker.requirementOnRGB;

public record RGB(int r, int g, int b, int a) implements Color {
    public RGB {
        requirementOnRGB(r, g, b, a);
    }
    public static RGB fromHex(String hex) {
        return new RGB(
                Integer.parseInt(hex.substring(1,3), 16),
                Integer.parseInt(hex.substring(3,5), 16),
                Integer.parseInt(hex.substring(5,7), 16),
                Integer.parseInt(hex.substring(7,9), 16)
        );
    }
    public static RGB fromHSV(double h, double s, double v, double a) {
        requirementOnHSV(h, s, v, a);
        int hi = ((int) Math.floor(h / 60.)) % 6;
        double f = h / 60. - hi;
        double l = v * (1 - s);
        double m = v * (1 - f * s);
        double n = v * (1 - (1 - f) * s);
        return switch (hi) {
            case 0 -> new RGB((int) (255 *  v), (int) (255 * n), (int) (255 * l), (int) (255 * a));
            case 1 -> new RGB((int) (255 * m), (int) (255 * v), (int) (255 * l), (int) (255 * a));
            case 2 -> new RGB((int) (255 * l), (int) (255 * v), (int) (255 * n), (int) (255 * a));
            case 3 -> new RGB((int) (255 * l), (int) (255 * m), (int) (255 * v), (int) (255 * a));
            case 4 -> new RGB((int) (255 * n), (int) (255 * l), (int) (255* v), (int) (255 * a));
            case 5 -> new RGB((int) (255 * v), (int) (255 * l), (int) (255 * m), (int) (255 * a));
            default -> throw new AssertionError("Impossible value processed: " + hi + " Math.floor(" + h + "/60.) % 6");
        };
    }
    @Override
    public RGB toRGB() {
        return new RGB(r, g, b, a);
    }
    @Override
    public HSV toHSV() {
        return HSV.fromRGB(r, g, b, a);
    }
    @Override
    public Hex toHex() {
        return Hex.fromRGB(r, g, b, a);
    }
}
