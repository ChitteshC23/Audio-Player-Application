// Name: Chittesh Chandranesan
// Student Number: 501196518

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{
		private ArrayList<AudioContent> contents;

		// Creating a HashMap to map the title of each audio content
		private HashMap<String, Integer> title_map = new HashMap<>();

		// Creating a HashMap to map the artist of each audio content
		public static HashMap<String, ArrayList<Integer>> artist_map = new HashMap<>();

		// Creating a HashMap to map the genre of each audio content
		public static HashMap<String, ArrayList<Integer>> genre_map = new HashMap<>();
		
		public AudioContentStore()
		{
			contents = new ArrayList<AudioContent>();
			// Reading & gathering the information about content in the store.txt file
			try {
				// Calling a private method called "readFile" that contains all the information/code to read the store.txt file
				readFile();
			// Catching if the file doesn't exist or if the file cannot be found
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

			// Adding the title of each content in the contents arraylist to the title map
			// Iterating through the contents arraylist to add the current content title & the current index of the contents arraylist to title map
			for (int  i = 0; i < contents.size(); i++){
				title_map.put(contents.get(i).getTitle(), i);
			}

			// Adding the artist/author of each content in the contents arraylist to the artist map
			// Iterating through the contents arraylist
			for (int  i = 0; i < contents.size(); i++){
				// Checking if the current content type is a SONG
				if(contents.get(i).getType() == Song.TYPENAME) {
					// Casting the current content to a SONG
					Song s1 = (Song) contents.get(i);

					/* Checking if the artist map already contains the artist of the current song
					 * If so, get the arraylist assigned to the current song artist
					 * Add the current index of the contents arraylist to the arraylist
					 * Then, add the artist of the current song & the arraylist to artist map
					 */
					if (artist_map.containsKey(s1.getArtist())){
						ArrayList<Integer> indices_1 = artist_map.get(s1.getArtist());
						indices_1.add(i);
						artist_map.put(s1.getArtist(), indices_1);

					/* If not, that means the artist hasn't yet been added to the artist map
					 * So, create an arraylist to store the spots in the contents arraylist that contains the artist name
					 * Add the artist of the current song & the arraylist to artist map
					 * Then, add the current index in the content arraylist to the  arraylist
					 */
					} else{
						ArrayList<Integer> indices_2 = new ArrayList<Integer>();
						artist_map.put(s1.getArtist(), indices_2);
						indices_2.add(i);
					}
				// If the current content type is not a SONG, that means the current content type must be a AUDIOBOOK
				// Checking to make sure the current content type is a AUDIOBOOK
				} else if (contents.get(i).getType() == AudioBook.TYPENAME){
					// Casting the current content to a AUDIOBOOK
					AudioBook a1 = (AudioBook) contents.get(i);

					/* Checking if the artist map already contains the author of the current audiobook as a key
					 * If so, get the arraylist assigned to the current audiobook author
					 * Add the current index of the contents arraylist to the indices arraylist
					 * Then, add the author of the current audiobook & the arraylist to artist map
					 */
					if (artist_map.containsKey(a1.getAuthor())){
						ArrayList<Integer> indices_1 = artist_map.get(a1.getAuthor());
						indices_1.add(i);
						artist_map.put(a1.getAuthor(), indices_1);

					/* If not, that means the author hasn't yet been added to the artist map
					 * So, create an arraylist to store the spots in the contents arraylist that contains the author name
					 * Add the author of the current audiobook & the indices arraylist to artist map
					 * Then, add the current index in the content arraylist to the indices arraylist
					 */
					} else{
						ArrayList<Integer> indices_2 = new ArrayList<Integer>();
						artist_map.put(a1.getAuthor(), indices_2);
						indices_2.add(i);
					}
				}
			}

			// Adding the genre of each content in the contents arraylist to the genre map
			// Iterating through the contents arraylist
			for (int  i = 0; i < contents.size(); i++) {
				// Checking if the current content type is a SONG
				if (contents.get(i).getType() == Song.TYPENAME) {
					// Casting the current content to a SONG
					Song s1 = (Song) contents.get(i);

					/* Checking if the genre map already contains the genre of the current song as a key
					 * If so, get the arraylist assigned to the current song genre
					 * Add the current index of the contents arraylist to the indices arraylist
					 * Then, add the genre of the current song & the indices arraylist to genre map
					 */
					if (genre_map.containsKey(String.valueOf(s1.getGenre()))) {
						ArrayList<Integer> indices_1 = genre_map.get(String.valueOf(s1.getGenre()));
						indices_1.add(i);
						genre_map.put(String.valueOf(s1.getGenre()), indices_1);

					/* If not, that means the genre hasn't yet been added to the genre map
					 * So, create an arraylist to store the spots in the contents arraylist that contains the genre
					 * Add the genre of the current song & the indices arraylist to genre map
					 * Then, add the current index in the content arraylist to the indices arraylist
					 */
					} else {
						ArrayList<Integer> indices_2 = new ArrayList<Integer>();
						genre_map.put(String.valueOf(s1.getGenre()), indices_2);
						indices_2.add(i);
					}
				}
			}
					
		}

		// Private method to read the file, gather all the information, store the information accordingly, & return the arraylist that contains all the audio content from the file
		private ArrayList<AudioContent> readFile () throws FileNotFoundException {
			// Creating a file that takes in the store.txt file
			File file = new File("store.txt");
			// Creating a scanner to read through the file
			Scanner myReader = new Scanner(file);
			// Looping through each line of the text file
			while (myReader.hasNextLine()) {
				// The 1st line of the text file indicates the type of content
				// Creating a string called "type" to store the next line, in this case the 1st line, of the text file
				String type = myReader.nextLine();
				// Checking if the type is a SONG
				if (type.equals(Song.TYPENAME)){
					// The 2nd – 8th lines contain the strings: id, title, year, length, artist, composer, genre
					// Creating a string called "id" & store the id of the song (which is the next line after the type)
					String id = myReader.nextLine();
					// Creating a string called "title" & store the title of the song (which is the next line after the id)
					String title = myReader.nextLine();
					// Creating an integer called "year" & store the year of the song (which is the line after the title)
					// Converting the line we are currently reading in the scanner to an integer
					int year = Integer.parseInt(myReader.nextLine());
					// Creating an integer called "length" & store the length of the song (which is the next line after the year)
					// Converting the line we are currently reading in the scanner to an integer
					int length = Integer.parseInt(myReader.nextLine());
					// Creating a string called "artist" & store the artist of the song (which is the next line after the length)
					String artist = myReader.nextLine();
					// Creating a string called "composer" & store the composer of the song (which is the next line after the artist)
					String composer = myReader.nextLine();
					// Creating genre & store the genre of the song (which is the next line after the composer)
					Song.Genre genre = Song.Genre.valueOf(myReader.nextLine());
					// Creating an integer called "numLines" & store the number of lines of the lyrics in the song (which is the next line after the composer)
					// Converting the line we are currently reading in the scanner to an integer
					int numLines = Integer.parseInt(myReader.nextLine());
					// Creating a string called "lyrics" to store the lyrics of the song
					String lyrics = "";
					// Iterating over the next few lines to get lyrics & add/store it to the string
					for (int i = 0; i < numLines; i++){
						lyrics = lyrics + myReader.nextLine() +"\n";
					}
					// Creating a string called "audioFile" which is basically the lyrics of the songs
					String audioFile = lyrics;
					// Adding the song with all the attributes of a song to the contents
					contents.add(new Song(title, year, id, type, audioFile, length, artist, composer, genre, lyrics));
				// If the type isn't a SONG, that means the type must be a AUDIOBOOK
				// Checking to make sure the type is a AUDIOBOOK
				} else if (type.equals(AudioBook.TYPENAME)){
					// The 2nd – 7th lines contain the strings: id, title, year, length, author, narrator
					// Creating a string called "id" & store the id of the song (which is the next line after the type)
					String id = myReader.nextLine();
					// Creating a string called "title" & store the title of the song (which is the next line after the id)
					String title = myReader.nextLine();
					// Creating an integer called "year" & store the year of the song (which is the line after the title)
					// Converting the line we are currently reading in the scanner to an integer
					int year = Integer.parseInt(myReader.nextLine());
					// Creating an integer called "length" & store the length of the song (which is the next line after the year)
					// Converting the line we are currently reading in the scanner to an integer
					int length = Integer.parseInt(myReader.nextLine());
					// Creating a string called "author" & store the author of the audiobook (which is the next line after the length)
					String author = myReader.nextLine();
					// Creating a string called "narrator" & store the narrator of the audiobook (which is the next line after the author)
					String narrator = myReader.nextLine();
					// Creating an integer called "numChapters" & store the number of chapters in the audiobook (which is the next line after the composer)
					// Converting the line we are currently reading in the scanner to an integer
					int numChapters = Integer.parseInt(myReader.nextLine());
					// Creating a string arraylist to store the chapter titles
					ArrayList<String> chapterTitles = new ArrayList<String>();
					ArrayList<String> chapterLines = new ArrayList<String>();
					// Iterating through the next few lines to get chapter title & add it to the chapterTitles arraylist
					for (int i  = 0; i < numChapters; i++) {
						chapterTitles.add(myReader.nextLine());
						// Creating an integer called "numLines" & store the number of lines of a chapter (which is the next line after the chapter title)
					}
					// Iterating through the next few lines to get the chapter line & add it to the chapterLines arraylist
					for (int i = 0; i < chapterTitles.size(); i++) {
						int numLines = Integer.parseInt(myReader.nextLine());
						for (int j = 0; j < numLines; j++){
							chapterLines.add(myReader.nextLine() +"\r\n");
						}
					}
					// Adding the audiobook  with all the attributes of an audiobook to the content
					contents.add(new AudioBook(title, year, id, type, "", length, author, narrator, chapterTitles, chapterLines));
				}
			}
			// Closing the scanner
			myReader.close();
			// Returning the content arraylist that contains all the audio contents from the store.txt file
			return contents;
		}

		// Search Title
		public void Search(String title){
			/* Checking if the title map contains the title name that's passed through
			 * If so, create a integer that will store the value of the title from the title map
			 * Print the index of this content & then print the info for this content by using .printInfo()
			 * If not, that means the title cannot be found, so throw a NotFound exception with a message "No matches for (title)"
			 */
			if (title_map.containsKey(title)){
				int i = title_map.get(title);
				System.out.println(i+1 +" ");
				contents.get(i).printInfo();
			} else{
				throw new NotFound("No matches for " + title);
			}
		}

		//Search Artist
		public void SearchA(String artist){
			/* Checking if the artist map contains the artist name that's passed through
			 * If so, create a temporary arraylist to store the value of the artist from the artist map
			 * Iterate over this temporary arraylist & print out the current integer from the temporary arraylist
			 * Then, get the current content at the current integer of the temporary list & print the info of it by using .printInfo()
			 * If not, that means the artist/author cannot be found, so throw a NotFound exception with a message "No matches for (artist)"
			 */
			if (artist_map.containsKey(artist)){
				ArrayList<Integer> temp = new ArrayList<>(artist_map.get(artist));
				for (int i = 0; i < temp.size(); i++){
					System.out.println(temp.get(i)+1 +" ");
					contents.get(temp.get(i)).printInfo();
				}
			} else{
				throw new NotFound("No matches for " + artist);
			}

		}

		//Search Genre
		public void SearchG(String genre) {
			/* Checking if the genre map contains the genre name that's passed through
			 * If so, create a temporary arraylist to store the value of the genre from the genre map
			 * Iterate over this temporary arraylist & print out the current integer from the temporary arraylist
			 * Then, get the current content at the current integer of the temporary list & print the info of it by using .printInfo()
			 * If not, that means the genre cannot be found, so throw a NotFound exception with a message "No matches for (genre)"
			 */
			if (genre_map.containsKey(genre)) {
				ArrayList<Integer> temp = new ArrayList<>(genre_map.get(genre));
				for (int i = 0; i < temp.size(); i++) {
					System.out.println(temp.get(i)+1 + " ");
					contents.get(temp.get(i)).printInfo();
				}
			} else{
				throw new NotFound("No matches for " + genre);
			}
		}

		// Return the arraylist that contains the integers of contents with the specified artist/author
		public ArrayList<Integer> DownloadA (String artist){
			return artist_map.get(artist);
		}

		// Return the arraylist that contains the integers of contents with the specified genre
		public ArrayList<Integer> DownloadG (String genre){
			return genre_map.get(genre);
		}

		public AudioContent getContent(int index)
		{
			if (index < 1 || index > contents.size())
			{
				return null;
			}
			return contents.get(index-1);
		}
		
		public void listAll()
		{
			for (int i = 0; i < contents.size(); i++)
			{
				int index = i + 1;
				System.out.print("" + index + ". ");
				contents.get(i).printInfo();
				System.out.println();
			}
		}
		
		private ArrayList<String> makeHPChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("The Riddle House");
			titles.add("The Scar");
			titles.add("The Invitation");
			titles.add("Back to The Burrow");
			return titles;
		}
		
		private ArrayList<String> makeHPChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("In which we learn of the mysterious murders\r\n"
					+ " in the Riddle House fifty years ago, \r\n"
					+ "how Frank Bryce was accused but released for lack of evidence, \r\n"
					+ "and how the Riddle House fell into disrepair. ");
			chapters.add("In which Harry awakens from a bad dream, \r\n"
					+ "his scar burning, we recap Harry's previous adventures, \r\n"
					+ "and he writes a letter to his godfather.");
			chapters.add("In which Dudley and the rest of the Dursleys are on a diet,\r\n"
					+ " and the Dursleys get letter from Mrs. Weasley inviting Harry to stay\r\n"
					+ " with her family and attend the World Quidditch Cup finals.");
			chapters.add("In which Harry awaits the arrival of the Weasleys, \r\n"
					+ "who come by Floo Powder and get trapped in the blocked-off fireplace\r\n"
					+ ", blast it open, send Fred and George after Harry's trunk,\r\n"
					+ " then Floo back to the Burrow. Just as Harry is about to leave, \r\n"
					+ "Dudley eats a magical toffee dropped by Fred and grows a huge purple tongue. ");
			return chapters;
		}
		
		private ArrayList<String> makeMDChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("Loomings.");
			titles.add("The Carpet-Bag.");
			titles.add("The Spouter-Inn.");
			return titles;
		}
		private ArrayList<String> makeMDChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("Call me Ishmael. Some years ago never mind how long precisely having little\r\n"
					+ " or no money in my purse, and nothing particular to interest me on shore,\r\n"
					+ " I thought I would sail about a little and see the watery part of the world.");
			chapters.add("stuffed a shirt or two into my old carpet-bag, tucked it under my arm, \r\n"
					+ "and started for Cape Horn and the Pacific. Quitting the good city of old Manhatto, \r\n"
					+ "I duly arrived in New Bedford. It was a Saturday night in December.");
			chapters.add("Entering that gable-ended Spouter-Inn, you found yourself in a wide, \r\n"
					+ "low, straggling entry with old-fashioned wainscots, \r\n"
					+ "reminding one of the bulwarks of some condemned old craft.");
			return chapters;
		}
		
		private ArrayList<String> makeSHChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("Prologue");
			titles.add("Chapter 1");
			titles.add("Chapter 2");
			titles.add("Chapter 3");
			return titles;
		}
		
		private ArrayList<String> makeSHChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("The gale tore at him and he felt its bite deep within\r\n"
					+ "and he knew that if they did not make landfall in three days they would all be dead");
			chapters.add("Blackthorne was suddenly awake. For a moment he thought he was dreaming\r\n"
					+ "because he was ashore and the room unbelieveable");
			chapters.add("The daimyo, Kasigi Yabu, Lord of Izu, wants to know who you are,\r\n"
					+ "where you come from, how ou got here, and what acts of piracy you have committed.");
			chapters.add("Yabu lay in the hot bath, more content, more confident than he had ever been in his life.");
			return chapters;
		}

}
