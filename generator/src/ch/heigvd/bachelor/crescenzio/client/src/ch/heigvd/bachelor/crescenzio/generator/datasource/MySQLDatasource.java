package ch.heigvd.bachelor.crescenzio.generator.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.dataset.SQLField;

public class MySQLDatasource extends SQLDatasource {
  private Connection connexion = null;
  private Statement statement;

  static {
    /* Loading of the JDBC mysql driver */
    try {
      Class.forName("com.mysql.jdbc.Driver");
    }
    catch (ClassNotFoundException e) {
    }
  }

  private LinkedList<SQLTable> tables;

  public MySQLDatasource(String name, String hostname, int port,
      String login, String password) {
    super(name, hostname, port, login, password);
  }

  @Override
  public boolean connect() {
    if (isConnectionOpen()) return true;
    else {
      /* Connexion � la base de donn�es */
      try {
        connexion = DriverManager.getConnection(getHostname(), getLogin(), getPassword());

      }
      catch (SQLException e) {

      }
      setConnectionOpenStatus(true);
    }

    return true;
  }

  @Override
  public boolean disconnect() {
    if (connexion != null) try {
      /* Fermeture de la connexion */
      connexion.close();
    }
    catch (SQLException ignore) {
      /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
    }
    setConnectionOpenStatus(false);
    return true;
  }

  @Override
  public void query(String query) {
    System.out.println(query);
    try {
      statement = connexion.createStatement();
      /* Ex�cution d'une requ�te de lecture */
      ResultSet resultat = statement.executeQuery(query);
    }
    catch (SQLException e) {

    }
  }

  @Override
  public LinkedList<Field> getFields() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HashMap<String, String> getDatas() {
    // TODO Auto-generated method stub
    return null;
  }

  public void describe() {
    LinkedList<SQLField> fields = null;
    connect();
    tables = new LinkedList<SQLTable>();
    query("show tables;");

    for (SQLTable table : tables) {
      query(String.format("describe %s;", table.getName()));
      for (SQLField field : fields) {
        table.addField(field);
      }
      System.out.println();
    }

    disconnect();

  };

}
