/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client;

import java.util.Collection;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;

import ch.heigvd.bachelor.crescenzio.generator.Project;

/**
 * @author Fabio
 */
public class ProjectDetailsNodePage extends AbstractPageWithNodes {

  private Integer m_projectNr;
  private Project project;

  /**
   * @param project
   */
  public ProjectDetailsNodePage(Project project) {
    this.project = project;
    setInitialExpanded(true);
  }

  @Override
  protected boolean getConfiguredExpanded() {
    return true;
  }

  @Override
  protected String getConfiguredTitle() {
    return project.getName();
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    new ProjectInfoForm(project).startView();
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    ServerPage serverPage = new ServerPage();
    serverPage.setProjectNr(getProjectNr());
    pageList.add(serverPage);

    DatasourcesTablePage datasourcesTablePage = new DatasourcesTablePage();
    datasourcesTablePage.setProjectNr(getProjectNr());
    pageList.add(datasourcesTablePage);

    DatasetsTablePage datasetsTablePage = new DatasetsTablePage();
    datasetsTablePage.setProjectNr(getProjectNr());
    pageList.add(datasetsTablePage);

    OutputsTablePage outputsTablePage = new OutputsTablePage();
    outputsTablePage.setProjectNr(getProjectNr());
    pageList.add(outputsTablePage);

    FieldsTablePage fieldsPage = new FieldsTablePage();
    fieldsPage.setProjectNr(getProjectNr());
    pageList.add(fieldsPage);

    CriteriasTablePage criteriasTablePage = new CriteriasTablePage();
    criteriasTablePage.setProjectNr(getProjectNr());
    pageList.add(criteriasTablePage);

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
