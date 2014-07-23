package ch.heigvd.bachelor.crescenzio.generator.datasets;

public class AbstractSQLDataset extends AbstractDataset {

  private String query;

  public AbstractSQLDataset(String name, String query) {
    super(name);
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
