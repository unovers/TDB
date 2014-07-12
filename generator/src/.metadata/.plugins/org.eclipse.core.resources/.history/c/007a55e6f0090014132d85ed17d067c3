/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.services.lookup;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

/**
 * @author Fabio
 */
public class ServerTypeLookupCall extends LocalLookupCall<String> {

  private static final long serialVersionUID = 1L;

  @Override
  protected List<ILookupRow<String>> execCreateLookupRows() throws ProcessingException {
    List<ILookupRow<String>> rows = new ArrayList<ILookupRow<String>>();
    rows.add(new LookupRow<String>("PHPServer", "PHP"));
    return rows;
  }
}
