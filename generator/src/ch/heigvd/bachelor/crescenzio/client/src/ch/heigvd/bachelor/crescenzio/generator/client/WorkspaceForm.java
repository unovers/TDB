/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.shared.WorkspaceFormData;

/**
 * @author Fabio
 */
@FormData(value = WorkspaceFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class WorkspaceForm extends AbstractForm {

  private Long m_workspaceNr;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public WorkspaceForm() throws ProcessingException {
    super();
  }

  @Override
  protected int getConfiguredDisplayHint() {
    return DISPLAY_HINT_VIEW;
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredDisplayViewId() {
    return VIEW_ID_W;
  }

  /**
   * @return the WorkspaceNr
   */
  @FormData
  public Long getWorkspaceNr() {
    return m_workspaceNr;
  }

  /**
   * @param workspaceNr
   *          the WorkspaceNr to set
   */
  @FormData
  public void setWorkspaceNr(Long workspaceNr) {
    m_workspaceNr = workspaceNr;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Workspace");
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
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
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Override
    protected boolean getConfiguredScrollable() {
      return true;
    }

    @Order(10.0)
    public class RightField extends AbstractLabelField {

      @Override
      protected String getConfiguredFont() {
        return "BOLD";
      }

      @Override
      protected boolean getConfiguredLabelVisible() {
        return false;
      }

      @Override
      protected void execInitField() throws ProcessingException {
        setValue(TEXTS.get("Workspace"));
      }
    }

  }

  public class ModifyHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
    }

    @Override
    public void execStore() throws ProcessingException {
    }
  }

}
