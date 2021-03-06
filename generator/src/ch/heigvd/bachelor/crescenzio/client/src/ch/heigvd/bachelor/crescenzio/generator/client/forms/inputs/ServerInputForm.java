/**
 * Nom du fichier         : ServerInputForm.java
 * Version                : 0.1
 * Auteur                 : Crescenzio Fabio
 *
 * Date derni�re r�vision : 30.07.2014
 *
 * Commentaires           : D�finit un formualaire pour entrer les informations pour un serveur
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ServerInputForm.MainBox.CancelButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ServerInputForm.MainBox.HostnameField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ServerInputForm.MainBox.OkButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ServerInputForm.MainBox.RootFolderField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ServerInputForm.MainBox.ServerTypeSmartField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ServerViewForm;
import ch.heigvd.bachelor.crescenzio.generator.client.services.lookup.ServerTypeLookupCall;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
import ch.heigvd.bachelor.crescenzio.generator.server.AbstractServer;

/**
 * Define a form for inserting server datas
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class ServerInputForm extends AbstractInputForm {

  private Project project;

  /**
   * @param project
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public ServerInputForm(Project project) throws ProcessingException {
    super(false);
    this.project = project;
    callInitializer();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ConfigureServer");
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
  public ServerTypeSmartField getServerTypeSmartField() {
    return getFieldByClass(ServerTypeSmartField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Order(10.0)
    public class ServerTypeSmartField extends AbstractSmartField<String> {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("ServerType");
      }

      @Override
      protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
        return ServerTypeLookupCall.class;
      }

      @Override
      protected int getConfiguredLabelWidthInPixel() {
        return 150;
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }

    }

    @Order(30.0)
    public class HostnameField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Hostname");
      }

      @Override
      protected int getConfiguredLabelWidthInPixel() {
        return 150;
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }
    }

    @Order(40.0)
    public class RootFolderField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("RootFolder");
      }

      @Override
      protected int getConfiguredLabelWidthInPixel() {
        return 150;
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }
    }

    @Order(50.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(60.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {
    @Override
    protected void execLoad() throws ProcessingException {
      AbstractServer server = project.getServer();
      getRootFolderField().setValue(server.getRootFolder());
      getHostnameField().setValue(server.getHost());
      String serverType = project.getServer().getClass().getSimpleName();
      getServerTypeSmartField().setValue(serverType);
    }

    @Override
    protected void execStore() throws ProcessingException {
      try {
        Desktop desktop = (Desktop) getDesktop();
        String serverType = getServerTypeSmartField().getValue();
        String pckage = Desktop.getServerTypes().get(serverType).getLocation();
        String clss = pckage + "." + serverType;
        Class<?> datasourceClass = Class.forName(clss);

        java.lang.reflect.Constructor constructor = datasourceClass.getConstructor(new Class[]{String.class, String.class});
        AbstractServer server = (AbstractServer) constructor.newInstance(new Object[]{getHostnameField().getValue(), getRootFolderField().getValue()});
        project.setServer(server);
        Desktop.loadOrRefreshFormServer(project, new ServerViewForm(project));
        desktop.refreshWorkspace();
      }
      catch (Exception e) {
        //TODO Log
        throw new ProcessingException(e.toString());
      }

    }
  }

  public class NewHandler extends AbstractFormHandler {
    @Override
    protected void execStore() throws ProcessingException {
      try {
        Desktop desktop = (Desktop) getDesktop();

        String serverType = getServerTypeSmartField().getValue();
        String pckage = Desktop.getServerTypes().get(serverType).getLocation();
        String clss = pckage + "." + serverType;
        Class<?> datasourceClass = Class.forName(clss);

        java.lang.reflect.Constructor constructor = datasourceClass.getConstructor(new Class[]{String.class, String.class});
        AbstractServer server = (AbstractServer) constructor.newInstance(new Object[]{getHostnameField().getValue(), getRootFolderField().getValue()});
        project.setServer(server);

        Desktop.loadOrRefreshFormServer(project, new ServerViewForm(project));
        desktop.refreshWorkspace();
      }
      catch (Exception e) {
        throw new ProcessingException(e.toString());
      }
    }

  }
}
