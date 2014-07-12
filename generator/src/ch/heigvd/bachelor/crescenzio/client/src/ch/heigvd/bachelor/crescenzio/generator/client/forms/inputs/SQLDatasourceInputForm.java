/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs;

import java.util.List;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
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
public abstract class SQLDatasourceInputForm extends InputForm {

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

    @Override
    protected void injectFieldsInternal(List<IFormField> fieldList) {
      fieldList.add(new AbstractSmartField<Project>() {

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

      });

      fieldList.add(new AbstractStringField() {
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
      });
      fieldList.add(new AbstractStringField() {
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
      });

      fieldList.add(new AbstractStringField() {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("databaseHost");
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
      });

      fieldList.add(new AbstractIntegerField() {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("databasePort");
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
      });

      fieldList.add(new AbstractStringField() {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("databaseLogin");
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

      });

      fieldList.add(new AbstractStringField() {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("databasePassword");
        }

        @Override
        public String getFieldId() {
          return "databasePassword";
        }

        @Override
        protected int getConfiguredLabelWidthInPixel() {
          return 200;
        }
      });
    }

    @Order(20.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(30.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }
}
