package com.nilhcem.md2html;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

import com.nilhcem.md2html.console.ConsoleMode;

public class DoDir {
	
	public static ConsoleMode cm = new ConsoleMode();
	
	public static void doDir(File dir) throws Exception{
		File[] files = dir.listFiles();
		for(int i=0; i<files.length; i++){
			if(files[i].isDirectory()){
				System.out.println( "doDir:" + files[i].getAbsolutePath());
				doDir(files[i]);
			}else{
				String markdownFile = files[i].getAbsolutePath(); 
				if(!markdownFile.endsWith(".md")){
					continue;
				}
				String strOutputFile = FilenameUtils.removeExtension(markdownFile) + ".html";
				
				File outputFile = new File(strOutputFile);
				
				if(outputFile.exists()){
					continue;
				}

				String strErrFile = FilenameUtils.removeExtension(markdownFile) + ".err";
				File errFile = new File(strErrFile);
				if(outputFile.exists()){
					continue;
				}
				errFile.createNewFile();
				System.out.println( "errFile:" + errFile.getAbsolutePath());
				
				System.out.println( "doM2h:" + files[i].getAbsolutePath());
				try {
					cm.process(files[i], new File(strOutputFile));
					errFile.deleteOnExit();
					System.out.println( "del errFile:" + errFile.getAbsolutePath());
				} catch (Exception e) {
					System.out.println( "doM2h:" + e.getMessage());
				}
			}
		}
	}

}
