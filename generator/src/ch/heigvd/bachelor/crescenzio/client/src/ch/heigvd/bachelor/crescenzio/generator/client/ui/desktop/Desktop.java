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
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.extension.client.ui.desktop.AbstractExtensibleDesktop;
import org.eclipse.scout.rt.shared.TEXTS;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.CriteriasViewForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.FieldsViewForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.OutputApplicationViewForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ProjectViewForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.ServerViewForm;
import ch.heigvd.bachelor.crescenzio.generator.client.forms.views.StartForm;
import ch.heigvd.bachelor.crescenzio.generator.client.ui.desktop.outlines.StandardOutline;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDataset;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasetViewForm;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasourceViewForm;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputApplication;
import ch.heigvd.bachelor.crescenzio.generator.server.AbstractServer;
import ch.heigvd.bachelor.crescenzio.generator.shared.Icons;
import ch.heigvd.bachelor.crescenzio.generator.utils.Utils;

public class Desktop extends AbstractExtensibleDesktop implements IDesktop {

  private static IScoutLogger logger = ScoutLogManager.getLogger(Desktop.class);
  private StartForm startForm;
  private ProjectViewForm projectInfoForm;
  private DefaultOutlineTreeForm treeForm;
  private DefaultOutlineTableForm tableForm;

  private static HashMap<Project, ProjectViewForm> projectViewForms;
  private static HashMap<AbstractDataset, AbstractDatasetViewForm> datasetViewForms;
  private static HashMap<AbstractDatasource, AbstractDatasourceViewForm> datasourceViewForms;
  private static HashMap<OutputApplication, OutputApplicationViewForm> outputViewForms;
  private static HashMap<Project, ServerViewForm> serverViewForms;
  private static HashMap<Project, FieldsViewForm> fieldsViewForms;
  private static HashMap<Project, CriteriasViewForm> criteriasViewForms;

  private String workspace;

  private static HashMap<String, DatasourceType> datasourceType;
  private static HashMap<String, OutputType> outputType;
  private static HashMap<String, ServerType> serverTypes;
  private static boolean init = false;

  static {
    loadDatas();

    projectViewForms = new HashMap<Project, ProjectViewForm>();
    datasourceViewForms = new HashMap<AbstractDatasource, AbstractDatasourceViewForm>();
    datasetViewForms = new HashMap<AbstractDataset, AbstractDatasetViewForm>();
    fieldsViewForms = new HashMap<Project, FieldsViewForm>();
    outputViewForms = new HashMap<OutputApplication, OutputApplicationViewForm>();
    criteriasViewForms = new HashMap<Project, CriteriasViewForm>();
    serverViewForms = new HashMap<Project, ServerViewForm>();
  }

  public Desktop() {
  }

  public static void loadOrRefreshFormProject(Project project, ProjectViewForm form) throws ProcessingException {
    if (projectViewForms.get(project) != null && projectViewForms.get(project).isFormOpen()) {
      projectViewForms.remove(project).doClose();
    }
    projectViewForms.put(project, form);
    form.startView();
  }

  public static void removeDataset(AbstractDataset dataset) throws ProcessingException {
    if (datasetViewForms.get(dataset) != null && datasetViewForms.get(dataset).isFormOpen()) {
      datasetViewForms.remove(dataset).doClose();
    }
  }

  public static void removeOutput(OutputApplication output) throws ProcessingException {
    if (outputViewForms.get(output) != null && outputViewForms.get(output).isFormOpen()) {
      outputViewForms.remove(output).doClose();
    }
  }

  public static void removeDatasource(AbstractDatasource datasource) throws ProcessingException {
    if (datasourceViewForms.get(datasource) != null && datasourceViewForms.get(datasource).isFormOpen()) {
      datasourceViewForms.remove(datasource).doClose();
    }
  }

  public static void loadOrRefreshFormDatasource(AbstractDatasource datasource, AbstractDatasourceViewForm form) throws ProcessingException {
    if (datasourceViewForms.get(datasource) != null && datasourceViewForms.get(datasource).isFormOpen()) {
      datasourceViewForms.remove(datasource).doClose();
    }
    datasourceViewForms.put(datasource, form);
    form.startView();
  }

  public static void loadOrRefreshFormDataset(AbstractDataset dataset, AbstractDatasetViewForm form) throws ProcessingException {
    if (datasetViewForms.get(dataset) != null && datasetViewForms.get(dataset).isFormOpen()) {
      datasetViewForms.remove(dataset).doClose();
    }
    datasetViewForms.put(dataset, form);
    form.startView();
  }

  public static void loadOrRefreshFormOutput(OutputApplication output, OutputApplicationViewForm form) throws ProcessingException {
    if (outputViewForms.get(output) != null && outputViewForms.get(output).isFormOpen()) {
      outputViewForms.remove(output).doClose();
    }
    outputViewForms.put(output, form);
    form.startView();
  }

  public static void loadOrRefreshFormFields(Project project, FieldsViewForm form) throws ProcessingException {
    if (fieldsViewForms.get(project) != null && fieldsViewForms.get(project).isFormOpen()) {
      fieldsViewForms.remove(project).doClose();
    }
    fieldsViewForms.put(project, form);
    form.startView();
  }

  public static void loadOrRefreshFormCriterias(Project project, CriteriasViewForm form) throws ProcessingException {
    if (criteriasViewForms.get(project) != null && criteriasViewForms.get(project).isFormOpen()) {
      criteriasViewForms.remove(project).doClose();
    }
    criteriasViewForms.put(project, form);
    form.startView();
  }

  public static void loadOrRefreshFormServer(Project project, ServerViewForm form) throws ProcessingException {
    if (serverViewForms.get(project) != null && serverViewForms.get(project).isFormOpen()) {
      serverViewForms.remove(project).doClose();
    }
    serverViewForms.put(project, form);
    form.startView();
  }

  private void loadProjectsInWorkspace() throws ProcessingException {

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
          throw new ProcessingException(e.getMessage());
        }
      }
    }
  }

  private static void loadDatas() {
    if (!init) {
      try {
        datasourceType = new HashMap<String, DatasourceType>();
        outputType = new HashMap<String, OutputType>();
        serverTypes = new HashMap<String, ServerType>();

        //charge les types de sources de données
        Document document;
        DocumentBuilderFactory factory = null;
        try {
          factory = DocumentBuilderFactory.newInstance();
          DocumentBuilder builder = factory.newDocumentBuilder();
          document = builder.parse(AbstractDatasource.class.getResourceAsStream("datasources.xml"));
          Node nodeDatasources = document.getDocumentElement();
          for (int i = 0; i < nodeDatasources.getChildNodes().getLength(); i++) {
            Node nodeDatasource = nodeDatasources.getChildNodes().item(i);
            if (nodeDatasource.getNodeType() == Node.ELEMENT_NODE) {
              Node nodeDatasourceName = Utils.getDirectChild((Element) nodeDatasource, "name");
              Node nodeDatasourceDisplayName = Utils.getDirectChild((Element) nodeDatasource, "displayName");
              Node nodeDatasourceLocation = Utils.getDirectChild((Element) nodeDatasource, "location");
              datasourceType.put(nodeDatasourceName.getTextContent(),
                  new DatasourceType(nodeDatasourceName.getTextContent(),
                      nodeDatasourceDisplayName.getTextContent(),
                      nodeDatasourceLocation.getTextContent()));

            }
          }
        }
        catch (Exception e) {
          //TODO Log
          throw new ProcessingException(e.getMessage());
        }

        //charge les types de serveurs
        try {
          factory = DocumentBuilderFactory.newInstance();
          DocumentBuilder builder = factory.newDocumentBuilder();
          document = builder.parse(AbstractServer.class.getResourceAsStream("servers.xml"));
          Node nodeServers = document.getDocumentElement();
          for (int i = 0; i < nodeServers.getChildNodes().getLength(); i++) {
            Node nodeServer = nodeServers.getChildNodes().item(i);
            if (nodeServer.getNodeType() == Node.ELEMENT_NODE) {
              Node nodeServerName = Utils.getDirectChild((Element) nodeServer, "name");
              Node nodeServerDisplayName = Utils.getDirectChild((Element) nodeServer, "displayName");
              Node nodeServerLocation = Utils.getDirectChild((Element) nodeServer, "location");
              serverTypes.put(nodeServerName.getTextContent(),
                  new ServerType(nodeServerName.getTextContent(),
                      nodeServerDisplayName.getTextContent(),
                      nodeServerLocation.getTextContent()));

            }
          }
        }
        catch (Exception e) {
          //TODO Log
          throw new ProcessingException(e.getMessage());
        }

        //charge les types de sorties d'application
        try {
          factory = DocumentBuilderFactory.newInstance();
          DocumentBuilder builder = factory.newDocumentBuilder();
          document = builder.parse(OutputApplication.class.getResourceAsStream("outputs.xml"));

          Node nodeOuputs = document.getDocumentElement();
          for (int i = 0; i < nodeOuputs.getChildNodes().getLength(); i++) {
            Node nodeOutput = nodeOuputs.getChildNodes().item(i);
            if (nodeOutput.getNodeType() == Node.ELEMENT_NODE) {
              Node nodeOutputName = Utils.getDirectChild((Element) nodeOutput, "name");
              Node nodeOutputDisplayName = Utils.getDirectChild((Element) nodeOutput, "displayName");
              Node nodeOutputLocation = Utils.getDirectChild((Element) nodeOutput, "location");
              outputType.put(nodeOutputName.getTextContent(),
                  new OutputType(nodeOutputName.getTextContent(),
                      nodeOutputDisplayName.getTextContent(),
                      nodeOutputLocation.getTextContent()));

            }
          }
        }
        catch (Exception e) {
          //TODO Log
          throw new ProcessingException(e.getMessage());
        }
      }
      catch (ProcessingException e) {
        //TODO Log
        System.err.println("Error loading config files - " + e.getMessage());
      }
      init = true;
    }
  }

  public static HashMap<String, DatasourceType> getDatasourceTypes() {
    return datasourceType;
  }

  public static HashMap<String, OutputType> getOutputTypes() {
    return outputType;
  }

  public static HashMap<String, ServerType> getServerTypes() {
    return serverTypes;
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
    WorkspaceSelectionInputForm form = new WorkspaceSelectionInputForm();
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

          for (Entry<String, DatasourceType> entry : datasourceType.entrySet()) {
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
                  //TODO Log
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
          for (Entry<String, DatasourceType> entry : datasourceType.entrySet()) {
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

    @Order(10.0)
    public class SaveAllProjectsMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("SaveAllProjects");
      }

      /* (non-Javadoc)
       * @see org.eclipse.scout.rt.client.ui.action.AbstractAction#execAction()
       */
      @Override
      protected void execAction() throws ProcessingException {
        for (Project project : Project.getAll()) {

          DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
          DocumentBuilder docBuilder;
          try {
            //Créer le dossier de l'application si il n'existe pas
            String folderPath = workspace + File.separator + project.getName();
            File folder = new File(folderPath);
            folder.mkdirs();

            docBuilder = docFactory.newDocumentBuilder();

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            Document document = docBuilder.newDocument();
            document.appendChild(ProjectXMLLoader.createProjectElement(document, project));
            DOMSource source = new DOMSource(document);

            StreamResult result = new StreamResult(new File(folderPath + File.separator + "project.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(source, result);
          }
          catch (ParserConfigurationException | TransformerException e) {
            throw new ProcessingException(e.getMessage());
          }
        }
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
    if (projectViewForms.get(project) != null) {
      projectViewForms.get(project).doClose();
      projectViewForms.remove(project);
    }

    projectViewForms.put(project, new ProjectViewForm(project));
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
