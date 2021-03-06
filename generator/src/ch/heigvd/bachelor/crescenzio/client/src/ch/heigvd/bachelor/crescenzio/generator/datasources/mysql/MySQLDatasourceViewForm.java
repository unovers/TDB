/**
 * Nom du fichier         : MySQLDatasourceViewForm.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date derni�re r�vision : 30.07.2014
 *
 * Commentaires           : D�finit un ViewForm pour une source de donn�es MySQL
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.datasources.mysql;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
import ch.heigvd.bachelor.crescenzio.generator.datasources.mysql.MySQLDatasourceViewForm.MainBox.DatabaseHostField;
import ch.heigvd.bachelor.crescenzio.generator.datasources.mysql.MySQLDatasourceViewForm.MainBox.DatabaseLoginField;
import ch.heigvd.bachelor.crescenzio.generator.datasources.mysql.MySQLDatasourceViewForm.MainBox.DatabaseNameField;
import ch.heigvd.bachelor.crescenzio.generator.datasources.mysql.MySQLDatasourceViewForm.MainBox.DatabasePasswordField;
import ch.heigvd.bachelor.crescenzio.generator.datasources.mysql.MySQLDatasourceViewForm.MainBox.DatabasePortField;
import ch.heigvd.bachelor.crescenzio.generator.datasources.mysql.MySQLDatasourceViewForm.MainBox.DeleteDatasourceButton;
import ch.heigvd.bachelor.crescenzio.generator.datasources.mysql.MySQLDatasourceViewForm.MainBox.EditDatasourceButton;
import ch.heigvd.bachelor.crescenzio.generator.datasources.mysql.MySQLDatasourceViewForm.MainBox.NameField;
import ch.heigvd.bachelor.crescenzio.generator.datasources.mysql.MySQLDatasourceViewForm.MainBox.TypeField;
import ch.heigvd.bachelor.crescenzio.generator.datasources.sql.AbstractSQLDatasourceViewForm;
import ch.heigvd.bachelor.crescenzio.generator.datasources.sql.SQLDatabaseTableViewForm;

/**
 * Define a ViewForm for a MySQL datasource
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class MySQLDatasourceViewForm extends AbstractSQLDatasourceViewForm {
  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public MySQLDatasourceViewForm(MySQLDatasource datasource) throws ProcessingException {
    super(datasource);
    callInitializer();
  }

  @Override
  public void startView() throws ProcessingException {
    startInternal(new ViewHandler());
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      MySQLDatasource datasource = (MySQLDatasource) getDatasource();
      getDatabaseHostField().setValue(datasource.getHostname());
      getDatabaseLoginField().setValue(datasource.getLogin());
      getDatabasePasswordField().setValue(datasource.getPassword());
      getDatabasePortField().setValue(Integer.toString(datasource.getPort()));
      getDatabaseNameField().setValue(datasource.getDatabase());
      getNameField().setValue(datasource.getName());
      getTypeField().setValue(TEXTS.get("MySQLDatasource"));

    }
  }

  /**
   * @return the DatabaseHostField
   */
  public DatabaseHostField getDatabaseHostField() {
    return getFieldByClass(DatabaseHostField.class);
  }

  /**
   * @return the DatabaseLoginField
   */
  public DatabaseLoginField getDatabaseLoginField() {
    return getFieldByClass(DatabaseLoginField.class);
  }

  /**
   * @return the DatabaseNameField
   */
  public DatabaseNameField getDatabaseNameField() {
    return getFieldByClass(DatabaseNameField.class);
  }

  /**
   * @return the DatabasePasswordField
   */
  public DatabasePasswordField getDatabasePasswordField() {
    return getFieldByClass(DatabasePasswordField.class);
  }

  /**
   * @return the DatabasePortField
   */
  public DatabasePortField getDatabasePortField() {
    return getFieldByClass(DatabasePortField.class);
  }

  /**
   * @return the DeleteDatasourceButton
   */
  public DeleteDatasourceButton getDeleteDatasourceButton() {
    return getFieldByClass(DeleteDatasourceButton.class);
  }

  /**
   * @return the EditDatasourceButton
   */
  public EditDatasourceButton getEditDatasourceButton() {
    return getFieldByClass(EditDatasourceButton.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the NameField
   */
  public NameField getNameField() {
    return getFieldByClass(NameField.class);
  }

  /**
   * @return the TypeField
   */
  public TypeField getTypeField() {
    return getFieldByClass(TypeField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected boolean getConfiguredEnabled() {
      return false;
    }

    @Override
    protected boolean getConfiguredExpanded() {
      return false;
    }

    @Override
    protected boolean getConfiguredFocusable() {
      return false;
    }

    @Override
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Override
    protected boolean getConfiguredScrollable() {
      return true;
    }

    @Order(10.0)
    public class NameField extends AbstractLabelField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Name");
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

    @Order(20.0)
    public class TypeField extends AbstractLabelField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Type");
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

    @Order(30.0)
    public class DatabasePortField extends AbstractLabelField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("DatabasePort");
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
    public class DatabaseHostField extends AbstractLabelField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("DatabaseHost");
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

    @Order(50.0)
    public class DatabaseNameField extends AbstractLabelField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("DatabaseName");
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
    public class DatabaseLoginField extends AbstractLabelField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("DatabaseLogin");
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
    public class DatabasePasswordField extends AbstractLabelField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("DatabasePassword");
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

    @Order(80.0)
    public class PreviewDatasourceButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("PreviewDatabaseContent");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        new SQLDatabaseTableViewForm((MySQLDatasource) getDatasource()).startView();
      }
    }

    @Order(90.0)
    public class EditDatasourceButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("EditDatasource");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        MySQLDatasourceInputForm form = new MySQLDatasourceInputForm((MySQLDatasource) getDatasource());
        form.startModify();
      }
    }

    @Order(100.0)
    public class DeleteDatasourceButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("DeleteDatasource");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        Desktop.removeDatasource(getDatasource());
        getDatasource().getProject().removeDatasource(getDatasource());
        ((Desktop) getDesktop()).refreshWorkspace();
      }
    }
  }

}
