package ch.heigvd.bachelor.crescenzio.generator;

import java.util.LinkedList;

import ch.heigvd.bachelor.crescenzio.generator.datasets.Dataset;
import ch.heigvd.bachelor.crescenzio.generator.datasets.MySQLDataset;
import ch.heigvd.bachelor.crescenzio.generator.datasources.Datasource;
import ch.heigvd.bachelor.crescenzio.generator.datasources.MySQLDatasource;
import ch.heigvd.bachelor.crescenzio.generator.outputs.AbstractOutputApplication;
import ch.heigvd.bachelor.crescenzio.generator.outputs.AndroidOutputApplication;
import ch.heigvd.bachelor.crescenzio.generator.outputs.FileField;
import ch.heigvd.bachelor.crescenzio.generator.outputs.ItemType;
import ch.heigvd.bachelor.crescenzio.generator.outputs.StringField;
import ch.heigvd.bachelor.crescenzio.generator.server.Server;

public class Project {
  private String name;
  private String packageName;
  private String author;
  private String organisation;
  private String icon;
  private Server server;
  private LinkedList<AbstractOutputApplication> outputs;
  private LinkedList<Datasource> datasources;
  private LinkedList<Field> fields;

  private static LinkedList<Project> projects;

  public Project(String name, String packageName, String author,
      String organisation, String icon) {
    if (Project.projects == null) Project.projects = new LinkedList<Project>();
    this.name = name;
    this.packageName = packageName;
    this.author = author;
    this.organisation = organisation;
    this.icon = icon;
    this.outputs = new LinkedList<AbstractOutputApplication>();
    this.datasources = new LinkedList<Datasource>();
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

  public LinkedList<Datasource> getDatasources() {
    return datasources;
  }

  public void addDatasource(Datasource datasource) {
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

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getIcon() {
    return icon;
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

    for (Datasource source : datasources) {
      System.out.println("Name :" + source.getName());

      for (Dataset set : source.getDatasets()) {
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
    if (projects == null) {
      projects = new LinkedList<Project>();
      Project p1 = new Project("P1", "pck1", "author1", "organ1", "icon1");
      AndroidOutputApplication android = new AndroidOutputApplication(p1, "Android output");
      android.addApplicationField(new Field("project_icon72"), new FileField());
      android.addApplicationField(new Field("application_title"), new StringField(p1.getName()));
      android.setField(new Field("name"), null);
      android.setField(new Field("icon"), null);
      android.setField(new Field("date"), null);
      android.setField(new Field("description"), null);
      android.addItemType(new ItemType("user"));
      android.addItemType(new ItemType("task"));

      MySQLDatasource datasource = new MySQLDatasource("MySQL", "localhost", 3306, "letstodoit", "root", "Qwe12345");
      p1.addDatasource(datasource);
      datasource.addDataset(new MySQLDataset("Set1", "SELECT * FROM tasks"));
      datasource.addDataset(new MySQLDataset("Set2", "SELECT * FROM users"));

      p1.addField(new Field("Name"));
      p1.addField(new Field("Type"));
      p1.addField(new Field("Date"));
      p1.addField(new Field("Icon"));
      p1.addOutput(android);

      new Project("P2", "pck2", "author2", "organ2", "icon2");
      new Project("P3", "pck3", "author3", "organ3", "icon3");
      new Project("P4", "pck4", "author4", "organ4", "icon4");
    }
    return projects;
  }

}
