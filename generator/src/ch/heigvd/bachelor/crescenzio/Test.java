package ch.heigvd.bachelor.crescenzio;

import ch.heigvd.bachelor.crescenzio.datasource.*;
import ch.heigvd.bachelor.crescenzio.dataset.*;
import ch.heigvd.bachelor.crescenzio.server.PHPServer;

public class Test {

	public static void test() {
		
		Project project = new Project("Project", "ch.heigvd.app", "Crescenzio Fabio", "HEIG-VD");
		Datasource source1 = new MySQLDatasource("MySQL1", "hostname", 3333, "admin", "password");
		source1.describe();
		
		Dataset set1 = new MySQLDataset("Set1");
		set1.addField(new Field("Field1"));
		set1.addField(new Field("Field2"));
		set1.addField(new Field("Field3"));
		set1.addField(new Field("Field4"));
		
		
		Dataset set2 = new MySQLDataset("Set1");
		set2.addField(new Field("Field1"));
		set2.addField(new Field("Field2"));
		set2.addField(new Field("Field3"));
		set2.addField(new Field("Field4"));
		
		source1.addDataset(set1);
		source1.addDataset(set2);
		
		project.setServer(new PHPServer("PHPServer", "localhost", "/"));
		project.addDatasource(source1);
		project.generateProject();
	}
	
	public static void main(String ... arg){
		test();
	}
}
