/**
 * Nom du fichier         : AbstractServerDatasourceScriptGenerator.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Cette classe définit un générateur abstrait de scripts pour un type de serveur
 *                          et un type de source de données
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.server;

import java.io.IOException;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;

/**
 * Define how to generate scripts for an abstract server and an abstract datasource
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public abstract class AbstractServerDatasourceScriptGenerator {
  AbstractDatasource datasource;
  Project project;

  public abstract String generate();

  /**
   * @return the datasource
   */
  public AbstractDatasource getDatasource() {
    return datasource;
  }

  /**
   * @param datasource
   *          the datasource to set
   */
  public void setDatasource(AbstractDatasource datasource) {
    this.datasource = datasource;
  }

  /**
   * @param datasource
   */
  public AbstractServerDatasourceScriptGenerator(Project project, AbstractDatasource datasource) {
    super();
    this.project = project;
    this.datasource = datasource;
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
   * Create all the needed files for this type of server / datasource
   */
  public abstract void createFiles(String destination) throws IOException;

}
