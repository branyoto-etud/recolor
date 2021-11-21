package fr.branyoto.tests;

import fr.branyoto.color.HSV;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HSVTests {
    /**
     * Ensure that HSV constructor take only h in [0.0, 360.0[ and s,v,a in [0.0, 1.0]
     */
    @Test
    public void constructorIAE() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new HSV(-1, 0, 0, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> new HSV(0, -1, 0, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> new HSV(0, 0, -1, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> new HSV(0, 0, 0, -1)),
                () -> assertThrows(IllegalArgumentException.class, () -> new HSV(360., 0, 0, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> new HSV(0, 1.1, 0, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> new HSV(0, 0, 1.1, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> new HSV(0, 0, 0, 1.1)),
                () -> assertDoesNotThrow(() -> new HSV(0,0,0,0)),
                () -> assertDoesNotThrow(() -> new HSV(359.9,1,1,1))
        );
    }

    @Test
    public void fromRGBIAE() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> HSV.fromRGB(-1, 0, 0, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> HSV.fromRGB(0, -1, 0, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> HSV.fromRGB(0, 0, -1, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> HSV.fromRGB(0, 0, 0, -1)),
                () -> assertThrows(IllegalArgumentException.class, () -> HSV.fromRGB(256, 0, 0, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> HSV.fromRGB(0, 256, 0, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> HSV.fromRGB(0, 0, 256, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> HSV.fromRGB(0, 0, 0, 256)),
                () -> Assertions.assertDoesNotThrow(() -> HSV.fromRGB(255,255,255,255)),
                () -> Assertions.assertDoesNotThrow(() -> HSV.fromRGB(0,0,0,0))
        );
    }
    @Test
    public void fromHSVComparingBatch() {
        assertAll(
                () -> Assertions.assertEquals(new HSV(0,0,0,0), HSV.fromRGB(0, 0, 0, 0)),
                () -> Assertions.assertEquals(new HSV(0,0,1,0), HSV.fromRGB(255, 255,255, 0)),
                () -> Assertions.assertEquals(new HSV(0,1,1,0), HSV.fromRGB(255,0, 0, 0)),
                () -> Assertions.assertEquals(new HSV(120,1,1,0), HSV.fromRGB(0, 255, 0, 0)),
                () -> Assertions.assertEquals(new HSV(240,1,1,0), HSV.fromRGB(0, 0, 255, 0)),
                () -> Assertions.assertEquals(new HSV(60,1,1,0), HSV.fromRGB(255,255, 0, 0)),
                () -> Assertions.assertEquals(new HSV(180,1,1,0), HSV.fromRGB(0, 255, 255, 0)),
                () -> Assertions.assertEquals(new HSV(300,1,1,0), HSV.fromRGB(255, 0, 255, 0)),
                () -> Assertions.assertEquals(new HSV(0,0,.7490196078431373,0), HSV.fromRGB(191, 191, 191, 0)),
                () -> Assertions.assertEquals(new HSV(0,0,.5019607843137255,0), HSV.fromRGB(128, 128, 128, 0)),
                () -> Assertions.assertEquals(new HSV(114.70588235294119, 0.9714285714285714, 0.4117647058823529,0), HSV.fromRGB(12, 105, 3, 0)),
                () -> Assertions.assertEquals(new HSV(16.53846153846154, 0.7464114832535884, 0.8196078431372549,0), HSV.fromRGB(209, 96, 53, 0)),
                () -> Assertions.assertEquals(new HSV(188.97959183673467, 0.9545454545454545, 0.6039215686274509,0), HSV.fromRGB(7, 132, 154, 0))
        );
    }
}
