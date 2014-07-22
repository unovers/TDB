/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.AndroidOutputApplicationViewForm;
import ch.heigvd.bachelor.crescenzio.generator.outputs.AndroidOutputApplication;

/**
 * @author Fabio
 */
public class AndroidOutputApplicationPage extends AbstractOutputApplicationPage {

  /**
   * @param output
   */
  public AndroidOutputApplicationPage(Project project, AndroidOutputApplication output) {
    super(project, output);
    callInitializer();
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    new AndroidOutputApplicationViewForm(getProject(), getOutput()).startView();
  }

}
