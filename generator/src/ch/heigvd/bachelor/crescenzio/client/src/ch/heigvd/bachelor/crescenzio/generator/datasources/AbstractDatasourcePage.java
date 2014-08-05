/**
 * Nom du fichier         : AbstractDatasourcePage.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit la navigation pour un set de données
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.datasources;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPage;

/**
 * Define the navigation for a datasource
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public abstract class AbstractDatasourcePage extends AbstractPage {

  private AbstractDatasource datasource;

  public AbstractDatasourcePage(AbstractDatasource datasource) {
    super(false);
    this.datasource = datasource;
    callInitializer();
  }

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

  @Override
  protected String getConfiguredTitle() {
    return datasource.getName();
  }
}
