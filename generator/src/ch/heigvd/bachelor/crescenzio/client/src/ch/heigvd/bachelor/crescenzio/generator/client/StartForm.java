/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.client.StartForm.MainBox.InformationDepartField;

/**
 * @author Fabio
 */
public class StartForm extends AbstractForm {

  private Long m_startNr;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public StartForm() throws ProcessingException {
    super();
  }

  /**
   * @return the StartNr
   */
  @FormData
  public Long getStartNr() {
    return m_startNr;
  }

  /**
   * @param startNr
   *          the StartNr to set
   */
  @FormData
  public void setStartNr(Long startNr) {
    m_startNr = startNr;
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
  protected String getConfiguredTitle() {
    return TEXTS.get("Start");
  }

  /**
   * @return the InformationDepartField
   */
  public InformationDepartField getInformationDepartField() {
    return getFieldByClass(InformationDepartField.class);
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
    protected boolean getConfiguredExpanded() {
      return false;
    }

    @Override
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Override
    protected int getConfiguredGridW() {
      return 5;
    }

    @Order(10.0)
    public class InformationDepartField extends AbstractLabelField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("InformationDepart");
      }

      @Override
      protected int getConfiguredLabelHorizontalAlignment() {
        return -1;
      }

      @Override
      protected int getConfiguredLabelPosition() {
        return LABEL_POSITION_LEFT;
      }

      @Override
      protected int getConfiguredLabelWidthInPixel() {
        return 500;
      }
    }
  }

  /**
   *
   */
  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  public class ModifyHandler extends AbstractFormHandler {
  }
}
