/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.services.lookup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

import ch.heigvd.bachelor.crescenzio.generator.Project;

/**
 * @author Fabio
 */
public class ProjectLookupCall extends LocalLookupCall<Project> {

  private static final long serialVersionUID = 1L;

  @Override
  protected List<ILookupRow<Project>> execCreateLookupRows() throws ProcessingException {
    LinkedList<Project> projects = Project.getAll();
    List<ILookupRow<Project>> rows = new ArrayList<ILookupRow<Project>>();
    for (Project project : projects) {
      rows.add(new LookupRow<Project>(project, project.getName()));
    }
    return rows;
  }
}
