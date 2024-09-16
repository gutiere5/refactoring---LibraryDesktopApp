import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Book{
	static List<Book> records = new ArrayList<Book>();
	String id;
	String title;
	String authors;
	String isbn;
	String pubYear;
	Book(String id, String title, String authors, String isbn, String pubYear){
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.isbn = isbn;
		this.pubYear = pubYear;
	}
	
	public static void ImportRecords(){
		String delimiter = ",";
		try (BufferedReader br = new BufferedReader(new FileReader("books.csv"))) {
		    String[] header = br.readLine().split(delimiter);
		    int i = 0;
		    int idIdx = 0;
		    int titleIdx = 0;
		    int authorsIdx = 0;
		    int isbnIdx = 0;
		    int pubYearIdx = 0;
		    
		    for(String col : header) {
		    	switch(col) {
		    	case "book_id":
					idIdx = i;
					break;
		    	case "title":
		    		titleIdx = i;
		    		break;
		    	case "authors":
		    		authorsIdx = i;
		    		break;
		    	case "isbn":
					isbnIdx = i;
					break;
		    	case "original_publication_year":
					pubYearIdx = i;
					break;
		    	}
		    }
			String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(delimiter);
		        Book book = new Book(String.valueOf(header[idIdx]), header[titleIdx], header[authorsIdx], header[isbnIdx], header[pubYearIdx]);
		        records.add(book);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static String GetBookByID(String id) {
		// binary search
		return Collections.binarySearch(records, id);
	}
	static String GetBookByISBN(String isbn) {
		
	}
}