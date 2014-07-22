/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPage;

import ch.heigvd.bachelor.crescenzio.generator.datasets.Dataset;

/**
 * @author Fabio
 */
public abstract class AbstractDatasetPage extends AbstractPage {

  /**
   * @return the dataset
   */
  public Dataset getDataset() {
    return dataset;
  }

  /**
   * @param dataset
   *          the dataset to set
   */
  public void setDataset(Dataset dataset) {
    this.dataset = dataset;
  }

  private Dataset dataset;

  /**
   * @param dataset
   */
  protected AbstractDatasetPage(Dataset dataset) {
    this.dataset = dataset;
  }

  @Override
  protected String getConfiguredTitle() {
    return dataset.getName();
  }

}
