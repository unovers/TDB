/**
 * Nom du fichier         : StandardOutlineService.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Cette classe définit le chargement de la liste des projets pour la navigation
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.services;

import java.util.LinkedList;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.AbstractService;

import ch.heigvd.bachelor.crescenzio.generator.Project;

/**
 * Define how are loaded the projects for the navigation outline
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class StandardOutlineService extends AbstractService implements IStandardOutlineService {

  @Override
  public Object[][] getProjectsTableData(Integer id) throws ProcessingException {
    //Static datas
    LinkedList<Project> projects = Project.getAll();

    Object[][] objects = new Object[projects.size()][3];
    for (int i = 0; i < projects.size(); i++) {
      Project p = projects.get(i);
      objects[i] = new Object[]{p.getName(), p.getAuthor(), p.getOrganisation()};
    }
    if (id != null) {
      Project p = projects.get(id);
      return new Object[][]{{p.getName(), p.getAuthor(), p.getOrganisation()}};
    }
    return objects;
  }
}
