/**
 * Nom du fichier         : ProjectXMLLoader.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date derni�re r�vision : 30.07.2014
 *
 * Commentaires           : Cette classe permet de charger un projet depuis un fichier XML ou de le sauvegarder dans un fichier XML
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
import ch.heigvd.bachelor.crescenzio.generator.criterias.Criteria;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDataset;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasourceXMLLoader;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputApplication;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputApplicationXMLLoader;
import ch.heigvd.bachelor.crescenzio.generator.server.AbstractServer;
import ch.heigvd.bachelor.crescenzio.generator.utils.Utils;

/**
 * This class can load a project from a XML file or save it to a XML file
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class ProjectXMLLoader {
  /**
   * Load a project from a element
   *
   * @param element
   *          the element containing the project
   */
  public static void loadProject(Element element) {
    Project project;

    try {
      project = new Project(Utils.getDirectChild(element, "name").getTextContent(),
          Utils.getDirectChild(element, "package").getTextContent(),
          Utils.getDirectChild(element, "author").getTextContent(),
          Utils.getDirectChild(element, "organisation").getTextContent());
      Node nodeDatasources = Utils.getDirectChild(element, "datasources");
      Node nodeOutputs = Utils.getDirectChild(element, "outputs");
      Node nodeCriterias = Utils.getDirectChild(element, "criterias");
      Node nodeFields = Utils.getDirectChild(element, "fields");
      Node nodeServer = Utils.getDirectChild(element, "server");

      //ajoute les sources de donn�es
      for (int i = 0; i < nodeDatasources.getChildNodes().getLength(); i++) {
        Node node = nodeDatasources.getChildNodes().item(i);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          String datasourceType = ((Element) node).getAttribute("type");
          String pckage = Desktop.getDatasourceTypes().get(datasourceType).getLocation();
          String clssLoader = pckage + "." + datasourceType + "XMLLoader";
          Class<?> datasourceClass = Class.forName(clssLoader);

          java.lang.reflect.Constructor constructor = datasourceClass.getConstructor();
          AbstractDatasourceXMLLoader loader = (AbstractDatasourceXMLLoader) constructor.newInstance();
          project.addDatasource(loader.loadDatasource(project, (Element) node));
        }
      }

      //ajoute les champs
      for (int i = 0; i < nodeFields.getChildNodes().getLength(); i++) {
        Node node = nodeFields.getChildNodes().item(i);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Field field;
          //Test si c'est le champs par defaut, si oui, ne le rajoute pas mais le recup�re
          if (Utils.getDirectChild((Element) node, "name").getTextContent().equals("__item_type")) {
            field = project.getFieldByName("__item_type");
          }
          else {
            field = new Field(Utils.getDirectChild((Element) node, "name").getTextContent());
            project.addField(field);
          }

          Node nodeMapping = Utils.getDirectChild((Element) node, "mapping");
          for (int j = 0; j < nodeMapping.getChildNodes().getLength(); j++) {
            Node nodeMapField = nodeMapping.getChildNodes().item(j);
            if (nodeMapField.getNodeType() == Node.ELEMENT_NODE) {

              Element elementMapField = (Element) nodeMapField;
              String value = nodeMapField.getTextContent();
              project.setMapping(field,
                  project.getDatasetByName(elementMapField.getAttribute("type"),
                      elementMapField.getAttribute("datasource"),
                      elementMapField.getAttribute("name")),
                      value);
            }
          }
        }
      }

      //ajoute les crit�res
      for (int i = 0; i < nodeCriterias.getChildNodes().getLength(); i++) {
        Node nodeCriteria = nodeCriterias.getChildNodes().item(i);
        if (nodeCriteria.getNodeType() == Node.ELEMENT_NODE) {
          Node nodeConditions = Utils.getDirectChild((Element) nodeCriteria, "conditions");
          Node nodeCriteriaTitle = Utils.getDirectChild((Element) nodeCriteria, "title");
          if (nodeCriteriaTitle.getNodeType() == Node.ELEMENT_NODE) {
            Criteria criteria = new Criteria(nodeCriteriaTitle.getTextContent());
            for (int j = 0; j < nodeConditions.getChildNodes().getLength(); j++) {
              Node nodeCondition = nodeConditions.getChildNodes().item(j);
              if (nodeCondition.getNodeType() == Node.ELEMENT_NODE) {
                criteria.addCondition(project.getFieldByName(nodeCondition.getTextContent()));
              }
            }
            project.addCriteria(criteria);
          }
        }
      }

      //Ajoute les informations du serveur
      if (!((Element) nodeServer).getAttribute("type").equals("none")) {
        String serverType = ((Element) nodeServer).getAttribute("type");
        String pckage = Desktop.getServerTypes().get(serverType).getLocation();
        String clss = pckage + "." + serverType;
        Class<?> serverClass = Class.forName(clss);

        String serverRoot = Utils.getDirectChild((Element) nodeServer, "root").getTextContent();
        String serverHost = Utils.getDirectChild((Element) nodeServer, "host").getTextContent();

        java.lang.reflect.Constructor constructor = serverClass.getConstructor(new Class[]{String.class, String.class});
        project.setServer((AbstractServer) constructor.newInstance(new Object[]{serverHost, serverRoot}));
      }

      //ajoute les sorties
      for (int i = 0; i < nodeOutputs.getChildNodes().getLength(); i++) {
        Node node = nodeOutputs.getChildNodes().item(i);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          project.addOutput(OutputApplicationXMLLoader.loadSavedOutput(project, (Element) node));
        }
      }
    }
    catch (DOMException | IllegalAccessException | IllegalArgumentException |
        InvocationTargetException | NoSuchMethodException |
        SecurityException | ClassNotFoundException | InstantiationException e) {
      e.printStackTrace();

    }
  }

  /**
   * Create an Element object for a given Project
   *
   * @param document
   *          the document creating the XML file
   * @param project
   *          the project
   * @return the project as an Element
   * @throws ProcessingException
   */
  public static Element createProjectElement(Document document, Project project) throws ProcessingException {
    try {
      Node nodeName = document.createElement("name");
      Node nodeOrganisation = document.createElement("organisation");
      Node nodeAuthor = document.createElement("author");
      Node nodePackage = document.createElement("package");
      Node nodeDatasources = document.createElement("datasources");
      Node nodeOutputs = document.createElement("outputs");
      Node nodeFields = document.createElement("fields");
      Node nodeServer = document.createElement("server");
      Node nodeCriterias = document.createElement("criterias");

      nodeName.setTextContent(project.getName());
      nodeOrganisation.setTextContent(project.getOrganisation());
      nodeAuthor.setTextContent(project.getAuthor());
      nodePackage.setTextContent(project.getPackageName());

      Element element = document.createElement("project");

      //ajoute les informations sur le serveur
      if (project.getServer() == null) {
        ((Element) nodeServer).setAttribute("type", "none");
      }
      else {
        ((Element) nodeServer).setAttribute("type", project.getServer().getClass().getSimpleName());
        Node serverRoot = document.createElement("root");
        Node serverHost = document.createElement("host");
        serverRoot.setTextContent(project.getServer().getRootFolder());
        serverHost.setTextContent(project.getServer().getHost());
        nodeServer.appendChild(serverRoot);
        nodeServer.appendChild(serverHost);
      }

      //Ajoute les sources de donn�es
      for (AbstractDatasource datasource : project.getDatasources()) {
        String datasourceType = datasource.getClass().getSimpleName().replace("Datasource", "");
        String pckage = Desktop.getDatasourceTypes().get(datasourceType).getLocation();
        String clss = pckage + "." + datasourceType + "XMLLoader";
        Class<?> datasourceClass = Class.forName(clss);

        java.lang.reflect.Constructor constructor = datasourceClass.getConstructor();
        AbstractDatasourceXMLLoader loader = (AbstractDatasourceXMLLoader) constructor.newInstance();
        nodeDatasources.appendChild(loader.createElementDatasource(document, datasource));
      }
      //Ajoute les sorties
      for (OutputApplication output : project.getOutputs()) {

        nodeOutputs.appendChild(OutputApplicationXMLLoader.createElementOutput(document, output));
      }

      //Ajoute les champs
      for (Field field : project.getFields()) {
        Node nodeField = document.createElement("field");
        Node nodeFieldName = document.createElement("name");
        Node nodeMapping = document.createElement("mapping");
        nodeFieldName.setTextContent(field.getName());
        nodeField.appendChild(nodeFieldName);
        nodeField.appendChild(nodeMapping);
        for (AbstractDatasource datasource : project.getDatasources()) {
          for (AbstractDataset dataset : datasource.getDatasets()) {
            Node nodeDataset = document.createElement("dataset");
            ((Element) nodeDataset).setAttribute("datasource", dataset.getDatasource().getName());
            ((Element) nodeDataset).setAttribute("type", dataset.getClass().getSimpleName().replace("Dataset", ""));
            ((Element) nodeDataset).setAttribute("name", dataset.getName());
            String value = dataset.getDatasource().getProject().getMapping(field, dataset);
            nodeDataset.setTextContent(value);
            nodeMapping.appendChild(nodeDataset);
          }
        }
        nodeFields.appendChild(nodeField);
      }

      //Ajoute les crit�res
      for (Criteria criteria : project.getCriterias()) {
        Node nodeCriteria = document.createElement("criteria");
        Node nodeCriteriaTitle = document.createElement("title");
        nodeCriteriaTitle.setTextContent(criteria.getTitle());
        nodeCriteria.appendChild(nodeCriteriaTitle);
        Node nodeCriteriaConditions = document.createElement("conditions");
        nodeCriteria.appendChild(nodeCriteriaConditions);

        for (Field field : criteria.getConditions()) {
          Node nodeCriteriaCondition = document.createElement("condition");
          nodeCriteriaCondition.setTextContent(field.getName());
          nodeCriteriaConditions.appendChild(nodeCriteriaCondition);
        }
        nodeCriterias.appendChild(nodeCriteria);
      }

      element.appendChild(nodeName);
      element.appendChild(nodeOrganisation);
      element.appendChild(nodeAuthor);
      element.appendChild(nodePackage);
      element.appendChild(nodeDatasources);
      element.appendChild(nodeOutputs);
      element.appendChild(nodeFields);
      element.appendChild(nodeCriterias);
      element.appendChild(nodeServer);
      return element;
    }
    catch (ClassNotFoundException | NoSuchMethodException | SecurityException |
        IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
      throw new ProcessingException(e.getMessage());
    }
  }
}
