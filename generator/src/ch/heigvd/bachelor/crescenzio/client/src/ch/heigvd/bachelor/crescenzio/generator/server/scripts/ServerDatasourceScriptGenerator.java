package ch.heigvd.bachelor.crescenzio.generator.server.scripts;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;

public abstract class ServerDatasourceScriptGenerator {
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
  public ServerDatasourceScriptGenerator(Project project, AbstractDatasource datasource) {
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

}
