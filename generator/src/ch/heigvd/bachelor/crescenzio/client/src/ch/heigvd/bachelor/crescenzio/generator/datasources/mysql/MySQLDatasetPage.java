/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.datasources.mysql;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasetPage;

/**
 * @author Fabio
 */
public class MySQLDatasetPage extends AbstractDatasetPage {

  @Override
  protected void execPageActivated() throws ProcessingException {
    Desktop.loadOrRefreshFormDataset(getDataset(), new MySQLDatasetViewForm((MySQLDataset) getDataset()));
  }

  /**
   * @param dataset
   */
  public MySQLDatasetPage(MySQLDataset dataset) {
    super(dataset);
    callInitializer();
  }
}
