package fr.recolor.canvas.imageboxbutton;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import fr.recolor.canvas.imagebox.ImageBox;

public class ImageBoxButton {
	private final ImageBoxButtonController controller;
	private final ImageBoxButtonView view;
	private final ImageBoxButtonData data;
	
	
	public ImageBoxButton(Rectangle position, ImageBox imageBox, JPanel panel) {
		data = new ImageBoxButtonData(position, imageBox);
		controller = new ImageBoxButtonController(data, panel);
		view = new ImageBoxButtonView();
	}
	
	public void paint(Graphics2D g) {
		view.paint(g, data);
	}

	public void move(Rectangle rect) {
		data.move(rect);
	}
	
	public MouseInputListener getMotionListener() {
		return controller;
	}
}
