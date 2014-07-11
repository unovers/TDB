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
public class ServerTypeLookupCall extends LocalLookupCall<Long> {

  private static final long serialVersionUID = 1L;

  @Override
  protected List<ILookupRow<Long>> execCreateLookupRows() throws ProcessingException {
    List<ILookupRow<Long>> rows = new ArrayList<ILookupRow<Long>>();
    rows.add(new LookupRow<Long>(1L, "PHP"));
    rows.add(new LookupRow<Long>(2L, "J2EE"));
    return rows;
  }
}
