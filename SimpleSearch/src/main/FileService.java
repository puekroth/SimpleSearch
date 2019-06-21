package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileService {
	
	protected Map<String, Integer> getTopFiles(Map<String, Set<String>> indexedFiles, String searchLine, int maxNbr) {
		
		// calculate ranking of files
		Map<String, Integer> rankedFiles = rankFiles(indexedFiles, searchLine);
		
		// get top ten files by ranking
		Map<String, Integer> topFiles = util.MapUtil.sortMapByValue(rankedFiles, maxNbr);
		
		return topFiles;
		
	}
	
	private Map<String, Integer> rankFiles(Map<String, Set<String>> indexedFiles, String searchLine) {
		
		Map<String, Integer> rankedFiles = new HashMap<String, Integer>();
		
		// split search line into list of words
		List<String> searchWords = new ArrayList<String>(Arrays.asList(searchLine.split(" ")));
		
		// search indexed files for words in line
		int nbrOfMatches;
		
		Iterator<Map.Entry<String, Set<String>>> it = indexedFiles.entrySet().iterator();
	    while (it.hasNext()) {
	    	Map.Entry<String, Set<String>> pair = (Map.Entry<String, Set<String>>)it.next();
	       
	        String fileName = (String) pair.getKey();
	        Set<String> words = (Set<String>) pair.getValue();
	        
	        try {
	    	    
		        // count number of matching words in file
	        	nbrOfMatches = 0;
		        for(String searchWord : searchWords) {
		        	if(words.contains(searchWord)) {
		        		nbrOfMatches++;
		        	}
		        }
		        
		        // if any matching words are found in the file, calculate and save ranking
		        if(nbrOfMatches > 0) {		        	
		        	rankedFiles.put(fileName, calculateRanking(nbrOfMatches, searchWords.size()));
		        }
		        
			} catch (Exception e) {
				System.out.println("Error ranking file " + fileName);
			}
	        
	    }
		
		return rankedFiles;
		
	}
	
	private int calculateRanking(int matchCounter, int nbrOfSearchWords) {
		
		// calculate % of words matched in file
		return (int) Math.round(((double)matchCounter / (double)nbrOfSearchWords) * 100);
	
	}
	
}
