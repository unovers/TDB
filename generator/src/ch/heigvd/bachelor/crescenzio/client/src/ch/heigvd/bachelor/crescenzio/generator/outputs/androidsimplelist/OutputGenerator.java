/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.outputs.androidsimplelist;

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
    header.append("Developped for bachelor work by Crescenzio Fabio");
    header.append("Project : " + getOutput().getProject().getName() + "\n");
    header.append("Author : " + getOutput().getProject().getAuthor() + "\n");
    header.append("Organisation : " + getOutput().getProject().getOrganisation() + "\n");

    return header.toString();
  }

  private StringBuffer generateMenu() {
    StringBuffer output = new StringBuffer();
    output.append("@Override\n");
    output.append(" public boolean onCreateOptionsMenu(Menu menu) {\n");
    for (int i = 0; i < getOutput().getProject().getCriterias().size(); i++) {
      Criteria criteria = getOutput().getProject().getCriterias().get(i);
      output.append(String.format("   menu.add(Menu.NONE, MENU_ITEM%d, Menu.NONE, \"%s\");\n", i, criteria.getTitle()));
    }
    output.append("       return super.onCreateOptionsMenu(menu);\n");
    output.append(" }\n\n");

    output.append("@Override\n");
    output.append(" public boolean onOptionsItemSelected(MenuItem item) {\n");
    output.append(" // Filter lists and ask for refresh\n");
    output.append(" switch (item.getItemId()) {\n");

    for (int i = 0; i < getOutput().getProject().getCriterias().size(); i++) {
      Criteria criteria = getOutput().getProject().getCriterias().get(i);
      String conditions = "";
      for (int j = 0; j < criteria.getConditions().size(); j++) {
        conditions += "\"" + criteria.getConditions().get(j).getName() + "\"";
        if (j != criteria.getConditions().size() - 1) {
          conditions += ", ";
        }
        output.append(String.format("       case MENU_ITEM%d:\n", i));
        output.append(String.format("           dataManager.organizeResource(%s);\n", conditions));
        output.append("           break;\n");
      }
      output.append("       return super.onOptionsItemSelected(item);\n");
      output.append(" }\n");

    }

    return output;

  }

  private void generateMainActivity(File destination) throws IOException {
    StringBuffer output = new StringBuffer();
    output.append("/*" + getHeader() + "*/\n");
    output.append("\n\n");
    output.append(String.format("package %s;\n\n", getOutput().getProject().getPackageName()));
    output.append("import ;");

    output.append("public class MainActivity extends Activity {\n");
    output.append(String.format("  private static final WS_URL = \"%s\";\n",
        getOutput().getProject().getServer().getHost() + "/" + getOutput().getProject().getServer().getRootFolder()));

    for (int i = 0; i < getOutput().getProject().getCriterias().size(); i++) {
      output.append(String.format("  private static final MENU_ITEM%d = %d;\n", i, i));
    }

    output.append("  DataManager dataManager = new DataManager(WS_URL);");

    output.append(generateMenu());

    output.append("  @Override\n");
    output.append("  protected void onCreate(Bundle savedInstanceState) {\n\n");
    output.append("    super.onCreate(savedInstanceState);\n");
    output.append("    LinearLayout layout = new LinearLayout(this);\n");

    output.append("  @Override\n");
    output.append("  protected void onCreate(Bundle savedInstanceState) {\n\n");
    output.append("    super.onCreate(savedInstanceState);\n");
    output.append("    LinearLayout layout = new LinearLayout(this);\n");

    output.append("    ListView listView = new ListView(this);\n");
    output.append("    layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));\n");

    output.append("    listView.setAdapter(new RowAdapter(this, datas_array));\n");
    output.append("    listView.setOnItemClickListener(new OnItemClickListener() {\n");

    output.append("      @Override\n");
    output.append("      public void onItemClick(AdapterView<?> parent, final View view,\n");
    output.append("        int position, long id) {\n");
    output.append("        Item item = (Item) datas_array[position];\n");
    output.append("        if(item.getItemType().equals/(\"Default\"){\n");
    output.append("          Intent myIntent = new Intent(MainActivity.this, DefaultItemDisplayActivity.class);\n");
    output.append("          myIntent.putExtra(\"item\", item );\n");
    output.append("          MainActivity.this.startActivity(myIntent);\n");
    output.append("        }\n");
    for (ItemType itemType : getOutput().getItemsTypes()) {
      output.append(String.format("        else if(item.getItemType().equals(\"%s\"){\n", itemType.getName()));
      output.append(String.format("          Intent myIntent = new Intent(MainActivity.this, %sItemDisplayActivity.class);\n", itemType.getName()));
      output.append("          myIntent.putExtra(\"item\", item );\n");
      output.append("          MainActivity.this.startActivity(myIntent);\n");
      output.append("        }\n");
    }
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
    output.append("          //Test si c'est un titre ou un item");
    output.append("          if(item instanceof TitleItem){ \n");
    output.append("            rows.add(new TitleRow(LayoutInflater.from(MainActivity.this), item));\n");
    for (ItemType itemType : getOutput().getItemsTypes()) {
      output.append(String.format("          else if (((DataItem) item).getItemType().equals(%s)) {\n", itemType.getName()));
      output.append(String.format("             rows.add(new %sRow(LayoutInflater.from(MainActivity.this), item));\n", itemType.getName()));
      output.append("          }\n");
    }
    output.append("      else {//otherwise use a DescriptionRow\n");
    output.append("            rows.add(new DefaultRow(LayoutInflater.from(MainActivity.this), item));\n");
    output.append("          }\n");
    output.append("       }\n");
    output.append("    }\n");

    output.append("    @Override\n");
    output.append("    public int getViewTypeCount() {\n");
    output.append("      return RowType.values().length;\n");
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

    output.append("    public View getView(int position, View convertView, ViewGroup parent) {\n");
    output.append("       return rows.get(position).getView(convertView);\n");
    output.append("    }\n");
    output.append("}\n");

    output.append(");\n");

    BufferedWriter out = new BufferedWriter(
        new FileWriter(destination.toPath() + File.separator + "MainActivity..php"));
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
    outText += getOutput().getProject().getPackageName() + ";\n\n";
    //créer les dossiers nécessaires
    new File(destination.toPath() + File.separator + getOutput().getName() + File.separator + "res" + File.separator + "layout").mkdirs();
    new File(destination.toPath() + File.separator + getOutput().getName() + File.separator + "src" + File.separator + packageFolder).mkdirs();
    new File(destination.toPath() + File.separator + getOutput().getName() + File.separator + "src" + File.separator + packageFolder + File.separator + "row").mkdirs();
    for (ItemType itemType : getOutput().getItemsTypes()) {

      //Création du fichier java
      BufferedWriter out = new BufferedWriter(
          new FileWriter(destination.toPath() + File.separator + getOutput().getName() + File.separator + "src"
              + File.separator + packageFolder + File.separator + "row" + File.separator + itemType.getName() + "Row.java"));
      FileResource resource = null;
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

      //Création du fichier xml
      out = new BufferedWriter(
          new FileWriter(destination.toPath() + File.separator + getOutput().getName() + File.separator + "src"
              + File.separator + "layout" + File.separator + itemType.getName() + "_row.xml"));
      outText = "/*" + getHeader() + "*/\n";
      outText += "\n\n";
      resource = null;
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

      //Création du fichier java pour l'activité
      out = new BufferedWriter(
          new FileWriter(destination.toPath() + File.separator + getOutput().getName() + File.separator + "src"
              + File.separator + packageFolder + File.separator + itemType.getName() + "Activity.java"));
      outText = "/*" + getHeader() + "*/\n";
      outText += getOutput().getProject().getPackageName() + ";\n\n";
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
      outText += readFile(source.toPath() + File.separator + getOutput().getName() + File.separator + getOutput().getName() + File.separator + resource.getValue(), StandardCharsets.UTF_8);
      out.write(outText);
      out.close();

      //Création du fichier xml pour l'activité
      String resourceTypeLayoutPath = destination.toPath() + File.separator + getOutput().getName() + File.separator + "src"
          + File.separator + "layout" + File.separator + itemType.getName();
      new File(resourceTypeLayoutPath).mkdirs();
      out = new BufferedWriter(
          new FileWriter(resourceTypeLayoutPath + "_activity.xml"));
      outText = "/*" + getHeader() + "*/\n";
      outText += "\n\n";
      resource = null;
      //Recupération de la ressource voulue
      for (FileResource res : itemType.getResources()) {
        if (res.getName().equals("ActivityResFile")) {
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

  private void createManifest(File destination) throws IOException {
    StringBuffer output = new StringBuffer();
    output.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
    output.append("<!--" + getHeader() + "-->\n");
    output.append("\n\n");
    output.append("<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"\n");
    output.append(String.format("    package=\"%s\"\n", getOutput().getProject().getPackageName()));
    output.append("    android:versionCode=\"1\"\n");
    output.append("    android:versionName=\"1.0\" >\n");
    output.append("    <uses-permission \n");
    output.append("   android:name=\"android.permission.WRITE_EXTERNAL_STORAGE\" />\n");
    output.append("<uses-permission \n");
    output.append("   android:name=\"android.permission.READ_EXTERNAL_STORAGE\" />\n");
    output.append("<uses-permission android:name=\"android.permission.INTERNET\"/>\n");
    output.append("    <uses-sdk\n");
    output.append("        android:minSdkVersion=\"8\"\n");
    output.append("        android:targetSdkVersion=\"19\" />\n");

    output.append("    <application\n");
    output.append("        android:allowBackup=\"true\"\n");
    output.append("        android:icon=\"@drawable/ic_launcher\"\n");
    output.append("        android:label=\"@string/app_name\"\n");
    output.append("        android:theme=\"@style/AppTheme\" >\n");
    output.append(String.format("        <activity android:name=\"%s.MainActivity\" >\n", getOutput().getProject().getPackageName()));
    output.append("            <intent-filter>\n");
    output.append("            <action android:name=\"android.intent.action.MAIN\" />\n");
    output.append("            <category android:name=\"android.intent.category.LAUNCHER\" />\n");
    output.append("        </intent-filter>\n");
    output.append("        </activity>\n");
    output.append(String.format("        <activity android:name=\"%s.DefaultActivity.java\" >\n", getOutput().getProject().getPackageName()));
    output.append("        </activity>\n");
    for (ItemType itemType : getOutput().getItemsTypes()) {
      if (!itemType.getName().equals("Default")) {
        FileResource resource = null;
        //Recupération de la ressource voulue
        for (FileResource res : itemType.getResources()) {
          if (res.getName().equals("ActivitySrcFile")) {
            resource = res;
            break;
          }
        }
        if (resource == null) {
          throw new IOException("Cette resource n'existe pas");
        }
        output.append(String.format("        <activity android:name=\"%s.%sActivity.java\" >\n", getOutput().getProject().getPackageName(), itemType.getName()));
        output.append("        </activity>\n");
      }
    }
    output.append("    </application>\n");

    output.append("</manifest>\n");

    BufferedWriter out = new BufferedWriter(
        new FileWriter(destination.toPath() + File.separator + "AndroidManifest.xml"));
    out.write(output.toString());
    out.close();

  }

  private void copyFiles(File src, File destination) throws IOException {

    //copie les fichiers pour l'application
    for (Entry<Field, OutputField> applicationField : getOutput().getApplicationFields().entrySet()) {
      Field field = applicationField.getKey();
      if (field.getName().equals("applicationIcon72")) {
        OutputField file = applicationField.getValue();
        Files.copy(new File(src.toPath() + File.separator + getOutput().getName() + File.separator + file.getValue()).toPath(),
            new File(destination.toPath() + File.separator + "res" + File.separator + "drawable-hdpi" + File.separator + "logo.png").toPath(), StandardCopyOption.REPLACE_EXISTING);
      }
    }
  }

  @Override
  public void generate(File src, File destination) throws IOException {
    System.out.println("GENERATION DE ANDROIDSIMPLELIST");
    System.out.println();
    String destinationSrc = destination.toPath() + File.separator + getOutput().getName() + File.separator + "src";
    String destinationRes = destination.toPath() + File.separator + getOutput().getName() + File.separator + "res";
    new File(destinationSrc).mkdirs();
    new File(destinationRes).mkdirs();

    //copie les fichier de bases de l'application
    //get the zip file content
    File zipFile = File.createTempFile("baseCode", ".zip");
    InputStream is = AbstractOutputGenerator.class.getResourceAsStream(getOutput().getName().toLowerCase() + File.separator + "baseCode.zip");
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
    Utils.unZipIt(zipFile.getPath(), destination.toPath().toString());
    temp.deleteOnExit();

    //Ajoute les ressources
    createManifest(destination);
    copyFiles(src, destination);

    generateMainActivity(destination);
    generateTypeFiles(src, destination);
  }
}
