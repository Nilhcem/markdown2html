package com.nilhcem.md2html.gui;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 * Provides the main panel of the application, which will contain all the components.
 *
 * @author Nilhcem
 * @since 1.0
 */
public final class MainPanel {
	private final MigLayout layout = new MigLayout(
		"", // Layout constraints
		"[fill,50%] 10 [fill,50%]", // Column constraints
		"[grow,fill]"); // Row constraints
	private final JPanel mainPanel = new JPanel(layout);

	private final InputPane input = new InputPane();
	private final PreviewPane preview = new PreviewPane();

	/**
	 * Creates the main panel, adding observer to the input and building the GUI.
	 */
	public MainPanel() {
		// Add observer
		input.addObserver(preview);

		// Build GUI
		mainPanel.add(input.get());
		mainPanel.add(preview.get());
	}

	/**
	 * Returns the JPanel object.
	 *
	 * @return the JPanel object.
	 */
	public JPanel get() {
		return mainPanel;
	}
}
