/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.MySQLDatasourceViewForm;
import ch.heigvd.bachelor.crescenzio.generator.datasources.MySQLDatasource;

/**
 * @author Fabio
 */
public class MySQLDatasourcePage extends AbstractDatasourcePage {

  /**
   * @param datasource
   */
  public MySQLDatasourcePage(MySQLDatasource datasource) {
    super(datasource);
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    new MySQLDatasourceViewForm((MySQLDatasource) getDatasource()).startView();
  }

}
