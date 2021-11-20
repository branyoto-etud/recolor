package fr.recolor.canvas.colorbox.bar;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;

class BarView {
	void paint(Graphics2D g, BarData data) {
		Rectangle rect = new Rectangle(data.getBounds());
		g.setColor(new Color(31, 25, 21));
		g.fill(rect);
		g.setPaint(new GradientPaint((float)rect.getX(), 0, data.getStart(), (float)rect.getMaxX(), 0, data.getEnd()));
		rect.grow(-4, -4);
		g.fill(rect);
		
		g.setColor(Color.white);
		int cursor = (int)((data.getValue()/256.0 ) * rect.getWidth() + rect.getX())-5;
		g.fillRect(cursor, (int)rect.getY(), 10, (int)rect.getHeight());
		var font = new Font("Courier", Font.BOLD, (int) (2*rect.getHeight()/3));
		g.setFont(font);
		var bounds = font.getStringBounds(data.getValue() + "", new FontRenderContext(null, false, false));
		g.drawString(data.getValue()+"", (float)(cursor+5 - bounds.getWidth()/2), (float)rect.getY()-4);
	}
}
