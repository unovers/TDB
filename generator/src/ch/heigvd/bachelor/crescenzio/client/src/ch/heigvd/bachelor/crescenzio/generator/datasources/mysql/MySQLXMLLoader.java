/**
 * Nom du fichier         : AbstractDatasourceXMLLoader.java
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
package ch.heigvd.bachelor.crescenzio.generator.datasources.mysql;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDataset;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasourceXMLLoader;

public class MySQLXMLLoader extends AbstractDatasourceXMLLoader {

  public MySQLXMLLoader() {
  }

  public static Element getDirectChild(Element parent, String name)
  {
    for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling())
    {
      if (child instanceof Element && name.equals(child.getNodeName())) return (Element) child;
    }
    return null;
  }

  @Override
  public AbstractDatasource loadDatasource(Project project, Element element) {
    MySQLDatasource datasource = new MySQLDatasource(project, getDirectChild(element, "name").getTextContent(),
        getDirectChild(element, "hostname").getTextContent(),
        Integer.parseInt(getDirectChild(element, "port").getTextContent()),
        getDirectChild(element, "database").getTextContent(),
        getDirectChild(element, "login").getTextContent(),
        getDirectChild(element, "password").getTextContent());
    Node nodeDatasets = getDirectChild(element, "datasets");
    for (int i = 0; i < nodeDatasets.getChildNodes().getLength(); i++) {
      Node nodeDataset = nodeDatasets.getChildNodes().item(i);
      if (nodeDataset.getNodeType() == Node.ELEMENT_NODE) datasource.addDataset(loadDataset(datasource, (Element) nodeDataset));
    }
    return datasource;

  }

  @Override
  public Element createElementDatasource(Document document, AbstractDatasource datasource) {
    MySQLDatasource ds = (MySQLDatasource) datasource;
    Node nodeDatabase = document.createElement("database");
    Node nodeName = document.createElement("name");
    Node nodeLogin = document.createElement("login");
    Node nodePassword = document.createElement("password");
    Node nodePort = document.createElement("port");
    Node nodeHostname = document.createElement("hostname");
    Node nodeDatasets = document.createElement("datasets");
    Element element = document.createElement("datasource");

    element.setAttribute("type", "MySQL");

    nodeDatabase.setTextContent(ds.getDatabase());
    nodeName.setTextContent(ds.getName());
    nodeLogin.setTextContent(ds.getLogin());
    nodePassword.setTextContent(ds.getPassword());
    nodeHostname.setTextContent(ds.getHostname());
    nodePort.setTextContent(ds.getPort() + "");

    element.appendChild(nodeName);
    element.appendChild(nodeDatabase);
    element.appendChild(nodeLogin);
    element.appendChild(nodePassword);
    element.appendChild(nodePort);
    element.appendChild(nodeHostname);
    element.appendChild(nodeDatasets);
    for (AbstractDataset dataset : ds.getDatasets()) {
      nodeDatasets.appendChild(createElementDataset(document, dataset));
    }

    return element;
  }

  @Override
  public AbstractDataset loadDataset(AbstractDatasource datasource, Element element) {
    return new MySQLDataset((MySQLDatasource) datasource, getDirectChild(element, "name").getTextContent(), getDirectChild(element, "query").getTextContent());
  }

  @Override
  public Element createElementDataset(Document document, AbstractDataset dataset) {
    MySQLDataset ds = (MySQLDataset) dataset;
    Node nodeQuery = document.createElement("query");
    Node nodeName = document.createElement("name");
    Element element = document.createElement("dataset");

    nodeQuery.setTextContent(ds.getQuery());
    nodeName.setTextContent(ds.getName());

    element.appendChild(nodeName);
    element.appendChild(nodeQuery);

    return element;
  }
}
