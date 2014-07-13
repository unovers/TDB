/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.integerfield.AbstractIntegerField;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.services.lookup.ProjectLookupCall;
import ch.heigvd.bachelor.crescenzio.generator.shared.forms.inputs.NewDatasourceFormData;

/**
 * @author Fabio
 */
@FormData(value = NewDatasourceFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public abstract class SQLDatasourceInputForm extends AbstractInputForm {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public SQLDatasourceInputForm() throws ProcessingException {
    super(true);
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
    return TEXTS.get("NewDatasource");
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
      protected boolean getConfiguredMandatory() {
        return true;
      }

      @Override
      protected Class<? extends ILookupCall<Project>> getConfiguredLookupCall() {
        return ProjectLookupCall.class;
      }

      @Override
      protected int getConfiguredLabelWidthInPixel() {
        return 200;
      }

    }

    @Order(20.0)
    public class NameField extends AbstractStringField {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Name");
      }

      @Override
      public String getFieldId() {
        return "name";
      }

      @Override
      protected int getConfiguredLabelWidthInPixel() {
        return 200;
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }
    }

    @Order(30.0)
    public class DatabaseNameField extends AbstractStringField {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("DatabaseName");
      }

      @Override
      public String getFieldId() {
        return "databaseName";
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }

      @Override
      protected int getConfiguredLabelWidthInPixel() {
        return 200;
      }
    }

    @Order(40.0)
    public class DatabaseHostField extends AbstractStringField {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("DatabaseHost");
      }

      @Override
      public String getFieldId() {
        return "databaseHost";
      }

      @Override
      protected int getConfiguredLabelWidthInPixel() {
        return 200;
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }
    }

    @Order(50.0)
    public class DatabasePortField extends AbstractIntegerField {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("DatabasePort");
      }

      @Override
      public String getFieldId() {
        return "databasePort";
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }

      @Override
      protected int getConfiguredLabelWidthInPixel() {
        return 200;
      }
    }

    @Order(60.0)
    public class DatabaseLogin extends AbstractStringField {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("DatabaseLogin");
      }

      @Override
      public String getFieldId() {
        return "databaseLogin";
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }

      @Override
      protected int getConfiguredLabelWidthInPixel() {
        return 200;
      }

    }

    @Order(70.0)
    public class DatabasePassword extends AbstractStringField {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("DatabasePassword");
      }

      @Override
      public String getFieldId() {
        return "databasePassword";
      }

      @Override
      protected int getConfiguredLabelWidthInPixel() {
        return 200;
      }
    }

    @Order(100.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(110.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }
}
