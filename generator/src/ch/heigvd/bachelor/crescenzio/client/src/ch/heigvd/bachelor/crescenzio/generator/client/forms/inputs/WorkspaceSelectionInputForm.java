/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.filechooserfield.AbstractFileChooserField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.WorkspaceSelectionInputForm.MainBox.CancelButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.WorkspaceSelectionInputForm.MainBox.OkButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.WorkspaceSelectionInputForm.MainBox.WorkspaceField;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;

/**
 * @author Fabio
 */
public class WorkspaceSelectionInputForm extends AbstractInputForm {

  private Long m_workspaceSelectionInputNr;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public WorkspaceSelectionInputForm() throws ProcessingException {
    super(false);
    callInitializer();
  }

  /**
   * @return the WorkspaceSelectionInputNr
   */
  @FormData
  public Long getWorkspaceSelectionInputNr() {
    return m_workspaceSelectionInputNr;
  }

  /**
   * @param workspaceSelectionInputNr
   *          the WorkspaceSelectionInputNr to set
   */
  @FormData
  public void setWorkspaceSelectionInputNr(Long workspaceSelectionInputNr) {
    m_workspaceSelectionInputNr = workspaceSelectionInputNr;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("SelectionDeLespaceDeTravail");
  }

  /**
   * @return the CancelButton
   */
  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the OkButton
   */
  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  /**
   * @return the WorkspaceField
   */
  public WorkspaceField getWorkspaceField() {
    return getFieldByClass(WorkspaceField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class WorkspaceField extends AbstractFileChooserField {

      @Override
      protected String getConfiguredDirectory() {
        return null;
      }

      @Override
      protected boolean getConfiguredFolderMode() {
        return true;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Workspace");
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }

      @Override
      protected boolean getConfiguredShowDirectory() {
        return true;
      }

      @Override
      protected boolean getConfiguredShowFileName() {
        return false;
      }

      @Override
      protected boolean getConfiguredTypeLoad() {
        return true;
      }
    }

    @Order(20.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(30.0)
    public class CancelButton extends AbstractCancelButton {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("ExitMenu");
      }
    }
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  @Override
  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  @Override
  public void startNew() throws ProcessingException {
    startInternal(new NewHandler());
  }

  public class ModifyHandler extends AbstractFormHandler {
  }

  public class NewHandler extends AbstractFormHandler {
    @Override
    protected void execStore() throws ProcessingException {
      Desktop desktop = (Desktop) getDesktop();
      desktop.setWorkspace(getWorkspaceField().getValue());
    }

  }

}
