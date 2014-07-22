/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.views;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;

import ch.heigvd.bachelor.crescenzio.generator.datasets.Dataset;

/**
 * @author Fabio
 */
public abstract class AbstractDatasetViewForm extends AbstractForm {

  /**
   * @return the dataset
   */
  protected Dataset getDataset() {
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
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public AbstractDatasetViewForm(Dataset dataset) throws ProcessingException {
    super(false);
    this.dataset = dataset;
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected int getConfiguredDisplayHint() {
    return DISPLAY_HINT_VIEW;
  }

  @Override
  protected String getConfiguredDisplayViewId() {
    return VIEW_ID_CENTER;
  }

  @Override
  protected boolean getConfiguredModal() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return dataset.getName();
  }

  public abstract void startView() throws ProcessingException;
}
