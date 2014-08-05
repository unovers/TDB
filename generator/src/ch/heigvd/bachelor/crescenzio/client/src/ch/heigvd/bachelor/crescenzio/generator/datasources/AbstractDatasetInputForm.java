/**
 * Nom du fichier         : AbstractDatasetInputForm.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Cette classe définit un Form pour la saisie d'un set de données
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.datasources;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.AbstractInputForm;

/**
 * Define an abstract dataset InputForm
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public abstract class AbstractDatasetInputForm extends AbstractInputForm {

  /**
   * @return the project
   */
  public Project getProject() {
    return project;
  }

  /**
   * @param project
   *          the project to set
   */
  public void setProject(Project project) {
    this.project = project;
  }

  private Project project;
  private AbstractDataset dataset;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public AbstractDatasetInputForm(Project project) throws ProcessingException {
    super(false);
    this.project = project;
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public AbstractDatasetInputForm(AbstractDataset dataset) throws ProcessingException {
    super(false);
    this.dataset = dataset;
  }

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

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected boolean getConfiguredModal() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    if (dataset == null) return TEXTS.get("NewDataset");
    else return TEXTS.get("EditDataset");

  }
}
