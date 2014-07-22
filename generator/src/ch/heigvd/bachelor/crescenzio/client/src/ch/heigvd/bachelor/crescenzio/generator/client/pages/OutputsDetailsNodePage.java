/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.outputs.AbstractOutputApplication;

/**
 * @author Fabio
 */
public class OutputsDetailsNodePage extends AbstractPageWithNodes {

  private Project project;

  /**
   * @param project
   */
  public OutputsDetailsNodePage(Project project) {
    this.project = project;
    setInitialExpanded(true);
  }

  @Override
  protected boolean getConfiguredExpanded() {
    return true;
  }

  @Override
  protected boolean getConfiguredLeaf() {
    return true;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Outputs");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    for (AbstractOutputApplication output : project.getOutputs()) {
      try {
        String pckage = "ch.heigvd.bachelor.crescenzio.generator.client.pages";
        String clss = pckage + "." + output.getClass().getSimpleName() + "Page";
        Class outputClass = Class.forName(clss);
        java.lang.reflect.Constructor constructor = outputClass.getConstructor(new Class[]{Project.class, output.getClass()});
        AbstractOutputApplicationPage page = (AbstractOutputApplicationPage) constructor.newInstance(new Object[]{project, output});
        pageList.add(page);
      }
      catch (Exception e) {
        throw new ProcessingException(e.toString());
      }
    }
  }
}
