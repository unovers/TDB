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
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.AndroidOutputApplicationViewForm.MainBox.DeleteOutputButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.AndroidOutputApplicationViewForm.MainBox.OutputsSectionBox;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.AndroidOutputApplicationViewForm.MainBox.OutputsSectionBox.FieldsBox;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.AndroidOutputApplicationViewForm.MainBox.OutputsSectionBox.ItemsTypesBox;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.AndroidOutputApplicationViewForm.MainBox.OutputsSectionBox.ItemsTypesBox.AddItemTypeButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.AndroidOutputApplicationViewForm.MainBox.OutputsSectionBox.OutputsDetailsBox;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.AndroidOutputApplicationViewForm.MainBox.SaveChangesButton;
import ch.heigvd.bachelor.crescenzio.generator.client.services.lookup.FieldsOutputMappingLookupCall;
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
   * @return the DeleteOutputButton
   */
  public DeleteOutputButton getDeleteOutputButton() {
    return getFieldByClass(DeleteOutputButton.class);
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

  /**
   * @return the SaveChangesButton
   */
  public SaveChangesButton getSaveChangesButton() {
    return getFieldByClass(SaveChangesButton.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected boolean getConfiguredScrollable() {
      return true;
    }

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

                @Override
                public String getFieldId() {
                  return "application_" + field.getId();
                }
              });
            }
            else if (outputField instanceof FileField) {
              fieldList.add(new AbstractFileChooserField() {
                @Override
                protected String getConfiguredLabel() {
                  return field.getName();
                }

                @Override
                public String getFieldId() {
                  return "application_" + field.getId();
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
            Field field = entry.getKey();
            Field mappedField = entry.getValue();
            fieldList.add(new AbstractSmartField<Field>() {
              @Override
              protected String getConfiguredLabel() {
                return field.getName();
              }

              @Override
              protected Class<? extends ILookupCall<Field>> getConfiguredLookupCall() {
                return FieldsOutputMappingLookupCall.class;
              }

              @Override
              protected void execPrepareLookup(ILookupCall<Field> call) throws ProcessingException {
                FieldsOutputMappingLookupCall c = (FieldsOutputMappingLookupCall) call;
                c.setProject(getProject());
              }

              @Override
              protected int getConfiguredLabelWidthInPixel() {
                return 150;
              }

              @Override
              public Field getInitValue() {
                // TODO Auto-generated method stub
                return field;
              }

              @Override
              public String getFieldId() {
                return "mapping_" + field.getId();
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
                return "";
              }

              @Override
              protected void injectFieldsInternal(List<IFormField> fieldList) {
                fieldList.add(new AbstractStringField() {
                  @Override
                  protected String getConfiguredLabel() {
                    return TEXTS.get("Name");
                  }

                  @Override
                  public String getFieldId() {
                    // TODO Auto-generated method stub
                    return "item_id_" + item.getId();
                  }
                });
                fieldList.add(new AbstractFileChooserField() {
                  @Override
                  protected String getConfiguredLabel() {
                    return TEXTS.get("ItemListView");
                  }

                  @Override
                  public String getFieldId() {
                    // TODO Auto-generated method stub
                    return "item_list_view_" + item.getId();
                  }
                });
                fieldList.add(new AbstractFileChooserField() {
                  @Override
                  protected String getConfiguredLabel() {
                    return TEXTS.get("ItemView");
                  }

                  @Override
                  public String getFieldId() {
                    // TODO Auto-generated method stub
                    return "item_view_" + item.getId();
                  }
                });

                if (!item.getName().equals("__default")) {
                  fieldList.add(new AbstractButton() {
                    @Override
                    protected String getConfiguredLabel() {
                      return TEXTS.get("RemoveItem");
                    }
                  });
                }

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

    @Order(20.0)
    public class SaveChangesButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("SaveChanges");
      }

      @Override
      protected void execClickAction() throws ProcessingException {

        //application infos
        for (Entry<Field, OutputField> entry : getOutput().getApplicationFields().entrySet()) {
          Field field = entry.getKey();
          OutputField outputField = entry.getValue();
          if (outputField instanceof StringField) {
            AbstractStringField f = (AbstractStringField) getFieldById("application_" + field.getId());
            getOutput().getApplicationFields().get(field).setValue(f.getValue());
          }
          else if (outputField instanceof FileField) {
            AbstractFileChooserField f = (AbstractFileChooserField) getFieldById("application_" + field.getId());
            getOutput().getApplicationFields().get(field).setValue(f.getValue());
          }
        }

        for (Entry<Field, Field> entry : getOutput().getMappedFields().entrySet()) {

          Field field = entry.getKey();
          AbstractSmartField<Field> f = (AbstractSmartField<Field>) getFieldById("mapping_" + field.getId());
          Field mappedField = f.getValue();
          entry.setValue(mappedField);
        }

        //item infos
        for (ItemType item : getOutput().getItemsTypes()) {
          AbstractStringField name = (AbstractStringField) getFieldById("item_id_" + item.getId());
          AbstractFileChooserField item_list_view_file = (AbstractFileChooserField) getFieldById("item_list_view_" + item.getId());
          AbstractFileChooserField item_view_file = (AbstractFileChooserField) getFieldById("item_view_" + item.getId());
          item.setName(name.getValue());
          item.setItemlist_file_path(item_list_view_file.getValue());
          item.setItemlist_file_path(item_view_file.getValue());
        }
      }
    }

    @Order(30.0)
    public class DeleteOutputButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("DeleteOutput");
      }
    }
  }

  @Override
  public void startView() throws ProcessingException {
    startInternal(new AndroidOutputApplicationViewForm.ViewHandler());
  }

  public class ViewHandler extends AbstractFormHandler {
    @Override
    protected void execFinally() throws ProcessingException {
      for (Entry<Field, Field> entry : getOutput().getMappedFields().entrySet()) {
        Field field = entry.getKey();
        Field mappedField = entry.getValue();
      }
    }

    @Override
    protected void execLoad() throws ProcessingException {
      //application infos
      for (Entry<Field, OutputField> entry : getOutput().getApplicationFields().entrySet()) {
        Field field = entry.getKey();
        OutputField outputField = entry.getValue();
        if (outputField instanceof StringField) {
          AbstractStringField f = (AbstractStringField) getFieldById("application_" + field.getId());
          if (outputField.getValue() != null) f.setValue(outputField.getValue());
        }
        else if (outputField instanceof FileField) {
          AbstractFileChooserField f = (AbstractFileChooserField) getFieldById("application_" + field.getId());
          if (outputField.getValue() != null) f.setValue(outputField.getValue());
        }
      }

      //mapping infos
      for (Entry<Field, Field> entry : getOutput().getMappedFields().entrySet()) {
        Field field = entry.getKey();
        Field mappedField = entry.getValue();
        AbstractSmartField<Field> f = (AbstractSmartField<Field>) getFieldById("mapping_" + field.getId());
        if (mappedField != null) f.setValue(mappedField);
      }

      //item infos
      for (ItemType item : getOutput().getItemsTypes()) {
        AbstractStringField name = (AbstractStringField) getFieldById("item_id_" + item.getId());
        AbstractFileChooserField item_list_view_file = (AbstractFileChooserField) getFieldById("item_list_view_" + item.getId());
        AbstractFileChooserField item_view_file = (AbstractFileChooserField) getFieldById("item_view_" + item.getId());
        if (item.getName() != null) name.setValue(item.getName());
        if (item.getItemlist_file_path() != null) {
          item_list_view_file.setValue(item.getItemlist_file_path());
        }
        if (item.getItemview_file_path() != null) {
          item_view_file.setValue(item.getItemview_file_path());
        }
      }
    }

  }
}
