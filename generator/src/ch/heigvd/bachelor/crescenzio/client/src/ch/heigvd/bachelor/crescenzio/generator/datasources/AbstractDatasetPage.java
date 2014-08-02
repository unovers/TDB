/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.datasources;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPage;

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
    super(false);
    this.dataset = dataset;
    callInitializer();
  }

  @Override
  protected String getConfiguredTitle() {
    return dataset.getName();
  }

}
