package ch.heigvd.bachelor.crescenzio.generator.datasets;

public class SQLDataset extends Dataset {

  private String query;

  public SQLDataset(String name, String query) {
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
