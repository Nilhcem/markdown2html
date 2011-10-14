package com.nilhcem.md2html.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Provides the menu bar of the application.
 *
 * @author Nilhcem
 * @since 1.0
 */
public final class MenuBar extends Observable {
	private final JMenuBar menuBar = new JMenuBar();

	/**
	 * Creates the menu bar and the different menus (file / edit / help).
	 */
	public MenuBar() {
		menuBar.add(createFileMenu());
		menuBar.add(createHelpMenu());
	}

	/**
	 * Returns the JMenuBar object.
	 *
	 * @return the JMenuBar object.
	 */
	public JMenuBar get() {
		return menuBar;
	}

	/**
	 * Creates the file menu.
	 * <p>
	 * The file menu contains an "Exit" item, to quit the application.
	 * </p>
	 *
	 * @return the newly created file menu.
	 */
	private JMenu createFileMenu() {
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');

		JMenuItem exit = new JMenuItem("Exit");
		exit.setMnemonic('x');
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		fileMenu.add(exit);
		return fileMenu;
	}

	/**
	 * Creates the help menu.
	 * <p>
	 * The help menu contains an "About" item, to display some software information.
	 * </p>
	 *
	 * @return the newly created help menu.
	 */
	private JMenu createHelpMenu() {
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('h');

		JMenuItem about = new JMenuItem("About");
		about.setMnemonic('a');
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(menuBar.getParent(),
					String.format("Extremely simple Markdown to HTML converter%nPowered by MarkdownJ%nhttps://github.com/nilhcem"),
					"Markdown2HTML: About", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		helpMenu.add(about);
		return helpMenu;
	}
}
