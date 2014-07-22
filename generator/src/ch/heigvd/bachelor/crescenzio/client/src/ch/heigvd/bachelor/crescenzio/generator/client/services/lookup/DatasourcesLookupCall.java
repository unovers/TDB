/**
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
import ch.heigvd.bachelor.crescenzio.generator.datasources.Datasource;

/**
 * @author Fabio
 */
public class DatasourcesLookupCall extends LocalLookupCall<Datasource> {
  private static final long serialVersionUID = 1L;
  private Project project;

  @Override
  public java.util.List<? extends org.eclipse.scout.rt.shared.services.lookup.ILookupRow<Datasource>> getDataByAll() throws ProcessingException {
    List<ILookupRow<Datasource>> rows = new ArrayList<ILookupRow<Datasource>>();

    for (Datasource datasource : project.getDatasources()) {
      rows.add(new LookupRow<Datasource>(datasource, datasource.getName()));
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
