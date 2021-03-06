package GroupProject.groupproject;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.CosmosDatabase;
import com.azure.cosmos.implementation.apachecommons.lang.ArrayUtils;
import com.azure.cosmos.models.CosmosContainerProperties;
import com.azure.cosmos.models.CosmosContainerResponse;
import com.azure.cosmos.models.CosmosDatabaseResponse;
import com.azure.cosmos.models.CosmosItemResponse;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;

import GroupProject.groupproject.entity.ContentKeyword;
import GroupProject.groupproject.entity.Entry;
import GroupProject.groupproject.repository.ContentKeywordRepository;
import GroupProject.groupproject.repository.EntryRepository;
import reactor.core.publisher.Mono;

@SpringBootApplication
@CrossOrigin(origins="*")
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan("GroupProject.groupproject")
public class BlogWriterApplication implements CommandLineRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BlogWriterApplication.class);
	
	@Autowired
	private ContentKeywordRepository keywordRepo;

//	@Autowired
//    private EntryRepository repository;
	
	/*disabling CORS*/
	@Bean
	public WebMvcConfigurer configure() {
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");;
			}
		};
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(BlogWriterApplication.class, args);
	
	}
	
	/* Populating Cosmos DB with existing data in h2 
	 * 
	 * YOU ONLY NEED TO RUN THIS ONCE TO GET EXISTING DATA!!
	 * YOU CAN COMMENT IT OUT AFTER THAT FIRST RUN! :D
	 * It takes a long time :c
	 * 
	 * Steps:
	 * 1. Go above at our BlogWriterApp class and uncomment CommandLineRunner
	 * 2. Uncomment the ContentKeywordRepo right underneath
	 * 3. Uncomment this run function
	 * 4. You may or may not have to change the credentials (.endpoint/.key)
	 * 	      & other things (databaseName/containerName) to match your cosmos
	 * 		  (idk if you can access mine)
	 *        If you do have to change them, remember to do it in the application.properties too!
	 * 5. Run it and wait like 5-10 mins DX
	 * 6. Remember to comment everything that you uncommented out when you're done!
	 * 
	 * */
	public void run(String... var1) throws SQLException {
		
		CosmosClient client;
		keywordRepo.deleteAll();

		final String databaseName = "blogwriter";
		//name of the container in the database 
		final String containerName = "contentKeywords";
		CosmosDatabase database;
		CosmosContainer container;
		
		// connecting database client to cosmos database
		client = new CosmosClientBuilder()
				.endpoint("https://blogwriter.documents.azure.com:443/")
				.key("sebfr4Csea1kBbpXNg9NhBToRxr083EunihpVrHfybxee8hBUWh0XRULRGC4AXKin1iqfgQ5SGqT9U7zVM71tA==")
				.buildClient();

		CosmosDatabaseResponse databaseResponse = client.createDatabaseIfNotExists(databaseName);
		database = client.getDatabase(databaseResponse.getProperties().getId());
		CosmosContainerProperties containerProperties = new CosmosContainerProperties(containerName, "word");
		CosmosContainerResponse containerResponse = database.createContainerIfNotExists(containerProperties);
		container = database.getContainer(containerResponse.getProperties().getId());

		try {
			// connecting to h2
			String myUrl = "jdbc:h2:file:./BlogDatabase";
			Connection conn = DriverManager.getConnection(myUrl, "sa", "");
			
			// query to grab all the blog entries in h2
			String query = "SELECT * FROM ENTRY";
			
			Statement st = conn.createStatement();
			
			// set of all the entries
			ResultSet rs = st.executeQuery(query);

			while(rs.next())  {	
				
					//taking out all the punctuation (except ') + newlines, making it lowercase
				String parseContent = rs.getString("content").replaceAll("[\\p{P}&&[^\\u0027]&&[\n]]", "").toLowerCase();
				String parseTitle = rs.getString("title").replaceAll("[\\p{P}&&[^\\u0027]&&[\n]]", "").toLowerCase();
				String parseAuthor = rs.getString("author").replaceAll("[\\p{P}&&[^\\u0027]&&[\n]]", "").toLowerCase();
				// getting entry id to store into the keywords later
				Long contentIndex = rs.getLong("id");
				
				String[] arrContent = parseContent.split(" ");
				String[] arrTitle = parseTitle.split(" ");
				String[] arrAuthor = parseAuthor.split(" ");
				ArrayList<String> arr = new ArrayList<String>();
				arr.addAll(Arrays.asList(arrContent));
				arr.addAll(Arrays.asList(arrTitle));
				arr.addAll(Arrays.asList(arrAuthor));
				
				//removing duplicates
				Set<String> set = new HashSet<String>(arr);
				
				// looping through ALL the words
				for(String s : set) {
					
					// if the word is not in the repo, create a new keyword
					if (keywordRepo.findByWord(s).isEmpty()) {
						ContentKeyword keyword = new ContentKeyword(s, contentIndex);
						keywordRepo.save(keyword);
					}
					// else add the entry id into the existing keyword
					else {
						Optional<ContentKeyword> opt = keywordRepo.findById(s);
						if(opt.isPresent()) {
						ContentKeyword word = opt.get();
							if(!word.getListOfIds().contains(contentIndex)) {
								word.addId(contentIndex);
								keywordRepo.save(word);
							}
						}
					}
				}
				
				}
			
		}
	
		finally {}
	
   }

}
