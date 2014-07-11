/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs;

import org.eclipse.scout.commons.annotations.ConfigOperation;
import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ServerInputForm.MainBox.CancelButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ServerInputForm.MainBox.HostnameField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ServerInputForm.MainBox.OkButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ServerInputForm.MainBox.RootFolderField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ServerInputForm.MainBox.ServerTypeField;
import ch.heigvd.bachelor.crescenzio.generator.shared.ConfigureServerFormData;

/**
 * @author Fabio
 */
@FormData(value = ConfigureServerFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class ServerInputForm extends AbstractForm {

  private Long m_configureServerNr;
  private Project project;

  /**
   * @param project
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public ServerInputForm(Project project) throws ProcessingException {
    super();
    this.project = project;
  }

  /**
   * @return the ConfigureServerNr
   */
  @FormData
  public Long getConfigureServerNr() {
    return m_configureServerNr;
  }

  /**
   * @param configureServerNr
   *          the ConfigureServerNr to set
   */
  @FormData
  public void setConfigureServerNr(Long configureServerNr) {
    m_configureServerNr = configureServerNr;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ConfigureServer");
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public void startNew() throws ProcessingException {
    startInternal(new NewHandler());
  }

  /**
   * @return the CancelButton
   */
  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  /**
   * @return the HostnameField
   */
  public HostnameField getHostnameField() {
    return getFieldByClass(HostnameField.class);
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
   * @return the RootFolderField
   */
  public RootFolderField getRootFolderField() {
    return getFieldByClass(RootFolderField.class);
  }

  /**
   * @return the ServerTypeField
   */
  public ServerTypeField getServerTypeField() {
    return getFieldByClass(ServerTypeField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Order(10.0)
    public class ServerTypeField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("ServerType");
      }
    }

    @Order(20.0)
    public class HostnameField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Hostname");
      }
    }

    @Order(30.0)
    public class RootFolderField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("RootFolder");
      }
    }

    @Order(40.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(50.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {
    /**
     * Before the form is activated, this method loads its data.<br>
     * After this method call, the form is in the state "Saved / Unchanged" All
     * field value changes done here appear as unchanged in the form.
     */
    @Override
    @ConfigOperation
    @Order(10)
    protected void execLoad() throws ProcessingException {
    }

    /**
     * Load additional form state<br>
     * this method call is after the form was loaded into the state
     * "Saved / Unchanged"<br>
     * any changes to fields might result in the form ot fields being changed and
     * therefore in the state "Save needed / Changed"
     */
    @Override
    @ConfigOperation
    @Order(20)
    protected void execPostLoad() throws ProcessingException {
    }

    /**
     * This method is called in order to check field validity.<br>
     * This method is called just after the {@link IForm#execCheckFields()} but
     * before the form is validated and stored.<br>
     * After this method, the form is checking fields itself and displaying a
     * dialog with missing and invalid fields.
     *
     * @return true when this check is done and further checks can continue, false
     *         to silently cancel the current process
     * @throws ProcessingException
     *           to cancel the current process with error handling and user
     *           notification such as a dialog
     */
    @Override
    @ConfigOperation
    @Order(40)
    protected boolean execCheckFields() throws ProcessingException {
      return true;
    }

    /**
     * This method is called in order to update derived states like button
     * enablings.<br>
     * This method is called after the {@link IForm#execValidate()} but before the
     * form is stored.<br>
     *
     * @return true when validate is successful, false to silently cancel the
     *         current process
     * @throws ProcessingException
     *           to cancel the current process with error handling and user
     *           notification such as a dialog
     */
    @Override
    @ConfigOperation
    @Order(50)
    protected boolean execValidate() throws ProcessingException {
      return true;
    }

    /**
     * Store form state<br>
     * after this method call, the form is in the state "Saved / Unchanged" When
     * the form is closed using Ok, Save, Search, Next, etc.. this method is
     * called to apply the changes to the persistency layer
     */
    @Override
    @ConfigOperation
    @Order(40)
    protected void execStore() throws ProcessingException {
    }

    /**
     * When the form is closed using cancel or close this method is called to
     * manage the case that no changes should be performed (revert case)
     */
    @Override
    @ConfigOperation
    @Order(30)
    protected void execDiscard() throws ProcessingException {
    }

    /**
     * Finalize form state<br>
     * called whenever the handler is finished and the form is closed When the
     * form is closed in any way this method is called to dispose of resources or
     * deallocate services
     */
    @Override
    @ConfigOperation
    @Order(60)
    protected void execFinally() throws ProcessingException {
    }
  }

  public class NewHandler extends AbstractFormHandler {
    /**
     * Before the form is activated, this method loads its data.<br>
     * After this method call, the form is in the state "Saved / Unchanged" All
     * field value changes done here appear as unchanged in the form.
     */
    @Override
    @ConfigOperation
    @Order(10)
    protected void execLoad() throws ProcessingException {
    }

    /**
     * Load additional form state<br>
     * this method call is after the form was loaded into the state
     * "Saved / Unchanged"<br>
     * any changes to fields might result in the form ot fields being changed and
     * therefore in the state "Save needed / Changed"
     */
    @Override
    @ConfigOperation
    @Order(20)
    protected void execPostLoad() throws ProcessingException {
    }

    /**
     * This method is called in order to check field validity.<br>
     * This method is called just after the {@link IForm#execCheckFields()} but
     * before the form is validated and stored.<br>
     * After this method, the form is checking fields itself and displaying a
     * dialog with missing and invalid fields.
     *
     * @return true when this check is done and further checks can continue, false
     *         to silently cancel the current process
     * @throws ProcessingException
     *           to cancel the current process with error handling and user
     *           notification such as a dialog
     */
    @Override
    @ConfigOperation
    @Order(40)
    protected boolean execCheckFields() throws ProcessingException {
      return true;
    }

    /**
     * This method is called in order to update derived states like button
     * enablings.<br>
     * This method is called after the {@link IForm#execValidate()} but before the
     * form is stored.<br>
     *
     * @return true when validate is successful, false to silently cancel the
     *         current process
     * @throws ProcessingException
     *           to cancel the current process with error handling and user
     *           notification such as a dialog
     */
    @Override
    @ConfigOperation
    @Order(50)
    protected boolean execValidate() throws ProcessingException {
      return true;
    }

    /**
     * Store form state<br>
     * after this method call, the form is in the state "Saved / Unchanged" When
     * the form is closed using Ok, Save, Search, Next, etc.. this method is
     * called to apply the changes to the persistency layer
     */
    @Override
    @ConfigOperation
    @Order(40)
    protected void execStore() throws ProcessingException {
    }

    /**
     * When the form is closed using cancel or close this method is called to
     * manage the case that no changes should be performed (revert case)
     */
    @Override
    @ConfigOperation
    @Order(30)
    protected void execDiscard() throws ProcessingException {
    }

    /**
     * Finalize form state<br>
     * called whenever the handler is finished and the form is closed When the
     * form is closed in any way this method is called to dispose of resources or
     * deallocate services
     */
    @Override
    @ConfigOperation
    @Order(60)
    protected void execFinally() throws ProcessingException {
    }
  }
}
