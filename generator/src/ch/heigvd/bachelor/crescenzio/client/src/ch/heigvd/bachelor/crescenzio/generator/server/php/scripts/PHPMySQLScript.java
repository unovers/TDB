package ch.heigvd.bachelor.crescenzio.generator.server.php.scripts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDataset;
import ch.heigvd.bachelor.crescenzio.generator.datasources.mysql.MySQLDataset;
import ch.heigvd.bachelor.crescenzio.generator.datasources.mysql.MySQLDatasource;
import ch.heigvd.bachelor.crescenzio.generator.server.ServerDatasourceScriptGenerator;

public class PHPMySQLScript extends ServerDatasourceScriptGenerator {

  /**
   * @param datasource
   */
  public PHPMySQLScript(Project project, MySQLDatasource datasource) {
    super(project, datasource);
  }

  @Override
  public String generate() {
    MySQLDatasource datasource = (MySQLDatasource) getDatasource();
    StringBuffer output = new StringBuffer();
    output.append(String.format("  $connexion = new PDO('mysql:host=%s;port=%d;dbname=%s', '%s', '%s');\n", datasource.getHostname(), datasource.getPort(), datasource.getDatabase(), datasource.getLogin(), (datasource.getPassword())));
    for (AbstractDataset dataset : datasource.getDatasets()) {
      output.append(String.format("  $res = $connexion->query('%s'); \n", ((MySQLDataset) dataset).getQuery()));
      output.append("  while($row = $res->fetch(PDO::FETCH_ASSOC)){\n");
      output.append("    $item = new Item();\n");
      output.append("    foreach( $row as $key => $value ){\n");
      output.append("      $item->addData(new Data($key, $value));\n");
      output.append("    }\n");

      for (Field field : getProject().getFields()) {
        String mapping = getProject().getMapping(field, dataset);
        output.append(String.format("    $item->addData(new Data('%s', format_string($row, '%s')));\n", field.getName(), mapping));
      }

      output.append("    array_push($datas, $item);\n");
      output.append("  }\n");
    }
    output.append("  $connexion = null;\n");
    return output.toString();
  }

  @Override
  public void createFiles(String destination) throws IOException {
    File destinationFile = new File(destination + File.separator + "function_and_classes.php");
    if (destinationFile.exists()) {
      destinationFile.delete();
    }
    Files.copy(PHPMySQLScript.class.getResourceAsStream("function_and_classes.php"), destinationFile.toPath());

  }
}
