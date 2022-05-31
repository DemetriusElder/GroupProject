package GroupProject.groupproject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.time.LocalDateTime;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@RestController
public class Controllers {
	
	@RequestMapping("/")
	public String HomePage() throws FileNotFoundException, IOException
	{
		return "Hello!";
	}
	
	@GetMapping("/entries")
	public static String getEntries() throws FileNotFoundException, IOException
	{
		// reading file and parsing each blog entry
		Path filePath = Path.of("src\\main\\resources\\static\\postentries.txt").toAbsolutePath();
		String fileTest = Files.readString(filePath);
		String[] splitStrings = fileTest.split("[$]#&");

		Entries[] entries = new Entries[splitStrings.length];
		ArrayList<String> arrList = new ArrayList<String>();
		
		// assigning data to blog entry variables
		for(int i = 0; i<splitStrings.length;i++) {
			
			for (String parsedStr: splitStrings[i].split(",",6)) {
				arrList.add(parsedStr);
			}
			Entries entry = new Entries();
			
			entry.setId(Integer.parseInt(arrList.get(0)));
			entry.setImageUrl(arrList.get(1));
			entry.setTitle(arrList.get(2));
			entry.setDate(arrList.get(3));
			entry.setAuthor(arrList.get(4));
			entry.setContent(arrList.get(5));
			
			entries[i]=entry;
			
			arrList.clear();
			
		}
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		String jsonStr = mapper.writeValueAsString(entries);
		
		jsonStr = jsonStr.replaceAll("\\\\r", "");
		
		return jsonStr;
		
		
	}
	@PostMapping("/post")
	public static String postEntries(String title, String date, String author, String post) throws IOException
	{
	 // Creates a FileWriter
      FileWriter file = new FileWriter("C:\\Users\\wishm\\GroupProject\\spring\\groupproject\\src\\main\\resources\\static\\postentries.txt", true);
      System.out.println("It works1");
      // Creates a BufferedWriter
      BufferedWriter output = new BufferedWriter(file);
      System.out.println("It works2");
      // Writes the string to the file
      output.write(title +"," + date + "," + author + "," + post + "$&#");
      System.out.println("It works3");
      // Closes the writer
      output.close();
      System.out.println("It works4");
	return null;
	}
}