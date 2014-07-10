/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.services;

import java.util.LinkedList;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.AbstractService;

import ch.heigvd.bachelor.crescenzio.generator.Project;

/**
 * @author Fabio
 */
public class StandardOulineService extends AbstractService implements IStandardOulineService {

  @Override
  public Object[][] getProjectsTableData(Integer id) throws ProcessingException {
    //TODO [Fabio] business logic here.
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
