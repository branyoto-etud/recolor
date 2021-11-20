package fr.recolor.canvas.imagebox;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;

import fr.recolor.canvas.colorbox.ColorBox;

/**
 * @author Bruce GAMEIRO COSTA
 * Contenent the data of the ImageBox
 */
class ImageBoxData {
	private final List<Color> oldColors, newColors;
	private final Image oldImg, newImg;
	private final ColorBox colorBox;
	private final List<File> files;
	private int index, lastIndex;
	private int x, y, w, h;
	
	/**
	 * Construct an ImageBox positioned in the rectangle position
	 * and containing all the images to modify
	 * @param position is the bounds of the box
	 * @param files are all the images to modify
	 * @param colorBox is a reference to the box where the color is modified
	 * @throws IOException if the first image cannot be readed
	 */
	ImageBoxData(Rectangle position, List<File> files, ColorBox colorBox) throws IOException {
		this.files = Objects.requireNonNull(files);
		this.oldImg = ImageIO.read(files.get(0));
		this.newImg = ImageIO.read(files.get(0));
		this.oldColors = getColorList(oldImg);
		this.newColors = getColorList(oldImg);
		this.colorBox = colorBox;
		this.index = 0;
		this.lastIndex = this.index;
		this.colorBox.setColors(oldColors.get(index), newColors.get(index));
		Objects.requireNonNull(position);
		this.x = (int) position.getX();
		this.y = (int) position.getY();
		this.w = (int) position.getWidth();
		this.h = (int) position.getHeight();
	}
	/**
	 * Get all the colors (except transparent) of the image
	 * @param img the image where search the colors
	 * @return the colors composing this image
	 */
	private List<Color> getColorList(Image img) {
		List<Color> colorList = new ArrayList<>();
		BufferedImage buff = (BufferedImage)img;
		for (int i = 0; i < img.getWidth(null); i++) {
			for (int j = 0; j < img.getHeight(null);j++) {
				Color color = new Color(buff.getRGB(i, j), true);
				if (!colorList.contains(color)) {
					colorList.add(color);
				}
			}
		}
		colorList.sort((c1, c2) -> c1.getRGB() - c2.getRGB()); // only to put the alpha = 0 at the end
		return colorList;
	}
	/**
	 * Change the position and size of this boxx
	 * @param rect the new position
	 */
	void move(Rectangle rect) {
		Objects.requireNonNull(rect);
		this.x = (int) rect.getX();
		this.y = (int) rect.getY();
		this.w = (int) rect.getWidth();
		this.h = (int) rect.getHeight();
	}
	/**
	 * Get the image before the modification
	 * @return the initial image
	 */
	Image getOldImg() {
		return oldImg;
	}
	/**
	 * Get the image with the current colors change
	 * @return the processed image
	 */
	Image getNewImg() {
		return newImg;
	}
	/**
	 * Get the box of the ImageBox
	 * @return the bounds of the box
	 */
	Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}
	/**
	 * Get the color of the initial image
	 * @return the old color
	 */
	Color getOldColor() {
		return oldColors.get(index);
	}
	/**
	 * Get the freshly modified color
	 * @return the new color
	 */
	Color getNewColor() {
		return newColors.get(index);
	}
	/**
	 * Check if the index correspond to the ancient index of the color
	 * If they aren't equals, update the colors of the colorBox
	 */
	void checkIndex() {
		if (index != lastIndex) {
			lastIndex = index;
			colorBox.setColors(oldColors.get(index), newColors.get(index));
		}
	}
	/**
	 * Get the position of the not modified image
	 * @return the bounds of the old image
	 */
	Rectangle getOldImageBounds() {
		var size = getSize();
		var ypos = y + h/2 - size/2;
		return new Rectangle(x + w/60, ypos, size, size);
	}
	/**
	 * Get the position of the modified image
	 * @return the bounds of the new image
	 */
	Rectangle getNewImageBounds() {
		var size = getSize();
		var ypos = y + h/2 - size/2;
		return new Rectangle(x + w-w/60-size, ypos, size, size);
	}
	/**
	 * Get the color of the image {@code img} at the position {@code (x,y)}
	 * @param x the X coordinate of the pixel
	 * @param y the Y coordinate of the pixel
	 * @param img an image where search the color
	 * @return the color found
	 */
	Color getColor(int x, int y, Image img) {
		BufferedImage buffImg = (BufferedImage) img;
		return new Color(buffImg.getRGB(x, y), true);
	}
	/**
	 * Get the list of the initial colors
	 * @return the list of the initial colors
	 */
	List<Color> getOldColors() {
		return oldColors;
	}
	/**
	 * Get the list of the modified colors
	 * @return the list of the modified colors
	 */
	List<Color> getNewColors() {
		return newColors;
	}
	/**
	 * Set the current color index at {@code index} 
	 * unless the index is out of the list
	 * @param index the new index 
	 */
	void setIndex(int index) {
		if (index >= 0 && index < oldColors.size()) {
			this.index = index;
		}
	}
	/**
	 * Check if the color of the colorBox is equal to 
	 * the color of the list.
	 * 
	 * If they're not equals, recolor the image with the color of the colorBox
	 * and update the color in the list
	 */
	void checkColor() {
		var color = colorBox.getNewColor();
		if (!color.equals(newColors.get(index))) {
			recolorNewImage(oldColors.get(index), color);
			newColors.set(index, color);
		}
	}
	/**
	 * Change on the new image the {@code oldColor} of the old image
	 * with the {@code newColor}
	 * @param oldColor the color to replace
	 * @param newColor the color to use instead
	 */
	private void recolorNewImage(Color oldColor, Color newColor) {
		BufferedImage buffNew = (BufferedImage) newImg;
		BufferedImage buffOld = (BufferedImage) oldImg;
		for (int i = 0; i < buffOld.getWidth(null); i++) {
			for (int j = 0; j < buffOld.getHeight(null); j++) {
				if (buffOld.getRGB(i, j) == oldColor.getRGB()) {
					buffNew.setRGB(i, j, newColor.getRGB());
				}
			}
		}
	}
	/**
	 * Get the size of the square containing the image
	 * @return the size of the square
	 */
	int getSize() {
		return Math.min(56*w/120, 38*h/40);
	}
	/**
	 * Save all the images after recoloring them
	 * the saved images are on the same folder but ending with "_processed"
	 */
	void saveImages() {
		var dir = new File(files.get(0).getParent() + "_processed");
		dir.mkdirs();
		for (var file : files) {
			try {
				saveImage(file);
			} catch (IOException e) {
				System.out.println("Cannot save Image : " + file.getName());
			}
		}
	}
	/**
	 * Recolor and save the image of the {@code file}
	 * the saved images are on the same folder but ending with "_processed"
	 * @param file image the process
	 * @throws IOException if the image cannot be opened
	 */
	void saveImage(File file) throws IOException{
		BufferedImage oldImg = ImageIO.read(file);
		BufferedImage newImg = ImageIO.read(file);
		
		for (int i = 0; i < oldColors.size(); i++) {
			for (int x = 0; x < oldImg.getWidth(); x++) {
				for (int y = 0; y < oldImg.getHeight(); y++) {
					if (oldImg.getRGB(x, y) == oldColors.get(i).getRGB()) {
						newImg.setRGB(x, y, newColors.get(i).getRGB());
					}
				}
			}
		}
		File outFile = new File(file.getParent() + "_processed/" + file.getName());
		ImageIO.write((BufferedImage)newImg, "png", outFile);
	}
	/**
	 * Change colorBox colors using {@code color} and the old color at {@code index}
	 * @param color new newColor to put in the colorBox
	 */
	void setNewColor(Color color) {
		colorBox.setColors(oldColors.get(index), color);
	}
}
