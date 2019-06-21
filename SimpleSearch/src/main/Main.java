package main;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		if(args.length == 0) {
			throw new IllegalArgumentException("No directory given to index.");
		}		
		final File indexableDirectory = new File(args[0]);
		
		// index all files in indexableDirectory
		Map<String, Set<String>> indexedFiles =  new FileReader().indexFiles(indexableDirectory);
		
		System.out.println(indexedFiles.size() + " files read in directory " + args[0]);
		
		Scanner keyboard = new Scanner(System.in);
		FileService fileService = new FileService();
		
		while (true) {
			System.out.print("search> ");
			final String line = keyboard.nextLine();
			
			if(line.equalsIgnoreCase(":quit")) {
				break;
			}
			
			// get top 10 files by matched words
			Map<String, Integer> topFiles = fileService.getTopFiles(indexedFiles, line.toUpperCase(), 10);
			
			// print results
			if(topFiles.isEmpty()) {
				System.out.println("no matches found");
			} else {
				util.MapUtil.printMap(topFiles);
			}
			
		}
		
		keyboard.close();
		
	}

}
