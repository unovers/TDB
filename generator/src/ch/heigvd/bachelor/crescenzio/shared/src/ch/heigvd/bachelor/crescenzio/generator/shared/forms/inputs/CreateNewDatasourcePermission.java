/**
 * 
 */
package ch.heigvd.bachelor.crescenzio.generator.shared.forms.inputs;

import java.security.BasicPermission;

/**
 * @author Fabio
 */
public class CreateNewDatasourcePermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public CreateNewDatasourcePermission() {
    super("CreateNewDatasource");
  }
}
