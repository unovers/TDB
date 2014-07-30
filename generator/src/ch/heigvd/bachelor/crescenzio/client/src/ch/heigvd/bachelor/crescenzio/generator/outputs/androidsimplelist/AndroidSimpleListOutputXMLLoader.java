package ch.heigvd.bachelor.crescenzio.generator.outputs.androidsimplelist;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import ch.heigvd.bachelor.crescenzio.generator.outputs.AbstractOutputApplication;
import ch.heigvd.bachelor.crescenzio.generator.outputs.AbstractOutputXMLLoader;

public class AndroidSimpleListOutputXMLLoader extends AbstractOutputXMLLoader {
  public AndroidSimpleListOutputXMLLoader() {

  }

  @Override
  public AbstractOutputApplication loadOutput(Element element) {

    return null;
  }

  @Override
  public Element createElementOutput(Document document, AbstractOutputApplication output) {
    AndroidSimpleListOutputApplication asloutput = (AndroidSimpleListOutputApplication) output;
    Node nodeOutput = document.createElement("output");
    ((Element) nodeOutput).setAttribute("type", "AndroidSimpleList");
    Node nodeName = document.createElement("name");
    nodeName.setTextContent(output.getName());
    nodeOutput.appendChild(nodeName);
    return (Element) nodeOutput;
  }
}
