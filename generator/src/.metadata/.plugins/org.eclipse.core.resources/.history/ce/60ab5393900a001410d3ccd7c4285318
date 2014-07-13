/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import java.util.Collection;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ProjectViewForm;
import ch.heigvd.bachelor.crescenzio.generator.datasets.Dataset;
import ch.heigvd.bachelor.crescenzio.generator.datasources.Datasource;

/**
 * @author Fabio
 */
public class DatasetsDetailsNodePage extends AbstractPageWithNodes {

  private Integer m_projectNr;
  private Project project;

  /**
   * @param project
   */
  public DatasetsDetailsNodePage(Project project) {
    this.project = project;
    setInitialExpanded(true);
  }

  @Override
  protected boolean getConfiguredExpanded() {
    return true;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Datasets");
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    new ProjectViewForm(project).startView();
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    for (Datasource datasource : getProject().getDatasources()) {
      for (Dataset dataset : datasource.getDatasets()) {
        pageList.add(new DatasetPage(dataset));
      }
    }

  }

  /**
   * @return the project
   */
  public Project getProject() {
    return project;
  }

  /**
   * @param project
   *          the project to set
   */
  public void setProject(Project project) {
    this.project = project;
  }

  /**
   * @return the ProjectNr
   */
  @FormData
  public Integer getProjectNr() {
    return m_projectNr;
  }

  /**
   * @param projectNr
   *          the ProjectNr to set
   */
  @FormData
  public void setProjectNr(Integer projectNr) {
    m_projectNr = projectNr;
  }
}
