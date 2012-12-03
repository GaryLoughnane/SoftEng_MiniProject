
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;


public class GUI {
	
	JTextArea giftCode = new JTextArea();
	int questionNumber = 1;
	
	public GUI(){
		
		giftCode.setLineWrap(true);
		giftCode.setWrapStyleWord(true);
		
		//set look and feel to "system look and feel"
		try{UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch(Exception e){
			//TODO add exception code later
		}
		
		
		//create main "background" panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		
		//create each of the "question type panels
		JPanel trueFalse = new JPanel();
		JPanel multiChoice = new JPanel();
		
		
		setupTrueFalse(trueFalse);
		
		
		//create tabbed panel and add each question type panel to it
		JTabbedPane inputPanel = new JTabbedPane(JTabbedPane.TOP);
		inputPanel.addTab("True/False", trueFalse);
		inputPanel.addTab("Multiple Choice", multiChoice);
		
		
		//create the output panel
		JPanel outputPanel = new JPanel();
		setupOutput(outputPanel);
		
		
		//add input (tabbed) and output panels to main panel
		mainPanel.add(inputPanel, "cell 0 0,grow");
		mainPanel.add(outputPanel, "cell 0 1,grow");
		
		
		//setup JFrame
		//Frame text, icon, close operation, add main panel, set full screen, show
		JFrame frame = new JFrame("Offline GIFT Editor - By Gary Loughnane");	
		frame.setIconImage(new ImageIcon(GUI.class.getResource("images/moodle.gif")).getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mainPanel);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);

		
	}

	private void setupOutput(JPanel panel) {
		
		panel.setLayout(new MigLayout("", "[grow]", "[400px:n,grow][]"));		
		JScrollPane scrollPane = new JScrollPane(giftCode);
		JButton save = new JButton("Save");
		JButton clear = new JButton("Clear");
		
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				giftCode.setText("");
				questionNumber = 1;
				
			}
		});
		
		panel.add(scrollPane, "cell 0 0, grow");
		panel.add(clear, "align right, cell 0 1");
		panel.add(save, "align right, cell 0 1");
		
	}

	private void setupTrueFalse(JPanel panel) {
		
		panel.setLayout(new MigLayout("", "[grow]", "[][][grow][][]"));
		
		JLabel title = new JLabel("Question Title:");
		final JTextField titleName = new JTextField();
		
		JLabel prompt = new JLabel("Enter a statement and select if it is true or false.");
		final JTextArea question = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(question);
		final JRadioButton isTrue = new JRadioButton("This statement is true.", true);
		final JRadioButton isFalse = new JRadioButton("This statement is false.", false);
		JButton generate = new JButton("Generate Code");
		JButton clear = new JButton("Clear");
		
		ButtonGroup group = new ButtonGroup();
		group.add(isTrue);
		group.add(isFalse);
		
		generate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				boolean answer = false;
				
				if(isTrue.isSelected()) answer = true;
				else if(isFalse.isSelected()) answer = false;
				
				
				giftCode.append("//Question " + questionNumber + "\n");
				
				giftCode.append(
						GiftGenerator.GenerateTrueFalse(titleName.getText(), question.getText(), answer)
							+ "\n\n");
				
				questionNumber++;
			}
		});
		
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				titleName.setText("");
				question.setText("");
				
			}
		});
		
		
		panel.add(title, "cell 0 0");
		panel.add(titleName, "cell 0 0, grow");
		panel.add(prompt, "cell 0 1");		
		panel.add(scrollPane, "cell 0 2,grow");		
		panel.add(isTrue, "align right, cell 0 3");		
		panel.add(isFalse, "align right, cell 0 3");
		panel.add(clear, "align right, cell 0 4");
		panel.add(generate, "align right, cell 0 4");

		
	}

}
