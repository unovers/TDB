/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs;

import java.util.List;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.textfield.AbstractTextField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.AbstractSQLDatasetInputForm.MainBox.NameField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.AbstractSQLDatasetInputForm.MainBox.PreviewButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.AbstractSQLDatasetInputForm.MainBox.QueryField;
import ch.heigvd.bachelor.crescenzio.generator.client.services.lookup.DatasourcesLookupCall;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
import ch.heigvd.bachelor.crescenzio.generator.datasets.AbstractSQLDataset;
import ch.heigvd.bachelor.crescenzio.generator.datasets.MySQLDataset;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;

/**
 * @author Fabio
 */
public abstract class AbstractSQLDatasetInputForm extends AbstractDatasetInputForm {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public AbstractSQLDatasetInputForm(Project project) throws ProcessingException {
    super(project);
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public AbstractSQLDatasetInputForm(AbstractSQLDataset dataset) throws ProcessingException {
    super(dataset);
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
   * @return the NameField
   */
  public NameField getNameField() {
    return getFieldByClass(NameField.class);
  }

  /**
   * @return the PreviewButton
   */
  public PreviewButton getPreviewButton() {
    return getFieldByClass(PreviewButton.class);
  }

  /**
   * @return the QueryField
   */
  public QueryField getQueryField() {
    return getFieldByClass(QueryField.class);
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
    public class NameField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Name");
      }

      @Override
      public String getFieldId() {
        // TODO Auto-generated method stub
        return "name";
      }
    }

    @Order(20.0)
    public class QueryField extends AbstractTextField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Query");
      }

      @Override
      public String getFieldId() {
        // TODO Auto-generated method stub
        return "query";
      }
    }

    @Override
    protected void injectFieldsInternal(List<IFormField> fieldList) {
      if (getDataset() == null) {
        fieldList.add(new AbstractSmartField<AbstractDatasource>() {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Datasource");
          }

          @Override
          public String getFieldId() {
            return "datasource";
          }

          @Override
          protected Class<? extends ILookupCall<AbstractDatasource>> getConfiguredLookupCall() {
            return DatasourcesLookupCall.class;
          }

          @Override
          protected void execPrepareLookup(ILookupCall<AbstractDatasource> call) throws ProcessingException {
            DatasourcesLookupCall c = (DatasourcesLookupCall) call;
            c.setProject(getProject());
          }

          @Override
          protected int getConfiguredLabelWidthInPixel() {
            return 150;
          }
        });
      }
    }

    @Order(60.0)
    public class PreviewButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Preview");
      }
    }

    @Order(40.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(50.0)
    public class CancelButton extends AbstractCancelButton {
    }

  }

  public class ModifyHandler extends AbstractFormHandler {
  }

  public class NewHandler extends AbstractFormHandler {
    @Override
    protected void execStore() throws ProcessingException {
      Desktop desktop = (Desktop) getDesktop();
      String name = (String) ((AbstractStringField) getFieldById("name")).getValue();
      String query = (String) ((AbstractStringField) getFieldById("query")).getValue();
      AbstractDatasource datasource = (AbstractDatasource) ((AbstractSmartField<AbstractDatasource>) getFieldById("datasource")).getValue();
      datasource.addDataset(new MySQLDataset(name, query));
      desktop.refreshWorkspace();
    }
  }
}
