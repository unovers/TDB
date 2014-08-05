/**
 * Nom du fichier         : AbstractDatasetPage.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date derni�re r�vision : 30.07.2014
 *
 * Commentaires           : D�finit la navigation pour un set de donn�es
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.datasources;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPage;

/**
 * Define the navigation for a dataset
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public abstract class AbstractDatasetPage extends AbstractPage {
  private AbstractDataset dataset;

  /**
   * @return the dataset
   */
  public AbstractDataset getDataset() {
    return dataset;
  }

  /**
   * @param dataset
   *          the dataset to set
   */
  public void setDataset(AbstractDataset dataset) {
    this.dataset = dataset;
  }

  /**
   * @param dataset
   */
  protected AbstractDatasetPage(AbstractDataset dataset) {
    super(false);
    this.dataset = dataset;
    callInitializer();
  }

  @Override
  protected String getConfiguredTitle() {
    return dataset.getName();
  }

}
