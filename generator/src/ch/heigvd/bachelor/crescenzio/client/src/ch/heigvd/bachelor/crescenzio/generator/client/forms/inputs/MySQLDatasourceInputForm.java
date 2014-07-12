/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.integerfield.AbstractIntegerField;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
import ch.heigvd.bachelor.crescenzio.generator.datasources.MySQLDatasource;
import ch.heigvd.bachelor.crescenzio.generator.shared.forms.inputs.NewDatasourceFormData;

/**
 * @author Fabio
 */
@FormData(value = NewDatasourceFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class MySQLDatasourceInputForm extends SQLDatasourceInputForm {

  /**
   * @param type
   * @throws ProcessingException
   */
  public MySQLDatasourceInputForm() throws ProcessingException {
    super();
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

  public class ModifyHandler extends AbstractFormHandler {
  }

  public class NewHandler extends AbstractFormHandler {
    @Override
    protected void execStore() throws ProcessingException {
      Desktop desktop = (Desktop) getDesktop();
      String name = (String) ((AbstractStringField) getFieldById("name")).getValue();
      String databaseName = (String) ((AbstractStringField) getFieldById("databaseName")).getValue();
      String databaseHost = (String) ((AbstractStringField) getFieldById("databaseHost")).getValue();
      String databaseLogin = (String) ((AbstractStringField) getFieldById("databaseLogin")).getValue();
      int databasePort = (Integer) ((AbstractIntegerField) getFieldById("databasePort")).getValue();
      String databasePassword = (String) ((AbstractStringField) getFieldById("databasePassword")).getValue();

      Project project = (Project) ((AbstractSmartField<Project>) getFieldById("project")).getValue();
      project.addDatasource(new MySQLDatasource(name, databaseHost, databasePort, databaseName, databaseLogin, databasePassword));
      desktop.refreshWorkspace();
    }
  }
}
