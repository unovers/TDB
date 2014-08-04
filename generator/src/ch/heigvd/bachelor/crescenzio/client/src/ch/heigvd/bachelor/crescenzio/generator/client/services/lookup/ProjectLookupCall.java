/**
 * Nom du fichier         : ProjectLookupCall.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Cette classe définit le lookupCall pour la liste des projets
 *
 * Historiques des modifications
 * -
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
 * Define a lookupCall for the project list
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
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
