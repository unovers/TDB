/**
 * Nom du fichier         : OutputField.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit un champs abstrait pour une application
 *
 * Historiques des modifications
 * -
 */
package ch.heigvd.bachelor.crescenzio.generator.outputs;

/**
 * Define a file field for the application
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public abstract class OutputField {

  private String value;//la valeur
  private String description; //une description éventuelle

  /**
   * @param name
   */
  public OutputField() {
  }

  /**
   * @return the value
   */
  public String getValue() {
    return value;
  }

  /**
   * @param value
   *          the value to set
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description
   *          the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

}
