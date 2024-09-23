import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Gui extends JFrame{
	
	private JPanel contentPane;
	
	public static void main(String[] args) {
	  try {
		  Book.importRecords();
          Gui mainMenuFrame = new Gui();
          mainMenuFrame.setVisible(true);
      } catch (Exception e) {
          e.printStackTrace();
      }
	  
	}  
	  
	public Gui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1280, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.setBackground(new Color(234, 219, 203));
        contentPane.add(BookRecords());
        //contentPane.add(SearchBar());
	}
	
	public JScrollPane BookRecords(){
		String[] Columns = {"ID", "Title", "Author(s)", "Rating", "Publication Year", "ISBN"};
		DefaultTableModel recordsDTM = new DefaultTableModel(Book.GetAllBooks(), Columns);
		JTable recordDisplay = new JTable(recordsDTM);
		recordDisplay.setDefaultEditor(Object.class, null);
		JScrollPane sp = new JScrollPane(recordDisplay);
		sp.enableInputMethods(false);
		return sp;
	}
	public JTextField SearchBar() {
		JTextField searchField = new JTextField("Type here to search for a book");
		searchField.setSize(20, 20);
		return searchField;
	}
}
