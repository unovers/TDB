package ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.scout.commons.CollectionUtility;
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
import org.eclipse.scout.rt.client.ui.form.outline.DefaultOutlineTableForm;
import org.eclipse.scout.rt.client.ui.form.outline.DefaultOutlineTreeForm;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.extension.client.ui.desktop.AbstractExtensibleDesktop;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.client.ClientSession;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.MultipleForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ProjectForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.LogsForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ProjectViewForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.StartForm;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop.EditMenu.EditProjectMenu;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.outlines.StandardOutline;
import ch.heigvd.bachelor.crescenzio.generator.shared.Icons;

public class Desktop extends AbstractExtensibleDesktop implements IDesktop {
  private static IScoutLogger logger = ScoutLogManager.getLogger(Desktop.class);
  private static Project project;
  private LogsForm logs;
  private StartForm startForm;
  private ProjectViewForm projectInfoForm;
  private LogsForm logsForm;
  private LinkedList<Project> projects;
  private DefaultOutlineTreeForm treeForm;
  private DefaultOutlineTableForm tableForm;
  private HashMap<Project, ProjectViewForm> projectsInfoForms;

  public Desktop() {
    projects = new LinkedList<Project>();
    projectsInfoForms = new HashMap<Project, ProjectViewForm>();
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
        MultipleForm form = new MultipleForm(10);
        form.startModify();
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

  public LinkedList<Project> getProjects() {
    return projects;
  }

  public void refreshWorkspace() throws ProcessingException {
    if (treeForm != null && !treeForm.isShowing()) {
      treeForm.startView();
    }
    if (getOutline() != null) getOutline().resetOutline();
  }

  /**
   * @param string
   */
  public void Log(String string) {
    logsForm.getLogsField().setValue(string);
  }

  /**
   * @param logsForm
   */
  public void setBottomForm(LogsForm logsForm) {
    this.logsForm = logsForm;
  }

  public void displayProjectInfo(Project project) throws ProcessingException {
    if (projectsInfoForms.get(project) != null) {
      projectsInfoForms.get(project).doClose();
      projectsInfoForms.remove(project);
    }

    projectsInfoForms.put(project, new ProjectViewForm(project));
  }

  public void createProject(Project project) {
    projects.add(project);
  }

  public void removeProject(Project project) {
    projects.remove(project);
  }

  /**
   * @throws ProcessingException
   */
  public void closeStartForm() throws ProcessingException {
    startForm.doClose();
  }

  /**
   * @throws ProcessingException
   */
  public void startViews() throws ProcessingException {

    // outline tree
    if (treeForm == null) {
      treeForm = new DefaultOutlineTreeForm();
      treeForm.setIconId(Icons.EclipseScout);
      treeForm.startView();
    }

    //outline table
    if (tableForm == null) {
      tableForm = new DefaultOutlineTableForm();
      tableForm.setIconId(Icons.EclipseScout);
      tableForm.startView();
    }

    IOutline firstOutline = CollectionUtility.firstElement(getAvailableOutlines());
    if (firstOutline != null) {
      setOutline(firstOutline);
    }

    LogsForm logsForm = new LogsForm();
    setBottomForm(logsForm);
    logsForm.startModify();

  }
}
