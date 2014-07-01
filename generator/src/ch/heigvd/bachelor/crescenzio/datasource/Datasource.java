package ch.heigvd.bachelor.crescenzio.datasource;

import java.util.HashMap;
import java.util.LinkedList;

import ch.heigvd.bachelor.crescenzio.dataset.Dataset;
import ch.heigvd.bachelor.crescenzio.dataset.Field;
public abstract class Datasource {
	private String name;
	private LinkedList<Dataset> datasets;
	private boolean isConnectionOpen;
	
	protected Datasource(String name){
		this.name = name;
		this.datasets = new LinkedList<Dataset>();
		this.isConnectionOpen = false;
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
	
	public boolean isConnectionOpen(){
		return isConnectionOpen;
	}
	
	public void setConnectionOpenStatus(boolean status){
		isConnectionOpen = status;
	}
	
	
	public abstract boolean connect();
	public abstract boolean disconnect();
	public abstract void describe();
	public abstract void query(String query);
	public abstract LinkedList<Field> getFields();
	public abstract HashMap<String, String> getDatas();
}
