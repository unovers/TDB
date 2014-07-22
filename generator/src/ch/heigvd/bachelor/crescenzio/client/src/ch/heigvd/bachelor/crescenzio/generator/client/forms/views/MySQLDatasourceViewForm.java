/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.views;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.MySQLDatasourceViewForm.MainBox.DatabaseHostField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.MySQLDatasourceViewForm.MainBox.DatabaseLoginField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.MySQLDatasourceViewForm.MainBox.DatabaseNameField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.MySQLDatasourceViewForm.MainBox.DatabasePasswordField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.MySQLDatasourceViewForm.MainBox.DatabasePortField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.MySQLDatasourceViewForm.MainBox.NameField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.MySQLDatasourceViewForm.MainBox.TypeField;
import ch.heigvd.bachelor.crescenzio.generator.datasources.MySQLDatasource;

/**
 * @author Fabio
 */
public class MySQLDatasourceViewForm extends AbstractViewForm {

  private MySQLDatasource datasource;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public MySQLDatasourceViewForm(MySQLDatasource datasource) throws ProcessingException {
    super(false);
    this.datasource = datasource;
    callInitializer();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("DatasourceInfo");
  }

  public void startView() throws ProcessingException {
    startInternal(new MySQLDatasourceViewForm.ViewHandler());
    new SQLDatabaseTableViewForm(datasource).startView();
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
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
  }
}
