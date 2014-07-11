/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs;

import java.util.List;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.shared.forms.inputs.NewDatasourceFormData;

/**
 * @author Fabio
 */
@FormData(value = NewDatasourceFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class SQLDatasourceInputForm extends DatasourceInputForm {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public SQLDatasourceInputForm(Project project) throws ProcessingException {
    super(project);
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("NewDatasource");
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

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected void injectFieldsInternal(List<IFormField> fieldList) {
      super.injectFieldsInternal(fieldList);

      fieldList.add(new AbstractStringField() {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DatasourceName");
        }
      });

      fieldList.add(new AbstractStringField() {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DatasourceHost");
        }
      });

      fieldList.add(new AbstractStringField() {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DatasourcePort");
        }
      });

      fieldList.add(new AbstractStringField() {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DatasourceLogin");
        }
      });

      fieldList.add(new AbstractStringField() {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DatasourcePassword");
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

  public class ModifyHandler extends AbstractFormHandler {
  }

  public class NewHandler extends AbstractFormHandler {
  }
}