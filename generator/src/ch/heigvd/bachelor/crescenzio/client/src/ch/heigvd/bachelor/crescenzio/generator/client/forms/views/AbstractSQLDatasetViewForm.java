/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.views;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.datasets.AbstractSQLDataset;

/**
 * @author Fabio
 */
public abstract class AbstractSQLDatasetViewForm extends AbstractDatasetViewForm {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public AbstractSQLDatasetViewForm(AbstractSQLDataset dataset) throws ProcessingException {
    super(dataset);
  }

}
