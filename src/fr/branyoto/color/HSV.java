package fr.branyoto.color;

import static fr.branyoto.utils.ArgumentsChecker.requirementOnHSV;
import static fr.branyoto.utils.ArgumentsChecker.requirementOnRGB;

public record HSV(double h, double s, double v, double a) implements Color {
    public HSV {
        requirementOnHSV(h, s, v, a);
    }
    public static HSV fromHex(String hex) {
        return RGB.fromHex(hex).toHSV();
    }
    public static HSV fromRGB(int r, int g, int b, int a) {
        requirementOnRGB(r, g, b, a);
        double r2 = r / 255.;
        double g2 = g / 255.;
        double b2 = b / 255.;
        double max = Double.max(Double.max(r2, g2), b2);
        double min = Double.min(Double.min(r2, g2), b2);
        double delta = max - min;
        double h = hue(r2, g2, b2, delta, max);
        double s = Double.compare(0., max) == 0 ? 0. : delta / max;
        return new HSV(h, s, max, a / 255.);
    }
    private static double hue(double r, double g, double b, double delta, double max) {
        if (Double.compare(delta, 0) == 0) return 0;
        if (b >= max) return 60. * (4. + (r - g) / delta);
        if (g >= max) return 60. * (2. + (b - r) / delta);
        double h = (60. * ((g - b) / delta));
        return h < 0. ? h+360. : h;
    }
    @Override
    public RGB toRGB() {
        return RGB.fromHSV(h, s, v, a);
    }
    @Override
    public HSV toHSV() {
        return new HSV(h, s, v, a);
    }
    @Override
    public Hex toHex() {
        return Hex.fromHSV(h, s, v, a);
    }
}
