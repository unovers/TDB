package ch.heigvd.bachelor.crescenzio.generator;

import java.util.HashMap;
import java.util.LinkedList;

import ch.heigvd.bachelor.crescenzio.generator.datasets.AbstractDataset;
import ch.heigvd.bachelor.crescenzio.generator.datasets.MySQLDataset;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;
import ch.heigvd.bachelor.crescenzio.generator.datasources.MySQLDatasource;
import ch.heigvd.bachelor.crescenzio.generator.outputs.AbstractOutputApplication;
import ch.heigvd.bachelor.crescenzio.generator.server.Server;

public class Project {
  private String name;
  private String packageName;
  private String author;
  private String organisation;
  private Server server;
  private LinkedList<AbstractOutputApplication> outputs;
  private LinkedList<AbstractDatasource> datasources;
  private LinkedList<Field> fields;
  private HashMap<AbstractDataset, HashMap<Field, String>> mapping = new HashMap<AbstractDataset, HashMap<Field, String>>();

  private static LinkedList<Project> projects;

  public Project(String name, String packageName, String author,
      String organisation) {
    if (Project.projects == null) Project.projects = new LinkedList<Project>();
    this.name = name;
    this.packageName = packageName;
    this.author = author;
    this.organisation = organisation;
    this.outputs = new LinkedList<AbstractOutputApplication>();
    this.datasources = new LinkedList<AbstractDatasource>();
    this.fields = new LinkedList<Field>();
    fields.add(new Field("__item_type"));
    projects.add(this);
  }

  public void addField(Field field) {
    this.fields.add(field);
  }

  public void removeField(Field field) {
    this.fields.remove(field);
  }

  public Server getServer() {
    return server;
  }

  public void setServer(Server server) {
    this.server = server;
  }

  public LinkedList<AbstractOutputApplication> getOutputs() {
    return outputs;
  }

  public void addOutput(AbstractOutputApplication output) {
    this.outputs.add(output);
  }

  public LinkedList<AbstractDatasource> getDatasources() {
    return datasources;
  }

  public void addDatasource(AbstractDatasource datasource) {
    this.datasources.add(datasource);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LinkedList<Field> getFields() {
    return fields;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getOrganisation() {
    return organisation;
  }

  public void setOrganisation(String organisation) {
    this.organisation = organisation;
  }

  public boolean create() {
    throw new UnsupportedOperationException("NOT IMPLEMENTED YET");
  }

  public static boolean destroy(Project project) {
    projects.remove(project);
    return true;
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
    getServer().generateScripts(datasources, fields);

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

  /**
   * @return
   */
  public static LinkedList<Project> getAll()
  {
    if (projects == null || projects.size() == 0) {
      projects = new LinkedList<Project>();
      Project p1 = new Project("P1", "pck1", "author1", "organ1");

      MySQLDatasource datasource = new MySQLDatasource("MySQL", "localhost", 3306, "letstodoit", "root", "Qwe12345");
      p1.addDatasource(datasource);
      datasource.addDataset(new MySQLDataset("Set1", "SELECT * FROM tasks"));
      datasource.addDataset(new MySQLDataset("Set2", "SELECT * FROM users"));

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
}
