/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPage;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.OutputApplicationViewForm;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputApplication;

/**
 * @author Fabio
 */
public class OutputApplicationPage extends AbstractPage {

  private Project project;
  private OutputApplication output;

  /**
   * @param output
   */
  public OutputApplicationPage(Project project, OutputApplication output) {
    super(false);
    this.project = project;
    this.output = output;
    callInitializer();
  }

  /**
   * @return the project
   */
  public OutputApplication getOutput() {
    return output;
  }

  /**
   * @return the project
   */
  public Project getProject() {
    return project;
  }

  @Override
  protected String getConfiguredTitle() {
    return output.getName();
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    new OutputApplicationViewForm(getProject(), getOutput()).startView();
  }
}
