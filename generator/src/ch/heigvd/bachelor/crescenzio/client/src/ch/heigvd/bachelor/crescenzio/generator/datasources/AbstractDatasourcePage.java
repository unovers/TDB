/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.datasources;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPage;

/**
 * @author Fabio
 */
public abstract class AbstractDatasourcePage extends AbstractPage {

  private AbstractDatasource datasource;

  public AbstractDatasourcePage(AbstractDatasource datasource) {
    this.datasource = datasource;
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
  public void setdatasource(AbstractDatasource datasource) {
    this.datasource = datasource;
  }

  @Override
  protected String getConfiguredTitle() {
    return datasource.getName();
  }
}