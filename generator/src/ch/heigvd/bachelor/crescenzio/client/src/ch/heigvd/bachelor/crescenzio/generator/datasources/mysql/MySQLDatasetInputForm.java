/**
 * Nom du fichier         : MySQLDatasetInputForm.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date derni�re r�vision : 30.07.2014
 *
 * Commentaires           : Cette classe d�finit un Form pour la saisie d'un set de donn�es MySQL
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.datasources.mysql;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;
import ch.heigvd.bachelor.crescenzio.generator.datasources.sql.AbstractSQLDatasetInputForm;

/**
 * Define a MYSQL dataset InputForm
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class MySQLDatasetInputForm extends AbstractSQLDatasetInputForm {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public MySQLDatasetInputForm(MySQLDataset dataset) throws ProcessingException {
    super(dataset);
    callInitializer();
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public MySQLDatasetInputForm(Project project) throws ProcessingException {
    super(project);
    callInitializer();
  }

  public class ModifyHandler extends AbstractFormHandler {
    @Override
    protected void execLoad() throws ProcessingException {

      MySQLDataset dataset = (MySQLDataset) getDataset();
      ((AbstractStringField) getFieldById("name")).setValue(dataset.getName());
      ((AbstractStringField) getFieldById("query")).setValue(dataset.getQuery());
    }

    @Override
    protected void execStore() throws ProcessingException {
      Desktop desktop = (Desktop) getDesktop();
      String name = (String) ((AbstractStringField) getFieldById("name")).getValue();
      String query = (String) ((AbstractStringField) getFieldById("query")).getValue();

      MySQLDataset dataset = (MySQLDataset) getDataset();
      dataset.setName(name);
      dataset.setQuery(query);

      Desktop.loadOrRefreshFormDataset(dataset, new MySQLDatasetViewForm(dataset));
      desktop.refreshWorkspace();
    }
  }

  public class NewHandler extends AbstractFormHandler {
    @SuppressWarnings("unchecked")
    @Override
    protected void execStore() throws ProcessingException {
      Desktop desktop = (Desktop) getDesktop();
      String name = (String) ((AbstractStringField) getFieldById("name")).getValue();
      String query = (String) ((AbstractStringField) getFieldById("query")).getValue();
      MySQLDatasource datasource = (MySQLDatasource) ((AbstractSmartField<AbstractDatasource>) getFieldById("datasource")).getValue();
      MySQLDataset dataset = new MySQLDataset(datasource, name, query);
      datasource.addDataset(dataset);
      Desktop.loadOrRefreshFormDataset(dataset, new MySQLDatasetViewForm(dataset));
      desktop.refreshWorkspace();
    }
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
}
