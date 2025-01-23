import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	BookRecords bookRecords = new BookRecords();
	
	SearchPerformance searchPerformance = new SearchPerformance();
	
	
	// DefaultTableModel
		String[] Columns = {"ID", "Title", "Author(s)", "ISBN", "Publication Year", "Rating"};
		@SuppressWarnings({ "serial", "serial" })
		DefaultTableModel recordsDTM = new DefaultTableModel() {
			@Override
			public Class<?> getColumnClass(int column){
				if (column == 0) {
					return Integer.class;
				}
				return super.getColumnClass(column);
			}};
			
		
	JTable recordDisplay;

	// JButtons
	JButton topTenButton; 
	JButton showAllButton;
	JButton testPerfButton;
	JButton searchButton;

	// JPanels
	private JPanel mainPanel;

	// JTextFields
	JTextField searchField;

	// JComboBox
	JComboBox<?> searchComboBox;

	// JLabels 
	JLabel timeLabel;
	boolean softSearch = false;
	boolean testMode = false;


	public Gui() {
		bookRecords.importRecords(100);
		
		recordsDTM.setDataVector(bookRecords.getTenRecords(), Columns);
		
		// Create main panel with BorderLayout
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Libray Desktop App (ArrayList)");
		setBounds(400, 100, 700, 500);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout(0,0));
		mainPanel.setBackground(new Color(234, 219, 203));
		
		// Load Icon
		ImageIcon icon = new ImageIcon("book.png");
		Image iconImage = icon.getImage();
		setIconImage(iconImage);

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
		timeLabel = new JLabel("");

		// JButtons
		topTenButton =  new JButton("Show Top Ten");
		showAllButton = new JButton("Show All");
		testPerfButton = new JButton("Test Mode = OFF");
		searchButton = new JButton("Search");
		showAllButton.setPreferredSize(new Dimension(100,40));

		// JComboBox
		searchComboBox = new JComboBox(new String[] {"ID" , "ISBN"});

		// Add Components to Search Panel
		searchPanel.add(searchComboBox);
		searchPanel.add(SearchBar());
		searchPanel.add(searchButton);

		// Add Components to Performance Panel
		performancePanel.add(PerformanceLabel);
		performancePanel.add(timeLabel);

		// Add Components to Action Panel
		actionPanel.add(actionLabel);
		actionPanel.add(Box.createVerticalStrut(5)); // Add space between label and buttons
		actionPanel.add(topTenButton);
		actionPanel.add(Box.createVerticalStrut(5)); // Add space between buttons
		actionPanel.add(showAllButton);
		actionPanel.add(Box.createVerticalStrut(5)); // Add space between buttons
		actionPanel.add(testPerfButton);

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
				recordsDTM.setDataVector(bookRecords.getTenRecords(), Columns);
			}
		});

		// Action for Show All Button
		showAllButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				recordsDTM.setDataVector(bookRecords.GetAllBooks(), Columns);
			}
		});
		
		testPerfButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(testPerfButton.getText() == "Test Mode = OFF") {
					testPerfButton.setText("Test Mode = ON ");
					testMode = true;					
				}
				else {
					testPerfButton.setText("Test Mode = OFF");
					testMode = false;
				}
			}
		});

		// Action for search Button
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {

					// Check if the input field is empty
					if (searchField.getText().isEmpty()) {
						throw new IllegalArgumentException("Input cannot be empty.");
					}

					if (searchComboBox.getSelectedItem() == "ID") {			
						// Perform the search and update the table
						int userInput = Integer.parseInt(searchField.getText());
						recordsDTM.setDataVector(searchPerformance.getBookByID(userInput), Columns);
						timeLabel.setText(String.valueOf(SearchPerformance.elapsedTime + " Nanoseconds"));
					}

					if (searchComboBox.getSelectedItem() == "ISBN") {								
						// Perform the search and update the table
						String userInput = searchField.getText();
						recordsDTM.setDataVector(SearchPerformance.getBookByISBN(userInput), Columns);
						timeLabel.setText(String.valueOf(SearchPerformance.elapsedTime + " Nanoseconds"));
					}
				}

				catch (NumberFormatException e1) {
					// Handle invalid input (non-integer input)
					if(softSearch == false) {
						JOptionPane.showMessageDialog(null, "Please enter a valid integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (IllegalArgumentException e1) {
					// Handle empty input
					if(softSearch == false) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);
					}
				}
				finally {
					softSearch = false;
				}
			}
		});
		
		searchField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(searchField.getText().equals("Type here to search for a book")) {
					searchField.setText("");
				}
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
				try {
					if(!searchField.getText().isEmpty() && testMode == false) {
						softSearch = true;
						searchButton.doClick();
					}
				}
				catch (Exception ex){
				}
			}
		});
	}
}
