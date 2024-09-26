import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Gui extends JFrame{

	// DefaultTableModel
	String[] Columns = {"ID", "Title", "Author(s)", "ISBN", "Publication Year", "Rating"};
	DefaultTableModel recordsDTM = new DefaultTableModel(SearchPerformance.getTenRecords(), Columns);
	JTable recordDisplay;

	// JButtons
	JButton topTenButton; 
	JButton showAllButton;
	JButton searchButton;

	// JPanels
	private JPanel mainPanel;

	// JTextFields
	JTextField searchField;

	// JComboBox
	JComboBox<?> searchComboBox;

	// JLabels 
	JLabel timeLabel;


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
				recordsDTM.setDataVector(SearchPerformance.getTenRecords(), Columns);
			}
		});

		// Action for Show All Button
		showAllButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				recordsDTM.setDataVector(SearchPerformance.GetAllBooks(), Columns);
			}
		});

		// Action for Top Ten Button
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
						recordsDTM.setDataVector(SearchPerformance.getBookByID(userInput), Columns);
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
					JOptionPane.showMessageDialog(null, "Please enter a valid integer.", "Input Error", JOptionPane.ERROR_MESSAGE);

				}
				catch (IllegalArgumentException e1) {
					// Handle empty input
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);



				}
			}
		});
	}
}
