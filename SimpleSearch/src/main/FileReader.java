package main;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {
	
	
	protected Map<String, Set<String>> indexFiles(File indexableDirectory) {
		
		// get array of files in the directory
		File[] listOfFiles = indexableDirectory.listFiles();
		
		if(listOfFiles == null) {
			throw new IllegalArgumentException("Directory does not exist.");
		}
		
		// index files with contained words
		Map<String, Set<String>> indexedFiles = new HashMap<String, Set<String>>();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			
			try {
				
				if (listOfFiles[i].isFile()) {
					
					// get path to file
					Path filePath = Paths.get(listOfFiles[i].getPath());
					
					// skip file if not plain text
					String contentType = Files.probeContentType(filePath);
					if(!contentType.equals("text/plain")) {
						continue;
					}
					
					// load set of words contained in the file into collection
					Set<String> wordSet = null;
					
					// read file into stream, try-with-resources
					try (Stream<String> stream = Files.lines(filePath, Charset.forName("Cp1252"))) {
	
						// stream.forEach(System.out::println);
						
						// map the words to their count
						wordSet = stream
								.flatMap(line -> Arrays.stream(line.trim().replaceAll("[\\-\\+\\.\\^:,]","").split(" "))) // remove some special characters
								.map(String::toUpperCase)
								.collect(Collectors.toSet());
	
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					// if any words are found in the file, index file with all contained words
					if(wordSet != null) {
						indexedFiles.put(listOfFiles[i].getName(), wordSet);
					}
					
				} 
				
			} catch(IOException e) {
				System.out.println("Error indexing file " + listOfFiles[i].getName() + ": " + e.getMessage());
			}
			
		}	
		
		if (indexedFiles.isEmpty()) {
			throw new IllegalArgumentException("No text files in directory.");
		}
		
		return indexedFiles;
		
	}
	
}
