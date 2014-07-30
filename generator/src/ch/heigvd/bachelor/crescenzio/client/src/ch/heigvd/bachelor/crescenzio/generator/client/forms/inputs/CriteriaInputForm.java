/**
 * Nom du fichier         : CriteriaInputForm.java
 * Version                : 0.1
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           :
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.CriteriaInputForm.MainBox.CancelButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.CriteriaInputForm.MainBox.OkButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.CriteriaInputForm.MainBox.TitleField;
import ch.heigvd.bachelor.crescenzio.generator.criterias.Criteria;

/**
 * @author Fabio
 */
public class CriteriaInputForm extends AbstractInputForm {

  private Project project;
  private Criteria criteria;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public CriteriaInputForm(Project project) throws ProcessingException {
    super(false);
    this.project = project;
    callInitializer();
  }

  public CriteriaInputForm(Project project, Criteria criteria) throws ProcessingException {
    super(false);
    this.project = project;
    this.criteria = criteria;
    callInitializer();
  }

  public void setCriteria(Criteria criteria) {
    this.criteria = criteria;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Field");
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  @Override
  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  @Override
  public void startNew() throws ProcessingException {
    startInternal(new NewHandler());
  }

  /**
   * @return the CancelButton
   */
  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the TitleField
   */
  public TitleField getTitleField() {
    return getFieldByClass(TitleField.class);
  }

  /**
   * @return the OkButton
   */
  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class TitleField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Field");
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }
    }

    @Order(20.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(30.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {
    @Override
    protected void execLoad() throws ProcessingException {
      getTitleField().setValue(criteria.getTitle());
    }

    @Override
    protected void execStore() throws ProcessingException {
      criteria.setTitle(getTitleField().getValue());
    }
  }

  public class NewHandler extends AbstractFormHandler {
    @Override
    protected void execStore() throws ProcessingException {
      project.addCriteria(new Criteria(getTitleField().getValue()));
    }
  }

}
