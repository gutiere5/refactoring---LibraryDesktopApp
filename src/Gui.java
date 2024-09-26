import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Gui extends JFrame{

	// DefaultTableModel
	String[] Columns = {"ID", "Title", "Author(s)", "ISBN", "Publication Year", "Rating"};
	DefaultTableModel recordsDTM = new DefaultTableModel(Book.GetAllBooks(), Columns);
	JTable recordDisplay;

	// JButtons
	JButton topTenButton; 
	JButton showAllButton;
	JButton searchButton;

	// JPanels
	private JPanel mainPanel;
	
	// JTextFields
	JTextField searchField;

	// Constructor
	public Gui() {
		// Create main panel with BorderLayout
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Libray Desktop App");
		setBounds(100, 100, 700, 400);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout(0,0));
		mainPanel.setBackground(new Color(234, 219, 203));

		// Create a sub panel for action buttons
		JPanel actionPanel = new JPanel();
		actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));

		// Create a sub panel for performance metrics 
		JPanel performancePanel = new JPanel();
		performancePanel.setLayout(new BoxLayout(performancePanel, BoxLayout.X_AXIS));

		// Create a sub panel for search bar
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));

		/////////
		// JLabels
		JLabel PerformanceLabel = new JLabel("Performance: ");
		JLabel actionLabel = new JLabel("Actions");

		// JButtons
		topTenButton =  new JButton("Show Top Ten");
		showAllButton = new JButton("Show All");
		searchButton = new JButton("Search");

		showAllButton.setPreferredSize(new Dimension(100,40));



		// Add Components to Search Panel
		searchPanel.add(SearchBar());
		searchPanel.add(searchButton);

		// Add Components to Performance Panel
		performancePanel.add(PerformanceLabel);

		// Add Components to Action Panel
		actionPanel.add(actionLabel);
		actionPanel.add(Box.createVerticalStrut(5)); // Add space between label and buttons
		actionPanel.add(topTenButton);
		actionPanel.add(Box.createVerticalStrut(5)); // Add space between buttons
		actionPanel.add(showAllButton);

		// Add Sub Panels to the main Panel
		mainPanel.add(performancePanel,BorderLayout.SOUTH);
		mainPanel.add(BookRecords(),BorderLayout.CENTER);
		mainPanel.add(actionPanel, BorderLayout.EAST);
		mainPanel.add(searchPanel,BorderLayout.NORTH);

	}


	public JScrollPane BookRecords(){		
		recordDisplay = new JTable(recordsDTM);
		recordDisplay.setDefaultEditor(Object.class, null);
		recordDisplay.setAutoCreateRowSorter(true);
		JScrollPane sp = new JScrollPane(recordDisplay);
		sp.enableInputMethods(false);

		return sp;
	}
	public JTextField SearchBar() {
		searchField = new JTextField("Type here to search for a book");
		searchField.setSize(20, 20);
		return searchField;

	}

	public void actionPerformed() {
		// Action for Top Ten Button
		topTenButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				recordsDTM.setDataVector(Book.getTenRecords(), Columns);
			}
		});

		// Action for Show All Button
		showAllButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				recordsDTM.setDataVector(Book.GetAllBooks(), Columns);
			}
		});

		// Action for Top Ten Button
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int userInput = Integer.parseInt(searchField.getText());
				recordsDTM.setDataVector(Book.searchBook(userInput), Columns);
				
			}
		});
		
		searchField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				searchField.setText("");
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		
		searchField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(searchField.getText().equals("Type here to search for a book")) {
					searchField.setText("");
				}
				else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					searchButton.doClick();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {	
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
	}
}
