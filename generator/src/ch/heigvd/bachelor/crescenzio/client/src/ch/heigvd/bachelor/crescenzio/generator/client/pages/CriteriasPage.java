/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPage;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.CriteriasViewForm;

/**
 * @author Fabio
 */
public class CriteriasPage extends AbstractPage {

  private Project project;

  /**
   * @param project
   */
  public CriteriasPage(Project project) {
    this.project = project;
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    new CriteriasViewForm(project).startView();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Criterias");
  }

}
