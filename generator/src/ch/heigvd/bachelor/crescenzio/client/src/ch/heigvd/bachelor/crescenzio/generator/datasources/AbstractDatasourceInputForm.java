/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.datasources;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.AbstractInputForm;

/**
 * @author Fabio
 */
public abstract class AbstractDatasourceInputForm extends AbstractInputForm {
  private AbstractDatasource datasource;

  /**
   * @return the datasource
   */
  public AbstractDatasource getDatasource() {
    return datasource;
  }

  /**
   * @param datasource
   *          the datasource to set
   */
  public void setDatasource(AbstractDatasource datasource) {
    this.datasource = datasource;
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public AbstractDatasourceInputForm(boolean init) throws ProcessingException {
    super(init);
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public AbstractDatasourceInputForm(AbstractDatasource datasource) throws ProcessingException {
    super(false);
    this.datasource = datasource;
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
    if (datasource == null) return TEXTS.get("NewDatasource");
    else return TEXTS.get("EditDatasource");

  }
}
