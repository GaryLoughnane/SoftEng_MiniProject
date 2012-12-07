
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;


public class FileWriter {
	
	FileWriter(String contents){
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy-HH-mm-ss");
		Date date = new Date();
		
		String filename = "GiftFile_" + dateFormat.format(date) + ".txt";
			
		try {
			
			FileUtils.writeStringToFile(new File(filename), contents);
		} 
		catch (IOException e){

			System.out.println("Error writing file");
		}
		
	}

}
