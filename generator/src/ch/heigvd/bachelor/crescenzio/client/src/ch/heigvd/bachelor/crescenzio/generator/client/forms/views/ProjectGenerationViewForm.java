/**
 * Nom du fichier         : ProjectGeneratioViewform.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit un formulaire affichant si le projet s'est bien généré
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.views;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ProjectGenerationViewForm.MainBox.GenerationStatusField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ProjectGenerationViewForm.MainBox.OkButton;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
import ch.heigvd.bachelor.crescenzio.generator.shared.StartFormData;

/**
 * Define a dialog popup telling if the project was generated correctly
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
@FormData(value = StartFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class ProjectGenerationViewForm extends AbstractViewForm {

  private Project project;
  private boolean generate;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public ProjectGenerationViewForm(Project project) throws ProcessingException {
    super(false);
    this.project = project;
    this.generate = false;
    try {
      //Réalise toutes les vérifications
      if (project.getDatasources().size() == 0) {
        throw new UnsupportedOperationException("il faut avoir au moins un datasource");
      }

      project.generateProject(((Desktop) getDesktop()).getWorkspace());
      generate = true;
    }
    catch (Exception e) {
      //TODO Log
      throw new ProcessingException(e.toString());
    }
    callInitializer();
  }

  @Override
  protected int getConfiguredDisplayHint() {
    return DISPLAY_HINT_DIALOG;
  }

  @Override
  protected String getConfiguredDisplayViewId() {
    return VIEW_ID_N;
  }

  @Override
  protected String getConfiguredTitle() {
    return project.getName() + " - " + TEXTS.get("ProjectInformations");
  }

  /**
   * @return the GenerationStatusField
   */
  public GenerationStatusField getGenerationStatusField() {
    return getFieldByClass(GenerationStatusField.class);
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
    public class GenerationStatusField extends AbstractLabelField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("GenerationStatus");
      }
    }

    @Order(20.0)
    public class OkButton extends AbstractCloseButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Ok");
      }
    }
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      if (generate) {
        getGenerationStatusField().setValue("OK");
      }
      else getGenerationStatusField().setValue("ERROR");
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
