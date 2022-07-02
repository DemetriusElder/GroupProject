package GroupProject.groupproject.entity;

import java.util.ArrayList;

import javax.persistence.*;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

@Container(containerName = "contentKeywords", ru = "400")
public class ContentKeyword {

	@Id
	private String id;
	
	private String word;
	
	private ArrayList<Long> listOfIds = new ArrayList<Long>();
	
	public ContentKeyword() {
		
	}
	
	public ContentKeyword(String word, Long id){
		this.word = word;
		this.id = word;
		listOfIds.add(id);
	}
	
	public String getWord() {
		return this.word;
	}
	
	public String getId() {
		return this.id;
	}
	
	public ArrayList<Long> getListOfIds(){
		return this.listOfIds;
	}
	
	public void addId(Long id) {
		System.out.println("adding id");
		this.listOfIds.add(id);
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	public void setId(String id) {
		this.id = id;
	}
}
