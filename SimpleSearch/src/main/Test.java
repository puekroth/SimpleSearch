package main;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Map;

class Test {

	@org.junit.jupiter.api.Test
	void testSimpleSearch() {
		
		String directory = "/foo/bar";
		String searchLine = "he found himself transformed in his bed";
		int maxNbr = 10;
		Map<String, Integer> topFiles =  new FileService().getTopFiles(new FileReader().indexFiles(new File(directory)), searchLine.toUpperCase(), maxNbr);
		
		assertEquals(10, topFiles.size());
		assertEquals(100, topFiles.get("filename1.txt"));
		assertEquals(29, topFiles.get("filename2.txt"));
		assertEquals(29, topFiles.get("filename3.txt"));
		assertEquals(29, topFiles.get("filename4.txt"));
		assertEquals(14, topFiles.get("filename5.txt"));
		assertEquals(14, topFiles.get("filename6.txt"));
		assertEquals(14, topFiles.get("filename7.txt"));
		assertEquals(14, topFiles.get("filename8.txt"));
		assertEquals(14, topFiles.get("filename9.txt"));
		assertEquals(14, topFiles.get("filename10.txt"));
		
	}	

}
