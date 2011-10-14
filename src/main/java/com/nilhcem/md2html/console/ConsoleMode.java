package com.nilhcem.md2html.console;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import com.petebevin.markdown.MarkdownProcessor;

/**
 * Converts the markdown file in a HTML file when the application is running in command line.
 * 
 * @author Nilhcem
 * @since 1.0
 */
public final class ConsoleMode {
	private final MarkdownProcessor processor = new MarkdownProcessor();

	/**
	 * Converts the file in HTML depending on the options sent in parameter.
	 *
	 * @param args an ArgsParser object containing the program's options.
	 * @throws FileNotFoundException if the file which should be converted doesn't exist.
	 */
	public void process(ArgsParser args) throws FileNotFoundException {
		File file = new File(args.getMarkdownFile());
		if (!file.exists()) {
			throw new FileNotFoundException(args.getMarkdownFile());
		}

		try {
			String fileContent = FileUtils.readFileToString(file, "UTF-8");
			String htmlContent = getFileContent(args.getHeaderFile()) + processor.markdown(fileContent) + getFileContent(args.getFooterFile());

			if (args.getOutputFile() == null) {
				// Display to console
				System.out.println(htmlContent);
			}
			else {
				// Save to file
				File output = new File(args.getOutputFile());
				FileUtils.write(output, htmlContent, "UTF-8");
			}
		}
		catch (IOException e) {
			System.err.println("ERROR");
			e.printStackTrace();
		}
	}

	/**
	 * Returns the content of the file in parameter (should be an HTML file), or an empty String if the file is {@code null}.
	 * <p>
	 * This method is useful to include header and footers.
	 * </p>
	 *
	 * @param filePath the file which should be opened and converted. The file is usually a header or a footer.
	 * @return the content of the file in parameter, or an empty String if the file is {@code null} or if an error happened.
	 * @throws FileNotFoundException if the file specified in parameter doesn't exist.
	 */
	private String getFileContent(String filePath) throws FileNotFoundException {
		if (filePath != null) {
			File file = new File(filePath);
			if (!file.exists()) {
				throw new FileNotFoundException(filePath);
			}

			try {
				return FileUtils.readFileToString(file, "UTF-8");
			}
			catch (IOException e) {
				System.err.println(String.format("Error parsing file: %s", filePath));
			}
		}
		return "";
	}
}
