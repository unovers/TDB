package ch.heigvd.bachelor.crescenzio.generator.outputs;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import ch.heigvd.bachelor.crescenzio.generator.Field;

public class OutputApplicationXMLLoader {

  public static Element getDirectChild(Element parent, String name)
  {
    for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling())
    {
      if (child instanceof Element && name.equals(child.getNodeName())) return (Element) child;
    }
    return null;
  }

  public static OutputApplication loadOutput(Element element) {
    OutputApplication output = null;

    Node nodeName = getDirectChild(element, "name");
    output = new OutputApplication(nodeName.getTextContent());

    Node nodeApplicationFields = getDirectChild(element, "applicationFields");
    Node nodeMappingFields = getDirectChild(element, "mappingFields");
    Node nodeItemTypes = getDirectChild(element, "itemTypes");

    //Ajoute les champs d'application simple
    for (int i = 0; i < nodeApplicationFields.getChildNodes().getLength(); i++) {
      Node nodeField = nodeApplicationFields.getChildNodes().item(i);
      if (nodeField.getNodeType() == Node.ELEMENT_NODE) {
        String typeField = ((Element) nodeField).getAttribute("type");
        OutputField outputField = null;
        if (typeField.equals("string")) {
          outputField = new StringField("");
        }
        else if (typeField.equals("file")) {
          outputField = new FileField();
        }

        output.addApplicationField(new Field(nodeField.getTextContent()), outputField);
      }
    }

    //Ajoute les champs pour chaque item
    for (int i = 0; i < nodeMappingFields.getChildNodes().getLength(); i++) {
      Node nodeField = nodeMappingFields.getChildNodes().item(i);
      if (nodeField.getNodeType() == Node.ELEMENT_NODE) {
        output.addMappedField(new Field(nodeField.getTextContent()), null);
      }
    }

    //Ajoute les types prédefinis
    for (int i = 0; i < nodeItemTypes.getChildNodes().getLength(); i++) {
      Node nodeType = nodeItemTypes.getChildNodes().item(i);
      if (nodeType.getNodeType() == Node.ELEMENT_NODE) {
        Node nodeNameType = getDirectChild((Element) nodeType, "name");

        ItemType itemType = new ItemType(nodeNameType.getTextContent());
        output.addItemType(itemType);
      }
    }
    return output;
  }

  public static Element createElementOutput(Document document, OutputApplication output) {
    OutputApplication asloutput = (OutputApplication) output;

    Node nodeApplicationFields = document.createElement("applicationFields");
    Node nodeOutput = document.createElement("output");
    Node nodeFields = document.createElement("mappingFields");
    Node nodeTypes = document.createElement("itemTypes");

    ((Element) nodeOutput).setAttribute("type", "AndroidSimpleList");

    Node nodeName = document.createElement("name");
    nodeName.setTextContent(output.getName());

    //Ajoute les types d'items
    for (ItemType itemType : asloutput.getItemsTypes()) {

      Node nodeItemType = document.createElement("itemType");
      Node nodeItemName = document.createElement("name");
      Node nodeItemListView = document.createElement("itemListViewFile");
      Node nodeItemView = document.createElement("itemView");

      nodeItemName.setTextContent(itemType.getName());
      nodeItemListView.setTextContent(itemType.getItemListViewFileName());
      nodeItemView.setTextContent(itemType.getItemViewFileName());

      nodeItemType.appendChild(nodeItemName);
      nodeItemType.appendChild(nodeItemListView);
      nodeItemType.appendChild(nodeItemView);

      nodeTypes.appendChild(nodeItemType);
    }

    nodeOutput.appendChild(nodeName);
    nodeOutput.appendChild(nodeApplicationFields);
    nodeOutput.appendChild(nodeFields);
    nodeOutput.appendChild(nodeTypes);

    return (Element) nodeOutput;
  }

  public static OutputApplication loadSavedOutput(Element element) {
    return loadOutput(element);
  }
}
