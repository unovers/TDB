/**
 * Nom du fichier         : StartViewForm.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit un formulaire affichant des informations de base si aucun projet n'existe dans le workspace
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.views;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.StartForm.MainBox.InformationDepartField;

/**
 * Define a form showing a informations if no project is present in the workspace
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class StartForm extends AbstractViewForm {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public StartForm() throws ProcessingException {
    super(true);
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Start");
  }

  /**
   * @return the InformationDepartField
   */
  public InformationDepartField getInformationDepartField() {
    return getFieldByClass(InformationDepartField.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected boolean getConfiguredEnabled() {
      return false;
    }

    @Override
    protected boolean getConfiguredFocusable() {
      return false;
    }

    @Override
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Override
    protected int getConfiguredGridW() {
      return 5;
    }

    @Order(10.0)
    public class InformationDepartField extends AbstractLabelField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("InformationDepart");
      }

      @Override
      protected int getConfiguredLabelHorizontalAlignment() {
        return -1;
      }

      @Override
      protected int getConfiguredLabelPosition() {
        return LABEL_POSITION_LEFT;
      }

      @Override
      protected int getConfiguredLabelWidthInPixel() {
        return 500;
      }
    }
  }

  /* (non-Javadoc)
   * @see ch.heigvd.bachelor.crescenzio.generator.client.forms.views.AbstractViewForm#startView()
   */
  @Override
  public void startView() throws ProcessingException {
    startInternal(new ViewHandler());
  }
}
