/**
 * Nom du fichier         : Desktop.java
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
package ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.scout.commons.CollectionUtility;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.ClientSyncJob;
import org.eclipse.scout.rt.client.ui.action.keystroke.AbstractKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.desktop.IDesktop;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutlineViewButton;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.form.ScoutInfoForm;
import org.eclipse.scout.rt.client.ui.form.outline.DefaultOutlineTableForm;
import org.eclipse.scout.rt.client.ui.form.outline.DefaultOutlineTreeForm;
import org.eclipse.scout.rt.client.ui.messagebox.MessageBox;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.extension.client.ui.desktop.AbstractExtensibleDesktop;
import org.eclipse.scout.rt.shared.TEXTS;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ch.heigvd.bachelor.crescenzio.generator.DatasourceType;
import ch.heigvd.bachelor.crescenzio.generator.OutputType;
import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.ProjectXMLLoader;
import ch.heigvd.bachelor.crescenzio.generator.ServerType;
import ch.heigvd.bachelor.crescenzio.generator.client.ClientSession;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.AbstractInputForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.DatasetSelectProjectForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.OutputApplicationTypeForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.ProjectInputForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.inputs.WorkspaceSelectionInputForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.LogsViewForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ProjectViewForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.StartForm;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.Desktop.EditMenu.EditProjectMenu;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.outlines.StandardOutline;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputApplication;
import ch.heigvd.bachelor.crescenzio.generator.server.Server;
import ch.heigvd.bachelor.crescenzio.generator.shared.Icons;

public class Desktop extends AbstractExtensibleDesktop implements IDesktop {

  private static IScoutLogger logger = ScoutLogManager.getLogger(Desktop.class);
  private StartForm startForm;
  private ProjectViewForm projectInfoForm;
  private LogsViewForm logsForm;
  private DefaultOutlineTreeForm treeForm;
  private DefaultOutlineTableForm tableForm;
  private HashMap<Project, ProjectViewForm> projectsInfoForms;
  private String workspace;

  private static HashMap<String, DatasourceType> datasourceTypeList;
  private static HashMap<String, OutputType> outputTypeList;
  private static HashMap<String, ServerType> serverTypeList;
  private static boolean init = false;

  static {
    loadDatas();
  }

  private void loadProjectsInWorkspace() {

    String[] folderContent = new File(workspace).list();
    for (int i = 0; i < folderContent.length; i++) {
      if (new File(workspace + File.separator + folderContent[i]).isDirectory()) {
        try {
          String filePathString = workspace + File.separator + folderContent[i] + File.separator + "project.xml";
          File file = new File(filePathString);
          if (file.exists() && !file.isDirectory()) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            ProjectXMLLoader.loadProject(doc.getDocumentElement());
          }
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }

  private static void loadDatas() {
    if (!init) {
      try {
        datasourceTypeList = new HashMap<String, DatasourceType>();
        outputTypeList = new HashMap<String, OutputType>();
        serverTypeList = new HashMap<String, ServerType>();

        //charge les types de sources de données
        Document document;
        DocumentBuilderFactory factory = null;
        try {
          factory = DocumentBuilderFactory.newInstance();
          DocumentBuilder builder = factory.newDocumentBuilder();
          document = builder.parse(AbstractDatasource.class.getResourceAsStream("datasources.xml"));
          NodeList nList = document.getElementsByTagName("datasources");
          for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
              Element eElement = (Element) nNode;
              datasourceTypeList.put(eElement.getElementsByTagName("name").item(0).getTextContent(),
                  new DatasourceType(eElement.getElementsByTagName("name").item(0).getTextContent(),
                      eElement.getElementsByTagName("displayName").item(0).getTextContent(),
                      eElement.getElementsByTagName("location").item(0).getTextContent()));

            }
          }
        }
        catch (Exception e) {
          throw new ProcessingException("Could not load datasource type");
        }

        //charge les types de serveurs
        try {
          factory = DocumentBuilderFactory.newInstance();
          DocumentBuilder builder = factory.newDocumentBuilder();
          document = builder.parse(Server.class.getResourceAsStream("servers.xml"));
          NodeList nList = document.getElementsByTagName("servers");
          for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
              Element eElement = (Element) nNode;
              serverTypeList.put(eElement.getElementsByTagName("name").item(0).getTextContent(),
                  new ServerType(eElement.getElementsByTagName("name").item(0).getTextContent(),
                      eElement.getElementsByTagName("displayName").item(0).getTextContent(),
                      eElement.getElementsByTagName("location").item(0).getTextContent()));

            }
          }
        }
        catch (Exception e) {
          throw new ProcessingException("Could not load server types");
        }

        //charge les types de sorties d'application
        try {
          factory = DocumentBuilderFactory.newInstance();
          DocumentBuilder builder = factory.newDocumentBuilder();
          document = builder.parse(OutputApplication.class.getResourceAsStream("outputs.xml"));
          NodeList nList = document.getElementsByTagName("outputs");
          for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
              Element eElement = (Element) nNode;
              outputTypeList.put(eElement.getElementsByTagName("name").item(0).getTextContent(),
                  new OutputType(eElement.getElementsByTagName("name").item(0).getTextContent(),
                      eElement.getElementsByTagName("displayName").item(0).getTextContent(),
                      eElement.getElementsByTagName("location").item(0).getTextContent()));
            }
          }
        }
        catch (Exception e) {
          throw new ProcessingException("Could not load outputs type");
        }
      }
      catch (ProcessingException e1) {
        new MessageBox("Error loading", e1.getMessage(), "ok").startMessageBox();
      }
      init = true;
    }
  }

  public static HashMap<String, DatasourceType> getDatasourceTypes() {
    return datasourceTypeList;
  }

  public static HashMap<String, OutputType> getOutputTypes() {
    return outputTypeList;
  }

  public static HashMap<String, ServerType> getServerTypes() {
    return serverTypeList;
  }

  public Desktop() throws ProcessingException {
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

    AbstractInputForm form = new WorkspaceSelectionInputForm();
    form.startNew();
    form.waitFor();
    if (form.isFormStored()) {
      loadProjectsInWorkspace();
      if (Project.getAll() == null || Project.getAll().size() == 0) {
        startForm = new StartForm();
        startForm.startView();
      }
      else {
        initWorkspace();
      }
      refreshWorkspace();
    }
    else {
      ClientSyncJob.getCurrentSession(ClientSession.class).stopSession();
    }

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

          ProjectInputForm form = new ProjectInputForm();
          form.startNew();
        }
      }

      @Order(20.0)
      public class DatasourceMenu extends AbstractExtensibleMenu {

        @Override
        protected Set<? extends IMenuType> getConfiguredMenuTypes() {
          return CollectionUtility.<IMenuType> hashSet();
        }

        @Override
        protected void injectActionNodesInternal(List<IMenu> nodeList) {

          for (Entry<String, DatasourceType> entry : datasourceTypeList.entrySet()) {
            String datasource = entry.getValue().getName() + "Datasource";
            nodeList.add(new AbstractExtensibleMenu() {

              @Override
              protected String getConfiguredKeyStroke() {
                return null;
              }

              @Override
              protected String getConfiguredText() {
                return TEXTS.get(datasource);
              }

              @Override
              protected void execAction() throws ProcessingException {
                try {
                  String pckage = entry.getValue().getLocation();
                  String clss = pckage + "." + datasource + "InputForm";
                  Class<?> datasourceClass = Class.forName(clss);
                  java.lang.reflect.Constructor constructor = datasourceClass.getConstructor(new Class[]{});
                  AbstractInputForm form = (AbstractInputForm) constructor.newInstance();
                  form.startNew();
                }
                catch (Exception e) {
                  throw new ProcessingException(e.toString());
                }
              }
            });
          }
        }

        @Override
        protected String getConfiguredText() {
          return TEXTS.get("Datasource");
        }
      }

      @Order(30.0)
      public class DatasetMenu extends AbstractExtensibleMenu {

        @Override
        protected Set<? extends IMenuType> getConfiguredMenuTypes() {
          return CollectionUtility.<IMenuType> hashSet();
        }

        @Override
        protected String getConfiguredText() {
          return TEXTS.get("dataset");
        }

        @Override
        protected void injectActionNodesInternal(List<IMenu> nodeList) {
          for (Entry<String, DatasourceType> entry : datasourceTypeList.entrySet()) {
            String dataset = entry.getValue().getName() + "Dataset";
            nodeList.add(new AbstractExtensibleMenu() {

              @Override
              protected String getConfiguredKeyStroke() {
                return null;
              }

              @Override
              protected String getConfiguredText() {
                return TEXTS.get(dataset);
              }

              @Override
              protected void execAction() throws ProcessingException {
                new DatasetSelectProjectForm(dataset).startNew();
              }
            });
          }
        }
      }

      @Order(40.0)
      public class OutputMenu extends AbstractExtensibleMenu {

        @Override
        protected Set<? extends IMenuType> getConfiguredMenuTypes() {
          return CollectionUtility.<IMenuType> hashSet();
        }

        @Override
        protected String getConfiguredText() {
          return TEXTS.get("Output");
        }

        @Override
        protected void execAction() throws ProcessingException {
          OutputApplicationTypeForm form = new OutputApplicationTypeForm();
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
      return TEXTS.get("Edit");
    }

    @Override
    protected void execAction() throws ProcessingException {
    }

    @Order(10.0)
    public class EditProjectMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return "";
      }

      @Override
      protected void execAction() throws ProcessingException {
        ProjectInputForm form = new ProjectInputForm();
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

  public void refreshWorkspace() throws ProcessingException {
    if (treeForm != null && !treeForm.isShowing()) {
      treeForm.startView();
    }
    if (getOutline() != null) getOutline().resetOutline();
  }

  public void displayProjectInfo(Project project) throws ProcessingException {
    if (projectsInfoForms.get(project) != null) {
      projectsInfoForms.get(project).doClose();
      projectsInfoForms.remove(project);
    }

    projectsInfoForms.put(project, new ProjectViewForm(project));
  }

  /**
   * @throws ProcessingException
   */
  public void closeStartForm() throws ProcessingException {
    if (startForm != null) startForm.doClose();
  }

  /**
   * @throws ProcessingException
   */
  public void initWorkspace() throws ProcessingException {

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

  }

  /**
   */
  public void setWorkspace(String workspace) {
    this.workspace = workspace;
  }

  /**
   * @return
   */
  public String getWorkspace() {
    return workspace;
  }
}
