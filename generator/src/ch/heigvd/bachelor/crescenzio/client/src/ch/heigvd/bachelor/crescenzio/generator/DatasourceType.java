/**
 * Nom du fichier         : DatasourceType.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit un type de source de données
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator;

/**
 * Define a datasource type resource
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class DatasourceType extends AbstractResourceType {

  /**
   * @param location
   * @param displayName
   * @param name
   */
  public DatasourceType(String name, String displayName, String location) {
    super(name, displayName, location);
  }

}
