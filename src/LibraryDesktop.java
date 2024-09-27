import java.util.Scanner;

public class LibraryDesktop{

	public static void main(String[] args){
		boolean DeveloperMode = false;
		int numRecords;
		//Imports Records from books.csv
		if(args.length > 0) {
			if(args[0].equals("Dev")) {
				DeveloperMode = true;
			}
		}
		if(DeveloperMode == true) {
			System.out.println("Enter the number of records to import: ");
			Scanner in = new Scanner(System.in);
			numRecords = in.nextInt();
		}
		else {
			numRecords = Integer.MAX_VALUE;
		}

		Book.importRecords(numRecords);

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
