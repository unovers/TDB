/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPage;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.DatasetViewForm;
import ch.heigvd.bachelor.crescenzio.generator.dataset.Dataset;

/**
 * @author Fabio
 */
public class DatasetPage extends AbstractPage {

  private Dataset dataset;

  /**
   * @param dataset2
   */
  public DatasetPage(Dataset dataset) {
    this.dataset = dataset;
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    new DatasetViewForm(dataset).startView();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Dataset");
  }

}
