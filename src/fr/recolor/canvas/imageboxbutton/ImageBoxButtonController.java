package fr.recolor.canvas.imageboxbutton;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

class ImageBoxButtonController implements MouseInputListener {
	private final ImageBoxButtonData data;
	private final JPanel panel;
	
	ImageBoxButtonController(ImageBoxButtonData data, JPanel panel) {
		this.data = data;
		this.panel = panel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (data.getSaveBox().contains(e.getPoint())) {
			data.saveAction();
			panel.repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
