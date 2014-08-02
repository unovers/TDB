/**
 * Nom du fichier         : DatasourcesDetailsNodePage.java
 * Version                : 0.1
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           :
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
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasourcePage;

public class DatasourcesDetailsNodePage extends AbstractPageWithNodes {

  private Project project;

  /**
   * @param project
   */
  public DatasourcesDetailsNodePage(Project project) {
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
  protected String getConfiguredTitle() {
    return TEXTS.get("Datasources");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    for (AbstractDatasource datasource : project.getDatasources()) {
      try {
        String pckage = Desktop.getDatasourceTypes().get(datasource.getClass().getSimpleName().replace("Datasource", "")).getLocation();
        String clss = pckage + "." + datasource.getClass().getSimpleName() + "Page";
        Class<?> datasourceClass = Class.forName(clss);
        java.lang.reflect.Constructor constructor = datasourceClass.getConstructor(new Class[]{datasource.getClass()});
        AbstractDatasourcePage page = (AbstractDatasourcePage) constructor.newInstance(new Object[]{datasource});
        pageList.add(page);
      }
      catch (Exception e) {
        //TODO Log
        throw new ProcessingException(e.toString());
      }
    }
  }
}
