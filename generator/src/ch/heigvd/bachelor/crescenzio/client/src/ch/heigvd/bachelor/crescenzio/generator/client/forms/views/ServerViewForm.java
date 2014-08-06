/**
 * Nom du fichier         : ServerViewForm.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit un formulaire affichant les détails d'un serveur
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.views;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ServerInputForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ServerViewForm.MainBox.EditServerInfoButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ServerViewForm.MainBox.HostnameField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ServerViewForm.MainBox.RootFolderField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ServerViewForm.MainBox.ServerTypeField;

/**
 * Define a form showing server informations
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class ServerViewForm extends AbstractViewForm {

  private Project project;

  /**
   * @param project
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public ServerViewForm(Project project) throws ProcessingException {
    super(false);
    this.project = project;
    callInitializer();
  }

  @Override
  public void startView() throws ProcessingException {
    startInternal(new ServerViewForm.ViewHandler());
  }

  @Override
  protected String getConfiguredTitle() {
    return project.getName() + " - " + TEXTS.get("ServerInformations");
  }

  /**
   * @return the EditServerInfoButton
   */
  public EditServerInfoButton getEditServerInfoButton() {
    return getFieldByClass(EditServerInfoButton.class);
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

    @Override
    protected int getConfiguredGridW() {
      return 1;
    }

    @Override
    protected int getConfiguredLabelPosition() {
      return LABEL_POSITION_LEFT;
    }

    @Override
    protected boolean getConfiguredEnabled() {
      return false;
    }

    @Override
    protected boolean getConfiguredMandatory() {
      return true;
    }

    @Order(10.0)
    public class ServerTypeField extends AbstractLabelField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("ServerType");
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }
    }

    @Order(20.0)
    public class HostnameField extends AbstractLabelField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Hostname");
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }
    }

    @Order(30.0)
    public class RootFolderField extends AbstractLabelField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("RootFolder");
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }
    }

    @Order(40.0)
    public class EditServerInfoButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Edit");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        new ServerInputForm(project).startModify();
      }
    }
  }

  public class ViewHandler extends AbstractFormHandler {
    @Override
    protected void execLoad() throws ProcessingException {
      if (project.getServer() != null) {
        getServerTypeField().setValue(project.getServer().getClass().getSimpleName().replace("Server", ""));
        getRootFolderField().setValue(project.getServer().getRootFolder());
        getHostnameField().setValue(project.getServer().getHost());
      }
    }
  }
}
