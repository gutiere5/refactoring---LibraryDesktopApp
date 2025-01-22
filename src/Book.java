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
		try (CSVReader csvReader  = new CSVReader(new FileReader("books.csv"))) {

			List<String[]> rows = csvReader.readAll();
			int recordsImported = 0;

			for (int i = 1; i < rows.size() && recordsImported < importLimit; i++) { 
				String[] values = rows.get(i);

				if (isValidRecord(values)) {
					try {
						records.add(parseBook(values));
						recordsImported++;
					} catch (NumberFormatException e) {
						System.err.println("Invalid number format, skipping row: " + Arrays.toString(values));
					}
				} else {
					System.out.println("Invalid or incomplete record, skipping row: " + Arrays.toString(values));
				} 

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static boolean isValidRecord(String[] values) {		   
		if (values.length < 6) {
			return false;
		}

		try {		    
			Integer.parseInt(values[0]);
			Integer.parseInt(values[4]);
			Double.parseDouble(values[5]); 

			return !values[1].trim().isEmpty() && 
					!values[2].trim().isEmpty() &&
					!values[3].trim().isEmpty(); 
		} catch (NumberFormatException e) {		       
			return false;
		}
	}

	private static Book parseBook(String[] values) {		    
		int bookId = Integer.parseInt(values[0]);
		String title = values[1].trim();
		String authors = values[2].trim();
		String isbn = values[3].trim();
		int publicationYear = Integer.parseInt(values[4]);
		double averageRating = Double.parseDouble(values[5]);


		return new Book(bookId, title, authors, isbn, publicationYear, averageRating);
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
