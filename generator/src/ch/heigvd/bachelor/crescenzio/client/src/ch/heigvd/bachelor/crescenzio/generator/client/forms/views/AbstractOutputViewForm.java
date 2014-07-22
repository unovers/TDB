/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.views;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.outputs.AbstractOutputApplication;

/**
 * @author Fabio
 */
public abstract class AbstractOutputViewForm extends AbstractForm {

  /**
   * @return the output
   */
  public AbstractOutputApplication getOutput() {
    return output;
  }

  /**
   * @param output
   *          the output to set
   */
  public void setOutput(AbstractOutputApplication output) {
    this.output = output;
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

  private AbstractOutputApplication output;
  private Project project;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public AbstractOutputViewForm(Project project, AbstractOutputApplication output) throws ProcessingException {
    super(false);
    this.project = project;
    this.output = output;
  }

  @Override
  protected String getConfiguredTitle() {
    return output.getName();
  }

  public abstract void startView() throws ProcessingException;
}
