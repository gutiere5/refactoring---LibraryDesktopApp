import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class Book{
	static List<Book> records = new ArrayList<Book>();

	String id;
	String title;
	String authors;
	String isbn;
	String pubYear;
	Double aveRating;

	Book(String id, String title, String authors, String isbn, String pubYear, Double aveRating){
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.isbn = isbn;
		this.pubYear = pubYear;
		this.aveRating = aveRating;

	}



	public static void ImportRecords() {
		// Define the delimiter used in the CSV file (in this case, a comma)
		String delimiter = ",";

		// Try-with-resources: Automatically close the BufferedReader after use
		try (BufferedReader br = new BufferedReader(new FileReader("books.csv"))) {

			// Read the first line of the CSV, which contains the column headers
			String[] header = br.readLine().split(delimiter);

			// Initialize variables to store the column indices of the relevant fields
			int idIdx = 0;
			int titleIdx = 0;
			int authorsIdx = 0;
			int isbnIdx = 0;
			int pubYearIdx = 0;
			int aveRatingIdx = 0;

			// Loop through the header array to determine the indices of each column
			for (int i = 0; i < header.length; i++) {
				// Use a switch statement to match the column name to its corresponding index
				switch (header[i].trim()) {  // Use trim() to remove any surrounding spaces
				case "book_id":
					idIdx = i;  // Store the index of the "book_id" column
					break;
				case "title":
					titleIdx = i;  // Store the index of the "title" column
					break;
				case "authors":
					authorsIdx = i;  // Store the index of the "authors" column
					break;
				case "isbn":
					isbnIdx = i;  // Store the index of the "isbn" column
					break;
				case "original_publication_year":
					pubYearIdx = i;  // Store the index of the "original_publication_year" column
					break;
				case "average_rating":
					aveRatingIdx = i; // Store the index of the "average_rating" column
					break;
				}
			}

			String line;  // Variable to hold each line read from the CSV

			// Read each subsequent line of the CSV (skipping the header)
			while ((line = br.readLine()) != null) {
				// Split the line into values using the defined delimiter (comma)
				String[] values = line.split(delimiter);

				// Create a new Book object using the parsed values from the line
				// Use the previously determined column indices to map the correct values to the Book's fields
				Book book = new Book(values[idIdx], 
						values[titleIdx], 
						values[authorsIdx],
						values[isbnIdx],
						values[pubYearIdx],
						Double.parseDouble(values[aveRatingIdx]));

				// Add the newly created Book object to the 'records' list
				records.add(book);
			}

		} catch (Exception e) {
			// Print any exceptions that occur during file reading or parsing
			e.printStackTrace();
		}
	}

	static String GetBookByID(String id) {
		// binary search
		return null; //Collections.binarySearch(records, id);
	}
	static String GetBookByISBN(String isbn) {
		return null;
	}
	
	// Sorting by rating ascending
	public static void sortByRatingAscending() {
		Collections.sort(records, Comparator.comparingDouble(Book -> Book.aveRating));
	}

	// Sorting by title ascending
	public static void SortByTitleAscending() {
		Collections.sort(records, Comparator.comparing(book -> book.title));
	}

	// Sorting by title descending
	public static void SortByTitleDescending() {
		//TODO  Collections.sort(records, Comparator.comparing(book -> book.title).reversed());
	}

	// Sorting by publication year ascending
	public static void SortByPublicationYearAscending() {
		Collections.sort(records, Comparator.comparing(book -> book.pubYear));
	}

	// Sorting by publication year descending
	public static void SortByPublicationYearDescending() {
		//TODO  Collections.sort(records, Comparator.comparing(book -> book.pubYear).reversed());
	}
}