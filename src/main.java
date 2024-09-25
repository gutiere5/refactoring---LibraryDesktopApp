


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
		
		
	}
}
