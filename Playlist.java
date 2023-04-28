// Name: Chittesh Chandranesan
// Student Number: 501196518

import java.lang.reflect.Type;
import java.util.ArrayList;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist
{
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture
	
	public Playlist(String title)
	{
		this.title = title;
		contents = new ArrayList<AudioContent>();
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void addContent(AudioContent content)
	{
		contents.add(content);
	}
	
	public ArrayList<AudioContent> getContent()
	{
		return contents;
	}

	public void setContent(ArrayList<AudioContent> contents)
	{
		this.contents = contents;
	}
	
	/*
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio content object
	 * Make sure the index starts at 1
	 */
	public void printContents()
	{
		// For to loop to iterate over the contents arraylist and print the information of the current audio content object
		for (int i = 0; i < contents.size(); i++){
			int index = i + 1;
			// Printing the index of the object
			System.out.print(index +". ");
			// Getting the current content object in the contents arraylist and using printInfo() to print the information of the content object
			contents.get(i).printInfo();
			System.out.println();
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll()
	{
		// For loop to iterate over the contents arraylist and play all the audio content in that list
		for (int i = 0; i < contents.size(); i++){
			// Getting the current content object in the contents arraylist and using play() to play the content object
			contents.get(i).play();
		}
	}
	
	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range. 
	public void play(int index)
	{
		// Checking if the index passed through is valid (a non-negative number)
		if (index > 0){
			contents.get(index-1).play();
		}
	}
	
	public boolean contains(int index)
	{
		return index >= 1 && index <= contents.size();
	}
	
	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other)
	{
		// Casting the other object that is passed through to a Song object (called s1)
		Playlist p1 = (Playlist) other;
		// If the current playlist has the same title as the other audiobook (p1), then return true
		if (this.title.equals(p1.title)){
			return true;
		}
		// If the 2 playlist don't have the same title, then return false
		return false;
	}
	
	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index)
	{
		if (!contains(index)) return;
		contents.remove(index-1);
	}
	
	
}
