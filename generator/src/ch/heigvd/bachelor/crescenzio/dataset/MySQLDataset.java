package ch.heigvd.bachelor.crescenzio.dataset;

import java.util.HashMap;

public class MySQLDataset extends Dataset {

	private String query;
	
	protected MySQLDataset(String name) {
		super(name);
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	@Override
	public HashMap<String, String> preview() {
		return null;
	}

}
