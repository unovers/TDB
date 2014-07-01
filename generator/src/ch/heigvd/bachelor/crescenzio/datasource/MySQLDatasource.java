package ch.heigvd.bachelor.crescenzio.datasource;

import java.util.HashMap;
import java.util.LinkedList;

import ch.heigvd.bachelor.crescenzio.dataset.Field;

public class MySQLDatasource extends SQLDatasource {

	protected MySQLDatasource(String name) {
		super(name);
	}

	@Override
	public boolean connect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean disconnect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LinkedList<Field> getFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> getDatas() {
		// TODO Auto-generated method stub
		return null;
	}

}
