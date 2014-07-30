/**
 * Nom du fichier         : ConditionInputForm.java
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
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ConditionInputForm.MainBox.CancelButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ConditionInputForm.MainBox.NameField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ConditionInputForm.MainBox.OkButton;
import ch.heigvd.bachelor.crescenzio.generator.client.services.lookup.FieldsOutputMappingLookupCall;
import ch.heigvd.bachelor.crescenzio.generator.criterias.Criteria;

/**
 * @author Fabio
 */
public class ConditionInputForm extends AbstractInputForm {
  private Criteria criteria;
  private Project project;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public ConditionInputForm(Project project, Criteria criteria) throws ProcessingException {
    super(false);
    this.project = project;
    this.criteria = criteria;
    callInitializer();
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
   * @return the NameField
   */
  public NameField getNameField() {
    return getFieldByClass(NameField.class);
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
    public class NameField extends AbstractSmartField<Field> {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Name");
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }

      @Override
      protected Class<? extends ILookupCall<Field>> getConfiguredLookupCall() {
        return FieldsOutputMappingLookupCall.class;
      }

      @Override
      protected void execPrepareLookup(ILookupCall<Field> call) throws ProcessingException {
        FieldsOutputMappingLookupCall c = (FieldsOutputMappingLookupCall) call;
        c.setProject(project);
      }
    }

    @Order(20.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(30.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class NewHandler extends AbstractFormHandler {
    @Override
    protected void execStore() throws ProcessingException {
      criteria.addCondition(getNameField().getValue());
    }
  }
}
