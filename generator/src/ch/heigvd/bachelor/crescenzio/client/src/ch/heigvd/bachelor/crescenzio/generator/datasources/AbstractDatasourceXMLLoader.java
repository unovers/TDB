/**
 * Nom du fichier         : AbstractDatasourceXMLLoader.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit comment sont stoqués les sources et sets de données en XML
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.datasources;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ch.heigvd.bachelor.crescenzio.generator.Project;

/**
 * Define how datasources and datasets are stored in a XML document
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public abstract class AbstractDatasourceXMLLoader {

  public abstract AbstractDatasource loadDatasource(Project project, Element element);

  public abstract Element createElementDatasource(Document document, AbstractDatasource datasource);

  public abstract AbstractDataset loadDataset(AbstractDatasource datasource, Element element);

  public abstract Element createElementDataset(Document document, AbstractDataset dataset);

}
