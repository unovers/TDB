/**
 * Nom du fichier         : ServerType.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Cette classe représente une ressource de type serveur
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator;

/**
 * Define a server type resource
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class ServerType extends AbstractResourceType {

  /**
   * @param location
   * @param displayName
   * @param name
   */
  public ServerType(String location, String displayName, String name) {
    super(location, displayName, name);
  }

}
