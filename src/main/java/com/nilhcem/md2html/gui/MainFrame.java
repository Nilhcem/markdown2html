package com.nilhcem.md2html.gui;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;

import org.apache.commons.io.FileUtils;

/**
 * Provides the main window of the application.
 *
 * @author Nilhcem
 * @since 1.0
 */
public final class MainFrame {
	private final JFrame mainFrame = new JFrame("Markdown editor");
	private final MenuBar menu = new MenuBar(this);
	private final MainPanel panel = new MainPanel();

	/**
	 * Creates the main window and makes it visible.
	 */
	public MainFrame() {
		Dimension frameSize = new Dimension(640, 440);

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(frameSize);
		mainFrame.setMinimumSize(frameSize);

		mainFrame.setJMenuBar(menu.get());
		mainFrame.getContentPane().add(panel.get());
		mainFrame.setLocationRelativeTo(null); // Center main frame
		mainFrame.setVisible(true);
	}

	public void load(File file) {
		String text;
		try {
			text = FileUtils.readFileToString(file);
			panel.setContent(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
