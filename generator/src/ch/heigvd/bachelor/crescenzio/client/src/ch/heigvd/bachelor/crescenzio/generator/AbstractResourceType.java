/**
 * Nom du fichier         : AbstractResourceType.java
 * Version                : 0.1
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           :
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator;

/**
 * Définit un type de ressource général chargé au démarrage de l'application
 * <P>
 * Définit le package, le nom et le nom affiché de sources de données
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public abstract class AbstractResourceType {
  private String location;
  private String displayName;
  private String name;

  /**
   * @return the location
   */
  public String getLocation() {
    return location;
  }

  /**
   * @param location
   *          the location to set
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
   *          the displayName to set
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
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  protected AbstractResourceType(String name, String displayName, String location) {
    this.location = location;
    this.displayName = displayName;
    this.name = name;
  }
}
