/**
 * Nom du fichier         : PHPMySQLScript.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Cette classe défini la génération de scripts PHP avec une source de type MySQL
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.server.php.scripts;

import java.io.IOException;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.Project;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDataset;
import ch.heigvd.bachelor.crescenzio.generator.datasources.mysql.MySQLDataset;
import ch.heigvd.bachelor.crescenzio.generator.datasources.mysql.MySQLDatasource;
import ch.heigvd.bachelor.crescenzio.generator.server.AbstractServerDatasourceScriptGenerator;

/**
 * Define how to generate scripts for a MySQL datasource with a PHP server
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public class PHPMySQLScript extends AbstractServerDatasourceScriptGenerator {

  /**
   * @param project
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
  }
}
