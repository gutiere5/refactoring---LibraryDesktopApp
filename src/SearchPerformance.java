import java.util.Collections;
import java.util.Comparator;

public class SearchPerformance {
	private static long elapsedTime;

	public int linearSearchByISBN(String isbn, BookRecords bookRecords) {
		for (int i = 0; i < bookRecords.getRecords().size(); i++) {
			if (bookRecords.getRecords().get(i).getIsbn().equals(isbn)) {
				return i;
			}
		}
		return -1;
	}

	public int linearSearchByID(int id, BookRecords bookRecords) {
		for (int i = 0; i < bookRecords.getRecords().size(); i++) {
			if (bookRecords.getRecords().get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}

	public Object[][] binarySearchByID(int id, BookRecords bookRecords) {
		bookRecords.sortRecords(Comparator.comparingInt(Book::getId)); // Sort before binary search
		long startTime = System.nanoTime();
		int idx = Collections.binarySearch(bookRecords.getRecords(), new Book(id),
				Comparator.comparingInt(Book::getId));
		elapsedTime = System.nanoTime() - startTime;
		return new Object[][] { bookRecords.getRecords().get(idx).toObjectArray() };
	}

	public Object[][] binarySearchByISBN(String isbn, BookRecords bookRecords) {
		bookRecords.sortRecords(Comparator.comparing(Book::getIsbn)); // Sort before binary search
		long startTime = System.nanoTime();
		int idx = Collections.binarySearch(bookRecords.getRecords(), new Book(isbn),
				Comparator.comparing(Book::getIsbn));
		elapsedTime = System.nanoTime() - startTime;
		return new Object[][] { bookRecords.getRecords().get(idx).toObjectArray() };
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

}
