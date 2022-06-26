package GroupProject.groupproject.entity;

import java.util.ArrayList;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

@Container(containerName = "myContainer", ru = "400")
public class ContentKeyword {

	private long id;
	
	@PartitionKey
	private String word;
	
	private ArrayList<Long> listOfIds = new ArrayList<Long>();
	
	public ContentKeyword() {
		
	}
	
	public ContentKeyword(String word, Long id){
		this.word = word;
		listOfIds.add(id);
	}
	
	public String getWord() {
		return this.word;
	}
	
	public ArrayList<Long> getListOfIds(){
		return this.listOfIds;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void addId(long id) {
		listOfIds.add(id);
	}
}
