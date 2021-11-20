package fr.recolor.canvas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;

class CanvasView {
	private void drawTitle(Graphics g, int w, int h) {
		var font = new Font("Courier", Font.BOLD, Math.min(w/8, 2*h/3));
		g.setColor(new Color(40, 33, 28));
		g.fillRect(0, 0, w, h);
		g.setColor(new Color(200, 202, 206));
		g.setFont(font);
		var bounds = font.getStringBounds("Recolor", new FontRenderContext(null, false, false));
		g.drawString("Recolor", (int) (w/2 - bounds.getWidth()/2)+w/100, (int) (h/2 + bounds.getHeight()/4));
	}
	
	void paint(Graphics g, CanvasData data) {
		var height = data.getHeight();
		var width = data.getWidth();
		// Title
		g.translate(width/100,  height/100);
		drawTitle(g, 98*width/100, 10*height/100);
		g.translate(-width/100, -height/100);
		
		data.getColorBox().move(data.getColorBoxBounds());
		data.getImageBox().move(data.getImageBoxBounds());
		data.getImgBoxButton().move(data.getImageBoxButtonBounds());
		data.getColorBox().paint((Graphics2D) g);
		data.getImageBox().paint((Graphics2D) g);
		data.getImgBoxButton().paint((Graphics2D) g);
	}
}
