/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPage;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ServerForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ServerInfoForm;

/**
 * @author Fabio
 */
public class ServerPage extends AbstractPage {

  private Integer m_projectNr;
  private String title;

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

  @Override
  protected void execPageActivated() throws ProcessingException {
    if (project.getServer() == null) {
      new ServerForm(project).startNew();
    }
    else {
      new ServerInfoForm(project).startView();
    }
  }

  private Project project;

  @Override
  protected String getConfiguredTitle() {
    if (title != null) return title;
    return TEXTS.get("Server");
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

  /**
   * @param string
   */
  public void setTitle(String title) {
    this.title = title;

  }
}
