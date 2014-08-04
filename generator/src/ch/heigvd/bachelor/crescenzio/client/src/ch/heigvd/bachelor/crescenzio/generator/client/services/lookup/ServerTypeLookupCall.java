/**
 * Nom du fichier         : ServerTypeLookupCall.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Cette classe définit un lookupCall pour les types de server
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.services.lookup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

import ch.heigvd.bachelor.crescenzio.generator.ServerType;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;

/**
 * Define a lookupCall for the server types
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class ServerTypeLookupCall extends LocalLookupCall<String> {

  private static final long serialVersionUID = 1L;

  @Override
  protected List<ILookupRow<String>> execCreateLookupRows() throws ProcessingException {
    List<ILookupRow<String>> rows = new ArrayList<ILookupRow<String>>();

    HashMap<String, ServerType> outputTypes = Desktop.getServerTypes();
    for (String outputType : outputTypes.keySet()) {
      rows.add(new LookupRow<String>(outputType, outputType));
    }
    return rows;
  }
}
