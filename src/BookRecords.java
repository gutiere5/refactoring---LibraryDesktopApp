import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;

public class BookRecords {
	private List<Book> records = new ArrayList<Book>();


	public void importRecords(int importLimit) {
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

	// TODO Other classes should not manipulate this data, only the book class 
	public List<Book> getRecords() {
		return records;
	}


	public Object[][] GetAllBooks(){
		return 	getRecords().stream().map(Book::getBook).collect(Collectors.toList())
				.toArray(new Object[0][0]);

	}

	// Prints the top ten book records
	public Object[][] getTenRecords() {
		if (this.records.isEmpty()) {
			return new Object[0][0];
		}
		return records.stream().limit(10).map(Book::getBook).collect(Collectors.toList())
				.toArray(new Object[0][0]);	
	}

	// Condense one class that does all sorting
	public void sortByISBNAscending() {
		Collections.sort(records, Comparator.comparing(records -> records.getIsbn()));
	}

	public void sortByISBNDescending() {
		Collections.sort(getRecords(), Comparator.comparing(records -> records.getIsbn()));
		Collections.reverse(getRecords());
	}

	public void sortByIDAscending() {
		Collections.sort(getRecords(), Comparator.comparingInt(records -> records.getId()));
	}

	public void sortByIDDescending() {
		Collections.sort(getRecords(), Comparator.comparingInt(records -> records.getId()));
		Collections.reverse(getRecords());
	}
}
