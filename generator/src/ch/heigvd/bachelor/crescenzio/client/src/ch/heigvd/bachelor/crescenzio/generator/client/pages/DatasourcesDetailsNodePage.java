/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;

/**
 * @author Fabio
 */
public class DatasourcesDetailsNodePage extends AbstractPageWithNodes {

  private Project project;

  /**
   * @param project
   */
  public DatasourcesDetailsNodePage(Project project) {
    this.project = project;
    setInitialExpanded(true);
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
        String pckage = "ch.heigvd.bachelor.crescenzio.generator.client.pages";
        String clss = pckage + "." + datasource.getClass().getSimpleName() + "Page";
        Class datasourceClass = Class.forName(clss);
        java.lang.reflect.Constructor constructor = datasourceClass.getConstructor(new Class[]{datasource.getClass()});
        AbstractDatasourcePage page = (AbstractDatasourcePage) constructor.newInstance(new Object[]{datasource});
        pageList.add(page);
      }
      catch (Exception e) {
        throw new ProcessingException(e.toString());
      }
    }
  }
}
