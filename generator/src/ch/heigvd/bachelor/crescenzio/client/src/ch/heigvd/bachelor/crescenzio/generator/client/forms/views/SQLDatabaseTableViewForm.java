/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.views;

import java.util.List;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.datasets.SQLField;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractSQLDatasource;
import ch.heigvd.bachelor.crescenzio.generator.datasources.SQLTable;

/**
 * @author Fabio
 */
public class SQLDatabaseTableViewForm extends AbstractViewForm {

  protected final AbstractSQLDatasource datasource;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public SQLDatabaseTableViewForm(AbstractSQLDatasource datasource) throws ProcessingException {
    super(false);
    this.datasource = datasource;
    try {
      datasource.describe();
    }
    catch (ProcessingException e) {
      datasource.setDescribed(false);
    }
    callInitializer();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
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

  @Override
  public void startView() throws ProcessingException {
    startInternal(new SQLDatabaseTableViewForm.ViewHandler());
  }

  public class ViewHandler extends AbstractFormHandler {

    public ViewHandler() throws ProcessingException {
    }

    @Override
    protected void execLoad() throws ProcessingException {
      if (!datasource.isDescribed()) {
        ((AbstractLabelField) getFieldById("message_error")).setValue("ERROR_DATABASE_INFO");
      }
    }

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

    @Override
    protected void injectFieldsInternal(List<IFormField> fieldList) {
      if (datasource.isDescribed()) {
        fieldList.add(new AbstractTabBox() {

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

                @SuppressWarnings("hiding")
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
        });
      }
      else {
        fieldList.add(new AbstractLabelField() {
          @Override
          protected String getConfiguredLabel() {
            return "ERROR:";
          }

          @Override
          public String getFieldId() {
            return "message_error";
          }

        });

      }
    }
  }

}
