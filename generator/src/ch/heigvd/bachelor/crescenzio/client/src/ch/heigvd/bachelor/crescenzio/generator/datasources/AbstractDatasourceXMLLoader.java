package ch.heigvd.bachelor.crescenzio.generator.datasources;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ch.heigvd.bachelor.crescenzio.generator.Project;

public abstract class AbstractDatasourceXMLLoader {

  public abstract AbstractDatasource loadDatasource(Project project, Element element);

  public abstract Element createElementDatasource(Document document, AbstractDatasource datasource);

  public abstract AbstractDataset loadDataset(AbstractDatasource datasource, Element element);

  public abstract Element createElementDataset(Document document, AbstractDataset dataset);

}
