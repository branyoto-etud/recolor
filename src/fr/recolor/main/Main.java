package fr.recolor.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.recolor.canvas.Canvas;

/**
 * @author Bruce GAMEIRO COSTA
 * This program is used to recolor a batch of images
 * by using a new color set chosen by the user
 */
public class Main extends JFrame {

	/**
	 * Initialize a JFrame who contains only a JPanel
	 * @param panel element to insert into the JFrame created (not null)
	 */
	public Main(JPanel panel) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    setBackground(new Color(5, 5, 5));
    setSize(new Dimension(700, 700));
    setTitle("Recolor in java");
    setLocation(100, 100);
    add(Objects.requireNonNull(panel));
    setVisible(true);
	}
	/**
	 * List all the png images within the specified directory
	 * @param dir directory where search files
	 * @return the list of the file founds
	 */
	private static List<File> listPng(File dir) {
		if (dir == null || !dir.exists()) {
			return null;
		}
		return Arrays.asList(dir.listFiles(file -> file.isFile() && file.getName().endsWith(".png")));
	}
	
	/**
	 * Entering point of the program create a frame to recolor the images
	 * @param args list of arguments given to the program
	 * @throws IOException if the first file can't be opened
	 */
	public static void main(String[] args) throws IOException {
		String dirName;
		if (args.length > 0) {
			dirName = args[0];
		} else {
			dirName = "data/images";
		}
		List<File> files = listPng(new File(dirName));
		if (files == null) {
			System.out.println("Directory " + dirName + " doesn't exist");
			System.exit(1);
		}
		if (files.size() <= 0) {
			System.out.println("No file found!");
			System.exit(1);
		}
		Canvas canvas = new Canvas(files);
		new Main(canvas);
	}
	
	
}
