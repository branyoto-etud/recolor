package fr.recolor.canvas;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

/**
 * @author Bruce GAMEIRO COSTA
 */
public class Canvas extends JPanel {
	private static final long serialVersionUID = 1L;
	private final CanvasView view;
	private final CanvasData data;
	
	public Canvas(List<File> files) throws IOException {
		view = new CanvasView();
		data = new CanvasData(files, this);
		List<MouseInputListener> listeners = data.getMouseListener();
		for (var listener : listeners) {
			this.addMouseListener(listener);
			this.addMouseMotionListener(listener);
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		data.setHeight(getHeight());
		data.setWidth(getWidth());
		view.paint(g, data);
	}
}
