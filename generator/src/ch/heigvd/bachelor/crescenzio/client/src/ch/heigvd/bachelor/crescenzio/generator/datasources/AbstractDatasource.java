package ch.heigvd.bachelor.crescenzio.generator.datasources;

import java.util.LinkedList;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.datasets.AbstractDataset;

public abstract class AbstractDatasource {
  private String name;
  private LinkedList<AbstractDataset> datasets;
  private boolean isConnectionOpen;
  private boolean isDescribed;

  /**
   * @return the isDescribed
   */
  public boolean isDescribed() {
    return isDescribed;
  }

  /**
   * @param isDescribed
   *          the isDescribed to set
   */
  public void setDescribed(boolean isDescribed) {
    this.isDescribed = isDescribed;
  }

  protected AbstractDatasource(String name) {
    this.name = name;
    this.datasets = new LinkedList<AbstractDataset>();
    this.isConnectionOpen = false;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void addDataset(AbstractDataset dataset) {
    this.datasets.add(dataset);
  }

  public void removeDataset(AbstractDataset dataset) {
    this.datasets.remove(datasets);
  }

  public LinkedList<AbstractDataset> getDatasets() {
    return datasets;
  }

  public void emptyDatasets() {
    this.datasets.clear();
  }

  public boolean isConnectionOpen() {
    return isConnectionOpen;
  }

  public void setConnectionOpenStatus(boolean status) {
    isConnectionOpen = status;
  }

  public abstract boolean connect() throws ProcessingException;

  public abstract boolean disconnect();

  public abstract void describe() throws ProcessingException;

  public abstract void query(String query) throws ProcessingException;
}
