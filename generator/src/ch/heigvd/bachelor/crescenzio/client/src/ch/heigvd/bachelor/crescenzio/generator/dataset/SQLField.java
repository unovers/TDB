package ch.heigvd.bachelor.crescenzio.generator.dataset;

import ch.heigvd.bachelor.crescenzio.generator.Field;

public class SQLField extends Field {

  private String type;

  public SQLField(String name, String type) {
    super(name);
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
