/**
 * Nom du fichier         : FieldsOutputMappingLookupCall.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Cette classe définit un lookupCall pour les champs dans un projet
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.services.lookup;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.Project;

/**
 * Define a lookupCall for the fields in a project
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class FieldsOutputMappingLookupCall extends LocalLookupCall<Field> {
  private static final long serialVersionUID = 1L;
  private Project project;

  @Override
  public java.util.List<? extends ILookupRow<Field>> execCreateLookupRows() throws ProcessingException {
    List<ILookupRow<Field>> rows = new ArrayList<ILookupRow<Field>>();

    for (Field field : project.getFields()) {
      rows.add(new LookupRow<Field>(field, field.getName()));
    }
    return rows;
  }

  /**
   * @param project
   */
  public void setProject(Project project) {
    this.project = project;
  }
}
