package fr.recolor.canvas;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import fr.recolor.canvas.colorbox.ColorBox;
import fr.recolor.canvas.imagebox.ImageBox;
import fr.recolor.canvas.imageboxbutton.ImageBoxButton;

class CanvasData {
	private final ImageBoxButton imgBoxButton;
	private final ColorBox colorBox;
	private final ImageBox imageBox;
	private int width, height;

	CanvasData(List<File> files, JPanel panel) throws IOException {
		width = panel.getWidth();
		height = panel.getHeight();	
		colorBox = new ColorBox(new Color(0), new Color(255), getColorBoxBounds(), panel);
		imageBox = new ImageBox(getImageBoxBounds(), files, colorBox, panel);
		imgBoxButton = new ImageBoxButton(getImageBoxButtonBounds(), imageBox, panel);
	}
	ImageBoxButton getImgBoxButton() {
		return imgBoxButton;
	}
	ColorBox getColorBox() {
		return colorBox;
	}
	ImageBox getImageBox() {
		return imageBox;
	}
	int getWidth() {
		return width;
	}
	void setWidth(int width) {
		this.width = width;
	}
	int getHeight() {
		return height;
	}
	void setHeight(int height) {
		this.height = height;
	}
	Rectangle getColorBoxBounds() {
		return new Rectangle(width/100, 14*height/100, 98*width/100, 26*height/100);
	}
	Rectangle getImageBoxBounds() {
		return new Rectangle(width/100, 42*height/100, 98*width/100, 40*height/100);
	}
	Rectangle getImageBoxButtonBounds() {
		return new Rectangle(width/3, 86*height/100, width/3, 10*height/100);
	}
	
	List<MouseInputListener> getMouseListener() {
		List<MouseInputListener> listeners = new ArrayList<>();
		listeners.addAll(colorBox.getMouseListeners());
		listeners.add(imageBox.getMouseListener());
		listeners.add(imgBoxButton.getMotionListener());
		return listeners;
	}
	
}
