/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.integerfield.AbstractIntegerField;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
import ch.heigvd.bachelor.crescenzio.generator.datasources.MySQLDatasource;

/**
 * @author Fabio
 */
public class MySQLDatasourceInputForm extends AbstractSQLDatasourceInputForm {

  /**
   * @param type
   * @throws ProcessingException
   */
  public MySQLDatasourceInputForm() throws ProcessingException {
    super();
  }

  /**
   * @param datasource
   * @throws ProcessingException
   */
  public MySQLDatasourceInputForm(MySQLDatasource datasource) throws ProcessingException {
    super(datasource);
    callInitializer();
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
    @Override
    protected void execLoad() throws ProcessingException {
      MySQLDatasource datasource = (MySQLDatasource) getDatasource();
      ((AbstractStringField) getFieldById("name")).setValue(datasource.getName());
      ((AbstractStringField) getFieldById("databaseName")).setValue(datasource.getDatabase());
      ((AbstractStringField) getFieldById("databaseHost")).setValue(datasource.getHostname());
      ((AbstractStringField) getFieldById("databaseLogin")).setValue(datasource.getLogin());
      ((AbstractIntegerField) getFieldById("databasePort")).setValue(datasource.getPort());
      ((AbstractStringField) getFieldById("databasePassword")).setValue(datasource.getPassword());

    }

    @Override
    protected void execStore() throws ProcessingException {
      Desktop desktop = (Desktop) getDesktop();
      String name = (String) ((AbstractStringField) getFieldById("name")).getValue();
      String databaseName = (String) ((AbstractStringField) getFieldById("databaseName")).getValue();
      String databaseHost = (String) ((AbstractStringField) getFieldById("databaseHost")).getValue();
      String databaseLogin = (String) ((AbstractStringField) getFieldById("databaseLogin")).getValue();
      int databasePort = (Integer) ((AbstractIntegerField) getFieldById("databasePort")).getValue();
      String databasePassword = (String) ((AbstractStringField) getFieldById("databasePassword")).getValue();

      MySQLDatasource source = (MySQLDatasource) getDatasource();
      source.setName(name);
      source.setDatabase(databaseName);
      source.setLogin(databaseLogin);
      source.setHostname(databaseHost);
      source.setPassword(databasePassword);
      source.setPort(databasePort);
      desktop.refreshWorkspace();
    }
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
