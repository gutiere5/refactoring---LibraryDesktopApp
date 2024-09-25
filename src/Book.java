import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Comparator;

public class Book{
	static List<Book> records = new ArrayList<Book>();

	int id;
	String title = null;
	String authors = null;
	String isbn = null;
	String pubYear = null;
	Double aveRating = null;

	Book(int id){
		this.id = id;
	}

	Book(String isbn){
		this.isbn = isbn;
	}

	Book(int id, String title, String authors, String isbn, String pubYear, Double aveRating){
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.isbn = isbn;
		this.pubYear = pubYear;
		this.aveRating = aveRating;

	}

	public static void importRecords() {
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
				Book book = new Book(
						Integer.parseInt(values[idIdx]), 
						values[titleIdx], 
						values[authorsIdx],
						values[isbnIdx],
						values[pubYearIdx],
						Double.parseDouble(values[aveRatingIdx])
						);

				// Add the newly created Book object to the 'records' list
				records.add(book);
			}

		} catch (Exception e) {
			// Print any exceptions that occur during file reading or parsing
			e.printStackTrace();
		}
	}

	// Prints the top ten book records
	public static Object[][] getTenRecords() {
		Object[][] bookRows = 
				records.stream().limit(10).map(Book::getBook).collect(Collectors.toList())
					.toArray(new Object[0][0]);
		return bookRows;

	}

	// Print all book records (for testing purposes)
	public static void printAllBooks() {
		for (Book book : Book.records) {
			printBook(book);
		}
	}
	
	public static Object[][] GetAllBooks(){
		Object[][] bookRows = 
				records.stream().map(Book::getBook).collect(Collectors.toList())
					.toArray(new Object[0][0]);
		return bookRows;
	}


	}
	static Book getBookByID(int id) {
		// binary search
		sortByIDAscending();
		int idx = Collections.binarySearch(records, new Book(id), Comparator.comparingInt(Book -> Book.id));
		Book search = records.get(idx);
		System.out.println(search.title);
		return search; //Collections.binarySearch(records, id);
	}

	static Book getBookByISBN(String isbn) {
		// binary search
		sortByISBNAscending();
		int idx = Collections.binarySearch(records, new Book(isbn), Comparator.comparing(Book -> Book.isbn));
		Book search = records.get(idx);
		System.out.println(search.title);
		return search; //Collections.binarySearch(records, id);
	}

	public static void sortByISBNAscending() {
		Collections.sort(records, Comparator.comparing(Book -> Book.isbn));
	}

	public static void sortByISBNDescending() {
		Collections.sort(records, Comparator.comparing(Book -> Book.isbn));
		Collections.reverse(records);
	}

	public static void sortByIDAscending() {
		Collections.sort(records, Comparator.comparingInt(Book -> Book.id));
	}

	public static void sortByIDDescending() {
		Collections.sort(records, Comparator.comparingInt(Book -> Book.id));
		Collections.reverse(records);
	}

	// Sorting by rating ascending
	public static void sortByRatingAscending() {
		Collections.sort(records, Comparator.comparingDouble(Book -> Book.aveRating));
	}

	public static void sortByRatingDescending() {
		//records.sort(Comparator.comparingDouble(Book -> Book.aveRating).reversed());
		Collections.sort(records, Comparator.comparingDouble(Book -> Book.aveRating));
		Collections.reverse(records);
	}
	// Sorting by title ascending
	public static void sortByTitleAscending() {
		Collections.sort(records, Comparator.comparing(Book -> Book.title));
	}

	// Sorting by title descending
	public static void sortByTitleDescending() {
		Collections.sort(records, Comparator.comparing(Book -> Book.title, Comparator.reverseOrder()));
	}

	// Sorting by publication year ascending
	public static void sortByPublicationYearAscending() {
		Collections.sort(records, Comparator.comparing(Book -> Book.pubYear));
	}

	// Sorting by publication year descending
	public static void sortByPublicationYearDescending() {
		Collections.sort(records, Comparator.comparing(Book -> Book.pubYear, Comparator.reverseOrder()));
	}

	public static String printBook(Book book) {
		System.out.println("ID: " + book.id
				+ ", Title: "   + book.title
				+ ", Authors: " + book.authors
				+ ", ISBN: "    + book.isbn 
				+ ", Year: " 	+ book.pubYear
				+ ", Rating: "  + book.aveRating);
		String bookInfo = ("ID: " + String.valueOf(book.id)
				+ ", Title: "   + book.title
				+ ", Authors: " + book.authors
				+ ", ISBN: "    + book.isbn 
				+ ", Year: " 	+ book.pubYear
				+ ", Rating: "  + String.valueOf(book.aveRating));
		return bookInfo;
	}
	
	public static Object[] getBook(Book book) {
		Object[] bookRow = {
				book.id,
				book.title,
				book.authors,
				book.isbn,
				book.pubYear,
				book.aveRating
		};
		return bookRow;
	}
}