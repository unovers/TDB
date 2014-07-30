/**
 * Nom du fichier         : DatasourceType.java
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
 * Définit un type de source de données
 * <P>
 * Définit le package, le nom et le nom affiché de sources de données
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
