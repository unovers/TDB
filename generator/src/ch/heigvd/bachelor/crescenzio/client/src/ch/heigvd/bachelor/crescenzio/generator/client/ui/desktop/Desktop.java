package ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.ClientSyncJob;
import org.eclipse.scout.rt.client.ui.action.keystroke.AbstractKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.desktop.IDesktop;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutlineViewButton;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.form.ScoutInfoForm;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.extension.client.ui.desktop.AbstractExtensibleDesktop;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.ui.UserAgentUtility;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.ClientSession;
import ch.heigvd.bachelor.crescenzio.generator.client.LogsForm;
import ch.heigvd.bachelor.crescenzio.generator.client.ProjectForm;
import ch.heigvd.bachelor.crescenzio.generator.client.ProjectInfoForm;
import ch.heigvd.bachelor.crescenzio.generator.client.StartForm;
import ch.heigvd.bachelor.crescenzio.generator.client.WorkspaceForm;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop.EditMenu.EditProjectMenu;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.outlines.StandardOutline;

public class Desktop extends AbstractExtensibleDesktop implements IDesktop {
  private static IScoutLogger logger = ScoutLogManager.getLogger(Desktop.class);
  private static Project project;
  private LogsForm logs;
  private StartForm startForm;
  private ProjectInfoForm projectInfoForm;
  private WorkspaceForm leftform;
  private LogsForm logsForm;

  public Desktop() {
  }

  @Override
  protected List<Class<? extends IOutline>> getConfiguredOutlines() {
    List<Class<? extends IOutline>> outlines = new ArrayList<Class<? extends IOutline>>();
    outlines.add(StandardOutline.class);
    return outlines;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ApplicationTitle");
  }

  @Override
  protected void execOpened() throws ProcessingException {
    //If it is a mobile or tablet device, the DesktopExtension in the mobile plugin takes care of starting the correct forms.
    if (!UserAgentUtility.isDesktopDevice()) {
      return;
    }

    getMenu(EditProjectMenu.class).setEnabled(false);

    startForm = new StartForm();
    startForm.startModify();

  }

  @Order(10.0)
  public class FileMenu extends AbstractMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("FileMenu");
    }

    @Order(10.0)
    public class NewMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredKeyStroke() {
        return null;
      }

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("New");
      }

      @Order(10.0)
      public class ProjectMenu extends AbstractExtensibleMenu {

        @Override
        protected String getConfiguredKeyStroke() {
          return null;
        }

        @Override
        protected String getConfiguredText() {
          return TEXTS.get("Project");
        }

        @Override
        protected void execAction() throws ProcessingException {

          ProjectForm form = new ProjectForm();
          form.startNew();
        }
      }
    }

    @Order(20.0)
    public class ExitMenu extends AbstractMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("ExitMenu");
      }

      @Override
      public void execAction() throws ProcessingException {
        ClientSyncJob.getCurrentSession(ClientSession.class).stopSession();
      }
    }
  }

  @Order(60.0)
  public class ToolsMenu extends AbstractMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ToolsMenu");
    }

    @Order(10.0)
    public class AfficherDetailsProjetMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("AfficherDetailsProjet");
      }

      @Override
      protected void execAction() throws ProcessingException {
        displayProjectInfo();
      }
    }
  }

  @Order(100.0)
  public class HelpMenu extends AbstractMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("HelpMenu");
    }

    @Order(10.0)
    public class AboutMenu extends AbstractMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("AboutMenu");
      }

      @Override
      public void execAction() throws ProcessingException {
        ScoutInfoForm form = new ScoutInfoForm();
        form.startModify();
      }
    }
  }

  @Order(10.0)
  public class RefreshOutlineKeyStroke extends AbstractKeyStroke {

    @Override
    protected String getConfiguredKeyStroke() {
      return "f5";
    }

    @Override
    protected void execAction() throws ProcessingException {
      if (getOutline() != null) {
        IPage page = getOutline().getActivePage();
        if (page != null) {
          page.reloadPage();
        }
      }
    }
  }

  @Order(40.0)
  public class EditMenu extends AbstractExtensibleMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Edition");
    }

    @Override
    protected void execAction() throws ProcessingException {
    }

    @Order(10.0)
    public class EditProjectMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("EditProject");
      }

      @Override
      protected void execAction() throws ProcessingException {
        ProjectForm form = new ProjectForm();
        form.startModify();
      }
    }
  }

  @Order(10.0)
  public class StandardOutlineViewButton extends AbstractOutlineViewButton {

    /**
     *
     */
    public StandardOutlineViewButton() {
      super(Desktop.this, StandardOutline.class);
    }
  }

  /**
   * @param project2
   */
  public void setProject(Project project) {
    this.project = project;
  }

  public Project getProject() {
    return project;
  }

  /**
   * @param string
   */
  public void Log(String string) {
    logsForm.getLogsField().setValue(string);
  }

  /**
   * @param workspace
   */
  public void setLeftForm(WorkspaceForm workspace) {
    this.leftform = workspace;
  }

  /**
   * @param logsForm
   */
  public void setBottomForm(LogsForm logsForm) {
    this.logsForm = logsForm;
  }

  public void displayProjectInfo() {
    try {
      if (projectInfoForm != null) {
        System.out.println("FERMETURE");
        projectInfoForm.doClose();
      }
    }
    catch (ProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    System.out.println("AFFICHAGE");
    try {
      projectInfoForm = new ProjectInfoForm();
    }
    catch (ProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   *
   */
  public void closeStartForm() {
    try {
      startForm.doClose();
    }
    catch (ProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
}