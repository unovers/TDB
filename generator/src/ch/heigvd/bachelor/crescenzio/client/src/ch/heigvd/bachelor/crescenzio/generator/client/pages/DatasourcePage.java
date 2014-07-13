/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPage;

import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.AbstractViewForm;
import ch.heigvd.bachelor.crescenzio.generator.datasources.Datasource;

/**
 * @author Fabio
 */
public class DatasourcePage extends AbstractPage {

  private Datasource datasource;

  public DatasourcePage(Datasource datasource) {
    this.datasource = datasource;
  }

  /**
   * @return the datasource
   */
  public Datasource getDatasource() {
    return datasource;
  }

  /**
   * @param datasource
   *          the datasource to set
   */
  public void setdatasource(Datasource datasource) {
    this.datasource = datasource;
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    try {
      String pckage = "ch.heigvd.bachelor.crescenzio.generator.client.forms.views";
      String clss = pckage + "." + datasource.getClass().getSimpleName() + "ViewForm";
      Class datasourceClass = Class.forName(clss);
      System.out.println(datasourceClass + "-+++++");
      java.lang.reflect.Constructor constructor = datasourceClass.getConstructor(new Class[]{datasource.getClass()});
      AbstractViewForm form = (AbstractViewForm) constructor.newInstance(new Object[]{datasource});
      form.startView();
    }
    catch (Exception e) {
      throw new ProcessingException(e.toString());
    }
  }

  @Override
  protected String getConfiguredTitle() {
    return datasource.getName();
  }
}
