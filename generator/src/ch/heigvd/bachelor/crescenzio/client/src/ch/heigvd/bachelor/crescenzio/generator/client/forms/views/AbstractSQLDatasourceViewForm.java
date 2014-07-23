/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.views;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractSQLDatasource;

/**
 * @author Fabio
 */
public abstract class AbstractSQLDatasourceViewForm extends AbstractDatasourceViewForm {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public AbstractSQLDatasourceViewForm(AbstractSQLDatasource datasource) throws ProcessingException {
    super(datasource);
  }
}
