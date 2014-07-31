/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.outputs;

import java.util.LinkedList;

/**
 * @author Fabio
 */
public class ItemType {
  private String name;
  private static int counter;
  private int id;
  private LinkedList<FileResource> resources;
  static {
    counter = 0;
  }

  /**
   * @param string
   */
  public ItemType(String name) {
    this.name = name;
    this.resources = new LinkedList<FileResource>();
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
   * @return
   */
  public int getId() {
    return id;
  }

  /**
   * @return the resources
   */
  public LinkedList<FileResource> getResources() {
    return resources;
  }

  /**
   * @param resources
   *          the resources to set
   */
  public void setResources(LinkedList<FileResource> resources) {
    this.resources = resources;
  }

  public void addResource(FileResource res) {
    this.resources.add(res);
  }

}
