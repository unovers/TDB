/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.services.lookup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import ch.heigvd.bachelor.crescenzio.generator.OutputType;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputApplication;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputApplicationXMLLoader;

/**
 * @author Fabio
 */
public class OutputTypeLookupCall extends LocalLookupCall<OutputApplication> {
  private static final long serialVersionUID = 1L;

  @Override
  protected List<ILookupRow<OutputApplication>> execCreateLookupRows() throws ProcessingException {
    List<ILookupRow<OutputApplication>> rows = new ArrayList<ILookupRow<OutputApplication>>();

    HashMap<String, OutputType> outputTypes = Desktop.getOutputTypes();

    //Charge les fichiers XML décrivant les applications disponibles
    for (String outputType : outputTypes.keySet()) {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder;
      try {
        dBuilder = dbFactory.newDocumentBuilder();

        Document doc = dBuilder.parse(OutputApplication.class.getResourceAsStream(outputTypes.get(outputType).getLocation() + File.separator + "application.xml"));
        doc.getDocumentElement().normalize();
        OutputApplication output = new OutputApplicationXMLLoader().loadOutput(doc.getDocumentElement());

        rows.add(new LookupRow<OutputApplication>(output, output.getName()));
      }
      catch (ParserConfigurationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      catch (SAXException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    return rows;
  }
}
