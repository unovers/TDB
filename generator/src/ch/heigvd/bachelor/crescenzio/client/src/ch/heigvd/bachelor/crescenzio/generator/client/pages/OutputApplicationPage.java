/**
 * Nom du fichier         : OutputApplicationPage.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit la navigation pour une sortie d'application
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPage;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.OutputApplicationViewForm;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputApplication;

/**
 * Define the navigation for an output
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
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
    setExpanded(true);
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
    Desktop.loadOrRefreshFormOutput(output, new OutputApplicationViewForm(getProject(), getOutput()));
  }
}
