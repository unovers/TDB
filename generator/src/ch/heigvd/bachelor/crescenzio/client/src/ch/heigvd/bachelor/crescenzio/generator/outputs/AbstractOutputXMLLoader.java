package ch.heigvd.bachelor.crescenzio.generator.outputs;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class AbstractOutputXMLLoader {

  public abstract AbstractOutputApplication loadOutput(Element element);

  public abstract Element createElementOutput(Document document, AbstractOutputApplication output);

}
