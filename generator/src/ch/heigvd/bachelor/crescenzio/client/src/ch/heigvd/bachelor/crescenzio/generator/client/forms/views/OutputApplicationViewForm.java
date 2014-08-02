/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.views;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
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
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
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

  @Override
  protected String getConfiguredTitle() {
    return output.getProject().getName() + " - " + output.getName();
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
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
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
                protected boolean getConfiguredMandatory() {
                  return true;
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
                protected boolean getConfiguredMandatory() {
                  return true;
                }

                @Override
                protected String execValidateValue(String rawValue) throws ProcessingException {
                  File f = new File(rawValue);
                  if (!f.exists() && f.isDirectory()) {
                    throw new VetoException("Invalid input", "fichier no existant", 267, IStatus.ERROR);
                  }
                  return rawValue;
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
                if (item.getName().equals("Default")) {
                  fieldList2.add(new AbstractLabelField() {
                    @Override
                    protected String getConfiguredLabel() {
                      return TEXTS.get("Name");
                    }

                    @Override
                    protected boolean getConfiguredMandatory() {
                      return true;
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
                    protected boolean getConfiguredMandatory() {
                      return true;
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
                    protected boolean getConfiguredMandatory() {
                      return true;
                    }

                    @Override
                    protected String execValidateValue(String rawValue) throws ProcessingException {
                      File f = new File(rawValue);
                      if (!f.exists() && f.isDirectory()) {
                        throw new VetoException("Invalid input", "fichier no existant", 267, IStatus.ERROR);
                      }
                      return rawValue;
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

                if (!item.getName().equals("Default")) {
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
    public class SaveChangesButton extends AbstractOkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("SaveChanges");
      }

    }

    @Order(30.0)
    public class DeleteOutputButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("DeleteOutput");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        Desktop.removeOutput(output);
        ((Desktop) getDesktop()).refreshWorkspace();
        project.removeOutput(output);
      }
    }
  }

  public void startView() throws ProcessingException {
    startInternal(new ViewHandler());
  }

  public class ViewHandler extends AbstractFormHandler {
    @SuppressWarnings("unchecked")
    @Override
    protected void execLoad() throws ProcessingException {
      String workspace = ((Desktop) getDesktop()).getWorkspace();
      String project_path = workspace + File.separator + project.getName();
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

          //S'assure que le répertoire soit correct
          String old_value = (project_path + File.separator + output.getName() + File.separator + outputField.getValue()).replace("/", File.separator).replace("\\", File.separator);
          f.setValue(old_value);
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
      for (ItemType itemType : getOutput().getItemsTypes()) {
        if (!itemType.getName().equals("Default")) {
          AbstractStringField name = (AbstractStringField) getFieldById("item_id_" + itemType.getId());
          name.setValue(itemType.getName());
        }
        else {
          AbstractLabelField name = (AbstractLabelField) getFieldById("item_id_" + itemType.getId());
          name.setValue(itemType.getName());
        }
        for (FileResource resource : itemType.getResources()) {
          AbstractFileChooserField itemResourceFile = (AbstractFileChooserField) getFieldById("itemResourceFile_" + resource.getId() + "_" + itemType.getId());
          //S'assure que le répertoire soit correct
          String old_value = (project_path + File.separator + output.getName() + File.separator + resource.getValue()).replace("/", File.separator).replace("\\", File.separator);

          itemResourceFile.setValue(old_value);
        }
      }
    }

    @Override
    protected void execStore() throws ProcessingException {

      String workspace = ((Desktop) getDesktop()).getWorkspace();
      String project_path = workspace + File.separator + project.getName();
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

          String path = project_path + File.separator + output.getName();
          String folder = "resources" + File.separator + "application" + File.separator + field.getName();
          new File(path + File.separator + folder).mkdirs();
          if (!f.getValue().equals(outputField.getValue())) {
            File new_file = new File(f.getValue());
            try {
              Files.copy(new File(f.getValue()).toPath(),
                  new File(path + File.separator + folder + File.separator + new_file.getName()).toPath(),
                  StandardCopyOption.REPLACE_EXISTING);
            }
            catch (IOException e) {
              throw new ProcessingException(e.toString());
            }
            getOutput().getApplicationFields().get(field).setValue(folder + File.separator + new_file.getName());
          }
        }
      }

      for (Entry<Field, Field> entry : getOutput().getMappedFields().entrySet()) {

        Field field = entry.getKey();
        AbstractSmartField<Field> f = (AbstractSmartField<Field>) getFieldById("mapping_" + field.getId());
        Field mappedField = f.getValue();
        entry.setValue(mappedField);
      }

      //item infos
      for (ItemType itemType : getOutput().getItemsTypes()) {
        if (!itemType.getName().equals("Default")) {
          AbstractStringField name = (AbstractStringField) getFieldById("item_id_" + itemType.getId());
          itemType.setName(name.getValue());
        }
        for (FileResource resource : itemType.getResources()) {
          AbstractFileChooserField itemResourceFile = (AbstractFileChooserField) getFieldById("itemResourceFile_" + resource.getId() + "_" + itemType.getId());

          //S'assure que le répertoire soit correct
          String old_value = (project_path + File.separator + output.getName() + File.separator + resource.getValue()).replace("/", File.separator).replace("\\", File.separator);

          String new_value = itemResourceFile.getValue();
          String type_path = "resources" + File.separator + "types" + File.separator + itemType.getName();
          new File(project_path + File.separator + output.getName() + File.separator + type_path).mkdirs();

          if (!old_value.equals(new_value)) {

            //Supprime l'ancienne valeur
            File file = new File(old_value);
            if (file.exists()) {
              file.delete();
            }
            //Copie la nouvelle valeur
            try {
              Files.copy(new File(new_value).toPath(),
                  new File(project_path + File.separator + output.getName() + File.separator + type_path + File.separator + new File(new_value).getName()).toPath(),
                  StandardCopyOption.REPLACE_EXISTING);
            }
            catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
          resource.setValue(type_path + File.separator + new File(new_value).getName());
        }
      }
      Desktop.loadOrRefreshFormOutput(getOutput(), new OutputApplicationViewForm(project, getOutput()));
    }
  }
}
