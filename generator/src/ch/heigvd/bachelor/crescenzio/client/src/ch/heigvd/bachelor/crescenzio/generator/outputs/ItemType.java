/**
 * Nom du fichier         : ItemType.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit un type d'item pour une application
 *
 * Historiques des modifications
 * -
 */
package ch.heigvd.bachelor.crescenzio.generator.outputs;

import java.util.LinkedList;

/**
 * Define an item type for an output
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
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
