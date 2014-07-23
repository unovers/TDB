package ch.heigvd.bachelor.crescenzio.generator.datasets;

import java.util.LinkedList;

import ch.heigvd.bachelor.crescenzio.generator.Field;

public abstract class AbstractDataset {
  private String name;
  private LinkedList<Field> fields;
  private int id;
  private static int counter;

  static {
    counter = 0;
  }

  protected AbstractDataset(String name) {
    this.name = name;
    this.fields = new LinkedList<Field>();
    this.id = counter++;
  }

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void addField(Field field) {
    this.fields.add(field);
  }

  public LinkedList<Field> getFields() {
    return fields;
  }

}
