/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.MySQLDatasetViewForm;
import ch.heigvd.bachelor.crescenzio.generator.datasets.MySQLDataset;

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
