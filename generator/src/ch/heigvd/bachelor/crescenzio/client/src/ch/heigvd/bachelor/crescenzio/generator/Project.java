package ch.heigvd.bachelor.crescenzio.generator;

import java.util.HashMap;
import java.util.LinkedList;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.criterias.Criteria;
import ch.heigvd.bachelor.crescenzio.generator.datasets.AbstractDataset;
import ch.heigvd.bachelor.crescenzio.generator.datasets.MySQLDataset;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;
import ch.heigvd.bachelor.crescenzio.generator.datasources.MySQLDatasource;
import ch.heigvd.bachelor.crescenzio.generator.outputs.AbstractOutputApplication;
import ch.heigvd.bachelor.crescenzio.generator.server.PHPServer;
import ch.heigvd.bachelor.crescenzio.generator.server.Server;

public class Project {
  private static LinkedList<Project> projects;

  public static boolean destroy(Project project) {
    projects.remove(project);
    return true;
  }

  /**
   * @return
   */
  public static LinkedList<Project> getAll()
  {
    if (projects == null || projects.size() == 0) {
      projects = new LinkedList<Project>();
      Project p1 = new Project("P1", "pck1", "author1", "organ1");
      p1.setServer(new PHPServer("localhost", "a"));
      MySQLDatasource datasource = new MySQLDatasource("MySQL", "localhost", 3306, "letstodoit", "root", "Qwe12345");
      MySQLDatasource datasource2 = new MySQLDatasource("MySQL2", "localhost", 3306, "example", "root", "");
      p1.addDatasource(datasource);
      p1.addDatasource(datasource2);
      datasource.addDataset(new MySQLDataset(datasource, "Set1", "SELECT * FROM tasks;"));
      datasource.addDataset(new MySQLDataset(datasource, "Set2", "SELECT * FROM users;"));
      datasource2.addDataset(new MySQLDataset(datasource2, "Set1", "SELECT * FROM table1;"));

      p1.addField(new Field("Name"));
      p1.addField(new Field("Type"));
      p1.addField(new Field("Date"));
      p1.addField(new Field("Icon"));

      new Project("P2", "pck2", "author2", "organ2");
      new Project("P3", "pck3", "author3", "organ3");
      new Project("P4", "pck4", "author4", "organ4");

    }
    return projects;
  }

  private String author;
  private LinkedList<Criteria> criterias;
  private LinkedList<AbstractDatasource> datasources;
  private LinkedList<Field> fields;
  private HashMap<AbstractDataset, HashMap<Field, String>> mapping = new HashMap<AbstractDataset, HashMap<Field, String>>();
  private String name;
  private String organisation;

  private LinkedList<AbstractOutputApplication> outputs;

  private String packageName;

  private Server server;

  public Project(String name, String packageName, String author,
      String organisation) {
    if (Project.projects == null) Project.projects = new LinkedList<Project>();
    this.name = name;
    this.packageName = packageName;
    this.author = author;
    this.organisation = organisation;
    this.outputs = new LinkedList<AbstractOutputApplication>();
    this.datasources = new LinkedList<AbstractDatasource>();
    this.criterias = new LinkedList<Criteria>();
    this.fields = new LinkedList<Field>();
    fields.add(new Field("__item_type"));
    projects.add(this);
  }

  public void addCriteria(Criteria criteria) {
    this.criterias.add(criteria);
  }

  public void addDatasource(AbstractDatasource datasource) {
    this.datasources.add(datasource);
  }

  public void addField(Field field) {
    this.fields.add(field);
  }

  public void addOutput(AbstractOutputApplication output) {
    this.outputs.add(output);
  }

  public boolean create() {
    throw new UnsupportedOperationException("NOT IMPLEMENTED YET");
  }

  public void generateProject() {

    System.out.println("Project infos : ");
    System.out.println("Project name         : " + name);
    System.out.println("Project author       : " + author);
    System.out.println("Project organisation : " + organisation);
    System.out.println("Project package      : " + packageName);
    System.out.println();

    System.out.println("Server infos : ");
    System.out
        .println("host: " + server.getHost() + server.getRootFolder());
    try {
      getServer().generateScripts(this);
    }
    catch (ProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    System.out.println("Fields : ");
    for (Field field : fields) {
      System.out.print(field.getName() + " ");
    }
    System.out.println();

    System.out.println("Datasources info : ");

    for (AbstractDatasource source : datasources) {
      System.out.println("Name :" + source.getName());

      for (AbstractDataset set : source.getDatasets()) {
        System.out.println("dataset " + set.getName());
        for (Field field : set.getFields()) {
          System.out.print(field.getName() + " ");
        }
        System.out.println();
      }
      System.out.println();
    }

  }

  public String getAuthor() {
    return author;
  }

  public LinkedList<Criteria> getCriterias() {
    return criterias;
  }

  public LinkedList<AbstractDatasource> getDatasources() {
    return datasources;
  }

  public LinkedList<Field> getFields() {
    return fields;
  }

  /**
   * @param field
   * @param dataset
   * @return
   */
  public String getMapping(Field field, AbstractDataset dataset) {
    HashMap<Field, String> datasetmap = mapping.get(dataset);
    if (datasetmap == null) {
      mapping.put(dataset, new HashMap<Field, String>());
      return "";
    }
    else {
      String value = datasetmap.get(field);
      if (value == null) {
        value = "";
      }
      return value;
    }
  }

  public String getName() {
    return name;
  }

  public String getOrganisation() {
    return organisation;
  }

  public LinkedList<AbstractOutputApplication> getOutputs() {
    return outputs;
  }

  public String getPackageName() {
    return packageName;
  }

  public Server getServer() {
    return server;
  }

  public void removeField(Field field) {
    this.fields.remove(field);
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * @param field
   * @param dataset
   */
  public void setMapping(Field field, AbstractDataset dataset, String value) {
    HashMap<Field, String> datasetmap = mapping.get(dataset);
    if (datasetmap == null) {
      mapping.put(dataset, new HashMap<Field, String>());
    }
    else {
      if (datasetmap.get(field) != null) datasetmap.remove(field);
      datasetmap.put(field, value);
    }
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setOrganisation(String organisation) {
    this.organisation = organisation;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public void setServer(Server server) {
    this.server = server;
  }

  /**
   * @param criteria
   */
  public void removeCriteria(Criteria criteria) {
    this.criterias.remove(criteria);

  }
}
