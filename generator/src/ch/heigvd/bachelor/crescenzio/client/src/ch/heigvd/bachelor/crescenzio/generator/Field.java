/**
 * Nom du fichier         : Field.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit un champ pour l'application
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator;

/**
 * Define a field in the application
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class Field {

  private String name; //le nom du champs
  private String id; //un id permettant d'identifier le champs

  /**
   * @param name
   *          : the name
   */
  public Field(String name) {
    super();
    this.name = name;
    this.id = name.replaceAll("\\s+", "");
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          : the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

}
