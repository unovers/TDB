/**
 * Nom du fichier         : OutputsDetailsNodePage.java
 * Version                : 0.1
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit la navigation pour les sorties d'application
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputApplication;

/**
 * Define the navigation for outputs
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class OutputsDetailsNodePage extends AbstractPageWithNodes {

  private Project project;

  /**
   * @param project
   */
  public OutputsDetailsNodePage(Project project) {
    super(false);
    this.project = project;
    setInitialExpanded(true);
    callInitializer();
  }

  @Override
  protected boolean getConfiguredExpanded() {
    return true;
  }

  @Override
  protected boolean getConfiguredLeaf() {
    return true;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Outputs");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    for (OutputApplication output : project.getOutputs()) {
      OutputApplicationPage page = new OutputApplicationPage(project, output);
      pageList.add(page);
    }
  }
}
