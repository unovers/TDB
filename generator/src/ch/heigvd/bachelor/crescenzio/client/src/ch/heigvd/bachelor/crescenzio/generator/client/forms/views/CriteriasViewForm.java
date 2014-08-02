/**
 * Nom du fichier         : CriteriasViewForm.java
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
package ch.heigvd.bachelor.crescenzio.generator.client.forms.views;

import java.util.List;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ConditionInputForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.CriteriaInputForm;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop;
import ch.heigvd.bachelor.crescenzio.generator.criterias.Criteria;

/**
 * @author Fabio
 */
public class CriteriasViewForm extends AbstractForm {

  private Project project;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public CriteriasViewForm(Project project) throws ProcessingException {
    super(false);
    this.project = project;
    callInitializer();
  }

  @Override
  protected String getConfiguredTitle() {
    return project.getName() + " - " + TEXTS.get("Criterias");
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

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected void injectFieldsInternal(List<IFormField> fieldList) {
      for (Criteria criteria : project.getCriterias()) {
        fieldList.add(new AbstractGroupBox() {
          @Override
          protected void injectFieldsInternal(List<IFormField> fieldList2) {
            fieldList2.add(new AbstractLabelField() {
              @Override
              protected String getConfiguredLabel() {
                return TEXTS.get("Title");
              }

              @Override
              public String getFieldId() {
                return "critera_" + criteria.getId();
              }
            });

            fieldList2.add(new AbstractGroupBox() {

              @Override
              protected String getConfiguredLabel() {
                return TEXTS.get("Conditions");
              }

              @Override
              protected void injectFieldsInternal(List<IFormField> fieldList3) {
                for (Field condition : criteria.getConditions()) {
                  fieldList3.add(new AbstractLabelField() {
                    @Override
                    protected String getConfiguredLabel() {
                      return TEXTS.get("Field");
                    }

                    @Override
                    public String getFieldId() {
                      return condition.getId();
                    }
                  });
                }
              }

            });

            fieldList2.add(new AbstractButton() {
              @Override
              protected String getConfiguredLabel() {
                // TODO Auto-generated method stub
                return TEXTS.get("AddCondition");
              }

              @Override
              protected void execClickAction() throws ProcessingException {
                new ConditionInputForm(project, criteria).startNew();
              }
            });

            fieldList2.add(new AbstractButton() {
              @Override
              protected String getConfiguredLabel() {
                return TEXTS.get("Edit");
              }

              @Override
              protected void execClickAction() throws ProcessingException {
                new CriteriaInputForm(project, criteria).startModify();
              }

            });
            fieldList2.add(new AbstractButton() {
              @Override
              protected String getConfiguredLabel() {
                return TEXTS.get("Delete");
              }

              @Override
              protected void execClickAction() throws ProcessingException {
                project.removeCriteria(criteria);
                Desktop.loadOrRefreshFormCriterias(project, new CriteriasViewForm(project));
              }
            });
          }

        });
      }
    }

    @Override
    protected boolean getConfiguredLabelVisible() {
      return false;
    }

    @Order(20.0)
    public class AddFieldButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("AddCriteria");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        new CriteriaInputForm(project).startNew();
      }

    }
  }

  public void startView() throws ProcessingException {
    startInternal(new CriteriasViewForm.ViewHandler());
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      for (Criteria criteria : project.getCriterias()) {
        AbstractLabelField f = (AbstractLabelField) getFieldById("critera_" + criteria.getId());
        f.setValue(criteria.getTitle());
        for (Field condition : criteria.getConditions()) {
          AbstractLabelField f2 = (AbstractLabelField) getFieldById(condition.getId());
          f2.setValue(condition.getName());
        }
      }

    }
  }
}
