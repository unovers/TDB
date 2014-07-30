/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.datasources.mysql;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasetPage;

/**
 * @author Fabio
 */
public class MySQLDatasetPage extends AbstractDatasetPage {

  /* (non-Javadoc)
   * @see org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPage#execPageActivated()
   */
  @Override
  protected void execPageActivated() throws ProcessingException {
    new MySQLDatasetViewForm((MySQLDataset) getDataset()).startView();
  }

  /**
   * @param dataset
   */
  public MySQLDatasetPage(MySQLDataset dataset) {
    super(dataset);
  }
}
