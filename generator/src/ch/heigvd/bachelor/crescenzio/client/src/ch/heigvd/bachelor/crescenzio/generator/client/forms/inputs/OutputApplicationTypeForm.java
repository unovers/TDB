/**
 * Nom du fichier         : OutputApplicationTypeForm.java
 * Version                : 0.1
 * Auteur                 : Crescenzio Fabio
 *
 * Date derni�re r�vision : 30.07.2014
 *
 * Commentaires           :
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs;

import java.io.File;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.shared.ScoutTexts;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.DatasourceTypeForm.MainBox.DatasourceTypeField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.OutputApplicationTypeForm.MainBox.CancelButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.OutputApplicationTypeForm.MainBox.OkButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.OutputApplicationTypeForm.MainBox.ProjectField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.OutputApplicationViewForm;
import ch.heigvd.bachelor.crescenzio.generator.client.services.lookup.OutputTypeLookupCall;
import ch.heigvd.bachelor.crescenzio.generator.client.services.lookup.ProjectLookupCall;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
import ch.heigvd.bachelor.crescenzio.generator.outputs.FileResource;
import ch.heigvd.bachelor.crescenzio.generator.outputs.ItemType;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputApplication;

public class OutputApplicationTypeForm extends AbstractForm {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public OutputApplicationTypeForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected boolean getConfiguredModal() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("OutputType");
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
   * @return the DatasourceTypeField
   */
  public DatasourceTypeField getDatasourceTypeField() {
    return getFieldByClass(DatasourceTypeField.class);
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
   * @return the ProjectField
   */
  public ProjectField getProjectField() {
    return getFieldByClass(ProjectField.class);
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

    @Order(10.0)
    public class ServerTypeSmartField extends AbstractSmartField<OutputApplication> {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Output");
      }

      @Override
      public String getFieldId() {
        return "output";
      }

      @Override
      protected Class<? extends ILookupCall<OutputApplication>> getConfiguredLookupCall() {
        return OutputTypeLookupCall.class;
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

    @Order(20.0)
    public class ProjectField extends AbstractSmartField<Project> {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Project");
      }

      @Override
      public String getFieldId() {
        return "project";
      }

      @Override
      protected int getConfiguredLabelWidthInPixel() {
        return 150;
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }

      @Override
      protected Class<? extends ILookupCall<Project>> getConfiguredLookupCall() {
        return ProjectLookupCall.class;
      }

    }

    @Order(30.0)
    public class OkButton extends AbstractOkButton {
      @Override
      protected String getConfiguredTooltipText() {
        return ScoutTexts.get("NextDatasourceButtonTip");
      }
    }

    @Order(40.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class NewHandler extends AbstractFormHandler {
    @SuppressWarnings("unchecked")
    @Override
    protected void execStore() throws ProcessingException {
      Desktop desktop = (Desktop) getDesktop();
      Project project = (Project) ((AbstractSmartField<Project>) getFieldById("project")).getValue();

      OutputApplication output = (OutputApplication) ((AbstractSmartField<OutputApplication>) getFieldById("output")).getValue();
      OutputApplication new_output = output.duplicate();

      String path = desktop.getWorkspace() + File.separator + project.getName() + File.separator + output.getName();
      new_output.setProject(project);
      project.addOutput(new_output);
      //cr�er le r�pertoire de l'application dans le dossier du projet
      new File(path).mkdirs();
      new File(path + File.separator + "code").mkdirs();

      for (ItemType itemType : output.getItemsTypes()) {
        String type_path = path + File.separator + "code" + File.separator + "types" + File.separator + itemType.getName();
        new File(type_path).mkdirs();

        for (FileResource resource : itemType.getResources()) {
          String pathSrc = getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + File.separator + output.getName().toLowerCase();
//          try {
          System.out.println("copy " + pathSrc + File.separator + resource.getValue() + " to " + type_path);
          //Files.copy(new File(pathSrc + File.separator + resource.getValue()).toPath(), new File(type_path).toPath());
//          }
//          catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//          }
        }

      }

      new OutputApplicationViewForm(project, new_output);
      desktop.refreshWorkspace();
    }
  }
}
