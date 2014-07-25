/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.criterias;

import java.util.LinkedList;

import ch.heigvd.bachelor.crescenzio.generator.Field;

/**
 * @author Fabio
 */
public class Criteria {
  private LinkedList<Field> conditions;
  private String title;
  private int id;
  private static int counter;

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
