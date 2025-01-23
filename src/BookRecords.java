import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;

public class BookRecords {
	private List<Book> records = new ArrayList<Book>();

	public void importRecords() {
		try (CSVReader csvReader = new CSVReader(new FileReader("books.csv"))) {

			List<String[]> rows = csvReader.readAll();

			for (int i = 1; i < rows.size(); i++) {
				String[] values = rows.get(i);

				if (isValidRecord(values)) {
					try {
						records.add(parseBook(values));
					} catch (NumberFormatException e) {
						System.err.println("Invalid number format, skipping row: " + Arrays.toString(values));
					}
				} else {
					System.out.println("Invalid or incomplete record, skipping row: " + Arrays.toString(values));
				}
			}
		} catch (Exception e) {
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

			return !values[1].trim().isEmpty() && !values[2].trim().isEmpty() && !values[3].trim().isEmpty();
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

	public List<Book> getRecords() {
		return records;
	}

	public void sortRecords(Comparator<Book> comparator) {
		records.sort(comparator);
	}

	public Object[][] getTopNRecords(int n) {
		return records.stream().limit(n).map(Book::toObjectArray).toArray(Object[][]::new);
	}

	public Object[][] GetAllBooks() {
		return records.stream().map(Book::getBook).collect(Collectors.toList()).toArray(new Object[0][0]);
	}
}
