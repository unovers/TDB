/**
 * Nom du fichier         : OutputApplicationXMLLoader.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit comment est stoqué une application dans un fichier XML
 *
 * Historiques des modifications
 * -
 */
package ch.heigvd.bachelor.crescenzio.generator.outputs;

import java.util.LinkedList;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.utils.Utils;

/**
 * Define how is stored an output application in a XML file
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class OutputApplicationXMLLoader {

  public static OutputApplication loadOutput(Element element) {
    OutputApplication output = null;

    Node nodeName = Utils.getDirectChild(element, "name");
    output = new OutputApplication(nodeName.getTextContent());

    Node nodeApplicationFields = Utils.getDirectChild(element, "applicationFields");
    Node nodeMappedFields = Utils.getDirectChild(element, "mappedFields");
    Node nodeItemTypes = Utils.getDirectChild(element, "itemTypes");

    //Ajoute les champs d'application simple
    for (int i = 0; i < nodeApplicationFields.getChildNodes().getLength(); i++) {
      Node nodeField = nodeApplicationFields.getChildNodes().item(i);
      if (nodeField.getNodeType() == Node.ELEMENT_NODE) {
        Node nodeFieldName = Utils.getDirectChild((Element) nodeField, "name");
        Node nodeFieldValue = Utils.getDirectChild((Element) nodeField, "value");
        Node nodeFieldDescription = Utils.getDirectChild((Element) nodeField, "description");
        String typeField = ((Element) nodeField).getAttribute("type");
        OutputField outputField = null;
        if (typeField.equals("string")) {
          outputField = new StringField("");
          outputField.setDescription(nodeFieldDescription.getTextContent());
        }
        else if (typeField.equals("file")) {
          Node nodeTypeResourceAcceptedExtension = Utils.getDirectChild((Element) nodeField, "extensions");

          LinkedList<String> exts = new LinkedList<String>();
          //Ajoute les extensions supportées
          for (int k = 0; k < nodeTypeResourceAcceptedExtension.getChildNodes().getLength(); k++) {
            Node nodeTypeExtension = nodeTypeResourceAcceptedExtension.getChildNodes().item(k);
            if (nodeTypeExtension.getNodeType() == Node.ELEMENT_NODE) {
              exts.add(nodeTypeExtension.getTextContent());
            }
          }

          String[] extensions = new String[exts.size()];
          for (int k = 0; k < exts.size(); k++) {
            extensions[k] = exts.get(k);
          }

          outputField = new FileField();
          ((FileField) outputField).setExtensions(extensions);
          outputField.setValue(nodeFieldValue.getTextContent());
          outputField.setDescription(nodeFieldDescription.getTextContent());
        }
        else {
          throw new UnsupportedOperationException();
        }

        output.addApplicationField(new Field(nodeFieldName.getTextContent()), outputField);
      }
    }

    //Ajoute les champs pour chaque item
    for (int i = 0; i < nodeMappedFields.getChildNodes().getLength(); i++) {
      Node nodeField = nodeMappedFields.getChildNodes().item(i);
      if (nodeField.getNodeType() == Node.ELEMENT_NODE) {
        Node nodeFieldName = Utils.getDirectChild((Element) nodeField, "name");
        output.addMappedField(new Field(nodeFieldName.getTextContent()), null);
      }
    }

    //Ajoute les types prédefinis
    for (int i = 0; i < nodeItemTypes.getChildNodes().getLength(); i++) {
      Node nodeItemType = nodeItemTypes.getChildNodes().item(i);

      if (nodeItemType.getNodeType() == Node.ELEMENT_NODE) {
        Node nodeTypeName = Utils.getDirectChild((Element) nodeItemType, "name");
        ItemType itemType = new ItemType(nodeTypeName.getTextContent());

        Node nodeTypeResources = Utils.getDirectChild((Element) nodeItemType, "resources");
        //Ajoute les ressources nécessaires pour ce type
        for (int j = 0; j < nodeTypeResources.getChildNodes().getLength(); j++) {
          Node nodeTypeResource = nodeTypeResources.getChildNodes().item(j);
          if (nodeTypeResource.getNodeType() == Node.ELEMENT_NODE) {
            LinkedList<String> exts = new LinkedList<String>();
            Node nodeTypeResourceName = Utils.getDirectChild((Element) nodeTypeResource, "name");
            Node nodeTypeResourceValue = Utils.getDirectChild((Element) nodeTypeResource, "value");
            Node nodeTypeResourceDescription = Utils.getDirectChild((Element) nodeTypeResource, "description");
            Node nodeTypeResourceAcceptedExtension = Utils.getDirectChild((Element) nodeTypeResource, "extensions");

            //Ajoute les extensions supportées
            for (int k = 0; k < nodeTypeResourceAcceptedExtension.getChildNodes().getLength(); k++) {
              Node nodeTypeExtension = nodeTypeResourceAcceptedExtension.getChildNodes().item(k);
              if (nodeTypeExtension.getNodeType() == Node.ELEMENT_NODE) {
                exts.add(nodeTypeExtension.getTextContent());
              }
            }

            String[] extensions = new String[exts.size()];
            for (int k = 0; k < exts.size(); k++) {
              extensions[k] = exts.get(k);
            }

            //ajoute la ressource au type
            itemType.addResource(new FileResource(nodeTypeResourceName.getTextContent(),
                nodeTypeResourceValue.getTextContent(),
                nodeTypeResourceDescription.getTextContent(),
                extensions));
          }
        }

        output.addItemType(itemType);
      }
    }
    return output;
  }

  public static Element createElementOutput(Document document, OutputApplication output) {
    OutputApplication asloutput = (OutputApplication) output;

    Node nodeApplicationFields = document.createElement("applicationFields");
    Node nodeOutput = document.createElement("output");
    Node nodeMappedFields = document.createElement("mappedFields");
    Node nodeItemTypes = document.createElement("itemTypes");

    ((Element) nodeOutput).setAttribute("type", "AndroidSimpleList");

    Node nodeName = document.createElement("name");
    nodeName.setTextContent(output.getName());

    //Ajout les champs d'applications
    for (Entry<Field, OutputField> applicationField : output.getApplicationFields().entrySet()) {
      Node nodeApplicationField = document.createElement("field");
      if (applicationField.getValue() instanceof StringField) {
        ((Element) nodeApplicationField).setAttribute("type", "string");
      }
      else if (applicationField.getValue() instanceof FileField) {
        ((Element) nodeApplicationField).setAttribute("type", "file");
      }

      Node nodeApplicationFieldName = document.createElement("name");
      Node nodeApplicationFieldValue = document.createElement("value");
      Node nodeApplicationFieldDescription = document.createElement("description");
      if (applicationField.getValue() instanceof FileField) {
        Node nodeApplicationFieldExtensions = document.createElement("extensions");
        FileField fileField = (FileField) applicationField.getValue();
        for (String extension : fileField.getExtensions()) {
          Node nodeApplcationFieldExtension = document.createElement("extension");
          nodeApplcationFieldExtension.setTextContent(extension);
          nodeApplicationFieldExtensions.appendChild(nodeApplcationFieldExtension);
        }
        nodeApplicationField.appendChild(nodeApplicationFieldExtensions);
      }

      nodeApplicationFieldName.setTextContent(applicationField.getKey().getName());
      nodeApplicationFieldValue.setTextContent(applicationField.getValue().getValue());
      nodeApplicationFieldDescription.setTextContent(applicationField.getValue().getDescription());

      nodeApplicationField.appendChild(nodeApplicationFieldName);
      nodeApplicationField.appendChild(nodeApplicationFieldValue);
      nodeApplicationField.appendChild(nodeApplicationFieldDescription);
      nodeApplicationFields.appendChild(nodeApplicationField);
    }

    //Ajout le mapping des champs
    for (Entry<Field, Field> mappedField : output.getMappedFields().entrySet()) {
      Node nodeMappedfield = document.createElement("field");
      Node nodeMappedFieldName = document.createElement("name");
      Node nodeMappedFieldValue = document.createElement("value");

      nodeMappedFieldName.setTextContent(mappedField.getKey().getName());
      if (mappedField.getValue() != null) nodeMappedFieldValue.setTextContent(mappedField.getValue().getName());

      nodeMappedfield.appendChild(nodeMappedFieldName);
      nodeMappedfield.appendChild(nodeMappedFieldValue);
      nodeMappedFields.appendChild(nodeMappedfield);
    }

    //Ajoute les types d'items
    for (ItemType itemType : asloutput.getItemsTypes()) {
      Node nodeItemType = document.createElement("itemType");
      Node nodeItemTypeName = document.createElement("name");
      Node nodeItemTypeResources = document.createElement("resources");

      nodeItemTypeName.setTextContent(itemType.getName());

      nodeItemType.appendChild(nodeItemTypeName);
      for (FileResource itemFileResource : itemType.getResources()) {
        Node nodeItemFileResource = document.createElement("resource");
        Node nodeItemFileResourceDescription = document.createElement("description");
        Node nodeItemFileResourceExtensions = document.createElement("extensions");
        Node nodeItemFileResourceName = document.createElement("name");
        Node nodeItemFileResourceValue = document.createElement("value");

        nodeItemFileResourceName.setTextContent(itemFileResource.getName());
        nodeItemFileResourceValue.setTextContent(itemFileResource.getValue());
        nodeItemFileResourceDescription.setTextContent(itemFileResource.getDescription());
        for (String extension : itemFileResource.getTypesAllowed()) {
          Node nodeItemFileResourceExtension = document.createElement("extension");
          nodeItemFileResourceExtension.setTextContent(extension);
          nodeItemFileResourceExtensions.appendChild(nodeItemFileResourceExtension);
        }

        nodeItemFileResource.appendChild(nodeItemFileResourceExtensions);
        nodeItemFileResource.appendChild(nodeItemFileResourceName);
        nodeItemFileResource.appendChild(nodeItemFileResourceDescription);
        nodeItemFileResource.appendChild(nodeItemFileResourceValue);

        nodeItemTypeResources.appendChild(nodeItemFileResource);
      }

      nodeItemType.appendChild(nodeItemTypeName);
      nodeItemType.appendChild(nodeItemTypeResources);
      nodeItemTypes.appendChild(nodeItemType);
    }

    nodeOutput.appendChild(nodeName);
    nodeOutput.appendChild(nodeApplicationFields);
    nodeOutput.appendChild(nodeMappedFields);
    nodeOutput.appendChild(nodeItemTypes);

    return (Element) nodeOutput;
  }

  public static OutputApplication loadSavedOutput(Project project, Element element) {
    OutputApplication output = null;

    Node nodeName = Utils.getDirectChild(element, "name");
    output = new OutputApplication(nodeName.getTextContent());
    output.setProject(project);

    Node nodeApplicationFields = Utils.getDirectChild(element, "applicationFields");
    Node nodeMappedFields = Utils.getDirectChild(element, "mappedFields");
    Node nodeItemTypes = Utils.getDirectChild(element, "itemTypes");

    //Ajoute les champs d'application simple
    for (int i = 0; i < nodeApplicationFields.getChildNodes().getLength(); i++) {
      Node nodeField = nodeApplicationFields.getChildNodes().item(i);
      if (nodeField.getNodeType() == Node.ELEMENT_NODE) {
        Node nodeApplicationFieldName = Utils.getDirectChild((Element) nodeField, "name");
        Node nodeApplicationFieldValue = Utils.getDirectChild((Element) nodeField, "value");
        Node nodeApplicationFieldDescription = Utils.getDirectChild((Element) nodeField, "description");
        String typeField = ((Element) nodeField).getAttribute("type");
        OutputField outputField = null;
        if (typeField.equals("string")) {
          outputField = new StringField("");
        }
        else if (typeField.equals("file")) {
          Node nodeTypeResourceAcceptedExtension = Utils.getDirectChild((Element) nodeField, "extensions");

          LinkedList<String> exts = new LinkedList<String>();
          //Ajoute les extensions supportées
          for (int k = 0; k < nodeTypeResourceAcceptedExtension.getChildNodes().getLength(); k++) {
            Node nodeTypeExtension = nodeTypeResourceAcceptedExtension.getChildNodes().item(k);
            if (nodeTypeExtension.getNodeType() == Node.ELEMENT_NODE) {
              exts.add(nodeTypeExtension.getTextContent());
            }
          }

          String[] extensions = new String[exts.size()];
          for (int k = 0; k < exts.size(); k++) {
            extensions[k] = exts.get(k);
          }

          outputField = new FileField();
          ((FileField) outputField).setExtensions(extensions);
        }
        else {
          throw new UnsupportedOperationException();
        }

        outputField.setValue(nodeApplicationFieldValue.getTextContent());
        outputField.setDescription(nodeApplicationFieldDescription.getTextContent());

        output.addApplicationField(new Field(nodeApplicationFieldName.getTextContent()), outputField);
      }
    }

    //Ajoute les champs pour chaque item
    for (int i = 0; i < nodeMappedFields.getChildNodes().getLength(); i++) {
      Node nodeField = nodeMappedFields.getChildNodes().item(i);
      if (nodeField.getNodeType() == Node.ELEMENT_NODE) {
        Node nodeMappedFieldName = Utils.getDirectChild((Element) nodeField, "name");
        Node nodeMappedFieldValue = Utils.getDirectChild((Element) nodeField, "value");
        output.addMappedField(new Field(nodeMappedFieldName.getTextContent()),
            output.getProject().getFieldByName(
                nodeMappedFieldValue.getTextContent()
                ));
      }
    }

    //Ajoute les types prédefinis
    for (int i = 0; i < nodeItemTypes.getChildNodes().getLength(); i++) {
      Node nodeItemType = nodeItemTypes.getChildNodes().item(i);

      if (nodeItemType.getNodeType() == Node.ELEMENT_NODE) {
        Node nodeTypeName = Utils.getDirectChild((Element) nodeItemType, "name");
        LinkedList<String> exts = new LinkedList<String>();
        ItemType itemType = new ItemType(nodeTypeName.getTextContent());

        Node nodeTypeResources = Utils.getDirectChild((Element) nodeItemType, "resources");
        //Ajoute les ressources nécessaires pour ce type
        for (int j = 0; j < nodeTypeResources.getChildNodes().getLength(); j++) {
          Node nodeTypeResource = nodeTypeResources.getChildNodes().item(j);
          if (nodeTypeResource.getNodeType() == Node.ELEMENT_NODE) {
            Node nodeTypeResourceName = Utils.getDirectChild((Element) nodeTypeResource, "name");
            Node nodeTypeResourceValue = Utils.getDirectChild((Element) nodeTypeResource, "value");
            Node nodeTypeResourceDescription = Utils.getDirectChild((Element) nodeTypeResource, "description");
            Node nodeTypeResourceAcceptedExtension = Utils.getDirectChild((Element) nodeTypeResource, "extensions");

            //Ajoute les extensions supportées
            for (int k = 0; k < nodeTypeResourceAcceptedExtension.getChildNodes().getLength(); k++) {
              Node nodeTypeExtension = nodeTypeResourceAcceptedExtension.getChildNodes().item(k);
              if (nodeItemType.getNodeType() == Node.ELEMENT_NODE) {
                exts.add(nodeTypeExtension.getTextContent());
              }
            }
            String[] extensions = new String[exts.size()];
            for (int k = 0; k < exts.size(); k++) {
              extensions[k] = exts.get(k);
            }

            //ajoute la ressource au type
            itemType.addResource(new FileResource(nodeTypeResourceName.getTextContent(),
                nodeTypeResourceValue.getTextContent(),
                nodeTypeResourceDescription.getTextContent(),
                extensions));
          }
        }

        output.addItemType(itemType);
      }
    }
    return output;
  }
}
