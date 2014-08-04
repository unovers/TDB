/**
 * Nom du fichier         : ProjectDetailsNodePage.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit la navigation pour une liste de projet
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

/**
 * Define the navigation for the project list
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class ProjectDetailsNodePage extends AbstractPageWithNodes {

  private Project project;

  /**
   * @param project
   */
  public ProjectDetailsNodePage(Project project) {
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
    return project.getName();
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    //Ajoute les sous pages d'un projet
    ProjectPage projectInfoPage = new ProjectPage(project);
    pageList.add(projectInfoPage);

    FieldsPage fieldsPage = new FieldsPage(project);
    pageList.add(fieldsPage);

    CriteriasPage criteriasDetailsNodePage = new CriteriasPage(project);
    pageList.add(criteriasDetailsNodePage);

    if (project.getServer() != null) {
      ServerPage serverPage = new ServerPage(project);
      pageList.add(serverPage);
    }
    else {
      ServerPage serverPage = new ServerPage(project);
      serverPage.setTitle(TEXTS.get("ConfigureServer"));
      pageList.add(serverPage);
    }

    DatasourcesDetailsNodePage datasourcesNodePage = new DatasourcesDetailsNodePage(project);
    pageList.add(datasourcesNodePage);

    DatasetsDetailsNodePage datasetsDetailsNodePage = new DatasetsDetailsNodePage(project);
    pageList.add(datasetsDetailsNodePage);

    OutputsDetailsNodePage outputsDetailsNodePage = new OutputsDetailsNodePage(project);
    pageList.add(outputsDetailsNodePage);

  }

}
