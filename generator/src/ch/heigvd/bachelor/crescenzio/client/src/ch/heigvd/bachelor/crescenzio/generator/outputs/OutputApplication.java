package ch.heigvd.bachelor.crescenzio.generator.outputs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.Project;

public class OutputApplication {

  private HashMap<Field, OutputField> applicationFields;
  private LinkedList<ItemType> itemsTypes;
  private HashMap<Field, Field> mappedFields;
  private String name;
  private Project project;

  /**
   * @param project
   * @param name
   */
  public OutputApplication(String name) {
    this.name = name;
    this.mappedFields = new HashMap<Field, Field>();
    this.applicationFields = new HashMap<Field, OutputField>();
    this.itemsTypes = new LinkedList<ItemType>();
  }

  public void addApplicationField(Field field, OutputField outputField) {
    if (applicationFields.get(field) != null) applicationFields.remove(field);
    applicationFields.put(field, outputField);
  }

  public void addMappedField(Field field, Field outputField) {
    if (mappedFields.get(field) != null) mappedFields.remove(field);
    mappedFields.put(field, outputField);
  }

  public void addItemType(ItemType itemType) {
    itemsTypes.add(itemType);
  }

  public void generate() {

  }

  /**
   * @return the applicationFields
   */
  public HashMap<Field, OutputField> getApplicationFields() {
    return applicationFields;
  }

  /**
   * @return the itemsTypes
   */
  public LinkedList<ItemType> getItemsTypes() {
    return itemsTypes;
  }

  /**
   * @return the mappedFields
   */
  public HashMap<Field, Field> getMappedFields() {
    return mappedFields;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the project
   */
  public Project getProject() {
    return project;
  }

  public void removeItemType(ItemType itemType) {
    if (itemsTypes.contains(itemType)) itemsTypes.remove(itemType);
  }

  public void setField(Field mappedField, Field field) {
    if (mappedFields.get(mappedField) != null) mappedFields.remove(mappedField);
    mappedFields.put(mappedField, field);
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @param project
   *          the project to set
   */
  public void setProject(Project project) {
    this.project = project;
  }

  public OutputApplication duplicate() {
    OutputApplication output = new OutputApplication(getName());
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
