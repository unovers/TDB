/**
 * 
 */
package ch.heigvd.bachelor.crescenzio.generator.shared.forms.inputs;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 * 
 * @generated
 */
@Generated(value = "org.eclipse.scout.sdk.workspace.dto.formdata.FormDataDtoUpdateOperation", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class DatasetInputFormData extends AbstractFormData {

  private static final long serialVersionUID = 1L;

  public DatasetInputFormData() {
  }

  /**
   * access method for property DatasetInputNr.
   */
  public Long getDatasetInputNr() {
    return getDatasetInputNrProperty().getValue();
  }

  /**
   * access method for property DatasetInputNr.
   */
  public void setDatasetInputNr(Long datasetInputNr) {
    getDatasetInputNrProperty().setValue(datasetInputNr);
  }

  public DatasetInputNrProperty getDatasetInputNrProperty() {
    return getPropertyByClass(DatasetInputNrProperty.class);
  }

  public static class DatasetInputNrProperty extends AbstractPropertyData<Long> {

    private static final long serialVersionUID = 1L;

    public DatasetInputNrProperty() {
    }
  }
}