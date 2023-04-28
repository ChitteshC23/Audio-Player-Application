// Name: Chittesh Chandranesan
// Student Number: 501196518

import java.util.ArrayList;

/*
 * An AudioBook is a type of AudioContent.
 * It is a recording made available on the internet of a book being read aloud by a narrator
 * 
 */
public class AudioBook extends AudioContent
{
	public static final String TYPENAME =	"AUDIOBOOK";
	
	private String author; 
	private String narrator;
	private ArrayList<String> chapterTitles;
	private ArrayList<String> chapters;
	private int currentChapter = 0;


	public AudioBook(String title, int year, String id, String type, String audioFile, int length,
									String author, String narrator, ArrayList<String> chapterTitles, ArrayList<String> chapters)
	{
		// Make use of the constructor in the super class AudioContent.
		// Initialize additional AudioBook instance variables.

		// Using the constructor in the super class to initialize the title, year, id, type, audioFile, & length of the audiobook
		super(title, year, id, type, audioFile, length);
		// Initializing the remaining instance variables of the audiobook (Author, narrator, chapterTitles, & chapters)
		this.author = author;
		this.narrator = narrator;
		this.chapterTitles = chapterTitles;
		this.chapters = chapters;
	}
	
	public String getType()
	{
		return TYPENAME;
	}

  // Print information about the audiobook. First print the basic information of the AudioContent
	// by making use of the printInfo() method in superclass AudioContent and then print author and narrator
	// see the video
	public void printInfo()
	{
		// Using printInfo() in the super class to print out the title, year, id, type, & length
		super.printInfo();
		// Printing the author and the narrator of the audiobook
		System.out.println("Author: " +author +" Narrated by: " +narrator);
	}
	
  // Play the audiobook by setting the audioFile to the current chapter title (from chapterTitles array list)
	// followed by the current chapter (from chapters array list)
	// Then make use of the the play() method of the superclass
	public void play()
	{
		// Calling the setAudioFile() method and passing through the current chapter title and the current chapter
		this.setAudioFile(chapterTitles.get(currentChapter) +".\n" +chapters.get(currentChapter) +"\n");
		// Executing the play() method in the super class (playing the audiobook)
		super.play();
	}
	
	// Print the table of contents of the book - i.e. the list of chapter titles
	// See the video
	public void printTOC()
	{
		// For loop to print the audiobook's chapter titles in the chapter titles arraylist
		for (int i = 0; i < chapterTitles.size(); i++){
			int index = i + 1;
			// Printing the chapter titles of the audiobook
			System.out.println("Chapter " +index +". " +chapterTitles.get(i) +"\n");
		}
	}

	// Select a specific chapter to play - nothing to do here
	public void selectChapter(int chapter)
	{
		if (chapter >= 1 && chapter <= chapters.size())
		{
			currentChapter = chapter - 1;
		}
	}
	//Two AudioBooks are equal if their AudioContent information is equal and both the author and narrators are equal
	public boolean equals(Object other)
	{
		// Casting the other object that is passed through to a AudioBook object (called a1)
		AudioBook a1 = (AudioBook) other;
		// If this audiobook (currently being used in the AudioBook class) has the same information as the other audiobook (a1), then return true
		// Using the equals method in the super class to check if the title, year, id, type, & length are the same
		if ((super.equals(a1)) && (this.author.equals(a1.author)) && (this.narrator.equals(a1.narrator))){
			return true;
		}
		// If the information of the 2 audiobooks aren't the same, then return false
		return false;
	}
	
	public int getNumberOfChapters()
	{
		return chapters.size();
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getNarrator()
	{
		return narrator;
	}

	public void setNarrator(String narrator)
	{
		this.narrator = narrator;
	}

	public ArrayList<String> getChapterTitles()
	{
		return chapterTitles;
	}

	public void setChapterTitles(ArrayList<String> chapterTitles)
	{
		this.chapterTitles = chapterTitles;
	}

	public ArrayList<String> getChapters()
	{
		return chapters;
	}

	public void setChapters(ArrayList<String> chapters)
	{
		this.chapters = chapters;
	}

}
