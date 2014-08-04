/**
 * Nom du fichier         : Project.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Cette classe réprésente un projet de géneration
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
import ch.heigvd.bachelor.crescenzio.generator.criterias.Criteria;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDataset;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;
import ch.heigvd.bachelor.crescenzio.generator.outputs.AbstractOutputGenerator;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputApplication;
import ch.heigvd.bachelor.crescenzio.generator.server.AbstractServer;

/**
 * Define a project for application generation
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class Project {
  private static LinkedList<Project> projects; //La liste de tous les projets instanciés

  public static boolean destroy(Project project) {
    projects.remove(project);
    return true;
  }

  /**
   * @return all instancied projects
   */
  public static LinkedList<Project> getAll()
  {
    if (projects == null) {
      projects = new LinkedList<Project>();
    }
    return projects;
  }

  private String author;//L'autheur du projet
  private LinkedList<Criteria> criterias;//la liste des critères
  private LinkedList<AbstractDatasource> datasources;//la liste des sources de données
  private LinkedList<Field> fields;//la liste des champs déclarés
  private HashMap<AbstractDataset, HashMap<Field, String>> mapping = new HashMap<AbstractDataset, HashMap<Field, String>>();//le mapping pour chaque set de données avec les champs
  private String name;//le nom du projet
  private String organisation;//l'organisation pour le projet

  private LinkedList<OutputApplication> outputs;//la liste des sorties

  private String packageName;//le nom du package a utiliser

  private AbstractServer server;//le serveur déclaré pour le projet

  /**
   * @param name
   *          the name
   * @param packageName
   *          the package name
   * @param author
   *          the author
   * @param organisation
   *          the organisation
   */
  public Project(String name, String packageName, String author,
      String organisation) {
    //Si c'est le premier projet créé, initialise la liste
    if (Project.projects == null) Project.projects = new LinkedList<Project>();

    this.name = name;
    this.packageName = packageName;
    this.author = author;
    this.organisation = organisation;
    this.outputs = new LinkedList<OutputApplication>();
    this.datasources = new LinkedList<AbstractDatasource>();
    this.criterias = new LinkedList<Criteria>();
    this.fields = new LinkedList<Field>();
    fields.add(new Field("__item_type"));//déclare un champs type par défaut

    projects.add(this);
  }

  /**
   * @param criteria
   */
  public void addCriteria(Criteria criteria) {
    this.criterias.add(criteria);
  }

  /**
   * @param datasource
   */
  public void addDatasource(AbstractDatasource datasource) {
    this.datasources.add(datasource);
  }

  /**
   * @param field
   */
  public void addField(Field field) {
    this.fields.add(field);
  }

  /**
   * @param output
   */
  public void addOutput(OutputApplication output) {
    this.outputs.add(output);
  }

  /**
   * @param output
   */
  public void removeOutput(OutputApplication output) {
    outputs.remove(output);
    //supprime les dossiers dans le projet si il y en a
    String directory = "";
    File appDirectory = new File(directory);
    if (appDirectory.exists()) appDirectory.delete();
  }

  /**
   * Generate the project in the workspace
   *
   * @param workspace
   *          : the project location
   */
  public void generateProject(String workspace) {

    String projectPath = workspace + File.separator + getName();
    File generatedDirectory = new File(projectPath + File.separator + "generated");
    File projectDirectory = new File(projectPath);
    try {
      //supprime tout ancien dossier
      if (generatedDirectory.exists()) generatedDirectory.delete();

      //créer le dossier script :
      String serverPath = generatedDirectory.toPath() + File.separator + "serverScript";
      File serverDirectory = new File(serverPath);
      serverDirectory.mkdirs();
      // appele le generateur pour le serveur
      getServer().generateScripts(this, serverPath);
    }
    catch (ProcessingException e) {
      e.printStackTrace();
    }

    for (OutputApplication output : getOutputs()) {
      try {
        String pckage = Desktop.getOutputTypes().get(output.getName()).getLocation();
        String clss = pckage + "." + "OutputGenerator";
        System.out.println(pckage);
        Class<?> outputGenerator;
        outputGenerator = Class.forName(clss);
        java.lang.reflect.Constructor constructor = outputGenerator.getConstructor(new Class[]{OutputApplication.class});
        AbstractOutputGenerator generator = (AbstractOutputGenerator) constructor.newInstance(new Object[]{output});
        try {
          generator.generate(projectDirectory, generatedDirectory);
        }
        catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      catch (NoSuchMethodException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      catch (SecurityException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      catch (InstantiationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      catch (IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      catch (IllegalArgumentException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      catch (InvocationTargetException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }
  }

  /**
   * @return the author
   */
  public String getAuthor() {
    return author;
  }

  /**
   * @return the criterias
   */
  public LinkedList<Criteria> getCriterias() {
    return criterias;
  }

  /**
   * @return the datasources
   */
  public LinkedList<AbstractDatasource> getDatasources() {
    return datasources;
  }

  /**
   * @return the fields
   */
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

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the organisation
   */
  public String getOrganisation() {
    return organisation;
  }

  /**
   * @return the outputs
   */
  public LinkedList<OutputApplication> getOutputs() {
    return outputs;
  }

  /**
   * @return the package name
   */
  public String getPackageName() {
    return packageName;
  }

  /**
   * @return the server
   */
  public AbstractServer getServer() {
    return server;
  }

  /**
   * @param field
   *          the field to remove
   */
  public void removeField(Field field) {
    this.fields.remove(field);
  }

  /**
   * @param author
   *          the author to set
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * Set the mapping for a field on a dataset
   *
   * @param field
   * @param dataset
   */
  public void setMapping(Field field, AbstractDataset dataset, String value) {
    HashMap<Field, String> datasetmap = mapping.get(dataset);
    if (datasetmap == null) {
      mapping.put(dataset, new HashMap<Field, String>());
      mapping.get(dataset).put(field, value);
    }
    else {
      if (datasetmap.get(field) != null) datasetmap.remove(field);
      datasetmap.put(field, value);
    }
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @param organisation
   *          the organisation to set
   */
  public void setOrganisation(String organisation) {
    this.organisation = organisation;
  }

  /**
   * @param packageName
   *          the package name to set
   */
  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  /**
   * @param server
   *          the server to set
   */
  public void setServer(AbstractServer server) {
    this.server = server;
  }

  /**
   * @param criteria
   *          the criteria to remove
   */
  public void removeCriteria(Criteria criteria) {
    this.criterias.remove(criteria);

  }

  /**
   * @param fieldName
   * @return the field having the name fieldName
   */
  public Field getFieldByName(String fieldName) {
    for (Field f : fields) {
      if (f.getName().equals(fieldName)) {
        return f;
      }
    }
    return null;
  }

  /**
   * @param type
   *          the dataset type
   * @param datasourceName
   *          the datasource name
   * @param datasetName
   *          the dataset name
   * @return the dataset having the name datasetName
   */
  public AbstractDataset getDatasetByName(String type, String datasourceName, String datasetName) {
    for (AbstractDatasource datasource : datasources) {
      if (datasource.getClass().getSimpleName().replace("Datasource", "").equals(type) && datasource.getName().equals(datasourceName)) {
        for (AbstractDataset dataset : datasource.getDatasets()) {
          if (dataset.getName().equals(datasetName)) {
            return dataset;
          }
        }
      }
    }
    return null;
  }

  /**
   * @param datasource
   *          the datasource to remove
   */
  public void removeDatasource(AbstractDatasource datasource) {
    datasources.remove();
  }
}
