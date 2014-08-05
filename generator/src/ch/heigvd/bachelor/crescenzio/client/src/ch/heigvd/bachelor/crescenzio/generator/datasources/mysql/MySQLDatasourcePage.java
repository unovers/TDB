/**
 * Nom du fichier         : MySQLDatasourcePage.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date derni�re r�vision : 30.07.2014
 *
 * Commentaires           : D�finit la navigation pour une source de donn�es MySQL
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.datasources.mysql;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasourcePage;

/**
 * Define the navigation for a MySQL datasource
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class MySQLDatasourcePage extends AbstractDatasourcePage {

  /**
   * @param datasource
   */
  public MySQLDatasourcePage(MySQLDatasource datasource) {
    super(datasource);
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    Desktop.loadOrRefreshFormDatasource(getDatasource(), new MySQLDatasourceViewForm((MySQLDatasource) getDatasource()));
  }

}
