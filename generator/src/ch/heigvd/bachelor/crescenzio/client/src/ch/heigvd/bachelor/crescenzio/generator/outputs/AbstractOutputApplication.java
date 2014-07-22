package ch.heigvd.bachelor.crescenzio.generator.outputs;

import java.util.HashMap;
import java.util.LinkedList;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.Project;

public abstract class AbstractOutputApplication {
  private HashMap<Field, OutputField> applicationFields;
  private LinkedList<ItemType> itemsTypes;
  private HashMap<Field, Field> mappedFields;
  private String name;
  private Project project;

  /**
   * @param project
   * @param name
   */
  public AbstractOutputApplication(Project project, String name) {
    this.project = project;
    this.name = name;
    this.mappedFields = new HashMap<Field, Field>();
    this.applicationFields = new HashMap<Field, OutputField>();
    this.itemsTypes = new LinkedList<ItemType>();
    itemsTypes.add(new ItemType("default"));

  }

  public void addApplicationField(Field field, OutputField outputField) {
    if (applicationFields.get(field) != null) applicationFields.remove(field);
    applicationFields.put(field, outputField);
  }

  public void addItemType(ItemType itemType) {
    itemsTypes.add(itemType);
  }

  public abstract AbstractOutputApplication clone();

  public abstract void generate();

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
}
