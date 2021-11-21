package fr.branyoto.tests;

import fr.branyoto.color.RGB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RGBTests {

    /**
     * Ensure that RGB constructor take only integers in [0, 255]
     */
    @Test
    public void constructorIAE() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new RGB(-1, 0, 0, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> new RGB(0, -1, 0, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> new RGB(0, 0, -1, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> new RGB(0, 0, 0, -1)),
                () -> assertThrows(IllegalArgumentException.class, () -> new RGB(256, 0, 0, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> new RGB(0, 256, 0, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> new RGB(0, 0, 256, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> new RGB(0, 0, 0, 256)),
                () -> Assertions.assertDoesNotThrow(() -> new RGB(255,255,255,255)),
                () -> Assertions.assertDoesNotThrow(() -> new RGB(0,0,0,0))
        );
    }

    @Test
    public void fromHSVIAE() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> RGB.fromHSV(-1, 0, 0, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> RGB.fromHSV(0, -1, 0, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> RGB.fromHSV(0, 0, -1, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> RGB.fromHSV(0, 0, 0, -1)),
                () -> assertThrows(IllegalArgumentException.class, () -> RGB.fromHSV(360., 0, 0, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> RGB.fromHSV(0, 1.1, 0, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> RGB.fromHSV(0, 0, 1.1, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> RGB.fromHSV(0, 0, 0, 1.1)),
                () -> assertDoesNotThrow(() -> RGB.fromHSV(0,0,0,0)),
                () -> assertDoesNotThrow(() -> RGB.fromHSV(359.9,1,1,1))
        );
    }
    @Test
    public void fromHSVComparingBatch() {
        assertAll(
                () -> Assertions.assertEquals(new RGB(0,0,0,0), RGB.fromHSV(0,0,0,0)),
                () -> Assertions.assertEquals(new RGB(255,255,255,0), RGB.fromHSV(0,0,1,0)),
                () -> Assertions.assertEquals(new RGB(255,0,0,0), RGB.fromHSV(0,1,1,0)),
                () -> Assertions.assertEquals(new RGB(0,255,0,0), RGB.fromHSV(120,1,1,0)),
                () -> Assertions.assertEquals(new RGB(0,0,255,0), RGB.fromHSV(240,1,1,0)),
                () -> Assertions.assertEquals(new RGB(255,255,0,0), RGB.fromHSV(60,1,1,0)),
                () -> Assertions.assertEquals(new RGB(0,255,255,0), RGB.fromHSV(180,1,1,0)),
                () -> Assertions.assertEquals(new RGB(255,0,255,0), RGB.fromHSV(300,1,1,0)),
                () -> Assertions.assertEquals(new RGB(191,191,191,0), RGB.fromHSV(0,0,.75,0)),
                () -> Assertions.assertEquals(new RGB(127,127,127,0), RGB.fromHSV(0,0,.50,0)),
                () -> Assertions.assertEquals(new RGB(14,42,20,0), RGB.fromHSV(132, .654, .168, 0)),
                () -> Assertions.assertEquals(new RGB(17,10,10,0), RGB.fromHSV(359, .399, .069, 0)),
                () -> Assertions.assertEquals(new RGB(235,142,102,0), RGB.fromHSV(18, .564, .923, 0))
        );
    }
}
