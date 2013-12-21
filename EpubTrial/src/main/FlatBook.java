package main;

import java.util.ArrayList;

public class FlatBook {
	
	private String authorName;
	private String bookName;
	private ArrayList<String> chapters = new ArrayList<String>();
	
	
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public ArrayList<String> getChapters() {
		return chapters;
	}
	public void addChapter(String chapter) {
		chapters.add(chapter);
	}
	public String getChapter(int index){
		return chapters.get(index);
	}
	public void updateChapter(int index, String chapter){
		chapters.set(0, chapter);
	}
}
