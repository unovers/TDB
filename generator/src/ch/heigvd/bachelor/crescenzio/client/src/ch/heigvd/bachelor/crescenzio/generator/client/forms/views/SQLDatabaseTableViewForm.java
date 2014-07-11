/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.views;

import java.util.List;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.SQLDatabaseTableViewForm.MainBox.TablesBox;
import ch.heigvd.bachelor.crescenzio.generator.dataset.SQLField;
import ch.heigvd.bachelor.crescenzio.generator.datasource.SQLDatasource;
import ch.heigvd.bachelor.crescenzio.generator.datasource.SQLTable;

/**
 * @author Fabio
 */
public class SQLDatabaseTableViewForm extends AbstractForm {

  protected final SQLDatasource datasource;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public SQLDatabaseTableViewForm(SQLDatasource datasource) throws ProcessingException {
    super(false);
    this.datasource = datasource;
    callInitializer();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected int getConfiguredDisplayHint() {
    return DISPLAY_HINT_VIEW;
  }

  @Override
  protected String getConfiguredDisplayViewId() {
    return VIEW_ID_N;
  }

  @Override
  protected boolean getConfiguredMaximized() {
    return true;
  }

  @Override
  protected boolean getConfiguredModal() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("DatasourceInfo");
  }

  public void startView() throws ProcessingException {
    startInternal(new SQLDatabaseTableViewForm.ViewHandler());
  }

  public class ViewHandler extends AbstractFormHandler {

    public ViewHandler() throws ProcessingException {
    }

    @Override
    protected void execLoad() throws ProcessingException {
    }

    @Override
    protected void execPostLoad() throws ProcessingException {
    };

    @Override
    protected void execStore() throws ProcessingException {

    }
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the TablesBox
   */
  public TablesBox getTablesBox() {
    return getFieldByClass(TablesBox.class);
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
      return 2;
    }

    @Override
    protected int getConfiguredGridW() {
      return 1;
    }

    @Override
    protected boolean getConfiguredScrollable() {
      return true;
    }

    @Order(10.0)
    public class TablesBox extends AbstractTabBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Tables");
      }

      @Override
      protected void injectFieldsInternal(List<IFormField> fieldList) {
        for (SQLTable table : datasource.getTables()) {
          fieldList.add(new AbstractGroupBox() {
            @Override
            protected String getConfiguredLabel() {
              return table.getName();
            }

            @Override
            protected void injectFieldsInternal(List<IFormField> fieldList) {
              for (SQLField field : table.getFields()) {

                fieldList.add(new AbstractLabelField() {
                  @Override
                  protected String getConfiguredLabel() {
                    return field.getName() + " - " + field.getType();
                  }
                });
              }
            }
          });
        }
      }
    }
  }
}