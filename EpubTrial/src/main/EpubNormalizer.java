package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.Spine;
import nl.siegmann.epublib.domain.SpineReference;
import nl.siegmann.epublib.epub.EpubReader;

public class EpubNormalizer {
	
	private String fileName;
	private String chapterDelimiter;
	private List<Replacement> replacements;
	

	public EpubNormalizer(String fileName, String chapterDelimiter, List<Replacement> replacements){
		this.fileName = fileName;
		this.chapterDelimiter = chapterDelimiter;
		this.replacements = replacements;
	}
	
	public FlatBook getFlatEpubData() throws FileNotFoundException, IOException{
		FlatBook flatBook = new FlatBook();
		EpubReader epubReader = new EpubReader();
		Book book;			
		book = epubReader.readEpub(new FileInputStream(fileName));
		
		Metadata metadata = book.getMetadata();		
		List<String> titles = metadata.getTitles();
		flatBook.setBookName((titles.isEmpty() ? "Unable to determine title." : titles.get(0)));
		List<Author> authors = metadata.getAuthors();
		flatBook.setAuthorName((authors.isEmpty() ? "Unable to determine author." : authors.get(0).getFirstname()+" "+authors.get(0).getLastname()));
		Spine spine = new Spine(book.getTableOfContents());
		
		ArrayList<SpineReference> refArl = (ArrayList<SpineReference>) spine.getSpineReferences();
		
		for(int i = 0; i<refArl.size(); i++){
			Resource res = refArl.get(i).getResource();
            InputStream is = res.getInputStream();
            String section = convertStreamToString(is);
            if(section.contains(chapterDelimiter)){
            	 section = section.substring(section.indexOf(chapterDelimiter)+chapterDelimiter.length(), section.indexOf("</div>"));
            	 for(int j = 0; j < replacements.size() ;j++){
            		 section = replace(replacements.get(j), section);
            	 }
            	 flatBook.addChapter(section);
            }
		}		
		return flatBook;
	}
	
	private String convertStreamToString (java.io.InputStream is) {
	    Scanner s = new Scanner(is).useDelimiter("\\A");
	    String str = s.hasNext() ? s.next() : "";
	    s.close();
	    return str;
	}
	
	private String replace(Replacement replacement, String section){
		section = section.replaceAll(replacement.getExpression(), replacement.getReplacement());
		return section;		
	}

}
