import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import net.miginfocom.swing.MigLayout;


public class GUI {
	
	public GUI(){
		
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		JTabbedPane inputPanel = new JTabbedPane();
		
		JPanel outputPanel = new JPanel();
		
		mainPanel.add(inputPanel);
		mainPanel.add(outputPanel);
		
		
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mainPanel);
		frame.pack();
		frame.setVisible(true);
		
		
		
		
	}

}
