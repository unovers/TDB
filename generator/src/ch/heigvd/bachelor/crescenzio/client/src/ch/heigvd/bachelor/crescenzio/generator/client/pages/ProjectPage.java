/**
 * Nom du fichier         : ProjectPage.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit la navigation pour un projet
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
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ProjectViewForm;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;

/**
 * Define the navigation for a project
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class ProjectPage extends AbstractPage {

  private String title;
  private Project project;

  /**
   * @param project
   */
  public ProjectPage(Project project) {
    super(false);
    this.project = project;
    callInitializer();
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    Desktop.loadOrRefreshFormProject(project, new ProjectViewForm(project));
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ProjectOverview");
  }
}
