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
	private List<Book> records = new ArrayList<Book>();
	private int id;
	private String title = null;
	private String authors = null;
	private String isbn;
	private int pubYear;
	private Double aveRating = null;

	Book(){};

	Book(int id){this.id = id;}

	Book(String isbn) {this.isbn = isbn;}

	Book(int id, String title, String authors, String isbn, int pubYear, Double aveRating) {
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.isbn = isbn;
		this.pubYear = pubYear;
		this.aveRating = aveRating;
	}

	public String getIsbn()	{
		return this.isbn;
	}

	public int getId() {
		return this.id;
	}

	public void importRecords(int importLimit) {
		try (CSVReader csvReader  = new CSVReader(new FileReader("books.csv"))) {

			List<String[]> rows = csvReader.readAll();
			int recordsImported = 0;

			for (int i = 1; i < rows.size() && recordsImported < importLimit; i++) { 
				String[] values = rows.get(i);

				if (isValidRecord(values)) {
					try {
						getRecords().add(parseBook(values));
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


	private boolean isValidRecord(String[] values) {		   
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

	private Book parseBook(String[] values) {		    
		int bookId = Integer.parseInt(values[0]);
		String title = values[1].trim();
		String authors = values[2].trim();
		String isbn = values[3].trim();
		int publicationYear = Integer.parseInt(values[4]);
		double averageRating = Double.parseDouble(values[5]);


		return new Book(bookId, title, authors, isbn, publicationYear, averageRating);
	}


	public void sortByISBNAscending() {
		Collections.sort(getRecords(), Comparator.comparing(records -> records.isbn));
	}

	public void sortByISBNDescending() {
		Collections.sort(getRecords(), Comparator.comparing(records -> records.isbn));
		Collections.reverse(getRecords());
	}

	public void sortByIDAscending() {
		Collections.sort(getRecords(), Comparator.comparingInt(records -> records.id));
	}

	public void sortByIDDescending() {
		Collections.sort(getRecords(), Comparator.comparingInt(records -> records.id));
		Collections.reverse(getRecords());
	}

	// TODO This can be changed, look for refactoring techniques
	public  Object[] getBook() {
		Object[] bookRow = {
				this.id,
				this.title,
				this.authors,
				this.isbn,
				this.pubYear,
				this.aveRating
		};
		return bookRow;
	}

	// TODO Other classes should not manipulate this data, only the book class 
	public List<Book> getRecords() {
		return records;
	}

	public void setRecords(List<Book> records) {
		this.records = records;
	}

	public Object[][] GetAllBooks(){
		return 	getRecords().stream().map(Book::getBook).collect(Collectors.toList())
				.toArray(new Object[0][0]);

	}

	// Prints the top ten book records
	public Object[][] getTenRecords() {
		return getRecords().stream().limit(10).map(Book::getBook).collect(Collectors.toList())
				.toArray(new Object[0][0]);

	}
}
