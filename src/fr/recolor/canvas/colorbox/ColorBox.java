package fr.recolor.canvas.colorbox;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class ColorBox {
	private final ColorBoxController controller;
	private final ColorBoxData data;
	private final ColorBoxView view;
	
	public ColorBox (Color oldColor, Color newColor, Rectangle position, JPanel panel) {
		data = new ColorBoxData(oldColor, newColor, position, panel);
		controller = new ColorBoxController(data, panel);
		view = new ColorBoxView();
	}
	
	public Rectangle getBounds() {
		return data.getBounds();
	}
	
	
	public void move(Rectangle rect) {
		data.move(rect);
	}
	
	public void paint(Graphics2D g) {
		view.paint(g, data);
	}
	public List<MouseInputListener> getMouseListeners() {
		List<MouseInputListener> listeners = data.getMouseListeners();
		listeners.add(controller);
		return listeners;
	}

	public void setColors(Color oldColor, Color newColor) {
		data.setColors(oldColor, newColor);
	}

	public Color getNewColor() {
		return data.getNewColor();
	}
}
