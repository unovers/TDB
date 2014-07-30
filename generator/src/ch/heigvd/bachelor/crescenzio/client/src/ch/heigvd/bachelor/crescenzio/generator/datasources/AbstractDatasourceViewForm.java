/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.datasources;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.AbstractViewForm;

/**
 * @author Fabio
 */
public abstract class AbstractDatasourceViewForm extends AbstractViewForm {

  private AbstractDatasource datasource;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public AbstractDatasourceViewForm(AbstractDatasource datasource) throws ProcessingException {
    super(false);
    this.datasource = datasource;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("DatasourceInfo");
  }

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

}
