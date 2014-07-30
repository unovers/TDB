/**
 * Nom du fichier         : DatasourceType.java
 * Version                : 0.1
 * Auteur                 : Crescenzio Fabio
 *
 * Date derni�re r�vision : 30.07.2014
 *
 * Commentaires           :
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator;

/**
 * D�finit un type de source de donn�es
 * <P>
 * D�finit le package, le nom et le nom affich� de sources de donn�es
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
