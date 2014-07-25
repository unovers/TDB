package ch.heigvd.bachelor.crescenzio.generator.datasets;

import java.util.LinkedList;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;

public abstract class AbstractDataset {
  private String name;
  private LinkedList<Field> fields;
  private int id;
  private Object[][] datas;
  private Object[] headers;
  private AbstractDatasource datasource;
  private boolean described;
  private static int counter;

  static {
    counter = 0;
  }

  protected AbstractDataset(AbstractDatasource datasource, String name) {
    this.datasource = datasource;
    this.described = false;
    this.name = name;
    this.fields = new LinkedList<Field>();
    this.id = counter++;
  }

  /**
   * @return the datasource
   */
  public AbstractDatasource getDatasource() {
    return datasource;
  }

  /**
   * @param datasource
   *          the datasource to set
   */
  public void setDatasource(AbstractDatasource datasource) {
    this.datasource = datasource;
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

  public Object[][] getDatas() {
    return datas;
  }

  public Object[] getHeaders() {
    return headers;
  }

  public void setDatas(Object[][] datas) {
    this.datas = datas;
  }

  public void setHeaders(Object[] headers) {
    this.headers = headers;
  }

  public abstract void preview() throws ProcessingException;

  public void setName(String name) {
    this.name = name;
  }

  public void addField(Field field) {
    this.fields.add(field);
  }

  public LinkedList<Field> getFields() {
    return fields;
  }

  /**
   * @return
   */
  public boolean isDescribed() {
    return described;
  }

  public void setDescribed(boolean status) {
    described = status;
  }
}
