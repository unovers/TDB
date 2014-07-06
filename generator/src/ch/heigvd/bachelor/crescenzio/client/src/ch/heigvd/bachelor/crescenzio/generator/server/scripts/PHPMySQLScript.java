package ch.heigvd.bachelor.crescenzio.generator.server.scripts;

public class PHPMySQLScript implements ServerDatasourceScriptGenerator {
  public static String connection(String hostname, int port, String login, String password) {
    return String.format("mysql_connect('%s:%d', '%s', '%s');", hostname, port, login, password);
  }

  public static String disconnection(String hostname, int port, String login, String password) {
    return String.format("mysql_close();");
  }
}
