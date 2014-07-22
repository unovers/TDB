/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.views;

import java.util.List;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.FieldInputForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.FieldsViewForm.MainBox.FieldsBox;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.FieldsViewForm.MainBox.FieldsBox.DatasetMappingBox;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.FieldsViewForm.MainBox.FieldsBox.FieldsListBox;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.FieldsViewForm.MainBox.FieldsBox.FieldsListBox.AddFieldButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.FieldsViewForm.MainBox.FieldsBox.FieldsListBox.FieldsField;
import ch.heigvd.bachelor.crescenzio.generator.datasets.Dataset;
import ch.heigvd.bachelor.crescenzio.generator.datasources.Datasource;

/**
 * @author Fabio
 */
public class FieldsViewForm extends AbstractForm {

  private Project project;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public FieldsViewForm(Project project) throws ProcessingException {
    super(false);
    this.project = project;
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
    return VIEW_ID_CENTER;
  }

  @Override
  protected boolean getConfiguredModal() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Fields");
  }

  /**
   * @return the AddFieldButton
   */
  public AddFieldButton getAddFieldButton() {
    return getFieldByClass(AddFieldButton.class);
  }

  /**
   * @return the DatasetMappingBox
   */
  public DatasetMappingBox getDatasetMappingBox() {
    return getFieldByClass(DatasetMappingBox.class);
  }

  /**
   * @return the FieldsBox
   */
  public FieldsBox getFieldsBox() {
    return getFieldByClass(FieldsBox.class);
  }

  /**
   * @return the FieldsField
   */
  public FieldsField getFieldsField() {
    return getFieldByClass(FieldsField.class);
  }

  /**
   * @return the FieldsListBox
   */
  public FieldsListBox getFieldsListBox() {
    return getFieldByClass(FieldsListBox.class);
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
    protected boolean getConfiguredLabelVisible() {
      return false;
    }

    @Override
    protected boolean getConfiguredScrollable() {
      return true;
    }

    @Order(20.0)
    public class FieldsBox extends AbstractTabBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Fields");
      }

      @Order(10.0)
      public class FieldsListBox extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Fields");
        }

        @Order(10.0)
        public class FieldsField extends AbstractTableField<FieldsField.Table> {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Fields");
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Order(10.0)
          public class Table extends AbstractExtensibleTable {

            @Override
            protected boolean getConfiguredAutoResizeColumns() {
              return true;
            }

            @Override
            protected boolean getConfiguredHeaderVisible() {
              return false;
            }

            @Override
            protected boolean getConfiguredMultiCheck() {
              return false;
            }

            @Override
            protected boolean getConfiguredMultiSelect() {
              return false;
            }

            /**
             * @return the NameColumn
             */
            public NameColumn getNameColumn() {
              return getColumnSet().getColumnByClass(NameColumn.class);
            }

            @Order(10.0)
            public class NameColumn extends AbstractStringColumn {

              @Override
              protected String getConfiguredHeaderText() {
                return TEXTS.get("Name");
              }
            }
          }
        }

        @Order(30.0)
        public class AddFieldButton extends AbstractButton {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("AddField");
          }

          @Override
          protected void execClickAction() throws ProcessingException {
            new FieldInputForm(project).startNew();
          }

        }
      }

      @Order(20.0)
      public class DatasetMappingBox extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DatasetMapping");
        }

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected int getConfiguredGridColumnCount() {
          return 1;
        }

        @Override
        protected void injectFieldsInternal(List<IFormField> fieldList) {
          for (Datasource datasource : project.getDatasources()) {
            for (Dataset dataset : datasource.getDatasets()) {
              fieldList.add(new AbstractGroupBox() {

                @Override
                protected String getConfiguredLabel() {
                  return dataset.getName();
                }

                @Override
                protected void injectFieldsInternal(List<IFormField> fieldList) {
                  for (Field field : project.getFields()) {
                    fieldList.add(new AbstractStringField() {
                      @Override
                      protected String getConfiguredLabel() {
                        return field.getName();
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

  }

  public void startView() throws ProcessingException {
    startInternal(new FieldsViewForm.ViewHandler());
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      for (Field field : project.getFields()) {
        ITableRow row = getFieldsField().getTable().createRow();
        getFieldsField().getTable().getNameColumn().setValue(row, field.getName());
        getFieldsField().getTable().addRow(row);
      }
    }
  }
}
