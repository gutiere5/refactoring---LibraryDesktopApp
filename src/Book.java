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

	public  Object[] getBook() {
		return new Object[] {id, title, authors, isbn, pubYear, aveRating};
	}
	
	public String toString() {
        return "Book{" +
                "bookId=" + id +
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationYear=" + pubYear +
                ", averageRating=" + aveRating +
                '}';
    }	
}
