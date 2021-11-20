package fr.recolor.canvas.imagebox;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import fr.recolor.canvas.colorbox.ColorBox;

/**
 * @author Bruce GAMEIRO COSTA
 * A box where is the initial image and the processed image
 */
public class ImageBox {
	private final ImageBoxController controller;
	private final ImageBoxView view;
	private final ImageBoxData data;

	/**
	 * Construct an ImageBox positioned in the rectangle position
	 * and containing all the images to modify
	 * @param position is the bounds of the box
	 * @param files are all the images to modify
	 * @param colorBox is a reference to the box where the color is modified
	 * @throws IOException if the first image cannot be readed
	 */
	public ImageBox(Rectangle position, List<File> files, ColorBox colorBox, JPanel panel) throws IOException {
		data = new ImageBoxData(position, files, colorBox);
		controller = new ImageBoxController(data, panel);
		view = new ImageBoxView();
	}
  /**
   * Draw the ImageBox using the specified graphics
   * @param g the graphics to use
   */
	public void paint(Graphics2D g) {
		view.paint(g, data);
	}
	/**
	 * Change the position of the box
	 * @param rect new position of the box
	 */
	public void move(Rectangle rect) {
		data.move(rect);
	}
	/**
	 * Get the InputListener of this image box
	 * @return the controller of this image box
	 */
	public MouseInputListener getMouseListener() {
		return controller;
	}
	/**
	 * Save the images
	 */
	public void saveImages() {
		data.saveImages();
	}
}
