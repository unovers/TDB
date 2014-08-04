/**
 * Nom du fichier         : DatasourcesLookupCall.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Ce fichier definit un lookupCall pour les sources de données présentes dans un projet
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

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;

/**
 * Define a lookupCall for the datasources in a project
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class DatasourcesLookupCall extends LocalLookupCall<AbstractDatasource> {
  private static final long serialVersionUID = 1L;
  private Project project;

  @Override
  public java.util.List<? extends org.eclipse.scout.rt.shared.services.lookup.ILookupRow<AbstractDatasource>> execCreateLookupRows() throws ProcessingException {
    List<ILookupRow<AbstractDatasource>> rows = new ArrayList<ILookupRow<AbstractDatasource>>();

    for (AbstractDatasource datasource : project.getDatasources()) {
      rows.add(new LookupRow<AbstractDatasource>(datasource, datasource.getName()));
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
