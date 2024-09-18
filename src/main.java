


public class main{
	
	public static void getTenRecords() {
		int count = 0;
		int limit = 10; // Number of books to print
		
		Book.sortByRatingAscending();
		
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
		
		getTenRecords(); // Testing - Elber
		//PrintAllBooks(); // Testing - Elber 
	}
}