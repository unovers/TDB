/**
 * Nom du fichier         : AbstractInputForm.java
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
package ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;

/**
 * @author Fabio
 */
public abstract class AbstractInputForm extends AbstractForm {

  /**
   * @throws ProcessingException
   */
  public AbstractInputForm(boolean init) throws ProcessingException {
    super(init);
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public abstract void startModify() throws ProcessingException;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public abstract void startNew() throws ProcessingException;

}
