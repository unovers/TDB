/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.views;

import java.util.List;
import java.util.Map.Entry;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.filechooserfield.AbstractFileChooserField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.AndroidOutputApplicationViewForm.MainBox.OutputsSectionBox;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.AndroidOutputApplicationViewForm.MainBox.OutputsSectionBox.FieldsBox;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.AndroidOutputApplicationViewForm.MainBox.OutputsSectionBox.ItemsTypesBox;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.AndroidOutputApplicationViewForm.MainBox.OutputsSectionBox.ItemsTypesBox.AddItemTypeButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.AndroidOutputApplicationViewForm.MainBox.OutputsSectionBox.OutputsDetailsBox;
import ch.heigvd.bachelor.crescenzio.generator.outputs.AbstractOutputApplication;
import ch.heigvd.bachelor.crescenzio.generator.outputs.FileField;
import ch.heigvd.bachelor.crescenzio.generator.outputs.ItemType;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputField;
import ch.heigvd.bachelor.crescenzio.generator.outputs.StringField;

/**
 * @author Fabio
 */
public class AndroidOutputApplicationViewForm extends AbstractOutputViewForm {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public AndroidOutputApplicationViewForm(Project project, AbstractOutputApplication output) throws ProcessingException {
    super(project, output);
    callInitializer();
  }

  /**
   * @return the OutputsDetailsBox
   */
  public OutputsDetailsBox getOutputsDetailsBox() {
    return getFieldByClass(OutputsDetailsBox.class);
  }

  /**
   * @return the OutputsSectionBox
   */
  public OutputsSectionBox getOutputsSectionBox() {
    return getFieldByClass(OutputsSectionBox.class);
  }

  @Override
  protected int getConfiguredDisplayHint() {
    return DISPLAY_HINT_VIEW;
  }

  @Override
  protected String getConfiguredDisplayViewId() {
    return VIEW_ID_CENTER;
  }

  /**
   * @return the AddItemTypeButton
   */
  public AddItemTypeButton getAddItemTypeButton() {
    return getFieldByClass(AddItemTypeButton.class);
  }

  /**
   * @return the FieldsBox
   */
  public FieldsBox getFieldsBox() {
    return getFieldByClass(FieldsBox.class);
  }

  /**
   * @return the ItemsTypesBox
   */
  public ItemsTypesBox getItemsTypesBox() {
    return getFieldByClass(ItemsTypesBox.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class OutputsSectionBox extends AbstractTabBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("OutputsSection");
      }

      @Order(10.0)
      public class OutputsDetailsBox extends AbstractGroupBox {

        @Override
        protected int getConfiguredGridColumnCount() {
          return 1;
        }

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected void injectFieldsInternal(List<IFormField> fieldList) {
          for (Entry<Field, OutputField> entry : getOutput().getApplicationFields().entrySet()) {
            Field field = entry.getKey();
            OutputField outputField = entry.getValue();

            if (outputField instanceof StringField) {
              fieldList.add(new AbstractStringField() {
                @Override
                protected String getConfiguredLabel() {
                  return field.getName();
                }
              });
            }
            else if (outputField instanceof FileField) {
              fieldList.add(new AbstractFileChooserField() {
                @Override
                protected String getConfiguredLabel() {
                  return field.getName();
                }
              });
            }
          }
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("OutputsDetails");
        }
      }

      @Order(20.0)
      public class FieldsBox extends AbstractGroupBox {

        @Override
        protected int getConfiguredGridColumnCount() {
          return 1;
        }

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected void injectFieldsInternal(List<IFormField> fieldList) {
          for (Entry<Field, Field> entry : getOutput().getMappedFields().entrySet()) {
            Field mappedField = entry.getKey();
            Field field = entry.getValue();
            fieldList.add(new AbstractSmartField() {
              @Override
              protected String getConfiguredLabel() {
                return mappedField.getName();
              }
            });
          }
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Fields");
        }
      }

      @Order(30.0)
      public class ItemsTypesBox extends AbstractGroupBox {

        @Override
        protected int getConfiguredGridColumnCount() {
          return 1;
        }

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ItemsTypes");
        }

        @Override
        protected void injectFieldsInternal(List<IFormField> fieldList) {
          for (ItemType item : getOutput().getItemsTypes()) {

            fieldList.add(new AbstractGroupBox() {
              @Override
              protected String getConfiguredLabel() {
                return item.getName();
              }

              @Override
              protected void injectFieldsInternal(List<IFormField> fieldList) {
                fieldList.add(new AbstractFileChooserField() {
                  @Override
                  protected String getConfiguredLabel() {
                    return "Item list view";
                  }
                });
                fieldList.add(new AbstractFileChooserField() {
                  @Override
                  protected String getConfiguredLabel() {
                    return "Item view";
                  }
                });
              }
            });
          }
        }

        @Order(10.0)
        public class AddItemTypeButton extends AbstractButton {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("AddItemType");
          }
        }
      }
    }
  }

  @Override
  public void startView() throws ProcessingException {
    startInternal(new AndroidOutputApplicationViewForm.ViewHandler());
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
