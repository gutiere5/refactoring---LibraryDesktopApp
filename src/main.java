


public class main{
	
	
	
	 public static void main(String[] args){
		Book.importRecords();
		
		// Initializes GUI
		 try {
	          Gui mainMenuFrame = new Gui();
	          mainMenuFrame.setVisible(true);
	          mainMenuFrame.actionPerformed();
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
		 
		 
		
		//getTenRecords(); // Testing - Elber
		//printAllBooks(); // Testing - Elber
		//Book.SortByRatingDescending(); // Testing - Sarah
		//GetTenRecords(); // Testing - Sarah
		//Book.GetBookByID(4); // Testing - Sarah
		//Book.GetBookByISBN("316015849"); // Testing - Sarah
		//System.out.println(Book.records.get(0).title);
		//System.out.println(Book.GetBookByID(1).title);
		//PrintAllBooks();
		
	}
}
