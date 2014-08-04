/**
 * Nom du fichier         : IStandardOutlineServer.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Cette classe permet de charger un projet depuis un fichier XML ou de le sauvegarder dans un fichier XML
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.IService;

/**
 * @author Fabio
 */
public interface IStandardOutlineService extends IService {

  /**
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  Object[][] getProjectsTableData(Integer id) throws ProcessingException;
}
