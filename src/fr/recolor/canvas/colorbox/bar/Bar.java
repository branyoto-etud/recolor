package fr.recolor.canvas.colorbox.bar;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class Bar {
	private final BarController controller;
	private final BarView view;
	private final BarData data;
	
	public Bar(Color min, Color max, int value, Rectangle bounds, JPanel panel) {
		data = new BarData(min, max, value, bounds);
		controller = new BarController(data, panel);
		view = new BarView();
	}
	
	public void paint(Graphics2D g) {
		view.paint(g, data);
	}

	public MouseInputListener getMouseListeners() {
		return controller;
	}

	public void move(Rectangle rectangle) {
		data.move(rectangle);
	}

	public void changeColors(Color start, Color end) {
		data.changeColors(start, end);
	}

	public int getValue() {
		return data.getValue();
	}
	public void setValue(int amount) {
		data.setValue(amount);
	}
}
