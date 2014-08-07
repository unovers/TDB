/**
 * Nom du fichier         : PHPServer.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit comment sont génerés les scripts serveur PHP
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.server.php;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;
import ch.heigvd.bachelor.crescenzio.generator.server.AbstractServer;
import ch.heigvd.bachelor.crescenzio.generator.server.AbstractServerDatasourceScriptGenerator;

/**
 * Define how to generate scripts for a PHP server
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class PHPServer extends AbstractServer {

  public PHPServer(String host, String rootFolder) {
    super(host, rootFolder);
  }

  private void copyFiles(String destination) throws IOException {
    File destinationFile = new File(destination + File.separator + "function_and_classes.php");
    if (destinationFile.exists()) {
      destinationFile.delete();
    }
    Files.copy(PHPServer.class.getResourceAsStream("function_and_classes.php"), destinationFile.toPath());
  }

  @Override
  public void generateScripts(Project project, String destination) throws ProcessingException {

    StringBuffer output = new StringBuffer();
    output.append("<?php\n");
    output.append("include_once 'function_and_classes.php';\n");
    output.append("$datas = array();\n");
    output.append("try{\n");
    for (int i = 0; i < project.getDatasources().size(); ++i) {
      AbstractDatasource datasource = project.getDatasources().get(i);
      //Pour chaque type de source de données, appelle le bon génerateur
      try {
        String pckage = "ch.heigvd.bachelor.crescenzio.generator.server.php.scripts";
        String classGenerator = "PHP" + datasource.getClass().getSimpleName().replace("Datasource", "");
        String clss = pckage + "." + classGenerator + "Script";
        Class<?> generatorClass = Class.forName(clss);
        java.lang.reflect.Constructor constructor = generatorClass.getConstructor(new Class[]{Project.class, datasource.getClass()});
        AbstractServerDatasourceScriptGenerator generator = (AbstractServerDatasourceScriptGenerator) constructor.newInstance(new Object[]{project, datasource});
        output.append(generator.generate());
        generator.createFiles(destination);
      }
      catch (Exception e) {
        //TODO Log
        throw new ProcessingException("Impossible de trouver le génerateur " + "PHP" + datasource.getClass().getSimpleName() + "Script");
      }
    }

    output.append("  if(file_exists ('resources')){\n");
    output.append("      $resources = listResources('resources');\n");
    output.append("  }else{\n");
    output.append("      $resources = array();\n");
    output.append("  }\n");
    output.append("  echo \'{';\n");
    output.append("  echo '\"status\":\"datas\",';\n");
    output.append("  echo '\"items\":[';\n");
    output.append("  $items = array();\n");
    output.append("  echo printItems($datas);\n");
    output.append("  echo '],';\n");
    output.append("  echo '\"ressources\":[';\n");
    output.append("  echo printResources($resources);\n");
    output.append("  echo ']';\n");
    output.append("  echo '}';\n");

    output.append("}\n");
    output.append("catch(Exception $e){\n");
    output.append("  echo '{ \"status\":\"error\"}';\n");
    output.append("}\n");
    output.append("?>");

    try {
      File destinationFile = new File(destination + File.separator + "datas.php");
      if (destinationFile.exists()) {
        destinationFile.delete();
      }
      BufferedWriter out = new BufferedWriter(
          new FileWriter(destination + File.separator + "datas.php"));
      String outText = output.toString();
      out.write(outText);
      out.close();
      copyFiles(destination);
    }
    catch (IOException e)
    {
      throw new ProcessingException("impossible de créer le fichier datas.php");
    }

  }
}
