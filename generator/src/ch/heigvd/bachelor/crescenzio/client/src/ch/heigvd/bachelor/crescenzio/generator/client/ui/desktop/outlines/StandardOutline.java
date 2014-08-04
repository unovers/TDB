/**
 * Nom du fichier         : StandardOutline.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date derni�re r�vision : 30.07.2014
 *
 * Commentaires           : Cette classe d�finit la navigation g�n�rale de l'application
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.outlines;

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.extension.client.ui.desktop.outline.AbstractExtensibleOutline;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.pages.ProjectDetailsNodePage;

/**
 * Define the navigation in the application
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class StandardOutline extends AbstractExtensibleOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ProjectsOutline");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    for (Project project : Project.getAll()) {
      ProjectDetailsNodePage page = new ProjectDetailsNodePage(project);
      pageList.add(page);
    }
  }
}
