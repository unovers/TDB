/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.datasources.mysql;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasourcePage;

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