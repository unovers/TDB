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

import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.MySQLDatasetViewForm.MainBox.NameField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.MySQLDatasetViewForm.MainBox.QueryField;
import ch.heigvd.bachelor.crescenzio.generator.datasets.MySQLDataset;

/**
 * @author Fabio
 */
public class MySQLDatasetViewForm extends AbstractDatasetViewForm {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public MySQLDatasetViewForm(MySQLDataset dataset) throws ProcessingException {
    super(dataset);
    callInitializer();
  }

  @Override
  public void startView() throws ProcessingException {
    startInternal(new MySQLDatasetViewForm.ViewHandler());
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      getNameField().setValue(getDataset().getName());
      getQueryField().setValue(((MySQLDataset) getDataset()).getQuery());
    }
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
   * @return the QueryField
   */
  public QueryField getQueryField() {
    return getFieldByClass(QueryField.class);
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
      protected int getConfiguredWidthInPixel() {
        return 250;
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }
    }

    @Order(20.0)
    public class QueryField extends AbstractLabelField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Query");
      }

      @Override
      protected int getConfiguredWidthInPixel() {
        return 250;
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }

      @Override
      protected boolean getConfiguredWrapText() {
        return true;
      }
    }
  }
}
