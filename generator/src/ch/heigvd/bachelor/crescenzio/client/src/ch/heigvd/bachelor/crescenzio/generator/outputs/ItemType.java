/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.outputs;

/**
 * @author Fabio
 */
public class ItemType {
  private String name;
  private static int counter;
  private int id;
  private String itemListViewFileName;
  private String itemViewFileName;

  static {
    counter = 0;
  }

  /**
   * @param string
   */
  public ItemType(String name) {
    this.name = name;
    this.id = counter++;
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
   * @return the itemListViewFileName
   */
  public String getItemListViewFileName() {
    return itemListViewFileName;
  }

  /**
   * @param itemListViewFileName
   *          the itemListViewFileName to set
   */
  public void setItemListViewFileName(String itemListViewFileName) {
    this.itemListViewFileName = itemListViewFileName;
  }

  /**
   * @return the itemViewFileName
   */
  public String getItemViewFileName() {
    return itemViewFileName;
  }

  /**
   * @param itemViewFileName
   *          the itemViewFileName to set
   */
  public void setItemViewFileName(String itemViewFileName) {
    this.itemViewFileName = itemViewFileName;
  }

  /**
   * @return
   */
  public int getId() {
    return id;
  }

}
