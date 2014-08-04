/**
 * Nom du fichier         : Criteria.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Cette classe définit un critère de regroupement
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.criterias;

import java.util.LinkedList;

import ch.heigvd.bachelor.crescenzio.generator.Field;

/**
 * Define a criteria
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class Criteria {
  private LinkedList<Field> conditions; // la liste des conditions
  private String title; // le titre
  private int id; //l'id unique
  private static int counter; //le compteur pour donner un id unique a chaque critère

  static {
    counter = 0;
  }

  public Criteria(String title) {
    this.title = title;
    this.conditions = new LinkedList<Field>();
    this.id = counter++;
  }

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param field
   *          the field to add
   */
  public void addCondition(Field field) {
    if (!conditions.contains(field)) conditions.add(field);
  }

  /**
   * @return the conditions
   */
  public LinkedList<Field> getConditions() {
    return conditions;
  }

  /**
   * @param conditions
   *          the conditions to set
   */
  public void setConditions(LinkedList<Field> conditions) {
    this.conditions = conditions;
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title
   *          the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }
}
