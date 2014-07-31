/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.views;

import java.util.List;
import java.util.Map.Entry;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.filechooserfield.AbstractFileChooserField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ItemTypeInputForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.OutputApplicationViewForm.MainBox.DeleteOutputButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.OutputApplicationViewForm.MainBox.OutputsSectionBox;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.OutputApplicationViewForm.MainBox.OutputsSectionBox.ItemsTypesBox;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.OutputApplicationViewForm.MainBox.OutputsSectionBox.ItemsTypesBox.AddItemTypeButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.OutputApplicationViewForm.MainBox.OutputsSectionBox.OutputsDetailsBox;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.OutputApplicationViewForm.MainBox.SaveChangesButton;
import ch.heigvd.bachelor.crescenzio.generator.client.services.lookup.FieldsOutputMappingLookupCall;
import ch.heigvd.bachelor.crescenzio.generator.outputs.FileField;
import ch.heigvd.bachelor.crescenzio.generator.outputs.FileResource;
import ch.heigvd.bachelor.crescenzio.generator.outputs.ItemType;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputApplication;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputField;
import ch.heigvd.bachelor.crescenzio.generator.outputs.StringField;

/**
 * @author Fabio
 */
public class OutputApplicationViewForm extends AbstractForm {

  private OutputApplication output;
  private Project project;

  public OutputApplicationViewForm(Project project, OutputApplication output) throws ProcessingException {
    super(false);
    this.output = output;
    this.project = project;
    callInitializer();
  }

  /**
   * @return the output
   */
  public OutputApplication getOutput() {
    return output;
  }

  /**
   * @param output
   *          the output to set
   */
  public void setOutput(OutputApplication output) {
    this.output = output;
  }

  /**
   * @return the project
   */
  public Project getProject() {
    return project;
  }

  /**
   * @param project
   *          the project to set
   */
  public void setProject(Project project) {
    this.project = project;
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
                protected boolean getConfiguredShowDirectory() {
                  return true;
                }

                @Override
                protected boolean getConfiguredTypeLoad() {
                  return true;
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
              protected void injectFieldsInternal(List<IFormField> fieldList2) {
                if (item.getName().equals("__default")) {
                  fieldList2.add(new AbstractLabelField() {
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
                }
                else {
                  fieldList2.add(new AbstractStringField() {
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
                }

                for (FileResource resource : item.getResources()) {
                  fieldList2.add(new AbstractFileChooserField() {
                    @Override
                    protected String getConfiguredLabel() {
                      return resource.getName();
                    }

                    @Override
                    protected boolean getConfiguredShowDirectory() {
                      return true;
                    }

                    @Override
                    protected boolean getConfiguredTypeLoad() {
                      return true;
                    }

                    @Override
                    public String getTooltipText() {
                      return resource.getDescription();
                    }

                    @Override
                    public String getFieldId() {
                      // TODO Auto-generated method stub
                      return "itemResourceFile_" + resource.getId() + "_" + item.getId();
                    }
                  });
                }

                if (!item.getName().equals("__default")) {
                  fieldList2.add(new AbstractButton() {
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

          @Override
          protected void execClickAction() throws ProcessingException {
            new ItemTypeInputForm(output).startNew();
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

      @SuppressWarnings("unchecked")
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
          if (!item.getName().equals("__default")) {
            AbstractStringField name = (AbstractStringField) getFieldById("item_id_" + item.getId());
            item.setName(name.getValue());
          }
          for (FileResource resource : item.getResources()) {
            AbstractFileChooserField itemResourceFile = (AbstractFileChooserField) getFieldById("itemResourceFile_" + resource.getId() + "_" + item.getId());
            resource.setValue(itemResourceFile.getValue());
          }
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

  public void startView() throws ProcessingException {
    startInternal(new ViewHandler());
  }

  public class ViewHandler extends AbstractFormHandler {
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
        if (!item.getName().equals("__default")) {
          AbstractStringField name = (AbstractStringField) getFieldById("item_id_" + item.getId());
          name.setValue(item.getName());
        }
        else {
          AbstractLabelField name = (AbstractLabelField) getFieldById("item_id_" + item.getId());
          name.setValue(item.getName());
        }
        for (FileResource resource : item.getResources()) {
          AbstractFileChooserField itemResourceFile = (AbstractFileChooserField) getFieldById("itemResourceFile_" + resource.getId() + "_" + item.getId());
          itemResourceFile.setValue(resource.getValue());
        }
      }
    }
  }
}
