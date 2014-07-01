package ch.heigvd.bachelor.crescenzio.server;

import java.util.LinkedList;

import ch.heigvd.bachelor.crescenzio.datasource.*;
import ch.heigvd.bachelor.crescenzio.dataset.*;

public abstract class Server {
	private String name;
	private String host;
	private String rootFolder;
	

	protected Server(String name, String host, String rootFolder) {
		this.name = name;
		this.host = host;
		this.rootFolder = rootFolder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getRootFolder() {
		return rootFolder;
	}

	public void setRootFolder(String rootFolder) {
		this.rootFolder = rootFolder;
	}
	
	public abstract void generateScripts(LinkedList<Datasource> datasources, LinkedList<Field> fields);

}
