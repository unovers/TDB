/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.outputs.androidsimplelist;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.outputs.AbstractOutputApplicationPage;

/**
 * @author Fabio
 */
public class AndroidSimpleListOutputApplicationPage extends AbstractOutputApplicationPage {

  /**
   * @param output
   */
  public AndroidSimpleListOutputApplicationPage(Project project, AndroidSimpleListOutputApplication output) {
    super(project, output);
    callInitializer();
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    new AndroidSimpleListOutputApplicationViewForm(getProject(), getOutput()).startView();
  }

}
