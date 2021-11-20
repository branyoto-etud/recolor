package fr.recolor.canvas.imageboxbutton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;

class ImageBoxButtonView {
	void paint(Graphics2D g, ImageBoxButtonData data) {
		g.setColor(new Color(40, 33, 28));
		g.fill(data.getBounds());
		drawButton(g, data.getSaveBox(), "Save");
	}
	
	private void drawButton(Graphics g, Rectangle rect, String label) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(31, 25, 21));
		g2.fill(rect);
		var fontSize = Math.min(rect.getWidth()/label.length(), rect.getHeight());
		var font = new Font("Courier", Font.BOLD, (int)fontSize);
		g2.setFont(font);
		g2.setColor(new Color(200, 202, 206));
		var bounds = font.getStringBounds(label, new FontRenderContext(null, false, false));
		var x = (float)(rect.getX() + rect.getWidth()/2 - bounds.getWidth()/2);
		g2.drawString(label, x, (float)(rect.getMaxY()-rect.getHeight()/4));
	}
}
