package ch.heigvd.bachelor.crescenzio.generator;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.dataset.Dataset;
import ch.heigvd.bachelor.crescenzio.generator.dataset.MySQLDataset;
import ch.heigvd.bachelor.crescenzio.generator.datasource.Datasource;
import ch.heigvd.bachelor.crescenzio.generator.datasource.MySQLDatasource;
import ch.heigvd.bachelor.crescenzio.generator.server.PHPServer;

public class Test {

  public static void test() {

    Project project = new Project("Project", "ch.heigvd.app", "Crescenzio Fabio", "HEIG-VD", "icon.png");
    Datasource source1 = new MySQLDatasource("MySQL1", "hostname", 3333, "database", "admin", "password");
    try {
      source1.describe();
    }
    catch (ProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

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

  public static void main(String... arg) {
    test();
  }
}
