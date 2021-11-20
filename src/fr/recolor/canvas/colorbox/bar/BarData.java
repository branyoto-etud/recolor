package fr.recolor.canvas.colorbox.bar;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Objects;

class BarData {
	private Color start, end;
	private Rectangle bounds;
	private int value;
	
  BarData(Color min, Color max, int value, Rectangle bounds) {
		start = Objects.requireNonNull(min);
		end = Objects.requireNonNull(max);
		this.bounds = new Rectangle(bounds);
		this.value = value;
	}

	Color getStart() {
		return start;
	}

	Color getEnd() {
		return end;
	}

	Rectangle getBounds() {
		return bounds;
	}

	void move(Rectangle rectangle) {
		bounds = new Rectangle(rectangle);
	}

	void changeColors(Color start, Color end) {
		this.start = start;
		this.end = end;
	}

	int getValue() {
		return value;
	}

	void setValue(int amount) {
		value = amount;
	}
}
