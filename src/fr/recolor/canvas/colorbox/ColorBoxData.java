package fr.recolor.canvas.colorbox;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import fr.recolor.canvas.colorbox.bar.Bar;

class ColorBoxData {
	private final Bar redBar, greenBar, blueBar, alphaBar;
	private final JPanel panel;
	private Color oldColor;
	private int x, y, w, h;
	
	ColorBoxData(Color oldColor, Color newColor, Rectangle position, JPanel panel) {
		this.oldColor = Objects.requireNonNull(oldColor);
		this.panel = Objects.requireNonNull(panel);
		Objects.requireNonNull(position);
		this.x = (int) position.getX();
		this.y = (int) position.getY();
		this.w = (int) position.getWidth();
		this.h = (int) position.getHeight();
		this.redBar = new Bar(new Color(0), new Color(0), 0, position, panel);
		this.greenBar = new Bar(new Color(0), new Color(0), 0, position, panel);
		this.blueBar = new Bar(new Color(0), new Color(0), 0, position, panel);
		this.alphaBar = new Bar(new Color(0), new Color(0), 0, position, panel);
		initBars();
		setNewColor(Objects.requireNonNull(newColor));
	}
	private void initBars() {
		var size = getSquareSize();
		int r = redBar.getValue(), g = greenBar.getValue(), b = blueBar.getValue(), a = alphaBar.getValue();
		Rectangle rectangle = new Rectangle(x + size + w/15, y + h/10, w-size*2-4*w/30, 3*h/20);
		redBar.move(rectangle);
		redBar.changeColors(new Color(0,g,b,a), new Color(255,g,b,a));
		rectangle.translate(0, 5*h/20);
		greenBar.move(rectangle);
		greenBar.changeColors(new Color(r,0,b,a), new Color(r,255,b,a));
		rectangle.translate(0, 2*h/10);
		blueBar.move(rectangle);
		blueBar.changeColors(new Color(r,g,0,a), new Color(r,g,255,a));
		rectangle.translate(0, 2*h/10);
		alphaBar.move(rectangle);
		alphaBar.changeColors(new Color(r,g,b,0), new Color(r,g,b,255));
	}
	void setNewColor(Color newColor) {
		redBar.setValue(newColor.getRed());
		greenBar.setValue(newColor.getGreen());
		blueBar.setValue(newColor.getBlue());
		alphaBar.setValue(newColor.getAlpha());
	}
	void move(Rectangle rect) {
		Objects.requireNonNull(rect);
		this.x = (int) rect.getX();
		this.y = (int) rect.getY();
		this.w = (int) rect.getWidth();
		this.h = (int) rect.getHeight();
		initBars();
	}
	int getSquareSize() {
		return Math.min(w/4, 8*h/10);
	}
	Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}
	Rectangle getOldColorBounds() {
		var size = getSquareSize();
		return new Rectangle(x+w/30, y+h/2 - size/2, size, size);
	}
	Rectangle getNewColorBounds() {
		var square = getOldColorBounds();
		square.translate(w-getSquareSize()-w/15, 0);
		return square;
	}
	int getX() {
		return x;
	}
	int getY() {
		return y;
	}
	int getW() {
		return w;
	}
	int getH() {
		return h;
	}
	Bar getRedBar() {
		return redBar;
	}
	Bar getGreenBar() {
		return greenBar;
	}
	Bar getBlueBar() {
		return blueBar;
	}
	Bar getAlphaBar() {
		return alphaBar;
	}
	Color getOldColor() {
		return oldColor;
	}
	Color getNewColor() {
		return new Color(redBar.getValue(), greenBar.getValue(), blueBar.getValue(), alphaBar.getValue());
	}
	void resetNewColor() {
		redBar.setValue(oldColor.getRed());
		greenBar.setValue(oldColor.getGreen());
		blueBar.setValue(oldColor.getBlue());
		alphaBar.setValue(oldColor.getAlpha());
	}
	List<MouseInputListener> getMouseListeners() {
		List<MouseInputListener> listeners = new ArrayList<>();
		listeners.add(redBar.getMouseListeners());
		listeners.add(blueBar.getMouseListeners());
		listeners.add(greenBar.getMouseListeners());
		listeners.add(alphaBar.getMouseListeners());
		return listeners;
	}
	void setColors(Color oldColor, Color newColor) {
		this.oldColor = Objects.requireNonNull(oldColor);
		setNewColor(newColor);
		panel.repaint();
	}
}
