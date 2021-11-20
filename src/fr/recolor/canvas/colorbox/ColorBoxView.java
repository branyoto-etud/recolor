package fr.recolor.canvas.colorbox;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

class ColorBoxView {
	void paint(Graphics2D g, ColorBoxData data) {
		g.setColor(new Color(40, 33, 28));
		g.fill(data.getBounds());
		
		drawColor(g, data.getOldColor(), data.getOldColorBounds());
		drawColor(g, data.getNewColor(), data.getNewColorBounds());
		
		data.getRedBar().paint(g);
		data.getGreenBar().paint(g);
		data.getBlueBar().paint(g);
		data.getAlphaBar().paint(g);
	}

	private void drawColor(Graphics g, Color color, Rectangle rect) {
		Rectangle rect2 = new Rectangle(rect);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(31, 25, 21));
		g2.fill(rect2);
		g2.setColor(color);
		rect2.grow(-4, -4);
		g2.fill(rect2);
	}
}
