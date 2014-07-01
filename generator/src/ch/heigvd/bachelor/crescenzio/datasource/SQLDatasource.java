package ch.heigvd.bachelor.crescenzio.datasource;

import java.util.HashMap;
import java.util.LinkedList;

import ch.heigvd.bachelor.crescenzio.dataset.Field;

public abstract class SQLDatasource extends Datasource {
	
	protected SQLDatasource(String name) {
		super(name);
	}

	private String hostname;
	private int port;
	private String login;
	private String password;

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
