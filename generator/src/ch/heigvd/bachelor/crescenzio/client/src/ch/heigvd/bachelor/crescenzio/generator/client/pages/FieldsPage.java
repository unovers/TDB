/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPage;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.FieldsViewForm;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;

/**
 * @author Fabio
 */
public class FieldsPage extends AbstractPage {

  private Project project;

  public FieldsPage(Project project) {
    super(false);
    this.project = project;
    callInitializer();
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    Desktop.loadOrRefreshFormFields(project, new FieldsViewForm(project));
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Fields");
  }
}
