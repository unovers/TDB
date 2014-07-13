/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPage;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.OutputApplicationViewForm;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputApplication;

/**
 * @author Fabio
 */
public class OutputApplicationPage extends AbstractPage {

  private OutputApplication output;
  private Project project;

  /**
   * @param output
   */
  public OutputApplicationPage(OutputApplication output) {
    this.output = output;
  }

  /**
   * @return the project
   */
  public OutputApplication getOutput() {
    return output;
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    new OutputApplicationViewForm(output).startView();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Server");
  }
}
