package ch.heigvd.bachelor.crescenzio.generator.datasets;

import java.util.LinkedList;

import ch.heigvd.bachelor.crescenzio.generator.Field;

public abstract class Dataset {
  private String name;
  private LinkedList<Field> fields;

  protected Dataset(String name) {
    this.name = name;
    this.fields = new LinkedList<Field>();
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
