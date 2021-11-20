package fr.recolor.canvas.colorbox;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

class ColorBoxController implements MouseInputListener {
	private final ColorBoxData data;
	private final JPanel panel;
	
	ColorBoxController(ColorBoxData data, JPanel panel) {
		this.data = data;
		this.panel = panel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (data.getOldColorBounds().contains(e.getPoint())) {
			data.resetNewColor();
			panel.repaint();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
