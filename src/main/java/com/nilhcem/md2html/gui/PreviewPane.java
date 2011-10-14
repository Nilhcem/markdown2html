package com.nilhcem.md2html.gui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import com.petebevin.markdown.MarkdownProcessor;

/**
 * Scrolled text area where will be displayed the HTML preview.
 *
 * @author Nilhcem
 * @since 1.0
 */
public final class PreviewPane implements Observer {
	private final JScrollPane previewPane = new JScrollPane();
	private final JLabel previewLabel = new JLabel();

	/**
	 * Creates the HTML JLabel and sets its vertical alignment as in the top.
	 */
	public PreviewPane() {
		previewLabel.setVerticalAlignment(JLabel.TOP);
		previewPane.getViewport().add(previewLabel, null);
	}

	/**
	 * Returns the JScrollPane object.
	 *
	 * @return the JScrollPane object.
	 */
	public JScrollPane get() {
		return previewPane;
	}

	/**
	 * Updates the content of the label by converting the input data to html and setting them to the label.
	 * <p>
	 * This method will be called by an {@code InputPane} observable.
	 * </p>
	 *
	 * @param o the observable element which will notify this class.
	 * @param data a String object containing the input data to be converted into HTML.
	 */
	@Override
	public void update(final Observable o, final Object data) {
		if (o instanceof InputPane) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					String content = (String)data;
					MarkdownProcessor processor = new MarkdownProcessor();
					previewLabel.setText(String.format("<html>%s</html>", processor.markdown(content)).replaceAll("src=\"", "src=\"file:")); // Fix to display images properly.
				}
			});
		}
	}
}
