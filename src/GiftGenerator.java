

public class GiftGenerator {
	
	
	public static String GenerateTrueFalse(String title, String statement, boolean answer){
		
		/**
			::TrueStatement about Grant::Grant was buried in a tomb in New York City.{T}
			::FalseStatement about sun::The sun rises in the West.{FALSE}
		**/
		
		String question = "";
		
		if(title.compareTo("") != 0){		
			question += "::" + title + "::";
		}
		
		question += statement;
		
		if(answer == true){
			question += "{TRUE}";
		}	
		else{
			question += "{FALSE}";
		}

		return question;
		
	}
	
}
