/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.services.lookup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.outputs.AbstractOutputApplication;
import ch.heigvd.bachelor.crescenzio.generator.outputs.FileField;
import ch.heigvd.bachelor.crescenzio.generator.outputs.ItemType;
import ch.heigvd.bachelor.crescenzio.generator.outputs.StringField;
import ch.heigvd.bachelor.crescenzio.generator.outputs.androidsimplelist.AndroidSimpleListOutputApplication;

/**
 * @author Fabio
 */
public class OutputTypeLookupCall extends LocalLookupCall<AbstractOutputApplication> {
  private static final long serialVersionUID = 1L;

  @Override
  protected List<ILookupRow<AbstractOutputApplication>> execCreateLookupRows() throws ProcessingException {
    List<ILookupRow<AbstractOutputApplication>> rows = new ArrayList<ILookupRow<AbstractOutputApplication>>();
    LinkedList<AbstractOutputApplication> outputs = new LinkedList<AbstractOutputApplication>();
    AndroidSimpleListOutputApplication android = new AndroidSimpleListOutputApplication("Android output");
    android.addApplicationField(new Field("project_icon72"), new FileField());
    android.addApplicationField(new Field("application_title"), new StringField(""));
    android.setField(new Field("name"), null);
    android.setField(new Field("icon"), null);
    android.setField(new Field("date"), null);
    android.setField(new Field("description"), null);
    ItemType itemType = new ItemType("__default");
    itemType.setItemlist_file_path("C:\\...");
    itemType.setItemview_file_path("C:\\...");
    android.addItemType(itemType);
    outputs.add(android);

    for (AbstractOutputApplication output : outputs) {
      rows.add(new LookupRow<AbstractOutputApplication>(output, output.getName()));
    }
    return rows;
  }
}
