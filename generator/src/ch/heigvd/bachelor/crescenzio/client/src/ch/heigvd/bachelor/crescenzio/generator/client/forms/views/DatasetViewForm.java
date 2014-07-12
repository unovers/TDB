/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.views;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.datasets.Dataset;

/**
 * @author Fabio
 */
public class DatasetViewForm extends AbstractForm {

  private Dataset Dataset;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public DatasetViewForm(Dataset dataset) throws ProcessingException {
    super(false);
    this.Dataset = dataset;
    callInitializer();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("DatasetInfo");
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {
  }

  public void startView() throws ProcessingException {
    startInternal(new DatasetViewForm.ViewHandler());
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
    }

    @Override
    protected void execStore() throws ProcessingException {
    }
  }
}
