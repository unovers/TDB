/**
 * Nom du fichier         : FieldViewform.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date derni�re r�vision : 30.07.2014
 *
 * Commentaires           : D�finit un formulaire affichant la liste des champs d'un projet
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.forms.views;

import java.util.List;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.FieldInputForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.FieldsViewForm.MainBox.FieldsBox;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.FieldsViewForm.MainBox.FieldsBox.DatasetMappingBox;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.FieldsViewForm.MainBox.FieldsBox.DatasetMappingBox.OkButton;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.FieldsViewForm.MainBox.FieldsBox.FieldsListBox;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.FieldsViewForm.MainBox.FieldsBox.FieldsListBox.AddFieldButton;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDataset;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;

/**
 * Define an view for the fields in a project
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class FieldsViewForm extends AbstractViewForm {

  private Project project;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public FieldsViewForm(Project project) throws ProcessingException {
    super(false);
    this.project = project;
    callInitializer();
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

  @Override
  protected boolean getConfiguredModal() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return project.getName() + " - " + TEXTS.get("Fields");
  }

  /**
   * @return the AddFieldButton
   */
  public AddFieldButton getAddFieldButton() {
    return getFieldByClass(AddFieldButton.class);
  }

  /**
   * @return the DatasetMappingBox
   */
  public DatasetMappingBox getDatasetMappingBox() {
    return getFieldByClass(DatasetMappingBox.class);
  }

  /**
   * @return the FieldsBox
   */
  public FieldsBox getFieldsBox() {
    return getFieldByClass(FieldsBox.class);
  }

  /**
   * @return the OkButton
   */
  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  /**
   * @return the FieldsListBox
   */
  public FieldsListBox getFieldsListBox() {
    return getFieldByClass(FieldsListBox.class);
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
    protected boolean getConfiguredLabelVisible() {
      return false;
    }

    @Override
    protected boolean getConfiguredScrollable() {
      return true;
    }

    @Order(20.0)
    public class FieldsBox extends AbstractTabBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Fields");
      }

      @Order(10.0)
      public class FieldsListBox extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Fields");
        }

        @Override
        protected void injectFieldsInternal(List<IFormField> fieldList) {
          //Cr�er tous les champs
          for (Field field : project.getFields()) {
            fieldList.add(new AbstractGroupBox() {
              @Override
              protected String getConfiguredLabel() {
                return "";
              }

              @Override
              protected int getConfiguredGridColumnCount() {
                return 3;
              }

              @Override
              protected int getConfiguredGridW() {
                return 3;
              }

              @Override
              protected void injectFieldsInternal(List<IFormField> fieldList2) {

                fieldList2.add(new AbstractLabelField() {

                  @Override
                  protected String getConfiguredLabel() {
                    return TEXTS.get("Name");
                  }

                  @Override
                  public String getFieldId() {
                    return field.getId();
                  }
                });

                if (!field.getName().equals("__item_type")) {
                  fieldList2.add(new AbstractButton() {

                    @Override
                    protected String getConfiguredLabel() {
                      return TEXTS.get("Edit");
                    }

                    @Override
                    protected void execClickAction() throws ProcessingException {
                      FieldInputForm form = new FieldInputForm(project);
                      form.setField(field);
                      form.startModify();
                    }

                  });
                  fieldList2.add(new AbstractButton() {

                    @Override
                    protected String getConfiguredLabel() {
                      return TEXTS.get("Delete");
                    }

                    @Override
                    protected void execClickAction() throws ProcessingException {
                      project.removeField(field);
                    }
                  });
                }
              }
            }
                );
          }
        }

        @Order(30.0)
        public class AddFieldButton extends AbstractButton {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("AddField");
          }

          @Override
          protected void execClickAction() throws ProcessingException {
            new FieldInputForm(project).startNew();
          }

        }
      }

      @Order(20.0)
      public class DatasetMappingBox extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DatasetMapping");
        }

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Override
        protected int getConfiguredGridColumnCount() {
          return 1;
        }

        @Override
        protected void injectFieldsInternal(List<IFormField> fieldList) {
          for (AbstractDatasource datasource : project.getDatasources()) {
            for (AbstractDataset dataset : datasource.getDatasets()) {
              fieldList.add(new AbstractGroupBox() {

                @Override
                protected String getConfiguredLabel() {
                  return dataset.getName();
                }

                @Override
                protected void injectFieldsInternal(List<IFormField> fieldList2) {
                  for (Field field : project.getFields()) {
                    fieldList2.add(new AbstractStringField() {
                      @Override
                      protected String getConfiguredLabel() {
                        return field.getName();
                      }

                      @Override
                      public String getFieldId() {
                        return dataset.getId() + "_" + field.getId();
                      }
                    });
                  }
                }
              });
            }
          }
        }

        @Order(10.0)
        public class OkButton extends AbstractButton {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Ok");
          }

          @Override
          protected void execClickAction() throws ProcessingException {
            for (AbstractDatasource datasource : project.getDatasources()) {
              for (AbstractDataset dataset : datasource.getDatasets()) {
                for (Field field : project.getFields()) {
                  IFormField f = getFieldById(dataset.getId() + "_" + field.getId());
                  project.setMapping(field, dataset, ((AbstractStringField) f).getValue());
                }
              }
            }
            Desktop.loadOrRefreshFormFields(project, new FieldsViewForm(project));
          }
        }
      }
    }

  }

  @Override
  public void startView() throws ProcessingException {
    startInternal(new FieldsViewForm.ViewHandler());
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      for (Field field : project.getFields()) {
        IFormField f = getFieldById(field.getId());
        ((AbstractLabelField) f).setValue(field.getName());
      }

      for (AbstractDatasource datasource : project.getDatasources()) {
        for (AbstractDataset dataset : datasource.getDatasets()) {
          for (Field field : project.getFields()) {
            IFormField f = getFieldById(dataset.getId() + "_" + field.getId());
            ((AbstractStringField) f).setValue(project.getMapping(field, dataset));
          }
        }
      }
    }
  }
}
