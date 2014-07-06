package ch.heigvd.bachelor.crescenzio.generator;

import java.util.LinkedList;

import ch.heigvd.bachelor.crescenzio.generator.dataset.Dataset;
import ch.heigvd.bachelor.crescenzio.generator.datasource.Datasource;
import ch.heigvd.bachelor.crescenzio.generator.output.OutputApplication;
import ch.heigvd.bachelor.crescenzio.generator.server.Server;

public class Project {
  private String name;
  private String packageName;
  private String author;
  private String organisation;
  private String icon;
  private Server server;
  private LinkedList<OutputApplication> outputs;
  private LinkedList<Datasource> datasources;
  private LinkedList<Field> fields;

  public Project(String name, String packageName, String author,
      String organisation, String icon) {
    this.name = name;
    this.packageName = packageName;
    this.author = author;
    this.organisation = organisation;
    this.icon = icon;
    this.outputs = new LinkedList<OutputApplication>();
    this.datasources = new LinkedList<Datasource>();
    this.fields = new LinkedList<Field>();
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

  public LinkedList<OutputApplication> getOutputs() {
    return outputs;
  }

  public void addOutput(OutputApplication output) {
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

  public void create() {
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

}
