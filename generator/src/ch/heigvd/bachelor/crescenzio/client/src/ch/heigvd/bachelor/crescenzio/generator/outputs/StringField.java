/**
 * Nom du fichier         : StringField.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit un champ de type string
 *
 * Historiques des modifications
 * -
 */
package ch.heigvd.bachelor.crescenzio.generator.outputs;

/**
 * Define a string field for the application
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class StringField extends OutputField {

  /**
   * @param name
   */
  public StringField(String value) {
    setValue(value);
  }

}
