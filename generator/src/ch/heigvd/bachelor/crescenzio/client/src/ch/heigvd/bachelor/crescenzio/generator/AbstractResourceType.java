/**
 * Nom du fichier         : AbstractResourceType.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit un type de ressource général pour l'application
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator;

/**
 * Define an abstract resource type for the application
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public abstract class AbstractResourceType {
  private String location; //le package où la ressource se trouve
  private String displayName; // le nom a utiliser pour l'affichage
  private String name; // le nom de la classe

  /**
   * Constructeur
   *
   * @param name
   *          : the class name
   * @param displayName
   *          : the display name
   * @param location
   *          : the package location
   */
  protected AbstractResourceType(String name, String displayName, String location) {
    this.location = location;
    this.displayName = displayName;
    this.name = name;
  }

  /**
   * @return the location
   */
  public String getLocation() {
    return location;
  }

  /**
   * @param location
   *          the location
   */
  public void setLocation(String location) {
    this.location = location;
  }

  /**
   * @return the displayName
   */
  public String getDisplayName() {
    return displayName;
  }

  /**
   * @param displayName
   *          the displayName
   */
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name
   */
  public void setName(String name) {
    this.name = name;
  }

}
