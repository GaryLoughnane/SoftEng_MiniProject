
import java.awt.Font;
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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

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
		JPanel multiChoice2 = new JPanel();
		JPanel fillBlanks = new JPanel();
		JPanel matching = new JPanel();
		JPanel numerical = new JPanel();
		
		
		setupTrueFalse(trueFalse);
		setupMultiChoice1(multiChoice1);
		setupMultiChoice2(multiChoice2);
		setupFillBlanks(fillBlanks);
		setupMatching(matching);
		setupNumerical(numerical);
		
		
		//create tabbed panel and add each question type panel to it
		JTabbedPane inputPanel = new JTabbedPane(JTabbedPane.TOP);
		inputPanel.addTab("True/False", trueFalse);
		inputPanel.addTab("Multiple Choice 1", multiChoice1);
		inputPanel.addTab("Multiple Choice 2", multiChoice2);
		inputPanel.addTab("Fill The Blank", fillBlanks);
		inputPanel.addTab("Matching", matching);
		inputPanel.addTab("Numerical", numerical);
		
		
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
	
	
	
	private void setupNumerical(JPanel panel) {

		panel.setLayout(new MigLayout("", "[grow]", "[][][][][grow]"));
		
		
		/**
		JLabel explanation = new JLabel("Question with matching pairs of answers.");
		explanation.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel title = new JLabel("Question Title:");	
		JLabel questionPrompt = new JLabel("Enter a question.");
		JLabel answerPrompt = new JLabel("Enter pairs of matching answers.");
		
		final JTextField titleName = new JTextField();
		final JTextField question = new JTextField();
		
		JButton addAnswer = new JButton("Add Answer");
		JButton remAnswer = new JButton("Remove Answer");
		JButton clear = new JButton("Clear");
		JButton generate = new JButton("Generate Code");
		**/
		
		
		JLabel explanation = new JLabel("Various types of numerical questions");
			explanation.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel title = new JLabel("Question Title:");
		JTextField titleName = new JTextField();
		
		JLabel answerPrompt = new JLabel("Enter a mathamatical question.");
		
		JTextField question = new JTextField("fffffffffffffff");
		
		
		JPanel answerPanel = new JPanel(new MigLayout("", "[][grow]", "[][][][]"));
		
		JRadioButton rdbtnRange = new JRadioButton("range");
		
		JPanel panel_2 = new JPanel(new MigLayout("", "[][][][]", "[]"));
			panel_2.setBorder(new TitledBorder(null, "JPanel 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			
		JPanel panel_3 = new JPanel(new MigLayout("", "[][][][][][]", "[]"));
			panel_3.setBorder(new TitledBorder(null, "JPanel 2", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			
		JPanel panel_4 = new JPanel(new MigLayout("", "[][][][]", "[]"));
			panel_4.setBorder(new TitledBorder(null, "JPanel 3", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	
		JLabel lblEnterAnswer = new JLabel("enter answer");
		
		
		JSpinner spinner = new JSpinner();	
			
		JLabel lblEnterRange = new JLabel("enter range");	
		
		JSpinner spinner_1 = new JSpinner();
		
		JRadioButton rdbtnRangeWithMark = new JRadioButton("range with mark");

		JLabel lblEnterCorrectAnswer = new JLabel("enter correct answer");
		
		JSpinner spinner_4 = new JSpinner();
		
		JLabel lblEnterRange_1 = new JLabel("enter range");
		
		JSpinner spinner_5 = new JSpinner();
		JLabel lblEnterMarkFor = new JLabel("enter mark for range");
		
		JSpinner spinner_6 = new JSpinner();
		JRadioButton rdbtnSpan = new JRadioButton("span");
		
		
		JLabel lblRnterLower = new JLabel("rnter lower");
		
		JSpinner spinner_3 = new JSpinner();
		
		JLabel lblEnterUpper = new JLabel("enter upper");
		
		JSpinner spinner_2 = new JSpinner();
		
		JButton btnClear = new JButton("clear");
		
		JButton btnGenerae = new JButton("generae");
		
		
			
		
		
		panel.add(explanation, "cell 0 0");	
		panel.add(title, "flowx,cell 0 1");
		panel.add(titleName, "cell 0 1");
		panel.add(answerPrompt, "cell 0 2");
		panel.add(question, "cell 0 3,growx");
		panel.add(answerPanel, "cell 0 4,grow");
		answerPanel.add(rdbtnRange, "cell 0 0");
		answerPanel.add(panel_2, "cell 1 0,grow");
		panel_2.add(lblEnterAnswer, "cell 0 0");
		panel_2.add(spinner, "cell 1 0");
		panel_2.add(lblEnterRange, "cell 2 0");
		panel_2.add(spinner_1, "cell 3 0");	
		answerPanel.add(rdbtnRangeWithMark, "cell 0 1");
		answerPanel.add(panel_3, "cell 1 1,grow");
		panel_3.add(lblEnterCorrectAnswer, "cell 0 0");
		panel_3.add(spinner_4, "cell 1 0");
		panel_3.add(lblEnterRange_1, "cell 2 0");
		panel_3.add(spinner_5, "cell 3 0");
		panel_3.add(lblEnterMarkFor, "cell 4 0");
		panel_3.add(spinner_6, "cell 5 0");
		answerPanel.add(rdbtnSpan, "cell 0 2");
		answerPanel.add(panel_4, "cell 1 2,grow");
		panel_4.add(lblRnterLower, "cell 0 0");
		panel_4.add(spinner_3, "cell 1 0");
		panel_4.add(lblEnterUpper, "cell 2 0");
		panel_4.add(spinner_2, "cell 3 0");		
		answerPanel.add(btnClear, "flowx,cell 1 3,alignx right");
		answerPanel.add(btnGenerae, "cell 1 3,alignx right");
		
	}



	private void setupMatching(JPanel panel) {
		
		panel.setLayout(new MigLayout("", "[grow]", "[][][][][][grow][]"));
		
		JLabel explanation = new JLabel("Question with matching pairs of answers.");
			explanation.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel title = new JLabel("Question Title:");	
		JLabel questionPrompt = new JLabel("Enter a question.");
		JLabel answerPrompt = new JLabel("Enter pairs of matching answers.");
		
		final JTextField titleName = new JTextField();
		final JTextField question = new JTextField();
		
		JButton addAnswer = new JButton("Add Answer");
		JButton remAnswer = new JButton("Remove Answer");
		JButton clear = new JButton("Clear");
		JButton generate = new JButton("Generate Code");
	
		final JPanel answerPanel = new JPanel(new MigLayout("", "[grow][grow]", "[]"));
		
		final ArrayList<JTextField> answer1 = new ArrayList<JTextField>();
		final ArrayList<JTextField> answer2 = new ArrayList<JTextField>();
		
		for(int i=0; i<2; i++){
			answer1.add(new JTextField());
			answer2.add(new JTextField());
		}
		
		addAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				answer1.add(new JTextField());
				answer2.add(new JTextField());
				answerPanel.add(answer1.get(answer1.size()-1), "flowy,cell 0 0,growx");
				answerPanel.add(answer2.get(answer2.size()-1), "flowy,cell 1 0,growx");
				
				answerPanel.updateUI();				
			}
		});
		
		
		remAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(answer1.size()>2){
				
					answerPanel.remove(answer1.get(answer1.size()-1));
					answerPanel.remove(answer2.get(answer2.size()-1));
					
					answer1.remove(answer1.size()-1);
					answer2.remove(answer2.size()-1);
					
					answerPanel.updateUI();
				}
			}
		});
		
		
		generate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
//				ArrayList<String> answerStrings = new ArrayList<String>();
//				int correctAnswer = 0;
//				
//				for(int i=0; i<answers.size(); i++){
//					answerStrings.add(answers.get(i).getText());
//					
//					if(select.get(i).isSelected()){
//						correctAnswer = i;
//					}
//				}
//
//				giftCode.append("//Question " + questionNumber + "\n");
//				
//				giftCode.append(
//						GiftGenerator.GenerateMultiChoice(titleName.getText(), question.getText(), answerStrings, correctAnswer)
//							+ "\n\n");
//				
//				questionNumber++;
				
				
			}
		});
		
		
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				titleName.setText("");
				question.setText("");
				
				for(int i=0; i<answer1.size(); i++){
					answer1.get(i).setText("");
					answer2.get(i).setText("");
				}
				
			}
		});	
		
		
		
		
		for(int i=0; i<2; i++){
			answerPanel.add(answer1.get(i), "flowy,cell 0 0,growx");
			answerPanel.add(answer2.get(i), "flowy,cell 1 0,growx");
		}
		
		JScrollPane scrollPane = new JScrollPane(answerPanel);
		
		panel.add(explanation, "cell 0 0");	
		panel.add(title, "flowx,cell 0 1");
		panel.add(titleName, "cell 0 1,growx");	
		panel.add(questionPrompt, "cell 0 2");
		panel.add(question, "cell 0 3,growx");		
		panel.add(answerPrompt, "flowx,cell 0 4");		
		panel.add(scrollPane, "cell 0 5,grow");		
		panel.add(addAnswer, "cell 0 4");	
		panel.add(remAnswer, "cell 0 4");	
		panel.add(clear, "flowx,cell 0 6,alignx right");
		panel.add(generate, "cell 0 6,alignx right");
		
	}



	private void setupFillBlanks(JPanel panel){
		
		panel.setLayout(new MigLayout("", "[grow,left][grow,center][grow,right]", "[][][][grow][]"));
		
		JLabel explanation = new JLabel("Fill in the blank question with one correct answer.");
			explanation.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel title = new JLabel("Question Title:");	
		JLabel beginingPrompt = new JLabel("Enter the begining of the question.");
		JLabel answerPrompt = new JLabel("Enter all possible answers and select the correct one.");
		JLabel endPrompt = new JLabel("Enter the end of the question.");
				
		final JTextArea questionStart = new JTextArea();
		final JTextArea questionEnd = new JTextArea();

		final JTextField titleName = new JTextField();

		JButton addAnswer = new JButton("Add Answer");
		JButton remAnswer = new JButton("Remove Answer");
		JButton clear = new JButton("Clear");
		JButton generate = new JButton("Generate Code");
		
		final JPanel answerPanel = new JPanel(new MigLayout("", "[grow][]", "[]"));
		
		final ButtonGroup selectgrGroup = new ButtonGroup();
		final ArrayList<JTextField> answers = new ArrayList<JTextField>();
		final ArrayList<JRadioButton> select = new ArrayList<JRadioButton>();
		
		
		for(int i=0; i<2; i++){
			answers.add(new JTextField());
			select.add(new JRadioButton());
			
			selectgrGroup.add(select.get(i));
		}
		select.get(0).setSelected(true);
		
		
		addAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				answers.add(new JTextField());
				select.add(new JRadioButton());
				answerPanel.add(answers.get(answers.size()-1), "flowy,cell 0 0,growx");
				answerPanel.add(select.get(select.size()-1), "flowy,cell 1 0,alignx right");
				
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
		
		
		generate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
//				ArrayList<String> answerStrings = new ArrayList<String>();
//				int correctAnswer = 0;
//				
//				for(int i=0; i<answers.size(); i++){
//					answerStrings.add(answers.get(i).getText());
//					
//					if(select.get(i).isSelected()){
//						correctAnswer = i;
//					}
//				}
//
//				giftCode.append("//Question " + questionNumber + "\n");
//				
//				giftCode.append(
//						GiftGenerator.GenerateMultiChoice(titleName.getText(), question.getText(), answerStrings, correctAnswer)
//							+ "\n\n");
//				
//				questionNumber++;
				
				
			}
		});
		
		
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				titleName.setText("");
				questionStart.setText("");
				questionEnd.setText("");
				
				for(int i=0; i<answers.size(); i++){
					answers.get(i).setText("");
				}
				
			}
		});	
		
		for(int i=0; i<2; i++){
			answerPanel.add(answers.get(i), "flowy,cell 0 0,growx");
			answerPanel.add(select.get(i), "flowy,cell 1 0,alignx right");
		}
		
		
		JScrollPane startScroll = new JScrollPane(questionStart);
		JScrollPane answerScroll = new JScrollPane(answerPanel);
		JScrollPane endScroll = new JScrollPane(questionEnd);


		panel.add(explanation, "cell 0 0");		
		panel.add(title, "flowx,cell 0 1,alignx left");	
		panel.add(beginingPrompt, "cell 0 2");	
		panel.add(answerPrompt, "cell 1 2,alignx left");
		panel.add(endPrompt, "cell 2 2,alignx left");
		panel.add(startScroll, "cell 0 3,grow");	
		panel.add(answerScroll, "cell 1 3,grow");
		panel.add(endScroll, "cell 2 3,grow");
		panel.add(titleName, "cell 0 1,growx");	
		panel.add(addAnswer, "flowx,cell 1 4");		
		panel.add(remAnswer, "cell 1 4");	
		panel.add(clear, "flowx,cell 2 4");	
		panel.add(generate, "cell 2 4");
		
	}
	
	
	

	private void setupMultiChoice2(JPanel panel) {
		
		panel.setLayout(new MigLayout("", "[grow][]", "[][][][grow][]"));
		
		JLabel explanation = new JLabel("Multiple choice question with multiple correct answers.");
			explanation.setFont(new Font("Tahoma", Font.BOLD, 12));
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
		
		final ArrayList<JTextField> answers = new ArrayList<JTextField>();
		final ArrayList<JSpinner> values = new ArrayList<JSpinner>();
		
		
		
		for(int i=0; i<2; i++){
			answers.add(new JTextField());

			SpinnerModel spinnerModel = new SpinnerNumberModel(0, -100, 100, 1);
			values.add(new JSpinner(spinnerModel));
			
		}
		
		
		addAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				answers.add(new JTextField());
				
				
				SpinnerModel spinnerModel = new SpinnerNumberModel(0, -100, 100, 1);
				values.add(new JSpinner(spinnerModel));
				
				answerPanel.add(answers.get(answers.size()-1), "flowy,cell 0 1,growx");
				answerPanel.add(values.get(values.size()-1), "flowy,cell 1 1,alignx right");
				
				answerPanel.updateUI();
				
			}
		});
		
		
		remAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(answers.size()>2){
				
					answerPanel.remove(answers.get(answers.size()-1));
					answerPanel.remove(values.get(values.size()-1));
					
					answers.remove(answers.size()-1);
					values.remove(values.get(values.size()-1));
					
					answerPanel.updateUI();
				}
			}
		});
		
		
		generate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
//				ArrayList<String> answerStrings = new ArrayList<String>();
//				int correctAnswer = 0;
//				
//				for(int i=0; i<answers.size(); i++){
//					answerStrings.add(answers.get(i).getText());
//					
//					if(select.get(i).isSelected()){
//						correctAnswer = i;
//					}
//				}
//
//				giftCode.append("//Question " + questionNumber + "\n");
//				
//				giftCode.append(
//						GiftGenerator.GenerateMultiChoice(titleName.getText(), question.getText(), answerStrings, correctAnswer)
//							+ "\n\n");
//				
//				questionNumber++;
				
				
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
			answerPanel.add(values.get(i), "flowy,cell 1 1,alignx right");
		}
				
		panel.add(explanation, "cell 0 0");
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

	
	
	private void setupMultiChoice1(JPanel panel) {
				
		panel.setLayout(new MigLayout("", "[grow][]", "[][][][grow][]"));
		
		JLabel explanation = new JLabel("Multiple choice question with one correct answer.");
			explanation.setFont(new Font("Tahoma", Font.BOLD, 12));
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
		select.get(0).setSelected(true);
		
		
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
		
		
		generate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ArrayList<String> answerStrings = new ArrayList<String>();
				int correctAnswer = 0;
				
				for(int i=0; i<answers.size(); i++){
					answerStrings.add(answers.get(i).getText());
					
					if(select.get(i).isSelected()){
						correctAnswer = i;
					}
				}

				giftCode.append("//Question " + questionNumber + "\n");
				
				giftCode.append(
						GiftGenerator.GenerateMultiChoice(titleName.getText(), question.getText(), answerStrings, correctAnswer)
							+ "\n\n");
				
				questionNumber++;
				
				
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
				
		panel.add(explanation, "cell 0 0");
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


	
	
	private void setupTrueFalse(JPanel panel) {
		
		panel.setLayout(new MigLayout("", "[grow]", "[][][][grow][][]"));
		
		JLabel explanation = new JLabel("True/False statement with one correct answer.");
			explanation.setFont(new Font("Tahoma", Font.BOLD, 12));
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
		
		
		panel.add(explanation, "cell 0 0");
		panel.add(title, "cell 0 1");
		panel.add(titleName, "cell 0 1, grow");
		panel.add(prompt, "cell 0 2");		
		panel.add(scrollPane, "cell 0 3,grow");		
		panel.add(isTrue, "align right, cell 0 4");		
		panel.add(isFalse, "align right, cell 0 4");
		panel.add(clear, "align right, cell 0 5");
		panel.add(generate, "align right, cell 0 5");

		
	}

	

	
	private void setupOutput(JPanel panel) {
		
		panel.setLayout(new MigLayout("", "[grow]", "[300px:n,grow][]"));		
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
}
