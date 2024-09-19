


public class main{
	
	public static void GetTenRecords() {
		int count = 0;
		int limit = 10; // Number of books to print
		
		//Book.SortByRatingAscending();
		
		for (Book book : Book.records) {
		    if (count >= limit) {
		        break;
		    }
		    System.out.println("ID: " + book.id
		            + ", Title: "   + book.title
		            + ", Authors: " + book.authors
		            + ", ISBN: "    + book.isbn 
		            + ", Year: "    + book.pubYear
		            + ", Rating: "  + book.aveRating);
		    count++;
		}
		
	}
	
	// Print all book records (for testing purposes)
    public static void PrintAllBooks() {
        for (Book book : Book.records) {
            System.out.println("ID: " + book.id
            		+ ", Title: "   + book.title
            		+ ", Authors: " + book.authors
            		+ ", ISBN: "    + book.isbn 
            		+ ", Year: " 	+ book.pubYear
            		+ ", Rating: "  + book.aveRating);
        }
    }
	
	public static void main(String args[]){
		Book.ImportRecords();
		
		GetTenRecords(); // Testing - Elber
		//PrintAllBooks(); // Testing - Elber
		Book.SortByRatingDescending(); // Testing - Sarah
		GetTenRecords(); // Testing - Sarah
		Book.GetBookByID(4); // Testing - Sarah
		Book.GetBookByISBN("316015849"); // Testing - Sarah
		//System.out.println(Book.records.get(0).title);
		//System.out.println(Book.GetBookByID(1).title);
		
	}
}