/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.outputs;

/**
 * @author Fabio
 */
public abstract class OutputField {

  private String value;
  private String description;
  private String[] extensions;

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

  /**
   * @param extensions
   */
  public void setExtensions(String[] extensions) {
    this.extensions = extensions;
  }

}
