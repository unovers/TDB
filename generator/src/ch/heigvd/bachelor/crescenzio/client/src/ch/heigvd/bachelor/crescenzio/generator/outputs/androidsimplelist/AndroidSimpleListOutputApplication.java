package ch.heigvd.bachelor.crescenzio.generator.outputs.androidsimplelist;

import java.util.Map.Entry;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.outputs.AbstractOutputApplication;
import ch.heigvd.bachelor.crescenzio.generator.outputs.ItemType;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputField;

public class AndroidSimpleListOutputApplication extends AbstractOutputApplication {

  /**
   * @param project
   * @param name
   */
  public AndroidSimpleListOutputApplication(String name) {
    super(name);
  }

  @Override
  public void generate() {
  }

  @Override
  public AndroidSimpleListOutputApplication duplicate() {
    AndroidSimpleListOutputApplication output = new AndroidSimpleListOutputApplication(getName());
    for (Entry<Field, OutputField> entry : getApplicationFields().entrySet()) {
      Field field = entry.getKey();
      OutputField outputField = entry.getValue();
      output.addApplicationField(field, outputField);
    }

    for (Entry<Field, Field> entry : getMappedFields().entrySet()) {
      Field field = entry.getKey();
      Field mappedField = entry.getValue();
      output.addMappedField(field, mappedField);
    }

    for (ItemType itemType : getItemsTypes()) {
      output.addItemType(itemType);
    }

    return output;
  }
}
