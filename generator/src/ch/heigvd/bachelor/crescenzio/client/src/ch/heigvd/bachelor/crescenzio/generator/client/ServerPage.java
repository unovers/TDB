/**
 * 
 */
package ch.heigvd.bachelor.crescenzio.generator.client;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPage;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.commons.annotations.FormData;

/**
 * @author Fabio
 */
public class ServerPage extends AbstractPage {

  private Integer m_projectNr;

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Server");
  }

  /**
   * @return the ProjectNr
   */
  @FormData
  public Integer getProjectNr() {
    return m_projectNr;
  }

  /**
   * @param projectNr
   *          the ProjectNr to set
   */
  @FormData
  public void setProjectNr(Integer projectNr) {
    m_projectNr = projectNr;
  }
}
