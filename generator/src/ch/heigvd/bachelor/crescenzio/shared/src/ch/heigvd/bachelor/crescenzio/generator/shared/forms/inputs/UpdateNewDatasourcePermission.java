/**
 * 
 */
package ch.heigvd.bachelor.crescenzio.generator.shared.forms.inputs;

import java.security.BasicPermission;

/**
 * @author Fabio
 */
public class UpdateNewDatasourcePermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public UpdateNewDatasourcePermission() {
    super("UpdateNewDatasource");
  }
}
