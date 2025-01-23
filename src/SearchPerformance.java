import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class SearchPerformance {
	private static long startTime;
	private static long endTime;
	public static long elapsedTime;

	static BookRecords book = new BookRecords();


	static int linearSearch(String ISBN){
		int result = -1;
		for (int i = 0; i < book.getRecords().size(); i++) {
			if(book.getRecords().get(i).getIsbn().equals(ISBN)) {
				result = i;
				break;
			}
		}
		return result;
	}

	static int linearSearch(int id) {
		int result = -1;
		for (int i = 0; i < book.getRecords().size(); i++) {
			if(book.getRecords().get(i).getId() == id) {
				result = i;
				break;
			}
		}
		return result;
	}



	public Object[][] getBookByID(int id) {
		try {
			// Sort by ID before performing binary search
			book.sortByIDAscending();

			// Record Start Time
			startTime = System.nanoTime();

			// Perform binary search
			int idx = Collections.binarySearch(book.getRecords(), new Book(id), Comparator.comparingInt(book -> book.getId()));

			//Record the end time
			endTime = System.nanoTime();

			// Calculate the elapsed time in miliseconds
			elapsedTime = endTime - startTime;

			// Check if the book was found (binarySearch returns a negative value if not found)
			//if (idx < 0 && Gui.softSearch == false) {
			if (idx < 0) {
				JOptionPane.showMessageDialog(null,"Book with ID " + id + " not found.");
			}

			// Find book
			Book search = book.getRecords().get(idx);

			// Convert Book to Object[] and wrap in Object[][]
			Object[][] bookRow = {null }; // book.getBook(search) };
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
			book.sortByISBNAscending();

			// Record Start Time
			startTime = System.nanoTime();   // System.currentTimeMillis();

			// Perform binary search
			int isbnx = Collections.binarySearch(book.getRecords(), new Book(isbn), Comparator.comparing(Book -> Book.getIsbn()));

			//Record the end time
			endTime = System.nanoTime();	// System.currentTimeMillis();

			// Calculate the elapsed time in milliseconds or nanoseconds
			elapsedTime = endTime - startTime;

			// Find Book
			Book search = book.getRecords().get(isbnx);

			// Convert Book to object[] and wrap in Object[][]
			Object[][] bookRow = null; //{book.getBook(search) };

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
