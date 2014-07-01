package ch.heigvd.bachelor.crescenzio.datasource;

import java.util.HashMap;
import java.util.LinkedList;

import ch.heigvd.bachelor.crescenzio.dataset.Dataset;
import ch.heigvd.bachelor.crescenzio.dataset.Field;
public abstract class Datasource {
	private String name;
	private LinkedList<Dataset> datasets;
	
	protected Datasource(String name){
		this.name = name;
		this.datasets = new LinkedList<Dataset>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addDataset(Dataset dataset){
		this.datasets.add(dataset);
	}
	
	public void removeDataset(Dataset dataset){
		this.datasets.remove(datasets);
	}
	
	public LinkedList<Dataset> getDatasets(){
		return datasets;
	}
	
	public void emptyDatasets(){
		this.datasets.clear();
	}
	
	public abstract boolean connect();
	public abstract boolean disconnect();
	public abstract LinkedList<Field> getFields();
	public abstract HashMap<String, String> getDatas();
}
