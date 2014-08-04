/**
 * Nom du fichier         : DatasetsDetailsNodePage.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date derni�re r�vision : 30.07.2014
 *
 * Commentaires           : D�finit la navigation pour les sets de donn�es
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
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDataset;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasetPage;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;

/**
 * Define how to navigate in datasets pages
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class DatasetsDetailsNodePage extends AbstractPageWithNodes {

  private Project project;

  /**
   * @param project
   */
  public DatasetsDetailsNodePage(Project project) {
    this.project = project;
    setInitialExpanded(true);
  }

  @Override
  protected boolean getConfiguredExpanded() {
    return true;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Datasets");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    for (AbstractDatasource datasource : project.getDatasources()) {
      for (AbstractDataset dataset : datasource.getDatasets()) {
        //Charge la bonne page pour le set :
        String pckage = Desktop.getDatasourceTypes().get(datasource.getClass().getSimpleName().replace("Datasource", "")).getLocation();
        String clss = pckage + "." + datasource.getClass().getSimpleName() + "DatasetPage";
        clss = clss.replace("Datasource", "");
        Class<?> datasetPageClass;
        try {
          datasetPageClass = Class.forName(clss);
          java.lang.reflect.Constructor constructor = datasetPageClass.getConstructor(new Class[]{dataset.getClass()});
          AbstractDatasetPage page = (AbstractDatasetPage) constructor.newInstance(new Object[]{dataset});
          pageList.add(page);
        }
        catch (Exception e) {
          //TODO Log
          throw new ProcessingException(e.toString());
        }
      }
    }
  }
}
