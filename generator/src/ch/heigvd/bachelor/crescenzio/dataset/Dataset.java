package ch.heigvd.bachelor.crescenzio.dataset;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class Dataset {
	private String name;
	private LinkedList<Field> fields;
	
	protected Dataset(String name){
		this.fields = new LinkedList<Field>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addField(Field field){
		this.fields.add(field);
	}
	
	public abstract HashMap<String, String> preview();
	
}
