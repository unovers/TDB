/**
 * Nom du fichier         : FileField.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit un champ de type fichier
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
public class FileField extends OutputField {
  private String[] extensions;

  /**
   * @return the extensions
   */
  public String[] getExtensions() {
    return extensions;
  }

  /**
   * @param extensions
   *          the extensions to set
   */
  public void setExtensions(String[] extensions) {
    this.extensions = extensions;
  }

  /**
   * @param name
   */
  public FileField() {
    super();
  }

}
