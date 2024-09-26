
public class LibraryDesktop{

	public static void main(String[] args){
		//Imports Records from books.csv
		Book.importRecords();

		// Initializes GUI
		try {
			Gui mainMenuFrame = new Gui();
			mainMenuFrame.setVisible(true);
			mainMenuFrame.actionPerformed();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
