/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.outputs;

/**
 * @author Fabio
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
