/**
 * Nom du fichier         : ItemTypeInputForm.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Ce ficher definit un formulaire pour l'ajout de nouveaux types
 *                          de données dans une application
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs;

import java.util.List;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.filechooserfield.AbstractFileChooserField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.ScoutTexts;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ItemTypeInputForm.MainBox.CancelButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ItemTypeInputForm.MainBox.NameField;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ItemTypeInputForm.MainBox.OkButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.OutputApplicationViewForm;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
import ch.heigvd.bachelor.crescenzio.generator.outputs.FileResource;
import ch.heigvd.bachelor.crescenzio.generator.outputs.ItemType;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputApplication;

/**
 * Define a form for inserting item types
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class ItemTypeInputForm extends AbstractInputForm {

  private OutputApplication output;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public ItemTypeInputForm(OutputApplication output) throws ProcessingException {
    super(false);
    this.output = output;
    callInitializer();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected boolean getConfiguredModal() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("itemType");
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

    @Override
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Override
    protected int getConfiguredGridW() {
      return 1;
    }

    @Override
    protected int getConfiguredLabelPosition() {
      return LABEL_POSITION_LEFT;
    }

    @Override
    protected void injectFieldsInternal(List<IFormField> fieldList) {
      ItemType itemTypeDefault = output.getItemsTypes().getFirst();

      for (FileResource resource : itemTypeDefault.getResources()) {
        fieldList.add(new AbstractFileChooserField() {
          @Override
          protected String getConfiguredLabel() {
            return resource.getName();
          }

          @Override
          public String getFieldId() {
            return "resource_" + resource.getId();
          }

          @Override
          protected boolean getConfiguredShowDirectory() {
            return true;
          }

          @Override
          protected boolean getConfiguredTypeLoad() {
            return true;
          }
        });
      }
    }

    @Order(10.0)
    public class NameField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Name");
      }
    }

    @Order(20.0)
    public class OkButton extends AbstractOkButton {
      @Override
      protected String getConfiguredTooltipText() {
        return ScoutTexts.get("NextDatasourceButtonTip");
      }
    }

    @Order(30.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class NewHandler extends AbstractFormHandler {
    @Override
    protected void execStore() throws ProcessingException {
      ItemType itemType = new ItemType(getNameField().getValue());
      ItemType itemTypeDefault = output.getItemsTypes().getFirst();

      for (FileResource resource : itemTypeDefault.getResources()) {
        String resourceValue = ((AbstractFileChooserField) getFieldById("resource_" + resource.getId())).getValue();
        FileResource new_resource = new FileResource(resource.getName(), resourceValue, resource.getDescription(), resource.getTypesAllowed());
        itemType.addResource(new_resource);
      }
      output.addItemType(itemType);
      Desktop.loadOrRefreshFormOutput(output, new OutputApplicationViewForm(output.getProject(), output));
    }
  }

  /**
   * Edit of itemType is not allowed
   */
  @Override
  public void startModify() throws ProcessingException {
  }
}
