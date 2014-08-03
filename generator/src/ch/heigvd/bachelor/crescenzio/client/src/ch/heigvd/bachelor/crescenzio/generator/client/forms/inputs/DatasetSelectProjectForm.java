/**
 * Nom du fichier         : DatasetSelectProjectForm.java
 * Version                : 0.1
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           :
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
import org.eclipse.scout.rt.client.ui.messagebox.MessageBox;
import org.eclipse.scout.rt.shared.ScoutTexts;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.DatasetSelectProjectForm.MainBox.CancelButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.DatasetSelectProjectForm.MainBox.OkButton;
import ch.heigvd.bachelor.crescenzio.generator.client.services.lookup.ProjectLookupCall;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;

/**
 * @author Fabio
 */
public class DatasetSelectProjectForm extends AbstractInputForm {

  private String type;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public DatasetSelectProjectForm(String type) throws ProcessingException {
    super(false);
    this.type = type;
    callInitializer();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("SelectProjectForDataset");
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
    protected int getConfiguredGridW() {
      return 1;
    }

    @Override
    protected int getConfiguredLabelPosition() {
      return LABEL_POSITION_LEFT;
    }

    @Order(10.0)
    public class ProjectTypeField extends AbstractSmartField<Project> {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Project");
      }

      @Override
      public String getFieldId() {
        return "project";
      }

      @Override
      protected Class<? extends ILookupCall<Project>> getConfiguredLookupCall() {
        return ProjectLookupCall.class;
      }

      @Override
      protected int getConfiguredLabelWidthInPixel() {
        return 150;
      }
    }

    @Order(30.0)
    public class OkButton extends AbstractOkButton {
      @Override
      protected String getConfiguredLabel() {
        return ScoutTexts.get("NextButton");
      }

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
      try {
        Project project = (Project) ((AbstractSmartField<Project>) getFieldById("project")).getValue();
        String pckage = Desktop.getDatasourceTypes().get(type.replace("Dataset", "")).getLocation();
        String clssDatasource = pckage + "." + type.replace("Dataset", "") + "Datasource";

        //Verifie si un datasource de ce type existe
        boolean datasourceTypeFound = false;
        for (AbstractDatasource datasource : project.getDatasources()) {
          if (Class.forName(clssDatasource).isInstance(datasource)) {
            datasourceTypeFound = true;
          }
        }
        if (datasourceTypeFound) {
          String clss = pckage + "." + type + "InputForm";
          Class datasetClass = Class.forName(clss);
          java.lang.reflect.Constructor constructor = datasetClass.getConstructor(new Class[]{Project.class});
          AbstractInputForm form = (AbstractInputForm) constructor.newInstance(new Object[]{project});
          form.startNew();
        }
        else {
          MessageBox message = new MessageBox(TEXTS.get("NoDatasourceFoundForType"), TEXTS.get("AddDatasourceFirst"), "Ok");
          message.startMessageBox();
        }

      }
      catch (Exception e) {
        //TODO Log
        throw new ProcessingException(e.toString());
      }
    }
  }

  @Override
  public void startModify() throws ProcessingException {

  }
}
