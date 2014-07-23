/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPage;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ServerInputForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ServerViewForm;

/**
 * @author Fabio
 */
public class ServerPage extends AbstractPage {

  private String title;
  private Project project;

  /**
   * @param project
   */
  public ServerPage(Project project) {
    this.project = project;
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    new ServerViewForm(project).startView();
    if (project.getServer() == null) {
      new ServerInputForm(project).startNew();
    }
  }

  @Override
  protected String getConfiguredTitle() {
    if (project.getServer() == null) {
      return TEXTS.get("DoConfigureServer");
    }
    return TEXTS.get("Server");
  }

  /**
   * @param string
   */
  public void setTitle(String title) {
    this.title = title;

  }
}
