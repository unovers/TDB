package ch.heigvd.bachelor.crescenzio.generator.server;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;
import ch.heigvd.bachelor.crescenzio.generator.server.scripts.ServerDatasourceScriptGenerator;

public class PHPServer extends Server {

  public PHPServer(String host, String rootFolder) {
    super(host, rootFolder);
  }

  @Override
  public void generateScripts(Project project) throws ProcessingException {

    StringBuffer output = new StringBuffer();
    output.append("<?php\n");
    output.append("include_once 'function_and_classes.php';\n");
    output.append("$datas = array();\n");
    output.append("try{\n");
    for (int i = 0; i < project.getDatasources().size(); ++i) {
      AbstractDatasource datasource = project.getDatasources().get(i);
      try {
        String pckage = "ch.heigvd.bachelor.crescenzio.generator.server.scripts";
        String classGenerator = "PHP" + datasource.getClass().getSimpleName().replace("Datasource", "");
        String clss = pckage + "." + classGenerator + "Script";
        Class generatorClass = Class.forName(clss);
        java.lang.reflect.Constructor constructor = generatorClass.getConstructor(new Class[]{Project.class, datasource.getClass()});
        ServerDatasourceScriptGenerator generator = (ServerDatasourceScriptGenerator) constructor.newInstance(new Object[]{project, datasource});
        output.append(generator.generate());
      }
      catch (Exception e) {
        e.printStackTrace();
        throw new ProcessingException(e.toString());
      }
    }

    output.append("  $resources = listResources('resources');\n");
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

    System.out.println(output);
  }
}
