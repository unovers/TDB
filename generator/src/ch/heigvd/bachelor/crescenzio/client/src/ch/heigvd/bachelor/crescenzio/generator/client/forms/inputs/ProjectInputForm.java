/**
 * Nom du fichier         : ProjectInputForm.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Ce fichier définit un formulaire pour la création d'un nouveau projet
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.scout.commons.annotations.ConfigOperation;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ProjectInputForm.MainBox.AuthorField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ProjectInputForm.MainBox.CancelButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ProjectInputForm.MainBox.OkButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ProjectInputForm.MainBox.OrganisationField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ProjectInputForm.MainBox.PackageNameField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ProjectInputForm.MainBox.ProjectNameField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ProjectViewForm;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;

/**
 * Define a form for creating and editing a project
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class ProjectInputForm extends AbstractInputForm {
  private Project project;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public ProjectInputForm() throws ProcessingException {
    super(false);
    callInitializer();
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public ProjectInputForm(Project project) throws ProcessingException {
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
    return TEXTS.get("CreateProject");
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
   * @return the AuthorField
   */
  public AuthorField getAuthorField() {
    return getFieldByClass(AuthorField.class);
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
   * @return the OrganisationField
   */
  public OrganisationField getOrganisationField() {
    return getFieldByClass(OrganisationField.class);
  }

  /**
   * @return the PackageNameField
   */
  public PackageNameField getPackageNameField() {
    return getFieldByClass(PackageNameField.class);
  }

  /**
   * @return the ProjectNameField
   */
  public ProjectNameField getProjectNameField() {
    return getFieldByClass(ProjectNameField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Order(10.0)
    public class ProjectNameField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("ProjectName");
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }
    }

    @Order(20.0)
    public class AuthorField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Author");
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }
    }

    @Order(30.0)
    public class OrganisationField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Organisation");
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }
    }

    @Order(40.0)
    public class PackageNameField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("PackageName");
      }

      @Override
      protected String execValidateValue(String rawValue) throws ProcessingException {
        if (!rawValue.matches("^([a-z_]{1}[a-z0-9_]*\\.[a-z_]{1}[a-z0-9_]*(\\.[a-z_]{1}[a-z0-9_]*)*)$")) throw new VetoException("Invalid input", "Le nom de package est incorrect", 267, IStatus.ERROR);
        return super.execValidateValue(rawValue);
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }
    }

    @Order(60.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(70.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {
    @Override
    protected void execLoad() throws ProcessingException {
      getProjectNameField().setValue(project.getName());
      getProjectNameField().setEnabled(false);
      getAuthorField().setValue(project.getAuthor());
      getOrganisationField().setValue(project.getOrganisation());
      getPackageNameField().setValue(project.getPackageName());
    }

    @Override
    protected void execStore() throws ProcessingException {
      Desktop desktop = ((Desktop) getDesktop());
      project.setName(getProjectNameField().getValue());
      project.setAuthor(getAuthorField().getValue());
      project.setOrganisation(getOrganisationField().getValue());
      project.setPackageName(getPackageNameField().getValue());
      desktop.refreshWorkspace();
      desktop.displayProjectInfo(project);
    }

  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    @ConfigOperation
    @Order(40)
    protected void execStore() throws ProcessingException {
      Desktop desktop = ((Desktop) getDesktop());
      project = new Project(getProjectNameField().getValue(), getPackageNameField().getValue(), getAuthorField().getValue(), getOrganisationField().getValue());
      desktop.closeStartForm();
      desktop.initWorkspace();

      Desktop.loadOrRefreshFormProject(project, new ProjectViewForm(project));
      desktop.refreshWorkspace();
    }

  }
}
