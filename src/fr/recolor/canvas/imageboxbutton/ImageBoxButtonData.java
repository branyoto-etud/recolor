package fr.recolor.canvas.imageboxbutton;

import java.awt.Rectangle;
import java.util.Objects;

import fr.recolor.canvas.imagebox.ImageBox;

class ImageBoxButtonData {
	private final ImageBox imageBox;
	private int x, y, w, h;
	
	ImageBoxButtonData(Rectangle position, ImageBox imageBox) {
		Objects.requireNonNull(position);
		this.x = (int) position.getX();
		this.y = (int) position.getY();
		this.w = (int) position.getWidth();
		this.h = (int) position.getHeight();
		this.imageBox = Objects.requireNonNull(imageBox);
	}
	void move(Rectangle rect) {
		Objects.requireNonNull(rect);
		this.x = (int) rect.getX();
		this.y = (int) rect.getY();
		this.w = (int) rect.getWidth();
		this.h = (int) rect.getHeight();
	}
	Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}
	Rectangle getSaveBox() {
		var rect = new Rectangle(x, y, w, h);
		rect.grow(-4, -4);
		return rect;
	}
	void saveAction() {
		imageBox.saveImages();
	}
}
