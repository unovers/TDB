/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.IService;

/**
 * @author Fabio
 */
public interface IStandardOulineService extends IService {

  /**
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  Object[][] getProjectsTableData(Integer id) throws ProcessingException;
}
