package ch.heigvd.bachelor.crescenzio.generator.outputs.windowssimplelist;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map.Entry;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.criterias.Criteria;
import ch.heigvd.bachelor.crescenzio.generator.outputs.AbstractOutputGenerator;
import ch.heigvd.bachelor.crescenzio.generator.outputs.FileResource;
import ch.heigvd.bachelor.crescenzio.generator.outputs.ItemType;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputApplication;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputField;
import ch.heigvd.bachelor.crescenzio.generator.ults.Utils;

/**
 * @author Fabio
 */
public class OutputGenerator extends AbstractOutputGenerator {

  /**
   * @param output
   */
  public OutputGenerator(OutputApplication output) {
    super(output);
  }

  private String getHeader() {
    StringBuffer header = new StringBuffer();

    header.append("Application concept based on the work of Indrajit Khare y\n");
    header.append("found at http://logc.at/2011/10/10/handling-listviews-with-multiple-row-types/ y\n");
    header.append("Permission is hereby granted, free of charge, to any person obtaining a copy\n");
    header.append("of this software and associated documentation files (the \"Software\"), to deal\n");
    header.append("in the Software without restriction, including without limitation the rights\n");
    header.append("to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n");
    header.append("copies of the Software, and to permit persons to whom the Software is\n");
    header.append("furnished to do so, subject to the following conditions:\n");
    header.append("\n");
    header.append("The above copyright notice and this permission notice shall be included in\n");
    header.append("all copies or substantial portions of the Software\n");
    header.append("\n");
    header.append("THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n");
    header.append("IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n");
    header.append("FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n");
    header.append("AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n");
    header.append("LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n");
    header.append("OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN\n");
    header.append("THE SOFTWARE.\n");
    header.append("\n\n");
    header.append("Developped for bachelor work by Crescenzio Fabio\n");
    header.append("Project : " + getOutput().getProject().getName() + "\n");
    header.append("Author : " + getOutput().getProject().getAuthor() + "\n");
    header.append("Organisation : " + getOutput().getProject().getOrganisation() + "\n");

    return header.toString();
  }

  private StringBuffer generateMenu() {
    StringBuffer output = new StringBuffer();
    output.append("@Override\n");
    output.append(" public boolean onCreateOptionsMenu(Menu menu) {\n");
    output.append(String.format("   menu.add(Menu.NONE, MENU_ITEM0, Menu.NONE, \"%s\");\n", "Default datas"));
    output.append(String.format("   menu.add(Menu.NONE, MENU_ITEM1, Menu.NONE, \"%s\");\n", "Update datas"));
    for (int i = 0; i < getOutput().getProject().getCriterias().size(); i++) {
      Criteria criteria = getOutput().getProject().getCriterias().get(i);
      output.append(String.format("   menu.add(Menu.NONE, MENU_ITEM%d, Menu.NONE, \"%s\");\n", i + 2, criteria.getTitle()));
    }
    output.append("       return super.onCreateOptionsMenu(menu);\n");
    output.append(" }\n\n");

    output.append("@Override\n");
    output.append(" public boolean onOptionsItemSelected(MenuItem item) {\n");
    output.append(" // Filter lists and ask for refresh\n");
    output.append("      switch (item.getItemId()) {\n");

    output.append("       case MENU_ITEM0:\n");
    output.append("           dataManager.defaultSort();\n");
    output.append("           putDatasInList();\n");
    output.append("           break;\n");
    output.append("       case MENU_ITEM1:\n");
    output.append("         try{\n");
    output.append("                   dataManager.updateDatas();\n");
    output.append("         }catch (Exception e){\n");
    output.append("            Toast.makeText(this, \"Couldnt update info\", Toast.LENGTH_LONG).show();\n");
    output.append("            return super.onOptionsItemSelected(item);\n");
    output.append("          }\n");
    output.append("           putDatasInList();\n");
    output.append("           break;\n");
    for (int i = 0; i < getOutput().getProject().getCriterias().size(); i++) {
      Criteria criteria = getOutput().getProject().getCriterias().get(i);
      String conditions = "";
      for (int j = 0; j < criteria.getConditions().size(); j++) {
        conditions += "\"" + criteria.getConditions().get(j).getName() + "\"";
        if (j != criteria.getConditions().size() - 1) {
          conditions += ", ";
        }
      }
      output.append(String.format("       case MENU_ITEM%d:\n", i + 2));
      output.append(String.format("           dataManager.sortDatas(%s);\n", conditions));
      output.append("           putDatasInList();\n");
      output.append("           break;\n");
    }
    output.append("         }\n");
    output.append("       return super.onOptionsItemSelected(item);\n");
    output.append(" }\n");

    return output;

  }

  private void generateMainActivity(File destination) throws IOException {
    StringBuffer output = new StringBuffer();
    output.append("/*" + getHeader() + "*/\n");
    output.append("\n\n");
    output.append(String.format("package %s;\n\n", getOutput().getProject().getPackageName()));
    output.append("import ch.heigvd.bachelor.crescenzio.androidsimplelist.DataManager;\n");
    output.append("import ch.heigvd.bachelor.crescenzio.androidsimplelist.Item;\n");
    output.append("import ch.heigvd.bachelor.crescenzio.androidsimplelist.DataItem;\n");
    output.append("import ch.heigvd.bachelor.crescenzio.androidsimplelist.TitleItem;\n");
    output.append("import ch.heigvd.bachelor.crescenzio.androidsimplelist.row.Row;\n");
    output.append("import ch.heigvd.bachelor.crescenzio.androidsimplelist.row.TitleRow;\n");
    output.append("import ch.heigvd.bachelor.crescenzio.androidsimplelist.row.RowType;\n");
    output.append("import android.widget.Toast;\n");

    output.append("import java.util.LinkedList;\n");
    output.append("import java.util.ArrayList;\n");
    output.append("import java.util.Map.Entry;\n");
    output.append("import java.util.List;\n");
    output.append("import java.io.File;\n");
    output.append("import android.content.Context;\n");
    output.append("import android.view.ViewGroup;\n");
    output.append("import android.view.LayoutInflater;\n");

    output.append("import android.app.Activity;\n");
    output.append("import android.content.Intent;\n");
    output.append("import android.os.Bundle;\n");
    output.append("import android.view.Menu;\n");
    output.append("import android.view.MenuItem;\n");
    output.append("import android.view.View;\n");
    output.append("import android.widget.BaseAdapter;\n");
    output.append("import android.view.ViewGroup.LayoutParams;\n");
    output.append("import android.widget.AdapterView;\n");
    output.append("import android.widget.AdapterView.OnItemClickListener;\n");
    output.append("import android.widget.LinearLayout;\n");
    output.append("import android.widget.ListView;\n");
    for (ItemType itemType : getOutput().getItemsTypes()) {
      output.append(String.format("import %s.%sItemActivity;\n", getOutput().getProject().getPackageName(), itemType.getName()));
      output.append(String.format("import %s.row.%sItemRow;\n", getOutput().getProject().getPackageName(), itemType.getName()));
    }
    String ws_url = getOutput().getProject().getServer().getHost() + "/" + getOutput().getProject().getServer().getRootFolder();
    if (!ws_url.startsWith("http://")) ws_url = "http://" + ws_url;
    output.append("public class MainActivity extends Activity {\n");
    output.append(String.format("  private final String WS_URL = \"%s/%s\";\n", ws_url, "datas.php"));
    output.append("   private String ROOT_FOLDER;\n");
    output.append("  private DataManager dataManager;\n");
    output.append("  private RowAdapter rowAdapter;\n");
    output.append("  private ListView listView;\n");
    output.append("  private LinkedList<Item> datas = new LinkedList<Item>();\n");

    output.append("  private final int MENU_ITEM0 = 0;\n");//Ajoute menu pour données par défault
    output.append("  private final int MENU_ITEM1 = 1;\n");//Ajoute menu pour mise a jour des données
    for (int i = 0; i < getOutput().getProject().getCriterias().size(); i++) {
      output.append(String.format("  private final int MENU_ITEM%d = %d;\n", i + 2, i + 2));
    }

    output.append(generateMenu());

    output.append("  private void putDatasInList(){\n");
    output.append("   datas = new LinkedList<Item>();\n");
    output.append("   // Ajoute les listes avec des titres\n");
    output.append("   for (Entry<String, ArrayList<DataItem>> entry : dataManager.getDatas()\n");
    output.append("     .entrySet()) {\n");
    output.append("     datas.add(new TitleItem(entry.getKey()));\n");
    output.append("     for (Item str : entry.getValue()) {\n");
    output.append("       datas.add(str);\n");
    output.append("     }\n");
    output.append("   }\n");
    output.append("   rowAdapter = new RowAdapter(datas);\n");
    output.append("   listView.setAdapter(rowAdapter);\n");
    output.append("   rowAdapter.notifyDataSetChanged();\n");
    output.append("  }\n\n");

    output.append("  @Override\n");
    output.append("  protected void onCreate(Bundle savedInstanceState) {\n\n");
    output.append("    super.onCreate(savedInstanceState);\n");
    output.append("    File mydir = getDir(\"datas\", Context.MODE_PRIVATE);\n");
    output.append("    ROOT_FOLDER = mydir.getAbsolutePath();\n");
    output.append("    dataManager = new DataManager(WS_URL, ROOT_FOLDER);\n");
    output.append("    LinearLayout layout = new LinearLayout(this);\n");

    output.append("    listView = new ListView(this);\n");
    output.append("    layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));\n");

    output.append("    try{\n");
    output.append("             dataManager.loadDatas();\n");
    output.append("    }catch (Exception e){\n");
    output.append("       Toast.makeText(this, \"Couldnt load info\", Toast.LENGTH_LONG).show();\n");
    output.append("       return;\n");
    output.append("    }\n");

    output.append("    putDatasInList();\n\n");

    output.append("    listView.setAdapter(new RowAdapter(datas));\n");
    output.append("    listView.setOnItemClickListener(new OnItemClickListener() {\n");

    output.append("      public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {\n");
    output.append("        if(datas.get(arg2) instanceof DataItem){\n");

    output.append("         DataItem item = (DataItem) datas.get(arg2);\n");
    for (int i = 0; i < getOutput().getItemsTypes().size(); i++) {
      ItemType itemType = getOutput().getItemsTypes().get(i);
      if (i != 0) {
        output.append("         else);\n");
      }
      output.append(String.format("         if(item.getData(\"__item_type\").equals(\"%s\")){\n", itemType.getName()));
      output.append(String.format("           Intent myIntent = new Intent(MainActivity.this, %sItemActivity.class);\n", itemType.getName()));
      output.append("           myIntent.putExtra(\"item\", item );\n");
      output.append("            MainActivity.this.startActivity(myIntent);\n");
      output.append("          }\n");
    }
    output.append("         else{\n");
    output.append("           Intent myIntent = new Intent(MainActivity.this, DefaultItemActivity.class);\n");
    output.append("           myIntent.putExtra(\"item\", item );\n");
    output.append("            MainActivity.this.startActivity(myIntent);\n");
    output.append("          }\n");
    output.append("       }\n");
    output.append("      }\n");
    output.append("    });\n");

    output.append("    layout.addView(listView);\n");
    output.append("    setContentView(layout);\n");
    output.append("  }\n");

    output.append("  private class RowAdapter extends BaseAdapter {\n");
    output.append("      final List<Row> rows;\n");

    output.append("      RowAdapter(List<Item> items) {\n");
    output.append("        rows = new ArrayList<Row>();//member variable\n");

    output.append("        for (Item item : items) {\n");
    output.append("          //Test si c'est un titre ou un item\n");
    output.append("          if(item instanceof TitleItem) \n");
    output.append("            rows.add(new TitleRow(LayoutInflater.from(MainActivity.this), item));\n");

    //Ajoute chaque type d'item
    for (ItemType itemType : getOutput().getItemsTypes()) {
      output.append(String.format("          else if (((DataItem) item).getData(\"__item_type\").equals(\"%s\")) {\n", itemType.getName()));
      output.append(String.format("             rows.add(new %sItemRow(LayoutInflater.from(MainActivity.this), (DataItem) item));\n", itemType.getName()));
      output.append("          }\n");
    }
    output.append("      else {//otherwise use a DescriptionRow\n");
    output.append("            rows.add(new DefaultItemRow(LayoutInflater.from(MainActivity.this), (DataItem)  item));\n");
    output.append("          }\n");
    output.append("       }\n");
    output.append("    }\n");

    output.append("    @Override\n");
    output.append("    public int getViewTypeCount() {\n");
    output.append("         return RowType.values().length;\n");
    output.append("    }\n");

    output.append("    @Override\n");
    output.append("    public int getItemViewType(int position) {\n");
    output.append("      return rows.get(position).getViewType();\n");
    output.append("    }\n");

    output.append("    public int getCount() {\n");
    output.append("      return rows.size();\n");
    output.append("    }\n");

    output.append("    public Object getItem(int position) {\n");
    output.append("      return position;\n");
    output.append("    }\n");

    output.append("    public long getItemId(int position) {\n");
    output.append("      return position;\n");
    output.append("    }\n");

    output.append("    public View getView(int position, View arg1, ViewGroup arg2) {\n");
    output.append("       return rows.get(position).getView(arg1);\n");
    output.append("    }\n");
    output.append("  }\n");

    output.append("}\n");

    String packagePath = destination.toPath() + File.separator + "src" + File.separator + getOutput().getProject().getPackageName().replace(".", File.separator) + File.separator;
    File packageFolder = new File(packagePath);
    packageFolder.mkdirs();
    BufferedWriter out = new BufferedWriter(
        new FileWriter(packagePath + "MainActivity.java"));
    String outText = output.toString();
    out.write(outText);
    out.close();
  }

  static String readFile(String path, Charset encoding)
      throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, encoding);
  }

  private void generateTypeFiles(File source, File destination) throws IOException {
    String packageFolder = getOutput().getProject().getPackageName().replace(".", File.separator);
    String outText = "/*" + getHeader() + "*/\n";
    outText += "package " + getOutput().getProject().getPackageName() + ".row;\n\n";
    outText += "import " + getOutput().getProject().getPackageName() + ".R;\n\n";
    for (ItemType itemType : getOutput().getItemsTypes()) {

      //Création du fichier java
      File resourceFile = new File(destination.toPath() + File.separator + "src"
          + File.separator + packageFolder + File.separator + "row");
      resourceFile.mkdirs();
      BufferedWriter out = new BufferedWriter(
          new FileWriter(resourceFile + File.separator + itemType.getName() + "ItemRow.java"));
      FileResource resource = null;
      //Recupération de la ressource voulue
      for (FileResource res : itemType.getResources()) {
        if (res.getName().equals("RowSrcFile")) {
          resource = res;
          break;
        }
      }
      if (resource == null) {
        out.close();
        throw new IOException("Cette resource n'existe pas");
      }
      outText += readFile(source.toPath() + File.separator + getOutput().getName() + File.separator + resource.getValue(), StandardCharsets.UTF_8);
      out.write(outText);
      out.close();

      //Création du fichier xml
      resourceFile = new File(destination.toPath() + File.separator + "res"
          + File.separator + "layout");
      resourceFile.mkdirs();
      out = new BufferedWriter(
          new FileWriter(resourceFile + File.separator + itemType.getName().toLowerCase() + "_row.xml"));
      outText = "<!--" + getHeader() + "-->\n";
      outText += "\n\n";
      resource = null;

      //Recupération de la ressource voulue
      for (FileResource res : itemType.getResources()) {
        if (res.getName().equals("RowResFile")) {
          resource = res;
          break;
        }
      }

      if (resource == null) {
        out.close();
        throw new IOException("Cette resource n'existe pas");
      }

      outText += readFile(source.toPath() + File.separator + getOutput().getName() + File.separator + resource.getValue(), StandardCharsets.UTF_8);
      out.write(outText);
      out.close();

      //Création du fichier java pour l'activité
      resourceFile = new File(destination.toPath() + File.separator + "src"
          + File.separator + packageFolder);
      resourceFile.mkdirs();
      out = new BufferedWriter(new FileWriter(resourceFile + File.separator + itemType.getName() + "ItemActivity.java"));
      outText = "/*" + getHeader() + "*/\n";
      outText += "package " + getOutput().getProject().getPackageName() + ";\n\n";
      resource = null;
      //Recupération de la ressource voulue
      for (FileResource res : itemType.getResources()) {
        if (res.getName().equals("ActivitySrcFile")) {
          resource = res;
          break;
        }
      }
      if (resource == null) {
        out.close();
        throw new IOException("Cette resource n'existe pas");
      }
      outText += readFile(source.toPath() + File.separator + getOutput().getName() + File.separator + resource.getValue(), StandardCharsets.UTF_8);
      out.write(outText);
      out.close();
    }
  }

  private void createAssemblyInfo(File destination) throws IOException {
    StringBuffer output = new StringBuffer();
    output.append("using System.Reflection;\n");
    output.append("using System.Runtime.CompilerServices;\n");
    output.append("using System.Runtime.InteropServices;\n");

    output.append("// General Information about an assembly is controlled through the following\n");
    output.append("// set of attributes. Change these attribute values to modify the information\n");
    output.append("// associated with an assembly.\n");
    output.append(String.format("[assembly: AssemblyTitle(\"%s\")]\n", getOutput().getProject().getName()));
    output.append("[assembly: AssemblyDescription(\"\")]\n");
    output.append("[assembly: AssemblyConfiguration(\"\")]\n");
    output.append("[assembly: AssemblyCompany(\"\")]\n");
    output.append(String.format("[assembly: AssemblyProduct(\"%s\")]\n", getOutput().getProject().getName()));
    output.append("[assembly: AssemblyCopyright(\"Copyright ©  2014\")]\n");
    output.append("[assembly: AssemblyTrademark(\"\")]\n");
    output.append("[assembly: AssemblyCulture(\"\")]\n");
    output.append("// Version information for an assembly consists of the following four values:\n");
    output.append("//\n");
    output.append("//          Major Version\n");
    output.append("//          Minor Version\n");
    output.append("//          Build Number\n");
    output.append("//          Revision\n");
    output.append("    //\n");
    output.append("    // You can specify all the values or you can default the Build and Revision Numbers\n");
    output.append("    // by using the '*' as shown below:\n");
    output.append("    // [assembly: AssemblyVersion(\"1.0.*\")]\n");
    output.append("    [assembly: AssemblyVersion(\"1.0.0.0\")]\n");
    output.append("    [assembly: AssemblyFileVersion(\"1.0.0.0\")]\n");
    output.append("    [assembly: ComVisible(false)]\n");

    BufferedWriter out = new BufferedWriter(
        new FileWriter(destination.toPath() + File.separator + getOutput().getName() + File.separator + "AssembleInfo.cs"));
    out.write(output.toString());
    out.close();

  }

  private void createTemplateDataItemSelector(File destination) {

  }

  private StringBuffer createMenus() {
    StringBuffer output = new StringBuffer();

    return output;
  }

  private void createMainPageCs(File destination) {

  }

  private void copyFiles(File src, File destination) throws IOException {

    //copie les fichiers pour l'application
    for (Entry<Field, OutputField> applicationField : getOutput().getApplicationFields().entrySet()) {
      Field field = applicationField.getKey();
      if (field.getName().equals("applicationIcon72")) {
        OutputField file = applicationField.getValue();
        Files.copy(new File(src.toPath() + File.separator + getOutput().getName() + File.separator + file.getValue()).toPath(),
            new File(destination.toPath() + File.separator + getOutput().getName() + File.separator + "res" + File.separator + "drawable-hdpi" + File.separator + "logo.png").toPath(), StandardCopyOption.REPLACE_EXISTING);
      }
    }
  }

  @Override
  public void generate(File src, File destination) throws IOException {
    //copie les fichier de bases de l'application
    //get the zip file content
    File zipFile = File.createTempFile("generated", ".zip");
    InputStream is = AbstractOutputGenerator.class.getResourceAsStream(getOutput().getName().toLowerCase() + File.separator + "generated.zip");
    OutputStream os = new FileOutputStream(zipFile);
    byte[] buffer = new byte[1024];
    int bytesRead;
    //read from is to buffer
    while ((bytesRead = is.read(buffer)) != -1) {
      os.write(buffer, 0, bytesRead);
    }
    is.close();
    //flush OutputStream to write any buffered data to file
    os.flush();
    os.close();

    File temp = Utils.createTempDirectory();
    File generatedApplicationFolder = new File(destination.toPath() + File.separator + getOutput().getName());
    generatedApplicationFolder.mkdirs();
    Utils.unZipIt(zipFile.getPath(), generatedApplicationFolder.toPath().toString());
    temp.deleteOnExit();

    //Ajoute les ressources
    createManifest(destination);
    copyFiles(src, destination);

    generateMainActivity(generatedApplicationFolder);
    generateTypeFiles(src, generatedApplicationFolder);
  }
}
