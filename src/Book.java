import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

import java.util.Comparator;

public class Book{
	static List<Book> records = new ArrayList<Book>();

	int id;
	String title = null;
	String authors = null;
	String isbn;
	int pubYear;
	Double aveRating = null;

	Book(int id){
		this.id = id;
	}

	Book(String isbn) {
		this.isbn = isbn;
	}

	Book(int id, String title, String authors, String isbn, int pubYear, Double aveRating){
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.isbn = isbn;
		this.pubYear = pubYear;
		this.aveRating = aveRating;

	}

	public static void importRecords(int importLimit) {
		// Define the delimiter used in the CSV file (in this case, a comma)
		//String delimiter = ",";
		String delimiter = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

		// Try-with-resources: Automatically close the BufferedReader after use
		try (BufferedReader br = new BufferedReader(new FileReader("books.csv"))) {

			// Read the first line of the CSV, which contains the column headers
			String[] header = br.readLine().split(delimiter);

			// Initialize variables to store the column indices of the relevant fields
			int idIdx = 0;
			int titleIdx = 1;
			int authorsIdx = 2;
			int isbnIdx = 3;
			int pubYearIdx = 4;
			int aveRatingIdx = 5;

			String line;  // Variable to hold each line read from the CSV
			int i = 0;
			// Read each subsequent line of the CSV (skipping the header)
			while ((line = br.readLine()) != null && i <= importLimit) {
				// Split the line into values using the defined delimiter (comma)
				String[] values = line.split(delimiter);
				
				
				
				// Check if the essential fields are not empty before creating the Book object
				 if (isValidRecord(values, idIdx, titleIdx, isbnIdx, aveRatingIdx)) {
					try {
						Book book = parseBook(values, idIdx, titleIdx, authorsIdx, isbnIdx, pubYearIdx, aveRatingIdx);
						records.add(book);
					} catch (NumberFormatException e) {
						// Handle cases where numbers cannot be parsed (invalid book_id or average_rating)
						System.err.println("Skipping row due to invalid number format: " + Arrays.toString(values));
					} finally {
						// Only increment the import limit if the row was successfully added
						i++;					}
				} else {
					// Skip row if any required field is empty
					System.out.println("Skipping row due to empty required fields: " + Arrays.toString(values));
				}
			}
		} catch (Exception e) {
			// Print any exceptions that occur during file reading or parsing
			e.printStackTrace();
		}
	}

	
	private static boolean isValidRecord(String[] values, int idIdx, int titleIdx, int isbnIdx, int avgRatingIdx) {
	    return !values[idIdx].isEmpty() && !values[titleIdx].isEmpty() &&
	           !values[isbnIdx].isEmpty() && !values[avgRatingIdx].isEmpty();
	}

	private static Book parseBook(String[] values, int idIdx, int titleIdx, int authorsIdx, int isbnIdx, int pubYearIdx, int avgRatingIdx) {
	    int id = Integer.parseInt(values[idIdx]);
	    String title = values[titleIdx];
	    String authors = values[authorsIdx];
	    String isbn = values[isbnIdx];
	    int publicationYear = Integer.parseInt(values[pubYearIdx]);
	    double averageRating = Double.parseDouble(values[avgRatingIdx]);

	    return new Book(id, title, authors, isbn, publicationYear, averageRating);
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
