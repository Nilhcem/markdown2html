package com.nilhcem.md2html.console;

import java.awt.GraphicsEnvironment;
import java.io.FileNotFoundException;
import org.apache.commons.io.FilenameUtils;

/**
 * Program arguments parser.
 *
 * @author Nilhcem
 * @since 1.0
 */
public final class ArgsParser {
	private String markdownFile = null;
	private String outputFile = null;
	private String headerFile = null;
	private String footerFile = null;
	private boolean consoleMode = GraphicsEnvironment.isHeadless();

	/**
	 * Checks arguments and determine the running mode (command line or GUI).
	 * <p>
	 * <ul>
	 *   <li>If there is no graphical environment, the program will run in command line;</li>
	 *   <li>If there is a graphical environment but program arguments, the program will run in command line;</li>
	 *   <li>Otherwise, the program will launch the GUI.</li>
	 * </ul>
	 * </p>
	 *
	 * @param args a list of arguments sent while launching the program.
	 * @throws DisplayUsageException if arguments are wrong.
	 * @throws FileNotFoundException if the file to open in command line was not found.
	 */
	public void checkArgs(String[] args) throws DisplayUsageException, FileNotFoundException {
		boolean specifyOutput = false;
		boolean specifyHeader = false;
		boolean specifyFooter = false;

		for (String arg : args) {
			if ("-usage".equals(arg) || "-h".equals(arg) || "-help".equals(arg)) {
				throw new DisplayUsageException();
			} else if (specifyHeader && headerFile == null) {
				headerFile = arg;
				specifyHeader = false;
			} else if (specifyFooter && footerFile == null) {
				footerFile = arg;
				specifyFooter = false;
			} else if (specifyOutput && outputFile == null) {
				outputFile = arg;
				specifyOutput = false;
			} else {
				if ("-out".equals(arg)) {
					specifyOutput = true;
				} else if ("-header".equals(arg)) {
					specifyHeader = true;
				} else if ("-footer".equals(arg)) {
					specifyFooter = true;
				} else if (markdownFile == null) {
					markdownFile = arg;
				} else {
					throw new DisplayUsageException();
				}
			}
		}

		if (consoleMode && markdownFile == null) {
			throw new DisplayUsageException();
		}
		if (specifyOutput && outputFile == null) {
			outputFile = FilenameUtils.removeExtension(markdownFile) + ".html";
		}

		// may run console mode even if there is a graphic environment
		consoleMode = (markdownFile != null || outputFile != null);
	}

	/**
	 * Returns the name of the markdown file (the file in input).
	 *
	 * @return the name of the markdown file (the file in input).
	 */
	public String getMarkdownFile() {
		return markdownFile;
	}

	/**
	 * Returns the name of the HTML file (the file in output).
	 *
	 * @return the name of the HTML file (the file in output).
	 */
	public String getOutputFile() {
		return outputFile;
	}

	/**
	 * Returns {@code true} if the program should be launched in console mode (command line), or {@code false} for a GUI mode.
	 *
	 * @return {@code true} if the program should be launched in console mode (command line), or {@code false} for a GUI mode.
	 */
	public boolean isConsoleMode() {
		return consoleMode;
	}

	/**
	 * Returns the name of the header file if any, or {@code null} if none was specified.
	 *
	 * @return the name of the header file if any, or {@code null} if none was specified.
	 */
	public String getHeaderFile() {
		return headerFile;
	}

	/**
	 * Returns the name of the footer file if any, or {@code null} if none was specified.
	 *
	 * @return the name of the footer file if any, or {@code null} if none was specified.
	 */
	public String getFooterFile() {
		return footerFile;
	}
}
