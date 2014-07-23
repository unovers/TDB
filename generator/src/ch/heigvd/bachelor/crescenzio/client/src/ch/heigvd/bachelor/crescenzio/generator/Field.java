package ch.heigvd.bachelor.crescenzio.generator;

public class Field {

  private String name;
  private String id;
  private String value;

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
   * @param id
   *          the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  public Field(String name) {
    super();
    this.name = name;
    this.id = name.replaceAll("\\s+", "");
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
