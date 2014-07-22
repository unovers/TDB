/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.outputs;

/**
 * @author Fabio
 */
public class ItemType {
  private String name;
  private String itemlist_file_path;
  private String itemview_file_path;

  /**
   * @param string
   */
  public ItemType(String name) {
    this.name = name;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the itemlist_file_path
   */
  public String getItemlist_file_path() {
    return itemlist_file_path;
  }

  /**
   * @param itemlist_file_path
   *          the itemlist_file_path to set
   */
  public void setItemlist_file_path(String itemlist_file_path) {
    this.itemlist_file_path = itemlist_file_path;
  }

  /**
   * @return the itemview_file_path
   */
  public String getItemview_file_path() {
    return itemview_file_path;
  }

  /**
   * @param itemview_file_path
   *          the itemview_file_path to set
   */
  public void setItemview_file_path(String itemview_file_path) {
    this.itemview_file_path = itemview_file_path;
  }

}
