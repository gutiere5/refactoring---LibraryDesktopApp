import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Gui extends JFrame{
	
	private JPanel contentPane;
	
	public static void main(String[] args) {
	  try {
          Gui mainMenuFrame = new Gui();
          mainMenuFrame.setVisible(true);
      } catch (Exception e) {
          e.printStackTrace();
      }
	  
	}  
	  
	
	
	public Gui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 635, 527);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0,0));
        
        
        
	}
}
