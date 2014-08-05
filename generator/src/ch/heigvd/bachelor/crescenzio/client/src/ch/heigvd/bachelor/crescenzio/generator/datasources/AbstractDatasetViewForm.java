/**
 * Nom du fichier         : AbstractDatasetViewForm.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit un ViewForm pour un set de données
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.datasources;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.AbstractViewForm;

/**
 * Define a ViewForm for a dataset
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public abstract class AbstractDatasetViewForm extends AbstractViewForm {
  private AbstractDataset dataset;

  /**
   * @return the dataset
   */
  protected AbstractDataset getDataset() {
    return dataset;
  }

  /**
   * @param dataset
   *          the dataset to set
   */
  public void setDataset(AbstractDataset dataset) {
    this.dataset = dataset;
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public AbstractDatasetViewForm(AbstractDataset dataset) throws ProcessingException {
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
}
