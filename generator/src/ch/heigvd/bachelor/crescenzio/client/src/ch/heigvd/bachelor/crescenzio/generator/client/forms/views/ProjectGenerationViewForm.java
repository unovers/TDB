/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.views;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ProjectInputForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ProjectGenerationViewForm.MainBox.AuthorField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ProjectGenerationViewForm.MainBox.DeleteProjectButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ProjectGenerationViewForm.MainBox.EditProjectButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ProjectGenerationViewForm.MainBox.OrganisationField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ProjectGenerationViewForm.MainBox.PackageNameField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ProjectGenerationViewForm.MainBox.ProjectNameField;
import ch.heigvd.bachelor.crescenzio.generator.shared.StartFormData;

/**
 * @author Fabio
 */
@FormData(value = StartFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class ProjectGenerationViewForm extends AbstractViewForm {

  private Project project;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public ProjectGenerationViewForm(Project project) throws ProcessingException {
    super(false);
    this.project = project;
    callInitializer();
  }

  @Override
  protected String getConfiguredDisplayViewId() {
    return VIEW_ID_S;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ProjectInformations");
  }

  /**
   * @return the AuthorField
   */
  public AuthorField getAuthorField() {
    return getFieldByClass(AuthorField.class);
  }

  /**
   * @return the DeleteProjectButton
   */
  public DeleteProjectButton getDeleteProjectButton() {
    return getFieldByClass(DeleteProjectButton.class);
  }

  /**
   * @return the EditProjectButton
   */
  public EditProjectButton getEditProjectButton() {
    return getFieldByClass(EditProjectButton.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
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

    @Override
    protected boolean getConfiguredMandatory() {
      return true;
    }

    @Order(10.0)
    public class ProjectNameField extends AbstractLabelField {

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
    public class OrganisationField extends AbstractLabelField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Organisation");
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }
    }

    @Order(30.0)
    public class AuthorField extends AbstractLabelField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Author");
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }

    }

    @Order(40.0)
    public class PackageNameField extends AbstractLabelField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("PackageName");
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }
    }

    @Order(60.0)
    public class GenerateProjectButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("GenerateProject");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        new ProjectGenerationViewForm(project).startView();
      }
    }

    @Order(60.0)
    public class EditProjectButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("EditProject");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        new ProjectInputForm(project).startModify();
      }
    }

    @Order(70.0)
    public class DeleteProjectButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("DeleteProject");
      }
    }
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      if (project != null) {
        getProjectNameField().setValue(project.getName());
        getAuthorField().setValue(project.getAuthor());
        getOrganisationField().setValue(project.getOrganisation());
        getPackageNameField().setValue(project.getPackageName());
      }
    }
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  public void startView() throws ProcessingException {
    startInternal(new ProjectGenerationViewForm.ViewHandler());
  }
}
