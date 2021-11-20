package fr.recolor.canvas.colorbox.bar;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

class BarController implements MouseInputListener {
	private final BarData data;
	private final JPanel panel;
	
	BarController(BarData data, JPanel panel) {
		this.data = data;
		this.panel = panel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		var rect = data.getBounds();
		var point = e.getPoint();
		if (rect.contains(point)) {
			var pos = ((point.getX() - rect.getX())/rect.getWidth()) * 256;
			
			data.setValue((int)pos);
			panel.repaint();
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		var rect = data.getBounds();
		var point = e.getPoint();
		if (rect.contains(point)) {
			var pos = ((point.getX() - rect.getX())/rect.getWidth()) * 256;
			data.setValue((int)pos);
			panel.repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
