/**
 * Nom du fichier         : AbstractInputForm.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Cette classe définit un Form de type saisie
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;

/**
 * Define an abstract input form
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public abstract class AbstractInputForm extends AbstractForm {

  /**
   * @throws ProcessingException
   */
  public AbstractInputForm(boolean init) throws ProcessingException {
    super(init);
  }

  /**
   * Starts a new form for editing datas
   * 
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public abstract void startModify() throws ProcessingException;

  /**
   * Starts a form for inserting datas
   * 
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public abstract void startNew() throws ProcessingException;

}
