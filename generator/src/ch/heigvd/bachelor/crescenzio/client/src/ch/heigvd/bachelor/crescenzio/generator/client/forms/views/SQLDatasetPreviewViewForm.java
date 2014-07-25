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
import org.eclipse.scout.rt.client.ui.form.fields.pagefield.AbstractPageField;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.client.pages.PreviewDatasetTablePage;
import ch.heigvd.bachelor.crescenzio.generator.datasets.AbstractSQLDataset;

/**
 * @author Fabio
 */
public class SQLDatasetPreviewViewForm extends AbstractViewForm {

  protected final AbstractSQLDataset dataset;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public SQLDatasetPreviewViewForm(AbstractSQLDataset dataset) throws ProcessingException {
    super(false);
    this.dataset = dataset;
    dataset.preview();
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
    return TEXTS.get("PreviewDataset");
  }

  @Override
  public void startView() throws ProcessingException {
    startInternal(new SQLDatasetPreviewViewForm.ViewHandler());
  }

  public class ViewHandler extends AbstractFormHandler {

    public ViewHandler() throws ProcessingException {
    }

    @Override
    protected void execLoad() throws ProcessingException {
      if (!dataset.isDescribed()) {
        ((AbstractLabelField) getFieldById("message_error")).setValue("ERROR_DATASET_INFO");
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
      if (dataset.isDescribed()) {
        AbstractPageField page = new AbstractPageField<PreviewDatasetTablePage>() {
        };
        page.setPage(new PreviewDatasetTablePage(dataset));
        fieldList.add(page);
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
