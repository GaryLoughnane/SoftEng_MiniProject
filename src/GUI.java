
import java.awt.Dimension;
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
		JPanel numericalFeedback = new JPanel();
		JPanel essay = new JPanel();
		
		
		setupTrueFalse(trueFalse);
		setupMultiChoice1(multiChoice1);
		setupMultiChoice2(multiChoice2);
		setupFillBlanks(fillBlanks);
		setupMatching(matching);
		setupNumerical(numerical);
		setupNumericalFeedback(numericalFeedback);
		setupEssay(essay);
		
		
		//create tabbed panel and add each question type panel to it
		JTabbedPane inputPanel = new JTabbedPane(JTabbedPane.TOP);
		inputPanel.addTab("True/False", trueFalse);
		inputPanel.addTab("Multiple Choice 1", multiChoice1);
		inputPanel.addTab("Multiple Choice 2", multiChoice2);
		inputPanel.addTab("Fill The Blank", fillBlanks);
		inputPanel.addTab("Matching", matching);
		inputPanel.addTab("Numerical", numerical);
		inputPanel.addTab("Numerical Multi", numericalFeedback);
		inputPanel.addTab("Essay", essay);
		
		
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
		frame.pack();
		frame.setVisible(true);

		
	}
	
	
	
	private void setupEssay(JPanel panel) {
		
		panel.setLayout(new MigLayout("", "[grow]", "[][][][grow][]"));
		
		JLabel explanation = new JLabel("Essay question.");
			explanation.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel title = new JLabel("Question Title:");
		final JTextField titleName = new JTextField();
		
		JLabel prompt = new JLabel("Enter a question that requires an essay answer.");
		final JTextArea question = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(question);
		
		JButton generate = new JButton("Generate Code");
		JButton clear = new JButton("Clear");
		
		
		generate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				writeToOutput(GiftGenerator.GenerateEssay(titleName.getText(), question.getText()));				

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
		panel.add(clear, "align right, cell 0 4");
		panel.add(generate, "align right, cell 0 4");

		
	}

	
	
	
	private void setupNumericalFeedback(JPanel panel) {

		panel.setLayout(new MigLayout("", "[grow]", "[][][][][][grow][]"));
		
		JLabel explanation = new JLabel("Numerical question with multiple answers, partial credit and feedback.");
			explanation.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel title= new JLabel("Question Title:");
		JLabel questionPrompt= new JLabel("Enter a mathamatical question.");
		final JTextField titleName = new JTextField();
		final JTextField question = new JTextField();
		
		JButton addAnswer = new JButton("Add Answer");
		JButton remAnswer = new JButton("Remove Answer");
		JButton clear = new JButton("Clear");
		JButton generate = new JButton("Generate Code");
		
		final ArrayList<JLabel> answerPrompt = new ArrayList<JLabel>();
		final ArrayList<JLabel> rangePrompt = new ArrayList<JLabel>();
		final ArrayList<JLabel> markPrompt = new ArrayList<JLabel>();
		final ArrayList<JLabel> feedbackPrompt = new ArrayList<JLabel>();
		final ArrayList<JSpinner> answer = new ArrayList<JSpinner>();
		final ArrayList<JSpinner> range = new ArrayList<JSpinner>();
		final ArrayList<JSpinner> mark = new ArrayList<JSpinner>();
		final ArrayList<JTextField> feedback = new ArrayList<JTextField>();
		
		final Dimension labelSize = new Dimension(0, 20);
		
		for(int i=0; i<2; i++){
			
			JLabel label1 = new JLabel("Enter answer:");
			label1.setPreferredSize(labelSize);
			
			JLabel label2 = new JLabel("     Enter mark:");
			label2.setPreferredSize(labelSize);
			
			JLabel label3 = new JLabel("     Enter feedback:");
			label3.setPreferredSize(labelSize);
			
			JLabel label4 = new JLabel("     Enter range:");
			label4.setPreferredSize(labelSize);
			
			answerPrompt.add(label1);
			rangePrompt.add(label4);
			markPrompt.add(label2);
			feedbackPrompt.add(label3);
			answer.add(new JSpinner(new SpinnerNumberModel(0, -999999999, 999999999, 1)));
			range.add(new JSpinner(new SpinnerNumberModel(0, -999999999, 999999999, 1)));
			mark.add(new JSpinner(new SpinnerNumberModel(0, -100, 100, 1)));
			feedback.add(new JTextField());
		}
		
		
		final JPanel answerPanel = new JPanel(new MigLayout("", "[][][][][][][][grow]", "[]"));
		
		
		addAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JLabel label1 = new JLabel("Enter answer:");
				label1.setPreferredSize(labelSize);
				
				JLabel label2 = new JLabel("     Enter mark:");
				label2.setPreferredSize(labelSize);
				
				JLabel label3 = new JLabel("     Enter feedback:");
				label3.setPreferredSize(labelSize);
				
				JLabel label4 = new JLabel("     Enter range:");
				label4.setPreferredSize(labelSize);
				
				answerPrompt.add(label1);
				rangePrompt.add(label4);
				markPrompt.add(label2);
				feedbackPrompt.add(label3);
				answer.add(new JSpinner(new SpinnerNumberModel(0, -999999999, 999999999, 1)));
				range.add(new JSpinner(new SpinnerNumberModel(0, -999999999, 999999999, 1)));
				mark.add(new JSpinner(new SpinnerNumberModel(0, -100, 100, 1)));
				feedback.add(new JTextField());
				
				answerPanel.add(answerPrompt.get(answerPrompt.size()-1), "flowy,cell 0 0");
				answerPanel.add(answer.get(answer.size()-1), "flowy,cell 1 0");		
				answerPanel.add(rangePrompt.get(rangePrompt.size()-1), "flowy,cell 2 0");
				answerPanel.add(range.get(range.size()-1), "flowy,cell 3 0");				
				answerPanel.add(markPrompt.get(markPrompt.size()-1), "flowy,cell 4 0");
				answerPanel.add(mark.get(mark.size()-1), "flowy,cell 5 0");
				answerPanel.add(feedbackPrompt.get(feedbackPrompt.size()-1), "flowy,cell 6 0,alignx trailing");	
				answerPanel.add(feedback.get(feedback.size()-1), "flowy,cell 7 0,growx");
				
				answerPanel.updateUI();				
			}
		});
		
		
		remAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(answer.size()>2){
				
					answerPanel.remove(answerPrompt.get(answerPrompt.size()-1));		
					answerPanel.remove(markPrompt.get(markPrompt.size()-1));
					answerPanel.remove(feedbackPrompt.get(feedbackPrompt.size()-1));
					answerPanel.remove(rangePrompt.get(rangePrompt.size()-1));
					answerPanel.remove(answer.get(answer.size()-1));
					answerPanel.remove(mark.get(mark.size()-1));
					answerPanel.remove(feedback.get(feedback.size()-1));
					answerPanel.remove(range.get(range.size()-1));
					
					answerPrompt.remove(answerPrompt.get(answerPrompt.size()-1));	
					markPrompt.remove(markPrompt.get(markPrompt.size()-1));
					feedbackPrompt.remove(feedbackPrompt.get(feedbackPrompt.size()-1));
					rangePrompt.remove(rangePrompt.get(rangePrompt.size()-1));
					answer.remove(answer.get(answer.size()-1));
					mark.remove(mark.get(mark.size()-1));
					feedback.remove(feedback.get(feedback.size()-1));
					range.remove(range.get(range.size()-1));
					
					answerPanel.updateUI();
				}
			}
		});
		
		
		
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				titleName.setText("");
				question.setText("");
				
				for(int i=0; i<answerPrompt.size(); i++){
					
					answer.get(i).setValue(0);	
					mark.get(i).setValue(0);
					feedback.get(i).setText("");
				}
				
			}
		});	
		
		
		generate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Integer> answerInts = new ArrayList<Integer>();
				ArrayList<Integer> rangeInts = new ArrayList<Integer>();
				ArrayList<Integer> partialInts = new ArrayList<Integer>();
				ArrayList<String> feedbackStrings = new ArrayList<String>();
				
				for(int i=0; i<answer.size(); i++){
					
					answerInts.add((Integer) answer.get(i).getValue());
					rangeInts.add((Integer) range.get(i).getValue());
					partialInts.add((Integer) mark.get(i).getValue());
					feedbackStrings.add(feedback.get(i).getText());				
				}
				
				writeToOutput(GiftGenerator.GenerateNumericalFeedback(titleName.getText(), question.getText(),
						answerInts, rangeInts, partialInts, feedbackStrings));
				
			}
		});
		
		
		
		for(int i=0; i<2; i++){
			
			answerPanel.add(answerPrompt.get(i), "flowy,cell 0 0");	
			answerPanel.add(answer.get(i), "flowy,cell 1 0");
			answerPanel.add(rangePrompt.get(i), "flowy,cell 2 0");
			answerPanel.add(range.get(i), "flowy,cell 3 0");	
			answerPanel.add(markPrompt.get(i), "flowy,cell 4 0");
			answerPanel.add(mark.get(i), "flowy,cell 5 0");
			answerPanel.add(feedbackPrompt.get(i), "flowy,cell 6 0,alignx trailing");		
			answerPanel.add(feedback.get(i), "flowy,cell 7 0,growx");
		}
		
		
		
		JScrollPane answerScroll = new JScrollPane(answerPanel);
		
		panel.add(explanation, "cell 0 0");		
		panel.add(title, "flowx,cell 0 1");
		panel.add(titleName, "cell 0 1,growx");
		panel.add(questionPrompt, "cell 0 2");
		panel.add(question, "cell 0 3,growx");
		panel.add(addAnswer, "flowx,cell 0 4");
		panel.add(remAnswer, "cell 0 4");
		panel.add(clear, "flowx,cell 0 6,alignx right");
		panel.add(generate, "cell 0 6,alignx right");
		panel.add(answerScroll, "cell 0 5,grow");
		
	}



	private void setupNumerical(JPanel panel) {

		panel.setLayout(new MigLayout("", "[grow]", "[][][][][grow]"));
		
		JLabel explanation = new JLabel("Various types of numerical questions.");
			explanation.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel title = new JLabel("Question Title:");		
		JLabel questionPrompt = new JLabel("Enter a mathamatical question.");
		final JLabel answer1 = new JLabel("Enter answer:");
		final JLabel range1 = new JLabel("     Enter range:");	
		final JLabel answer2 = new JLabel("Enter correct answer:");
		final JLabel range2 = new JLabel("     Enter range for partial mark:");
		final JLabel range2mark = new JLabel("     Enter partial mark:");
		final JLabel min = new JLabel("Enter min:");
		final JLabel max = new JLabel("     Enter max:");
		

		final JTextField question = new JTextField();		
		final JTextField titleName = new JTextField();
		
		final JSpinner answer1Spin = new JSpinner(new SpinnerNumberModel(0, -999999999, 999999999, 1));
		final JSpinner range1Spin = new JSpinner(new SpinnerNumberModel(0, -999999999, 999999999, 1));
		final JSpinner answer2Spin = new JSpinner(new SpinnerNumberModel(0, -999999999, 999999999, 1));
		final JSpinner range2Spin = new JSpinner(new SpinnerNumberModel(0, -999999999, 999999999, 1));
		final JSpinner mark2Spin = new JSpinner(new SpinnerNumberModel(0, -100, 100, 1));
		final JSpinner minSpin =  new JSpinner(new SpinnerNumberModel(0, -999999999, 999999999, 1));
		final JSpinner maxSpin =  new JSpinner(new SpinnerNumberModel(0, -999999999, 999999999, 1));

			
		JButton clear = new JButton("Clear");	
		JButton generate = new JButton("Generate Code");

		JPanel answerPanel = new JPanel(new MigLayout("", "[][grow]", "[][][][]"));	
		
		final JPanel rangePanel = new JPanel(new MigLayout("", "[][][][]", "[]"));
			rangePanel.setBorder(new TitledBorder(null, "Range", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			
		final JPanel rangeMarkPanel = new JPanel(new MigLayout("", "[][][][][][]", "[]"));
			rangeMarkPanel.setBorder(new TitledBorder(null, "Range with mark", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			
		final JPanel spanPanel = new JPanel(new MigLayout("", "[][][][]", "[]"));
			spanPanel.setBorder(new TitledBorder(null, "Span", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			
		
		final JRadioButton range = new JRadioButton("Simple Range");
		final JRadioButton rangeMark = new JRadioButton("Range with mark");
		final JRadioButton span = new JRadioButton("Span");
		
		
		ButtonGroup selectGroup= new ButtonGroup();
			selectGroup.add(range);
			selectGroup.add(rangeMark);
			selectGroup.add(span);
			
		range.setSelected(true);
			
			
		range.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				rangePanel.setEnabled(true);
				rangeMarkPanel.setEnabled(false);
				spanPanel.setEnabled(false);
				
				
				
				answer1Spin.setEnabled(true);
				range1Spin.setEnabled(true);
				
				answer2Spin.setEnabled(false);
				range2Spin.setEnabled(false);
				mark2Spin.setEnabled(false);
				
				minSpin.setEnabled(false);
				maxSpin.setEnabled(false);
				
				
				
				answer1.setEnabled(true);
				range1.setEnabled(true);
				
				answer2.setEnabled(false);
				range2.setEnabled(false);
				range2mark.setEnabled(false);
				
				min.setEnabled(false);
				max.setEnabled(false);

			}
		});
		
		
		
		rangeMark.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				rangePanel.setEnabled(false);
				rangeMarkPanel.setEnabled(true);
				spanPanel.setEnabled(false);
				
				
				
				answer1Spin.setEnabled(false);
				range1Spin.setEnabled(false);
				
				answer2Spin.setEnabled(true);
				range2Spin.setEnabled(true);
				mark2Spin.setEnabled(true);
				
				minSpin.setEnabled(false);
				maxSpin.setEnabled(false);
				
				
				
				answer1.setEnabled(false);
				range1.setEnabled(false);
				
				answer2.setEnabled(true);
				range2.setEnabled(true);
				range2mark.setEnabled(true);
				
				min.setEnabled(false);
				max.setEnabled(false);

			}
		});
		
		
		
		span.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				rangePanel.setEnabled(false);
				rangeMarkPanel.setEnabled(false);
				spanPanel.setEnabled(true);
				
				
				
				answer1Spin.setEnabled(false);
				range1Spin.setEnabled(false);
				
				answer2Spin.setEnabled(false);
				range2Spin.setEnabled(false);
				mark2Spin.setEnabled(false);
				
				minSpin.setEnabled(true);
				maxSpin.setEnabled(true);
				
				
				
				answer1.setEnabled(false);
				range1.setEnabled(false);
				
				answer2.setEnabled(false);
				range2.setEnabled(false);
				range2mark.setEnabled(false);
				
				min.setEnabled(true);
				max.setEnabled(true);

			}
		});
		
		
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				titleName.setText("");
				question.setText("");
				
				answer1Spin.setValue(0);
				range1Spin.setValue(0);
				answer2Spin.setValue(0);
				range2Spin.setValue(0);
				mark2Spin.setValue(0);
				minSpin.setValue(0);
				maxSpin.setValue(0);
				
			}
		});	
		
		
		generate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if(range.isSelected()){
					
					int answer = (Integer) answer1Spin.getValue();
					int range = (Integer) range1Spin.getValue();
					
					writeToOutput(GiftGenerator.GenerateNumerical(titleName.getText(), question.getText(), 1, answer, range, 0));
					
					
				}
				else if(rangeMark.isSelected()){
					
					int answer = (Integer) answer2Spin.getValue();
					int range = (Integer) range2Spin.getValue();
					int mark = (Integer) mark2Spin.getValue();
					
					writeToOutput(GiftGenerator.GenerateNumerical(titleName.getText(), question.getText(), 2, answer, range, mark));
					
					
				}
				else if(span.isSelected()){
					
					int min = (Integer) minSpin.getValue();
					int max = (Integer) maxSpin.getValue();
					
					writeToOutput(GiftGenerator.GenerateNumerical(titleName.getText(), question.getText(), 3, min, max, 0));
					
					
				}
				
			}
		});
		
		
		rangePanel.setEnabled(true);
		rangeMarkPanel.setEnabled(false);
		spanPanel.setEnabled(false);
		answer1Spin.setEnabled(true);
		range1Spin.setEnabled(true);
		answer2Spin.setEnabled(false);
		range2Spin.setEnabled(false);
		mark2Spin.setEnabled(false);
		minSpin.setEnabled(false);
		maxSpin.setEnabled(false);
		answer1.setEnabled(true);
		range1.setEnabled(true);
		answer2.setEnabled(false);
		range2.setEnabled(false);
		range2mark.setEnabled(false);
		min.setEnabled(false);
		max.setEnabled(false);
		
	
		panel.add(explanation, "cell 0 0");	
		panel.add(title, "flowx,cell 0 1");
		panel.add(titleName, "flowx,cell 0 1,growx");
		panel.add(questionPrompt, "cell 0 2");
		panel.add(question, "cell 0 3,growx");
		panel.add(answerPanel, "cell 0 4,grow");
		answerPanel.add(range, "cell 0 0");
		answerPanel.add(rangePanel, "cell 1 0,grow");
		rangePanel.add(answer1, "cell 0 0");
		rangePanel.add(answer1Spin, "cell 1 0");
		rangePanel.add(range1, "cell 2 0");
		rangePanel.add(range1Spin, "cell 3 0");	
		answerPanel.add(rangeMark, "cell 0 1");
		answerPanel.add(rangeMarkPanel, "cell 1 1,grow");
		rangeMarkPanel.add(answer2, "cell 0 0");
		rangeMarkPanel.add(answer2Spin, "cell 1 0");
		rangeMarkPanel.add(range2, "cell 2 0");
		rangeMarkPanel.add(range2Spin, "cell 3 0");
		rangeMarkPanel.add(range2mark, "cell 4 0");
		rangeMarkPanel.add(mark2Spin, "cell 5 0");
		answerPanel.add(span, "cell 0 2");
		answerPanel.add(spanPanel, "cell 1 2,grow");
		spanPanel.add(min, "cell 0 0");
		spanPanel.add(minSpin, "cell 1 0");
		spanPanel.add(max, "cell 2 0");
		spanPanel.add(maxSpin, "cell 3 0");		
		answerPanel.add(clear, "flowx,cell 1 3,alignx right");
		answerPanel.add(generate, "cell 1 3,alignx right");
		
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
				
				ArrayList<String> answer1Strings = new ArrayList<String>();
				ArrayList<String> answer2Strings = new ArrayList<String>();
				
				
				for(int i=0; i<answer1.size(); i++){
					answer1Strings.add(answer1.get(i).getText());
					answer2Strings.add(answer2.get(i).getText());
					
				}
				
				writeToOutput(GiftGenerator.GenerateMatching(titleName.getText(), question.getText(), answer1Strings, answer2Strings));

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
				
				ArrayList<String> answerStrings = new ArrayList<String>();
				int correctAnswer = 0;
				
				for(int i=0; i<answers.size(); i++){
					answerStrings.add(answers.get(i).getText());
					
					if(select.get(i).isSelected()){
						correctAnswer = i;
					}
				}
				
				
				
				writeToOutput(GiftGenerator.GenerateMissingWord(titleName.getText(), 
									questionStart.getText(), answerStrings, correctAnswer, questionEnd.getText()));
				
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
		JLabel selectPrompt = new JLabel("Enter all possible answers and add a mark for each.");
		
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
		final ArrayList<JSpinner> marks = new ArrayList<JSpinner>();
		
		
		
		for(int i=0; i<2; i++){
			answers.add(new JTextField());

			SpinnerModel spinnerModel = new SpinnerNumberModel(0, -100, 100, 1);
			marks.add(new JSpinner(spinnerModel));
			
		}
		
		
		addAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				answers.add(new JTextField());
				
				
				SpinnerModel spinnerModel = new SpinnerNumberModel(0, -100, 100, 1);
				marks.add(new JSpinner(spinnerModel));
				
				answerPanel.add(answers.get(answers.size()-1), "flowy,cell 0 1,growx");
				answerPanel.add(marks.get(marks.size()-1), "flowy,cell 1 1,alignx right");
				
				answerPanel.updateUI();
				
			}
		});
		
		
		remAnswer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(answers.size()>2){
				
					answerPanel.remove(answers.get(answers.size()-1));
					answerPanel.remove(marks.get(marks.size()-1));
					
					answers.remove(answers.size()-1);
					marks.remove(marks.get(marks.size()-1));
					
					answerPanel.updateUI();
				}
			}
		});
		
		
		generate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ArrayList<String> answerStrings = new ArrayList<String>();
				ArrayList<Integer> markInts = new ArrayList<Integer>();
			
				
				for(int i=0; i<answers.size(); i++){
					answerStrings.add(answers.get(i).getText());
					markInts.add((Integer) marks.get(i).getValue());
					
				}

				writeToOutput(GiftGenerator.GenerateMultiChoice2(titleName.getText(), question.getText(), answerStrings, markInts));
				
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
			answerPanel.add(marks.get(i), "flowy,cell 1 1,alignx right");
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
				
				
				writeToOutput(GiftGenerator.GenerateMultiChoice1(titleName.getText(), question.getText(), answerStrings, correctAnswer));

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
				
				
				writeToOutput(GiftGenerator.GenerateTrueFalse(titleName.getText(), question.getText(), answer));
				
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
		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				new FileWriter(giftCode.getText());
				
			}
		});
		
		panel.add(scrollPane, "cell 0 0, grow");
		panel.add(clear, "align right, cell 0 1");
		panel.add(save, "align right, cell 0 1");
		
	}
	
	
	
	
	
	private void writeToOutput(String gift){
		
		giftCode.append("//Question " + questionNumber + "\n");
		
		giftCode.append(gift + "\n\n\n");
		
		questionNumber++;
		
	}
	
}
