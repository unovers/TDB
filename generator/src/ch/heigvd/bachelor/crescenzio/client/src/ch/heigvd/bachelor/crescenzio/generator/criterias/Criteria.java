/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.criterias;

import java.util.LinkedList;

/**
 * @author Fabio
 */
public abstract class Criteria {
  private LinkedList<Condition> conditions;
  private String title;

  /**
   * @return the conditions
   */
  public LinkedList<Condition> getConditions() {
    return conditions;
  }

  /**
   * @param conditions
   *          the conditions to set
   */
  public void setConditions(LinkedList<Condition> conditions) {
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
