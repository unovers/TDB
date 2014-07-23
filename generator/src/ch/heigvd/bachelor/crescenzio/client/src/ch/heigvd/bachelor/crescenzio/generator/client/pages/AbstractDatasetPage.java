/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPage;

import ch.heigvd.bachelor.crescenzio.generator.datasets.AbstractDataset;

/**
 * @author Fabio
 */
public abstract class AbstractDatasetPage extends AbstractPage {

  /**
   * @return the dataset
   */
  public AbstractDataset getDataset() {
    return dataset;
  }

  /**
   * @param dataset
   *          the dataset to set
   */
  public void setDataset(AbstractDataset dataset) {
    this.dataset = dataset;
  }

  private AbstractDataset dataset;

  /**
   * @param dataset
   */
  protected AbstractDatasetPage(AbstractDataset dataset) {
    this.dataset = dataset;
  }

  @Override
  protected String getConfiguredTitle() {
    return dataset.getName();
  }

}
