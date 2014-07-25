package ch.heigvd.bachelor.crescenzio.generator.datasets;

import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractSQLDatasource;

public abstract class AbstractSQLDataset extends AbstractDataset {

  private String query;

  public AbstractSQLDataset(AbstractSQLDatasource datasource, String name, String query) {
    super(datasource, name);
    this.query = query;
  }

  /**
   * @return the query
   */
  public String getQuery() {
    return query;
  }

  /**
   * @param query
   *          the query to set
   */
  public void setQuery(String query) {
    this.query = query;
  }

}
