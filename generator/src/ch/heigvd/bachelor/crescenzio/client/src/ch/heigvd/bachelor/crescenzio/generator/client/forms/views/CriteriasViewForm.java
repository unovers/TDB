/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.views;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;

/**
 * @author Fabio
 */
public class CriteriasViewForm extends AbstractForm {

  private Project project;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public CriteriasViewForm(Project project) throws ProcessingException {
    super(false);
    this.project = project;
    callInitializer();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Criterias");
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

    @Order(10.0)
    public class FieldsField extends AbstractTableField<FieldsField.Table> {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Criterias");
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

    @Order(20.0)
    public class AddFieldButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("AddCriterias");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        //new CriteriasInputForm(project).startNew();
      }

    }
  }

  public void startView() throws ProcessingException {
    startInternal(new CriteriasViewForm.ViewHandler());
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
    }
  }
}
