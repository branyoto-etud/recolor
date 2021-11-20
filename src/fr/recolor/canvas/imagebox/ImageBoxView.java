package fr.recolor.canvas.imagebox;

import java.awt.Color;
import java.awt.Graphics2D;

class ImageBoxView {
	
	void paint(Graphics2D g, ImageBoxData data) {
		data.checkIndex();
		data.checkColor();
		g.setColor(new Color(40, 33, 28));
		g.fill(data.getBounds());
		g.setColor(new Color(31, 25, 21));
		var bounds = data.getOldImageBounds();
		g.fill(bounds);
		g.drawImage(data.getOldImg(), (int)bounds.getX(), (int)bounds.getY(), data.getSize(), data.getSize(), null);
		bounds = data.getNewImageBounds();
		g.fill(bounds);
		g.drawImage(data.getNewImg(), (int)bounds.getX(), (int)bounds.getY(), data.getSize(), data.getSize(), null);
	}
}
