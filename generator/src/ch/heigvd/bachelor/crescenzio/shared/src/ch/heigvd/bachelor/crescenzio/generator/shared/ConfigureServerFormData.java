/**
 * 
 */
package ch.heigvd.bachelor.crescenzio.generator.shared;

import java.util.Map;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;

import ch.heigvd.bachelor.crescenzio.generator.client.services.lookup.ServerTypeLookupCall;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 * 
 * @generated
 */
@Generated(value = "org.eclipse.scout.sdk.workspace.dto.formdata.FormDataDtoUpdateOperation", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class ConfigureServerFormData extends AbstractFormData {

  private static final long serialVersionUID = 1L;

  public ConfigureServerFormData() {
  }

  public Hostname getHostname() {
    return getFieldByClass(Hostname.class);
  }

  public RootFolder getRootFolder() {
    return getFieldByClass(RootFolder.class);
  }

  public ServerTypeSmart getServerTypeSmart() {
    return getFieldByClass(ServerTypeSmart.class);
  }

  public static class Hostname extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;

    public Hostname() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MANDATORY, true);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }

  public static class RootFolder extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;

    public RootFolder() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MANDATORY, true);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }

  public static class ServerTypeSmart extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;

    public ServerTypeSmart() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.LOOKUP_CALL, ServerTypeLookupCall.class);
      ruleMap.put(ValidationRule.MANDATORY, true);
      ruleMap.put(ValidationRule.ZERO_NULL_EQUALITY, true);
    }
  }
}
