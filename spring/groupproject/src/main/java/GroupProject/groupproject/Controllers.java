package GroupProject.groupproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.time.LocalDateTime;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
		Path filePath = Path.of("C:\\Users\\wishm\\GroupProject\\spring\\groupproject\\src\\main\\resources\\static\\postentries.txt");
		String fileTest = Files.readString(filePath);
		String[] splitStrings = fileTest.split("[$]#&");

//		ObjectMapper mapTime = new ObjectMapper();
//		mapTime.registerModule(new JavaTimeModule());
//		mapTime.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		Entries[] entries = new Entries[splitStrings.length];
		ArrayList<String> arrList = new ArrayList<String>();
		
		for(int i = 0; i<splitStrings.length;i++) {
			
			for (String parsedStr: splitStrings[i].split(",",5)) {
				arrList.add(parsedStr);
			}
			Entries entry = new Entries();
			
			entry.setId(Integer.parseInt(arrList.get(0)));
			entry.setTitle(arrList.get(1));
			entry.setDate(arrList.get(2));
			entry.setAuthor(arrList.get(3));
			entry.setContent(arrList.get(4));
			
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
}