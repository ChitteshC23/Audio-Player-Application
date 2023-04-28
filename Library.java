// Name: Chittesh Chandranesan
// Student Number: 501196518

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks.
 */
public class Library
{
	private ArrayList<Song> 			songs;
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists;

  //private ArrayList<Podcast> 	podcasts;

	// Public methods in this class set errorMesg string
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	//String errorMsg = "";

	/*
	public String getErrorMessage()
	{
		return errorMsg;
	}
	 */

	public Library()
	{
		songs 		= new ArrayList<Song>();
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();
	  //podcasts		= new ArrayList<Podcast>(); ;
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 *
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	public void download(AudioContent content) {
		// Checking if the type of the content is a song
		if (content.getType().equals("SONG")) {
			// If so, cast the content that is passed through to a Song object (called s1)
			Song s1 = (Song) content;
			// Checking if the songs arraylist already contains the song, s1. If so, set the error message to "Song already downloaded" and return false
			if (songs.contains(s1)) {
				throw new AlreadyDownloaded("SONG " +s1.getTitle() +" already downloaded");
			// If not, add the song, s1, to the songs arraylist
			} else {
				songs.add(s1);
				System.out.println("SONG " +s1.getTitle() +" successfully added to Library");

			}
		// If the type of the content is not a song, then we need to check if the type of the content is a audiobook
		} else if (content.getType().equals("AUDIOBOOK")) {
			// If so, cast the content that is passed through to a AudioBook object (called a1)
			AudioBook a1 = (AudioBook) content;
			// Checking if the audiobooks arraylist already contains the audiobook, a1. If so, set the error message to "Audiobook already downloaded" and return false
			if (audiobooks.contains(a1)) {
				throw new AlreadyDownloaded("AUDIOBOOK " +a1.getTitle() +" already downloaded");
			// If not, add the audiobook, a1, to the audiobooks arraylist
			} else {
				audiobooks.add(a1);
				System.out.println("AUDIOBOOK " +a1.getTitle() +" successfully added to Library");

			}
		} else {
			// If the type of the content is neither a song nor an audiobook, then throw a NotFound exception with a message "Neither a song nor a audiobook"
			throw new NotFound("Neither a song nor a audiobook");
		}
	}

	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		// For loop to iterate over the songs arraylist and print each song in that arraylist by using printInfo()
		for (int i = 0; i < songs.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo();
			System.out.println();
		}
	}

	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		// For loop to iterate over the audiobooks arraylist and print each audiobook in that arraylist by using printInfo()
		for (int i = 0; i < audiobooks.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			audiobooks.get(i).printInfo();
			System.out.println();
		}
	}

  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
	}

  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		// For loop to iterate over the playlists arraylist and print each playlist's title
		for (int i = 0; i < playlists.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			//Printing/getting the current playlist's title
			System.out.print(playlists.get(i).getTitle());
			System.out.println();
		}
	}

  // Print the name of all artists.
	public void listAllArtists()
	{
		// First create a new (empty) array list of string
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names

		// Creating a new empty arraylist of string
		ArrayList<String> artists = new ArrayList<String>();
		// For loop to iterate over the songs arraylist and check if the artists arraylist doesn't have the current song artist
		// If so, add the current song artist to the artists arraylist
		for (int i = 0; i < songs.size(); i++){
			if(!(artists.contains(songs.get(i).getArtist()))){
				artists.add(songs.get(i).getArtist());
			}
		}
		// For loop to iterate over the artists arraylist and print each artist in that arraylist
		for (int j = 0; j < artists.size(); j++){
			System.out.println(j+1 +". " +artists.get(j));
		}
	}

	// Delete a song from the library (i.e. the songs list) -
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public void deleteSong(int index)
	{
			// Copying the song at the index that's passed through (called remove-song)
			Song remove_song = songs.get(index-1);
			// Removing that song that's wanted to be removed
			songs.remove(index -1);
			// For loops to iterate over the playlists arraylist and iterate over each content in that current playlist
			for (int i  = 0; i < playlists.size(); i++){
				for (int j = 0; j < playlists.get(i).getContent().size(); j++){
					// If the current content in the current playlist is equal to the copied song object, remove_song, then delete that content from that current playlist and return true
					if (playlists.get(i).getContent().get(j).equals(remove_song)){
						playlists.get(i).deleteContent(j+1);
					}
				}
			}
			throw new NotFound("SONG " +remove_song.getTitle() +" not found");
	}

  //Sort songs in library by year
	public void sortSongsByYear()
	{
		// Using collections sort to sort the year of the songs from the oldest song released to the newest song released
		Collections.sort(songs, new SongYearComparator());
		// For loop to iterate over the songs arraylist and print out each song by printInfo()
		for (int i = 0; i < songs.size(); i++) {
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo();
			System.out.println();
		}
	}
  	// Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song>
	{
		// Creating a compare method that compares the year of 2 songs by using the comparator interface
		public int compare(Song s1, Song s2){
			// If the year of the 2 songs are the same, then return 0
			if (s1.getYear() == s2.getYear()){
				return 0;
			// Otherwise, if the year of 1st song is greater than the 2nd song (meaning that the 1st song is the most recent), then return 1
			} else if (s1.getYear() > s2.getYear()){
				return 1;
			// If none of the above is true, that means the year 1st song is less than 2nd song (meaning that the 1st song is the oldest), so return -1
			} else {
				return -1;
			}
		}
	}

	// Sort songs by length
	public void sortSongsByLength() {
	 	// Use Collections.sort()
		Collections.sort(songs, new SongLengthComparator());
		// For loop to iterate over the songs arraylist and print out each song by using printInfo()
		for (int i = 0; i < songs.size(); i++){
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo();
			System.out.println();
		}

	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>
	{
		// Creating a compare method that compares the length of 2 songs by using the comparator interface
		public int compare(Song s1, Song s2){
			// If the length of the 2 songs are the same, then return 0
			if (s1.getLength() == s2.getLength()){
				return 0;
			// Otherwise, if the length of 1st song is greater than the 2nd song, then return 1
			} else if (s1.getLength() > s2.getLength()){
				return 1;
			// If none of the above is true, that means the length of 1st song is less than 2nd song, so return -1
			} else {
				return -1;
			}
		}
	}

	// Sort songs by title
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code

		//Collection sort to sort the names of the song in alphabetical order, which is done/used in the Song class
		Collections.sort(songs);
		// For loop to iterate over the songs arraylist, and print out each song by using printInfo()
		for (int i = 0; i < songs.size(); i++){
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo();
			System.out.println();
		}

	}



	/*
	 * Play Content
	 */

	// Play song from songs list
	public void playSong(int index)
	{
		// If the index is invalid, then throw NotFound execution with a message "SONG not found"
		if (index < 1 || index > songs.size())
		{
			throw new NotFound("SONG not found");
		}
		songs.get(index-1).play();
	}

	// Play podcast from list (specify season and episode)
	// Bonus
	public boolean playPodcast(int index, int season, int episode)
	{
		return false;
	}

	// Print the episode titles of a specified season
	// Bonus
	public boolean printPodcastEpisodes(int index, int season)
	{
		return false;
	}

	// Play a chapter of an audiobook from list of audiobooks
	public void playAudioBook(int index, int chapter)
	{
		// If the index that's passed through is invalid, then throw NotFound exception with a message "AUDIOBOOK not found"
		if (index <= 0 || index > audiobooks.size()){
			throw new NotFound("AUDIOBOOK not found");
		}
		// If not, then select the chapter to the chapter that's passed through of the audiobook at that index, play that audiobook by using play(), & return true
		audiobooks.get(index-1).selectChapter(chapter);
		audiobooks.get(index-1).play();
	}

	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index)
	{
		// If the index that's passed through is invalid, then throw NotFound exception with a message "AUDIOBOOK not found"
		if (index <= 0 || index > audiobooks.size()){
			throw new NotFound("AUDIOBOOK not found");
		}
		// If not, then print the table of contents of the audiobook at that index that is passed through by using printTOC()
		audiobooks.get(index-1).printTOC();
	}

  /*
   * Playlist Related Methods
   */

	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title)
	{
		// Creating a playlist object with the title that's passed through (called p1)
		Playlist p1 = new Playlist(title);
		// If the playlists arraylists doesn't already contain p1, then add it to the playlists arraylist and return true
		if(!(playlists.contains(p1))){
			playlists.add(p1);
		}
		// If not, throw AlreadyExists exception with a message "Playlist (playlist title) already exists"
		else{
			throw new AlreadyExists("Playlist " +p1.getTitle() +" already exists");
		}
	}

	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title)
	{
		// Creating a playlist object with the title that's passed through (called p1)
		Playlist p1 = new Playlist(title);
		// If the playlists arraylists already contains p1, then create new playlist object (p2) and get the index of p1 and set it to that index in the playlist arraylist, print the contents of p2 by using printContents(), & return true
		if(playlists.contains(p1)){
			Playlist p2 = playlists.get(playlists.indexOf(p1));
			p2.printContents();
		} else {
			//If not, throw NotFound exception with a message "Playlist (playlist title) not found"
			throw new NotFound("Playlist " + p1.getTitle() + " not found");
		}
	}

	// Play all content in a playlist
	public void playPlaylist(String playlistTitle)
	{
		// Creating a playlist object with the title that's passed through (called p1)
		Playlist p1 = new Playlist(playlistTitle);
		// If the playlists arraylists already contains p1, then create new playlist object (p2) and get the index of p1 and set it to index in the playlist arraylist, play all the contents in p2 by using playAll(), & return true
		if (playlists.contains(p1)){
			Playlist p2 = playlists.get(playlists.indexOf(p1));
			p2.playAll();
		} else {
			//If not, throw NotFound exception with a message "Playlist (playlist title) not found"
			throw new NotFound("Playlist " + p1.getTitle() + " not found");
		}
	}

	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL)
	{
		// Creating a playlist object with the title that's passed through (called p1)
		Playlist p1 = new Playlist(playlistTitle);
		// If the playlists arraylists already contains p1, then create new playlist object (p2) and get the index of p1 and set it to index in the playlist arraylist
		if (playlists.contains(p1)){
			Playlist p2 = playlists.get(playlists.indexOf(p1));
			// If the p2 contains that index that's passed through, then get the audio content at that index and play it by using play() and return true
			if (p2.contains(indexInPL)){
				p2.getContent().get(indexInPL - 1).play();
			}
		} else {
			//If not, throw NotFound exception with a message "Playlist (playlist title) not found"
			throw new NotFound("Playlist " + p1.getTitle() + " not found");
		}
	}

	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle)
	{
		// Creating a playlist object with the title that's passed through (called p1)
		Playlist p1 = new Playlist(playlistTitle);
		// Checking if the playlists arraylists already contains p1
		if (playlists.contains(p1)){
			// Then, checking if the type is a song
			if (type.equalsIgnoreCase(Song.TYPENAME)){
				// Then, if the index is valid then  create new playlist object (p2) and get the index of p1 and set it to index in the playlist arraylist, get that song at that index and add it to the playlist, & return true
				if (index > 0 && index < songs.size()+1) {
					Playlist p2 = playlists.get(playlists.indexOf(p1));
					p2.addContent(songs.get(index - 1));
				}
			}
			// If the type is not a song, then we need to check if the type is a audiobook
			if (type.equalsIgnoreCase(AudioBook.TYPENAME)){
				// Then, if the index is valid then create new playlist object (p3) and get the index of p1 and set it to index in the playlist arraylist, get that audiobook at that index and add it to the playlist, & return true
				if (index > 0 && index < audiobooks.size()+1){
					Playlist p3 = playlists.get(playlists.indexOf(p1));
					p3.addContent(audiobooks.get(index - 1));
				}
			}
		}
		else {
			// If none of the above returns true, then set the error message to "Playlist " (title of the playlist) +" Already Exists" and return false
			throw new NotFound("Playlist " + p1.getTitle() + " not found");
		}
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid
	public void delContentFromPlaylist(int index, String title)
	{
		// Creating a playlist object with the title that's passed through (called p1)
		Playlist p1 = new Playlist(title);
		// If the playlists arraylists already contains p1, then create new playlist object (p2) and get the index of p1 and set it to index in the playlist arraylist
		if (playlists.contains(p1)){
				Playlist p2 = playlists.get(playlists.indexOf(p1));
				// Then, check if that playlist contains that index, if so delete that content at that index from the playlist and return true
				if (p2.contains(index)){
					p2.deleteContent(index);
			}
		}
		else {
			//If not, throw NotFound exception with a message "Playlist (playlist title) not found"
			throw new NotFound("Playlist " + p1.getTitle() + " not found");
		}
	}
}

// Creating a custom exception if the content is already downloaded
class AlreadyDownloaded extends RuntimeException{
	public AlreadyDownloaded(String msg){
		super(msg);
	}
}

// Creating a custom exception if the content or playlist cannot be found
class NotFound extends RuntimeException{
	public NotFound (String msg){
		super(msg);
	}
}

// Creating a custom exception if the content or the playlist already exists
class AlreadyExists extends RuntimeException{
	public AlreadyExists (String msg){
		super(msg);
	}
}


