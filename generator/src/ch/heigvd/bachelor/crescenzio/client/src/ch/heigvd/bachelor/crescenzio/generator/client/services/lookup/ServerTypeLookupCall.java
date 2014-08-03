/**
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
 * @author Fabio
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
