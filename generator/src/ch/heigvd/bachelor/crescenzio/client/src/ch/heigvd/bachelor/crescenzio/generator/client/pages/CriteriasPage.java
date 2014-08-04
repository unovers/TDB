/**
 * Nom du fichier         : CriteriasPage.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit la navigation pour les critères dans un projet
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPage;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.CriteriasViewForm;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;

/**
 * Define how to navigate in criterias page
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class CriteriasPage extends AbstractPage {

  private Project project;

  /**
   * @param project
   */
  public CriteriasPage(Project project) {
    super(false);
    this.project = project;
    callInitializer();
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    Desktop.loadOrRefreshFormCriterias(project, new CriteriasViewForm(project));
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Criterias");
  }

}
