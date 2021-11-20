package fr.recolor.canvas.imagebox;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

class ImageBoxController implements MouseInputListener{
	private final ImageBoxData data;
	private final JPanel panel;

	ImageBoxController(ImageBoxData data, JPanel panel) {
		this.data = data;
		this.panel = panel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		var point = e.getPoint();
		var bounds = data.getOldImageBounds();
		if (bounds.contains(point)) {
			var x = (data.getOldImg().getWidth(null)) * (point.getX() - bounds.getX()) / (bounds.getWidth());
			var y = (data.getOldImg().getHeight(null)) * (point.getY() - bounds.getY()) / (bounds.getHeight());
			var color = data.getColor((int)x, (int)y, data.getOldImg());
			var index = data.getOldColors().indexOf(color);
			data.setIndex(index);
			panel.repaint();
			return ;
		}
		bounds = data.getNewImageBounds();
		if (bounds.contains(point)) {
			var x = (data.getNewImg().getWidth(null)) * (point.getX() - bounds.getX()) / (bounds.getWidth());
			var y = (data.getNewImg().getHeight(null)) * (point.getY() - bounds.getY()) / (bounds.getHeight());
			if (e.getButton() == MouseEvent.BUTTON1) {
				var color = data.getColor((int)x, (int)y, data.getOldImg());
				var index = data.getOldColors().indexOf(color);
				data.setIndex(index);
			} else if (e.getButton() == MouseEvent.BUTTON3) {
				var color = data.getColor((int)x, (int)y, data.getNewImg());
				data.setNewColor(color);
			}
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
