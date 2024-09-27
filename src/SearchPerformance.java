import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class SearchPerformance {
	private static long startTime;
	private static long endTime;
	public static long elapsedTime;
	
	static int linearSearch(String ISBN){
		int result = -1;
		for (int i = 0; i < Book.records.size(); i++) {
			if(Book.records.get(i).isbn.equals(ISBN)) {
				result = i;
				break;
			}
		}
		return result;
	}

	static int linearSearch(int id) {
		int result = -1;
		for (int i = 0; i < Book.records.size(); i++) {
			if(Book.records.get(i).id == id) {
				result = i;
				break;
			}
		}
		return result;
	}
	
		// Prints the top ten book records
		public static Object[][] getTenRecords() {
			Object[][] bookRows = 
					Book.records.stream().limit(10).map(Book::getBook).collect(Collectors.toList())
					.toArray(new Object[0][0]);
			return bookRows;
		}

		public static Object[][] GetAllBooks(){
			Object[][] bookRows = 
					Book.records.stream().map(Book::getBook).collect(Collectors.toList())
					.toArray(new Object[0][0]);
			return bookRows;
		}

		public static Object[][] getBookByID(int id) {
			try {
				// Sort by ID before performing binary search
				Book.sortByIDAscending();
				
				// Record Start Time
				startTime = System.nanoTime();
				
				// Perform binary search
				int idx = Collections.binarySearch(Book.records, new Book(id), Comparator.comparingInt(book -> book.id));

				//Record the end time
				endTime = System.nanoTime();
				
				// Calculate the elapsed time in miliseconds
				elapsedTime = endTime - startTime;
				
				// Check if the book was found (binarySearch returns a negative value if not found)
				if (idx < 0 && Gui.softSearch == false) {
					JOptionPane.showMessageDialog(null,"Book with ID " + id + " not found.");
				}

				// Find book
				Book search = Book.records.get(idx);

				// Convert Book to Object[] and wrap in Object[][]
				Object[][] bookRow = { Book.getBook(search) };
				return bookRow;

			} 
			catch (IndexOutOfBoundsException e) {
				return new Object[0][0]; // Return empty array if book not found
			} 
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				return new Object[0][0]; // Return empty array if any other error occurs
			}
		}

		static Object[][] getBookByISBN(String isbn) {
			try {
				// Sort by ISBN before performing binary search	
				Book.sortByISBNAscending();

				// Record Start Time
				startTime = System.nanoTime();   // System.currentTimeMillis();
				
				// Perform binary search
				int isbnx = Collections.binarySearch(Book.records, new Book(isbn), Comparator.comparing(Book -> Book.isbn));

				//Record the end time
				endTime = System.nanoTime();	// System.currentTimeMillis();
				
				// Calculate the elapsed time in milliseconds or nanoseconds
				elapsedTime = endTime - startTime;
				
				// Find Book
				Book search = Book.records.get(isbnx);

				// Convert Book to object[] and wrap in Object[][]
				Object[][] bookRow = {Book.getBook(search) };

				return bookRow; 
			}
			catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null,"Book with ISBN " + isbn + " not found.");
				return new Object[0][0]; // Return empty array if book not found
				
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				return new Object[0][0]; // Return empty array if any other error occurs
			}
		}
}
