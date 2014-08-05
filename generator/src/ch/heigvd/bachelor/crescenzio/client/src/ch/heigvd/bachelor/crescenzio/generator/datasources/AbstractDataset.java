/**
 * Nom du fichier         : AbstractDataset.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Cette classe définit un set de données
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.datasources;

import org.eclipse.scout.commons.exception.ProcessingException;

/**
 * Define an abstract dataset
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public abstract class AbstractDataset {
  private String name; //le nom
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

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return a grid of Objects containing the datasets datas
   */
  public Object[][] getDatas() {
    return datas;
  }

  /**
   * @return an object array containing all headers
   */
  public Object[] getHeaders() {
    return headers;
  }

  /**
   * @param datas
   *          the datas to set
   */
  public void setDatas(Object[][] datas) {
    this.datas = datas;
  }

  /**
   * @param headers
   *          the headers to set
   */
  public void setHeaders(Object[] headers) {
    this.headers = headers;
  }

  /**
   * Define how a dataset is previewed
   *
   * @throws ProcessingException
   */
  public abstract void preview() throws ProcessingException;

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return a boolean saying if the dataset result has already been loaded
   */
  public boolean isDescribed() {
    return described;
  }

  /**
   * Set the descibed status of the dataset
   *
   * @param status
   *          the new status
   */
  public void setDescribed(boolean status) {
    described = status;
  }
}
