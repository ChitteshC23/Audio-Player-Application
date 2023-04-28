// Name: Chittesh Chandranesan
// Student Number: 501196518

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args) throws FileNotFoundException {
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.println("Hello, Welcome to MyAudio App. How may I help you?" +
				"\nFor any help with the commands, please take a look at the command text file or you can type 'help' to list all the commands\n");
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();

			if (action == null || action.equals(""))
			{
				System.out.print("\n>");
				continue;
			} else if (action.equalsIgnoreCase("HELP")) {
				Scanner input = new Scanner(new File("commands-help.txt"));
				while (input.hasNextLine())
				{
					System.out.println(input.nextLine());
				}

			} else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT")) {
				System.out.println("Goodbye, have a great day!");
				return;
			}
			else if (action.equalsIgnoreCase("STORE"))	// List all songs
			{
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{
				mylibrary.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
			{
				mylibrary.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				mylibrary.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				mylibrary.listAllPlaylists(); 
			}
			try{
				// Download audiocontent (song/audiobook/podcast) from the store
				// Specify the index of the content
				 if (action.equalsIgnoreCase("DOWNLOAD")) {
					int fromIndex = 0;
					int toIndex = 0;
						// Asking the user to enter the start of the range of the content they want to download
						System.out.print("From Store Content #: ");
						// If the user entered a number, then take that number & store it in fromIndex
						if (scanner.hasNextInt()) {
							fromIndex = scanner.nextInt();
							scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
						}
						// Asking the user to enter the end of the range of the content they want to download
						System.out.print("To Store Content #: ");
						// If the user entered a number, then take that number & store it in toIndex
						if (scanner.hasNextInt()) {
							toIndex = scanner.nextInt();
							scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
						}

					// Iterate through the range of the contents the user wants to download
					for (int i = fromIndex; i <= toIndex; i++) {
						try {
							// Get the current content in the range & store it in a AudioContent object called content
							AudioContent content = store.getContent(i);
							// If the content is blank, that means the content isn't in the store
							if (content == null)
								System.out.println("Content Not Found in Store");
							// Passing through the content to the donwload() method in Library, so we can download current content
							mylibrary.download(content);
						// Catching if the content is already downloaded
						} catch (AlreadyDownloaded e){
							System.out.println(e.getMessage());
						}
					}
				}

				//Downloading content by artist/author
				else if (action.equalsIgnoreCase("DOWNLOADA")) {
					// Creating a string to store the artist/author the user wants to download
					String artist = "";
					// Asking the user to enter a artist/author
					System.out.print("Artist/Author: ");
					// If the user entered anything, then take that input & store it in the artist string
					if (scanner.hasNextLine()) {
						artist = scanner.nextLine();
					}
					// Creating a integer arraylist to get the arraylist that contains the integers of contents with that specified artist/author name
					ArrayList<Integer> artistIndices = store.DownloadA(artist);
					// Iterating through the integer arraylist to download each content
					for (int i = 0; i < artistIndices.size(); i++){
						try{
							mylibrary.download(store.getContent(artistIndices.get(i)+1));
						// Catching if the content has already been downloaded
						} catch (AlreadyDownloaded e){
							System.out.println(e.getMessage());
						}
					}

				}

				// Download content by genre
				else if (action.equalsIgnoreCase("DOWNLOADG")) {
					// Creating a string to store the genre the user wants to download
					String genre = "";
					// Asking the user to enter a genre
					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
					// If the user entered anything, then take that inout & store it in the genre string
					if (scanner.hasNextLine()) {
						genre = scanner.nextLine();
					}
					 // Creating a integer arraylist to get the arraylist that contains the integers of contents with that specified genre
					 ArrayList<Integer> genreIndices = store.DownloadG(genre);
					 // Iterating through the integer arraylist to download each content
					 for (int i = 0; i < genreIndices.size(); i++){
						 try{
							 mylibrary.download(store.getContent(genreIndices.get(i)+1));
						 // Catching if the content has already been downloaded
						 } catch (AlreadyDownloaded e){
							 System.out.println(e.getMessage());
						 }
					 }

				}

				// Get the *library* index (index of a song based on the songs list)
				// of a song from the keyboard and play the song
				if (action.equalsIgnoreCase("PLAYSONG")) {
					// Create an index variable to store the song number
					int index = 0;
					// Ask the user to enter a song number
					System.out.print("Song Number: ");
					// If the user entered an integer, then take that number and store it to index
					if (scanner.hasNextInt()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.playSong(index);

				}

				// Print the table of contents (TOC) of an audiobook that
				// has been downloaded to the library. Get the desired book index
				// from the keyboard - the index is based on the list of books in the library
				else if (action.equalsIgnoreCase("BOOKTOC")) {
					// Create an index variable to store the audio number
					int index = 0;
					// Ask the user to enter an audiobook number
					System.out.print("Audiobook Number: ");
					// If the user entered an integer, then take that number and store it to index
					if (scanner.hasNextInt()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.printAudioBookTOC(index);
				}

				// Similar to playsong above except for audiobook
				// In addition to the book index, read the chapter
				// number from the keyboard - see class Library
				else if (action.equalsIgnoreCase("PLAYBOOK")) {
					// Create an index variable to store the audio number and an int variable called chapter to store the chapter
					int index = 0;
					int chapter = 0;
					// Ask the user to enter an audio number
					System.out.print("Audio Book Number: ");
					// If the user entered an integer, then take that number and store it to index
					if (scanner.hasNextInt()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}
					// Ask the user to enter a chapter
					System.out.print("Chapter: ");
					// If the user entered an integer, then take that number and store it to chapter
					if (scanner.hasNextInt()) {
						chapter = scanner.nextInt();
						scanner.nextLine();
					}

					// Print error message if the song doesn't exist in the library
					mylibrary.playAudioBook(index, chapter);
				}

				// Specify a playlist title (string)
				// Play all the audio content (songs, audiobooks, podcasts) of the playlist
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYALLPL")) {
					// Create a string variable called title to store the title of the playlist
					String title = "";
					// Ask the user to enter a playlist title
					System.out.print("Playlist Title: ");
					// If the user entered something, then take that  and store it to title
					if (scanner.hasNext()) {
						title = scanner.next();
						scanner.nextLine();
					}
					// Print error message if the playlist doesn't exist in the library
					mylibrary.playPlaylist(title);
				}

				// Specify a playlist title (string)
				// Read the index of a song/audiobook/podcast in the playist from the keyboard
				// Play all the audio content
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPL")) {
					// Create a string variable called title to store the title of the playlist and an index variable to store the content number
					String title = "";
					int index = 0;
					// Ask the user to enter a playlist title
					System.out.print("Playlist Title: ");
					// If the user entered something, then take that  and store it to title
					if (scanner.hasNext()) {
						title = scanner.next();
						scanner.nextLine();
					}
					// Ask the user to enter a content number
					System.out.print("Content Number: ");
					// If the user entered an integer, then take that number and store it to index
					if (scanner.hasNextInt()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.playPlaylist(title, index);
				}

				// Delete a song from the list of songs in mylibrary and any play lists it belongs to
				// Read a song index from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELSONG")) {
					//Create an index variable to store the song number
					int index = 0;
					// Ask the user to enter a library song number
					System.out.print("Library Song #: ");
					// If the user entered an integer, then take that number and store it to index
					if (scanner.hasNextInt()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.deleteSong(index);
				}

				// Read a title string from the keyboard and make a playlist
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("MAKEPL")) {
					// Create a string variable called title to store the title of the playlist
					String title = "";
					// Ask the user to enter a playlist title
					System.out.print("Playlist Title: ");
					// If the user entered something, then take that and store it to title
					if (scanner.hasNext()) {
						title = scanner.next();
						scanner.nextLine();
					}
					mylibrary.makePlaylist(title);
				}

				// Print the content information (songs, audiobooks, podcasts) in the playlist
				// Read a playlist title string from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PRINTPL"))    // print playlist content
				{
					// Create a string variable called title to store the title of the playlist
					String title = "";
					// Ask the user to enter a playlist title
					System.out.print("Playlist Title: ");
					// If the user entered something, then take that and store it to title
					if (scanner.hasNext()) {
						title = scanner.next();
						scanner.nextLine();
					}
					mylibrary.printPlaylist(title);
				}

				// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
				// Read the playlist title, the type of content ("song" "audiobook" "podcast")
				// and the index of the content (based on song list, audiobook list etc) from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("ADDTOPL")) {
					// Create a string variable called title to a store the title of a playlist, another string variable called type to store the type of content, and an integer variable called index to store the library content number
					String title = "";
					String type = "";
					int index = 0;
					// Ask the user to enter a playlist title
					System.out.print("Playlist Title: ");
					// If the user entered something, then take that and store it to title
					if (scanner.hasNext()) {
						title = scanner.next();
						scanner.nextLine();
					}
					// Ask the user to enter a content type
					System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
					// If the user entered song, podcast or audiobook, then store what they entered into type
					if (scanner.hasNext()) {
						type = scanner.next();
						scanner.nextLine();
					}
					// Ask the user to enter a library content number
					System.out.print("Library Content #: ");
					// If the user entered an integer, then take that number and store it to index
					if (scanner.hasNextInt()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.addContentToPlaylist(type, index, title);
				}

				// Delete content from play list based on index from the playlist
				// Read the playlist title string and the playlist index
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELFROMPL")) {
					// Create a string variable called title to store the title of the playlist and an index variable to store the playlist content number
					String title = "";
					int index = 0;
					// Ask the user to enter a playlist title
					System.out.print("Playlist Title: ");
					// If the user entered something, then take that and store it into title
					if (scanner.hasNext()) {
						title = scanner.next();
						scanner.nextLine();
					}
					// Ask the user to enter a playlist content number
					System.out.print("Playlist Content #: ");
					// If the user entered an integer, then take that number and store it into index
					if (scanner.hasNextInt()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}
					mylibrary.delContentFromPlaylist(index, title);

				}

				else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
				{
					mylibrary.sortSongsByYear();
				}

				else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
				{
					mylibrary.sortSongsByName();
				}

				else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
				{
					mylibrary.sortSongsByLength();
				}

				// Searching for songs & audiobooks by title
				else if (action.equalsIgnoreCase("SEARCH")) {
					// Creating a string to store the title the user wants to search for
					String title = "";
					// Asking the user to enter a title
					System.out.print("Title: ");
					// If the user entered anything, then take that input & store it in the title string
					if (scanner.hasNextLine()) {
						title = scanner.nextLine();
					}
					// Passing the title the user entered to the Search() method in AudioCOContentStore
					store.Search(title);
				}

				// Searching for songs & audiobboks by artist/author
				else if (action.equalsIgnoreCase("SEARCHA")) {
					// Creating a string to store the artist/author the user wants to search for
					String artist = "";
					// Asking the user to enter a artist/author
					System.out.print("Artists/Author: ");
					// If the user entered anything, then take that input & store it in the artist string
					if (scanner.hasNextLine()) {
						artist = scanner.nextLine();
					}
					// Passing the artist/author the user entered to the SearchA() method in AudioContentStore
					store.SearchA(artist);
				}

				// Searching for songs by genre
				else if (action.equalsIgnoreCase("SEARCHG")) {
					// Creating a string to store the genre the user wants to search for
					String genre = "";
					// Asking the uset to enter a genre
					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
					// If the user entered anything, then take that input & store it in the genre string
					if (scanner.hasNextLine()) {
						genre = scanner.nextLine();

					}
					// Passing through the genre the user entered to the SearchG() method in AudioContentStore
					store.SearchG(genre);
				}
			// Catching if a NotFound exception is thrown, if so printing the message of that exception
			} catch (NotFound e) {
				System.out.println(e.getMessage());
			}

			// Catching if a AlreadyDownloaded exception is thrown, if so printing the message of that exception
			catch (AlreadyDownloaded e){
				System.out.println(e.getMessage());
			}

			// Catching if a AlreadyExists exception is thrown, if so printing the message of that exception
			catch (AlreadyExists e){
				System.out.println(e.getMessage());
			}

			System.out.print("\n>");
		}
	}
}
