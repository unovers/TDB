/**
 * Nom du fichier         : OutputApplication.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit une sortie pour une application génerée
 *
 * Historiques des modifications
 * -
 */
package ch.heigvd.bachelor.crescenzio.generator.outputs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.Project;

/**
 * Define an abstact output generator for an application
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class OutputApplication {

  private HashMap<Field, OutputField> applicationFields; //les champs pour une application
  private LinkedList<ItemType> itemsTypes; //la liste des types déclarés pour l'application
  private HashMap<Field, Field> mappedFields; //le mapping pour les champs obligatoire avec les champs ciblés
  private String name; //le nom de l'output
  private Project project; //le projet

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

  /**
   * @param field
   *          the field we want to add
   * @param outputField
   *          the type of the field
   */
  public void addApplicationField(Field field, OutputField outputField) {
    if (applicationFields.get(field) != null) applicationFields.remove(field);
    applicationFields.put(field, outputField);
  }

  /**
   * @param field
   *          the field we are setting
   * @param outputField
   *          the value of the field in the project
   */
  public void addMappedField(Field field, Field outputField) {
    if (mappedFields.get(field) != null) mappedFields.remove(field);
    mappedFields.put(field, outputField);
  }

  /**
   * @param itemType
   *          the item type to add
   */
  public void addItemType(ItemType itemType) {
    itemsTypes.add(itemType);
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

  /**
   * @param itemType
   *          the item type to remove
   */
  public void removeItemType(ItemType itemType) {
    itemsTypes.remove(itemType);
  }

  /**
   * @param mappedField
   *          the field we want to edit
   * @param field
   *          the new value
   */
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

  /**
   * @return duplicate an output application for the project
   */
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
