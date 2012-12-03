

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
		JPanel multiChoice1 = new JPanel();
		
		
		setupTrueFalse(trueFalse);
		setupMultiChoice1(multiChoice1);
		
		
		//create tabbed panel and add each question type panel to it
		JTabbedPane inputPanel = new JTabbedPane(JTabbedPane.TOP);
		inputPanel.addTab("True/False", trueFalse);
		inputPanel.addTab("Multiple Choice", multiChoice1);
		
		
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

	private void setupMultiChoice1(JPanel panel) {
				
		panel.setLayout(new MigLayout("", "[grow,left][grow]", "[][][][grow][]"));
		
		JLabel explination = new JLabel("Multiple choice question with one correct answer");
		JLabel title = new JLabel("Question Title:");	
		JLabel prompt = new JLabel("Enter a question.");
		JLabel selectPrompt = new JLabel("Enter all possible answers and select one correct answer.");
		
		final JTextField titleName = new JTextField();	
		
		final JTextArea question = new JTextArea();	
		JScrollPane questionScroll = new JScrollPane(question);
		
		final JPanel answerPanel = new JPanel();
		answerPanel.setLayout(new MigLayout("", "[grow,fill][right]", "[][]"));	
		JScrollPane answerScroll = new JScrollPane(answerPanel);
		
		JButton addAnswer = new JButton("Add Answer");
		JButton remAnswer = new JButton("Remove Answer");
		JButton generate = new JButton("Generate Code");	
		JButton clear = new JButton("Clear");
		
		final ButtonGroup selectgrGroup = new ButtonGroup();
		final ArrayList<JTextField> answers = new ArrayList<JTextField>();
		final ArrayList<JRadioButton> select = new ArrayList<JRadioButton>();
		
		for(int i=0; i<2; i++){
			answers.add(new JTextField());
			select.add(new JRadioButton());
			
			selectgrGroup.add(select.get(i));
		}
		
		
		addAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				answers.add(new JTextField());
				select.add(new JRadioButton());
				
				answerPanel.add(answers.get(answers.size()-1), "flowy,cell 0 1,growx");
				answerPanel.add(select.get(select.size()-1), "flowy,cell 1 1,alignx right");
				
				selectgrGroup.add(select.get(select.size()-1));
				
				answerPanel.updateUI();
				
			}
		});
		
		
		remAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(answers.size()>2){
				
					answerPanel.remove(answers.get(answers.size()-1));
					answerPanel.remove(select.get(select.size()-1));
					
					selectgrGroup.remove(select.get(select.size()-1));
					
					answers.remove(answers.size()-1);
					select.remove(select.size()-1);
					
					answerPanel.updateUI();
				}
			}
		});
		
		
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				titleName.setText("");
				question.setText("");
				
				for(int i=0; i<answers.size(); i++){
					answers.get(i).setText("");
				}
				
			}
		});
		
		for(int i=0; i<2; i++){
			answerPanel.add(answers.get(i), "flowy,cell 0 1,growx");
			answerPanel.add(select.get(i), "flowy,cell 1 1,alignx right");
		}
				
		panel.add(explination, "cell 0 0");
		panel.add(title, "flowx,cell 0 1,alignx left");
		panel.add(titleName, "cell 0 1,alignx left, grow");
		panel.add(prompt, "cell 0 2");
		panel.add(questionScroll, "cell 0 3,grow");
		
		
		panel.add(selectPrompt, "align right, cell 1 2");
		panel.add(addAnswer, "align right, cell 1 2");	
		panel.add(remAnswer, "align right, cell 1 2");	
		panel.add(answerScroll, "cell 1 3, grow");
		panel.add(clear, "align right, cell 1 4");
		panel.add(generate, "align right, cell 1 4");
	
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
