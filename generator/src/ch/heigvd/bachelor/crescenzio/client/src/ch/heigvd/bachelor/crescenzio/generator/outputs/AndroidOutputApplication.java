package ch.heigvd.bachelor.crescenzio.generator.outputs;

import java.util.Map.Entry;

import ch.heigvd.bachelor.crescenzio.generator.Field;

public class AndroidOutputApplication extends AbstractOutputApplication {

  /**
   * @param project
   * @param name
   */
  public AndroidOutputApplication(String name) {
    super(name);
  }

  @Override
  public void generate() {
  }

  @Override
  public AndroidOutputApplication duplicate() {
    AndroidOutputApplication output = new AndroidOutputApplication(getName());
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
