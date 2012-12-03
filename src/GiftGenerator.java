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
	
	
	

	public static String GenerateMultiChoice(String title, String question, ArrayList<String> answers, int correctAnswer){
		
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
	
}
