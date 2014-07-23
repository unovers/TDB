/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs;

import java.util.List;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.MultipleForm.MainBox.AddFieldButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.MultipleForm.MainBox.CancelButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.MultipleForm.MainBox.Fields0Box;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.MultipleForm.MainBox.Fields0Box.Name0Field;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.MultipleForm.MainBox.MultiField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.MultipleForm.MainBox.MultiField.Table;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.MultipleForm.MainBox.OkButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.MultipleForm.MainBox.TestField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.MultipleForm.MainBox.TestSmartField;

/**
 * @author Fabio
 */
public class MultipleForm extends AbstractForm {

  private Long m_multipleNr;
  private int number;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public MultipleForm() throws ProcessingException {
    super();
  }

  public MultipleForm(int params) throws ProcessingException {
    super(false);//false=do not yet initialize the form
    number = 1;
    callInitializer();

  }

  /**
   * @return the MultipleNr
   */
  @FormData
  public Long getMultipleNr() {
    return m_multipleNr;
  }

  /**
   * @param multipleNr
   *          the MultipleNr to set
   */
  @FormData
  public void setMultipleNr(Long multipleNr) {
    m_multipleNr = multipleNr;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Multiple");
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public void startNew() throws ProcessingException {
    startInternal(new NewHandler());
  }

  /**
   * @return the AddFieldButton
   */
  public AddFieldButton getAddFieldButton() {
    return getFieldByClass(AddFieldButton.class);
  }

  /**
   * @return the CancelButton
   */
  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  /**
   * @return the Fields0Box
   */
  public Fields0Box getFields0Box() {
    return getFieldByClass(Fields0Box.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the MultiField
   */
  public MultiField getMultiField() {
    return getFieldByClass(MultiField.class);
  }

  /**
   * @return the Name0Field
   */
  public Name0Field getName0Field() {
    return getFieldByClass(Name0Field.class);
  }

  /**
   * @return the OkButton
   */
  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  /**
   * @return the TestField
   */
  public TestField getTestField() {
    return getFieldByClass(TestField.class);
  }

  /**
   * @return the TestSmartField
   */
  public TestSmartField getTestSmartField(){
    return getFieldByClass(TestSmartField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Override
    protected void injectFieldsInternal(List<IFormField> fieldList) {

      for (int i = 1; i <= number; i++) {
        final int param = i;
        fieldList.add(new AbstractStringField() {
          @Override
          protected String getConfiguredLabel() {
            return "Field " + param;
          }
        });
      }

    }

    @Order(10.0)
    public class MultiField extends AbstractTableField<MultiField.Table> {

      @Override
      protected int getConfiguredGridH() {
        return 5;
      }

      @Override
      protected boolean getConfiguredGridUseUiHeight() {
        return true;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Multi");
      }

      @Order(10.0)
      public class Table extends AbstractExtensibleTable {

        @Override
        protected boolean getConfiguredMultilineText() {
          return true;
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
            return TEXTS.get("name");
          }

          @Override
          protected boolean getConfiguredEditable() {
            return true;
          }
        }
      }
    }

    @Order(20.0)
    public class Fields0Box extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Fields0");
      }

      @Order(10.0)
      public class Name0Field extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Name0");
        }
      }
    }

    @Order(30.0)
    public class TestField extends AbstractLabelField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("test");
      }

      @Override
      protected boolean getConfiguredLabelVisible() {
        return false;
      }
    }

    @Order(40.0)
    public class TestSmartField extends AbstractSmartField<String> {

      @Override
      protected String getConfiguredBrowseNewText() {
        return "aaa";
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("test");
      }

      @Override
      protected void execInitField() throws ProcessingException {
        //TODO [Fabio] Auto-generated method stub.
        super.execInitField();
      }
    }

    @Order(50.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(60.0)
    public class CancelButton extends AbstractCancelButton {
    }

    @Order(70.0)
    public class AddFieldButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("AddField");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        Table table = getMultiField().getTable();
        ITableRow row = table.createRow();
        table.getNameColumn().setValue(row, "Smith");
        table.addRow(row, true);
        //TODO [Fabio] Auto-generated method stub.
        getFields().add(new AbstractStringField() {
          @Override
          protected String getConfiguredLabel() {
            return "Field " + 15;
          }
        });
      }
    }
  }

  public class AddFieldHandler extends AbstractFormHandler {
  }

  public class ModifyHandler extends AbstractFormHandler {
  }

  public class NewHandler extends AbstractFormHandler {
  }
}
