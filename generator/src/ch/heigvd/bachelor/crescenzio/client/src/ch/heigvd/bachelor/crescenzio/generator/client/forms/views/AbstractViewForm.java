/**
 * Nom du fichier         : AbstractViewForm.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Cette classe défini un Form abstrait pour l'affichage d'informations dans l'application
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.views;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;

import ch.heigvd.bachelor.crescenzio.generator.shared.Icons;

/**
 * Define an abstract input form
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public abstract class AbstractViewForm extends AbstractForm {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public AbstractViewForm(boolean init) throws ProcessingException {
    super(init);
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected boolean getConfiguredMaximized() {
    return true;
  }

  @Override
  protected boolean getConfiguredModal() {
    return false;
  }

  public abstract void startView() throws ProcessingException;

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
    }

    @Override
    protected void execStore() throws ProcessingException {
    }
  }

  @Override
  protected int getConfiguredDisplayHint() {
    return DISPLAY_HINT_VIEW;
  }

  @Override
  protected String getConfiguredDisplayViewId() {
    return VIEW_ID_CENTER;
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.EclipseScout;
  }

}
