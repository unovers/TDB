/**
 * Nom du fichier         : MySQLDatasetPage.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit la navigation pour un set de données MySQL
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.datasources.mysql;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasetPage;

/**
 * Define the navigation for a MySQLdataset
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class MySQLDatasetPage extends AbstractDatasetPage {

  @Override
  protected void execPageActivated() throws ProcessingException {
    Desktop.loadOrRefreshFormDataset(getDataset(), new MySQLDatasetViewForm((MySQLDataset) getDataset()));
  }

  /**
   * @param dataset
   */
  public MySQLDatasetPage(MySQLDataset dataset) {
    super(dataset);
    callInitializer();
  }
}
