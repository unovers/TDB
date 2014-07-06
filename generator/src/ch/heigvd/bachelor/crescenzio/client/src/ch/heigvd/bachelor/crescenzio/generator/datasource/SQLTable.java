package ch.heigvd.bachelor.crescenzio.generator.datasource;

import java.util.LinkedList;

import ch.heigvd.bachelor.crescenzio.generator.dataset.SQLField;

public class SQLTable {
  private String name;
  private LinkedList<SQLField> fields;

  public SQLTable(String name) {
    super();
    this.name = name;
    this.fields = new LinkedList<SQLField>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LinkedList<SQLField> getFields() {
    return fields;
  }

  public void setFields(LinkedList<SQLField> fields) {
    this.fields = fields;
  }

  public void addField(SQLField field) {
    this.fields.add(field);
  }

  public void removeField(SQLField field) {
    this.fields.remove(field);
  }
}
