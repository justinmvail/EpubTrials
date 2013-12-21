package main;
import java.io.IOException;
import java.util.ArrayList;

public class Driver {
	
	
	public static void main(String[] args) throws IOException {
		        
		//This is for the feedbook style of epub.
		
        ArrayList<Replacement> replacements = new ArrayList<Replacement>();       
        replacements.add(new Replacement("–", "-"));
        replacements.add(new Replacement("’", "'"));
        replacements.add(new Replacement("—", "-"));
        replacements.add(new Replacement("…", "..."));
        replacements.add(new Replacement("&#160;", " "));
        replacements.add(new Replacement("&amp;", "&"));
        replacements.add(new Replacement("\n", " "));
//        replacements.add(new Replacement("<p>", "\n"));
//        replacements.add(new Replacement("</p>", ""));
                
		String fileName = "C:\\Users\\Justin\\Desktop\\e books\\William Shakespeare - Macbeth.epub";
				
		String chapterDelimiter = "<div class=\"text\">";
		
		EpubNormalizer eNorm = new EpubNormalizer(fileName, chapterDelimiter, replacements);
		
		FlatBook flatBook = eNorm.getFlatEpubData();
				
		System.out.println(flatBook.getBookName());
		System.out.println(flatBook.getAuthorName());
		System.out.println(flatBook.getChapter(0));
		System.out.println(flatBook.getChapter(1));
		System.out.println(flatBook.getChapter(2));
		System.out.println(flatBook.getChapter(3));
		System.out.println(flatBook.getChapter(4));
		System.out.println(flatBook.getChapter(5));
		
		
	}
	
	

	
	
	
	
	
	
	
//	public static void main(String[] args) {
//		EpubReader epubReader = new EpubReader();
//		Book book;
//		try {
//			book = epubReader.readEpub(new FileInputStream("C:/Users/VailJ/Desktop/epub.epub"));
//			
//			Spine spine = new Spine(book.getTableOfContents());
//			ArrayList<SpineReference> refArl = (ArrayList<SpineReference>) spine.getSpineReferences();
//			for(int i = 0; i<refArl.size(); i++){
//				Resource res = refArl.get(i).getResource();
//                try {
//                    InputStream is = res.getInputStream();
//                    String section = convertStreamToString(is);
//                    if(section.contains("<div class=\"text\">")){
//                    	 section = section.substring(section.indexOf("<div class=\"text\">")+18, section.indexOf("</div>"));
////                    	 section = section.replaceAll("\n", "");
//                         section = section.replaceAll("–", "-");
//                         section = section.replaceAll("’", "'");
//                         section = section.replaceAll("—", "-");
//                         section = section.replaceAll("…", "...");
//                         section = section.replaceAll("&#160;", " ");
//                         section = section.replaceAll("&amp;", "&");
//                         System.out.println(i +"____________________");
//                         System.out.println(section);                    
//                    }else{
//                    	section = "";
//                    }
//                } catch (IOException e) {
//                	
//                }
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}	
//	
//	static String convertStreamToString(java.io.InputStream is) {
//	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
//	    return s.hasNext() ? s.next() : "";
//	}
}
