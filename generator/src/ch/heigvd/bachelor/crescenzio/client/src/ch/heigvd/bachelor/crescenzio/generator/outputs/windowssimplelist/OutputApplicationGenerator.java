/**
 * Nom du fichier         : OutputApplicationGenerator.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Génère une application wiondows phone avec le template androidsimplelist
 *
 * Historiques des modifications
 * -
 */
package ch.heigvd.bachelor.crescenzio.generator.outputs.windowssimplelist;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map.Entry;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.outputs.AbstractOutputGenerator;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputApplication;
import ch.heigvd.bachelor.crescenzio.generator.outputs.OutputField;
import ch.heigvd.bachelor.crescenzio.generator.utils.Utils;

/**
 * @author Fabio
 */
public class OutputApplicationGenerator extends AbstractOutputGenerator {

  /**
   * @param output
   */
  public OutputApplicationGenerator(OutputApplication output) {
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
    return output;

  }

  private void generateMainFile(File destination) throws IOException {
  }

  static String readFile(String path, Charset encoding)
      throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, encoding);
  }

  private void generateTypeFiles(File source, File destination) throws IOException {
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
    createAssemblyInfo(destination);
    copyFiles(src, destination);

    generateMainFile(generatedApplicationFolder);
    generateTypeFiles(src, generatedApplicationFolder);
  }
}
