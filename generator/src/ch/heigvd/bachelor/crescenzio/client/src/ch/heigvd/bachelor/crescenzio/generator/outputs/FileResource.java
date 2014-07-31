/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.outputs;

/**
 * @author Fabio
 */
public class FileResource {
  private String name;
  private String description;
  private String value;
  private int id;
  private static int current;

  private String[] typesAllowed;

  static {
    current = 0;
  }

  /**
   * @param name
   * @param description
   * @param typesAllowed
   */
  public FileResource(String name, String value, String description, String[] typesAllowed) {
    super();
    this.name = name;
    this.value = value;
    this.description = description;
    this.typesAllowed = typesAllowed;
    this.id = current++;
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
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
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
   * @return the typesAllowed
   */
  public String[] getTypesAllowed() {
    return typesAllowed;
  }

  /**
   * @param typesAllowed
   *          the typesAllowed to set
   */
  public void setTypesAllowed(String[] typesAllowed) {
    this.typesAllowed = typesAllowed;
  }

  /**
   * @return
   */
  public int getId() {
    return id;
  }

}
