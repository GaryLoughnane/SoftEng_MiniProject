import java.util.ArrayList;



public class GiftGenerator {
	
	
	public static String GenerateTrueFalse(String title, String statement, boolean answer){
		
		/**
			::TrueStatement about Grant::Grant was buried in a tomb in New York City.{T}
			::FalseStatement about sun::The sun rises in the West.{FALSE}
		**/
		
		String gift = "";
		
		if(title.compareTo("") != 0){		
			gift += "::" + title + "::";
		}
		
		gift += statement;
		
		if(answer == true){
			gift += "{TRUE}";
		}	
		else{
			gift += "{FALSE}";
		}

		return gift;
		
	}
	
	
	

	public static String GenerateMultiChoice1(String title, String question, ArrayList<String> answers, int correctAnswer){
		
		/**
		 	::Grants tomb::Who is buried in Grant's tomb in New York City? {
			=Grant
			~No one
			~Napoleon
			~Churchill
			~Mother Teresa
			}
		**/
		
		String gift = "";
		
		if(title.compareTo("") != 0){		
			gift += "::" + title + "::";
		}
		
		gift += question + "{\n";
		
		for(int i=0; i<answers.size(); i++){
			
			if(i == correctAnswer){
				gift += "=";
			}
			else{
				gift += "~";
			}
			
			gift += answers.get(i) + "\n";
			
		}
		
		gift += "}";
		
		return gift;

	}
	
	
	
	public static String GenerateMultiChoice2(String title, String question, ArrayList<String> answers, ArrayList<Integer> marks){
		
		/**
 			What two people are entombed in Grant's tomb? {
		   ~%-100%No one
		   ~%50%Grant
		   ~%50%Grant's wife
		   ~%-100%Grant's father
			}
		**/
		
		String gift = "";
		
		if(title.compareTo("") != 0){		
			gift += "::" + title + "::";
		}
		
		gift += question + "{\n";
		
		for(int i=0; i<answers.size(); i++){
			
			gift += "~%" + marks.get(i) + "%" + answers.get(i) + "\n";			
		}
		
		gift += "}";
		
		return gift;

	}
	
	
	
	public static String GenerateMissingWord(String title, String start, ArrayList<String> answers, int correctAnswer, String end){
		
		/**
 			Since {
			  ~495 AD
			  =1066 AD
			  ~1215 AD
			  ~ 43 AD
			}
			the town of Hastings England has been "famous with visitors".
		**/
		
		String gift = "";
		
		if(title.compareTo("") != 0){		
			gift += "::" + title + "::" + "\n";
		}
		
		gift += start + "{\n";
		
		for(int i=0; i<answers.size(); i++){
			
			if(i == correctAnswer){
				gift += "=";
			}
			else{
				gift += "~";
			}
			
			gift += answers.get(i) + "\n";
			
		}
		
		gift += "}\n" + end;
		
		return gift;

	}
	
	
	
	public static String GenerateMatching(String title, String question, ArrayList<String> answer1, ArrayList<String> answer2){
		
		/**
 			Match the following countries with their corresponding capitals. {
		   =Canada -> Ottawa
		   =Italy  -> Rome
		   =Japan  -> Tokyo
		   =India  -> New Delhi
		   }
		**/
		
		String gift = "";
		
		if(title.compareTo("") != 0){		
			gift += "::" + title + "::" + "\n";
		}
		
		gift += question + "{\n";
		
		for(int i=0; i<answer1.size(); i++){
			
			gift += answer1.get(i) + " -> " + answer2.get(i) + "\n";	
		}
		
		gift += "}";
		
		return gift;
	
	}
	
	
	
	public static String GenerateNumerical(String title, String question, int type, int answer, int range, int partial){
		
		/**
 			What is the value of pi (to 3 decimal places)? {#
 				3.14159:0.0005
 			}
 						
 			When was Ulysses S. Grant born? {#
			   =1822:0
			   =%50%1822:2
			}
						
			What is the value of pi (to 3 decimal places)? {#
				3.141..3.142
			}
		**/
		
		String gift = "";
		
		if(title.compareTo("") != 0){		
			gift += "::" + title + "::" + "\n";
		}
		
		gift += question + " {#\n";
		
		
		
		
		if(type == 1){
			gift += answer + ":" + range;
		}
		else if(type == 2){
			gift += "=" + answer + ":0\n";
			gift += "=%" + partial + "%" + answer + ":" + range;
		}
		else if(type == 3){
			gift += answer + ".." + range;
		}
		
		gift += "\n}";
		
		return gift;
	
	}
	
	
	
	
}
