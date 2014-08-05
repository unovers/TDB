/**
 * Nom du fichier         : AbstractDatasource.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit une source de données abstraite
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.datasources;

import java.util.LinkedList;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.Project;

/**
 * Define an abstract datasource
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public abstract class AbstractDatasource {
  private String name; //le nom
  private Project project; //le projet
  private LinkedList<AbstractDataset> datasets; //la liste des sets de données
  private boolean isConnectionOpen; //permet de savoir si la connexion est ouverte
  private boolean isDescribed;//permet de savoir si la source a déjà été décrite

  protected AbstractDatasource(Project project, String name) {
    this.project = project;
    this.name = name;
    this.datasets = new LinkedList<AbstractDataset>();
    this.isConnectionOpen = false;
  }

  /**
   * @return the project
   */
  public Project getProject() {
    return project;
  }

  /**
   * @param project
   *          the project to set
   */
  public void setProject(Project project) {
    this.project = project;
  }

  /**
   * @return the isDescribed
   */
  public boolean isDescribed() {
    return isDescribed;
  }

  /**
   * @param isDescribed
   *          the isDescribed to set
   */
  public void setDescribed(boolean isDescribed) {
    this.isDescribed = isDescribed;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @param dataset
   *          the dataset to add
   */
  public void addDataset(AbstractDataset dataset) {
    this.datasets.add(dataset);
  }

  /**
   * @param dataset
   *          the dataset to remove
   */
  public void removeDataset(AbstractDataset dataset) {
    this.datasets.remove(datasets);
  }

  /**
   * @return the datasets list
   */
  public LinkedList<AbstractDataset> getDatasets() {
    return datasets;
  }

  /**
   * clear all datasets for a datasource
   */
  public void emptyDatasets() {
    this.datasets.clear();
  }

  /**
   * @return the connection status
   */
  public boolean isConnectionOpen() {
    return isConnectionOpen;
  }

  /**
   * @param status
   *          the new status
   */
  public void setConnectionOpenStatus(boolean status) {
    isConnectionOpen = status;
  }

  /**
   * Connect to the datasource
   *
   * @return
   * @throws ProcessingException
   */
  public abstract boolean connect() throws ProcessingException;

  /**
   * disconnect to the datasource
   *
   * @return
   */
  public abstract boolean disconnect();

  /**
   * describe a datasource
   *
   * @throws ProcessingException
   */
  public abstract void describe() throws ProcessingException;

  /**
   * Create a query on the datasource
   *
   * @param query
   * @throws ProcessingException
   */
  public abstract void query(String query) throws ProcessingException;
}
