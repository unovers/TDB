/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPage;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.outputs.AbstractOutputApplication;

/**
 * @author Fabio
 */
public abstract class AbstractOutputApplicationPage extends AbstractPage {

  private Project project;
  private AbstractOutputApplication output;

  /**
   * @param output
   */
  public AbstractOutputApplicationPage(Project project, AbstractOutputApplication output) {
    super(false);
    this.project = project;
    this.output = output;
  }

  /**
   * @return the project
   */
  public AbstractOutputApplication getOutput() {
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
}
