// Name: Chittesh Chandranesan
// Student Number: 501196518

import java.util.Comparator;

/*
 * A Song is a type of AudioContent. A Song has extra fields such as Artist (person(s) singing the song) and composer 
 */
public class Song extends AudioContent implements Comparable<Song>// implement the Comparable interface
{
	public static final String TYPENAME =	"SONG";
	
	public static enum Genre {POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL}; 
	private String artist; 		// Can be multiple names separated by commas
	private String composer; 	// Can be multiple names separated by commas
	private Genre  genre; 
	private String lyrics;
	
	
	
	public Song(String title, int year, String id, String type, String audioFile, int length, String artist,
			String composer, Song.Genre genre, String lyrics)
	{
		// Make use of the constructor in the super class AudioContent.
		// Initialize additional Song instance variables.

		// Using the constructor in the super class to initialize the title, year, id, type, audioFile, & length of the audiobook
		super(title, year, id, type, audioFile, length);
		// Initializing the additional instance variables of the song (Artist, Composer, Genre, & Lyrics)
		this.artist = artist;
		this.composer = composer;
		this.genre = genre;
		this.lyrics = lyrics;
	}
	
	public String getType()
	{
		return TYPENAME;
	}
	
	// Print information about the song. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print artist, composer, genre
	public void printInfo()
	{
		// Using printInfo() in the super class to print out the title, year, id, type, & length
		super.printInfo();
		// Printing the artist, composer, & the genre of the song
		System.out.println("Artist: " +artist +" Composer: " +composer +" Genre: " +genre);
	}
	
	// Play the song by setting the audioFile to the lyrics string and then calling the play() method of the superclass
	public void play()
	{
		// Calling the setAudioFile() method and passing through the lyrics of the song
		setAudioFile(lyrics +"\n");
		// Using the play() method in the super class to play the song
		super.play();
	}
	
	public String getComposer()
	{
		return composer;
	}
	public void setComposer(String composer)
	{
		this.composer = composer;
	}
	
	public String getArtist()
	{
		return artist;
	}
	public void setArtist(String artist)
	{
		this.artist = artist;
	}
	
	public String getLyrics()
	{
		return lyrics;
	}
	public void setLyrics(String lyrics)
	{
		this.lyrics = lyrics;
	}

	public Genre getGenre()
	{
		return genre;
	}

	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}	
	
	// Two songs are equal if their AudioContent information is equal and both the composer and artists are the same
	// Make use of the superclass equals() method
	public boolean equals(Object other)
	{
		// Casting the other object that is passed through to a Song object (called s1)
		Song s1 = (Song) other;
		// If the current song has the same information as the other audiobook (s1), then return true
		// Using the equals method in the super class to check if the title, year, id, type, & length are the same
		if ((super.equals(s1)) && (this.composer.equals(s1.composer)) && (this.artist.equals(s1.artist))){
			return true;
		}
		// If the information of the 2 songs aren't the same, then return false
		return false;
	}
	
	// Implement the Comparable interface 
	// Compare two songs based on their title
	// This method will allow songs to be sorted alphabetically
	public int compareTo(Song other)
	{
		// Comparing the titles of the current song & the other song that is passed through
		if (this.getTitle().compareTo(other.getTitle()) == 0){
			return 0;
		} else if (this.getTitle().compareTo(other.getTitle()) > 0){
			return 1;
		}
		return -1;
	}
}
