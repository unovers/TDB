package ch.heigvd.bachelor.crescenzio.generator.datasources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.datasets.SQLField;

public class MySQLDatasource extends AbstractSQLDatasource {
  private Connection connexion = null;
  private Statement statement;

  static {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    }
    catch (ClassNotFoundException e) {
      System.out.println(e);
    }
  }

  public MySQLDatasource(String name, String hostname, int port, String database,
      String login, String password) {
    super(name, hostname, port, database, login, password);
  }

  @Override
  public boolean connect() throws ProcessingException {
    if (isConnectionOpen()) return true;
    else {
      String url = "jdbc:mysql://" + getHostname() + ":" + getPort() + "/" + getDatabase();
      /* Connexion � la base de donn�es */
      try {
        connexion = DriverManager.getConnection(url, getLogin(), getPassword());
        setConnectionOpenStatus(true);
      }
      catch (SQLException e) {
        throw new ProcessingException(e.toString());
      }
    }

    return true;
  }

  @Override
  public boolean disconnect() {
    if (connexion != null) try {
      connexion.close();
    }
    catch (SQLException ignore) {
    }
    setConnectionOpenStatus(false);
    return true;
  }

  @Override
  public void query(String query) throws ProcessingException {
    System.out.println(query);
    try {
      statement = connexion.createStatement();
      statement.executeQuery(query);
    }
    catch (SQLException e) {
      throw new ProcessingException(e.toString());
    }
  }

  @Override
  public ResultSet queryDatas(String query) {
    System.out.println(query);
    try {
      statement = connexion.createStatement();
      return statement.executeQuery(query);
    }
    catch (SQLException e) {
      System.out.println(e);
    }
    return null;
  }

  @Override
  public void describe() throws ProcessingException {
    connect();
    ResultSet tablesResult = queryDatas("show tables;");

    try {
      while (tablesResult.next()) {
        SQLTable table = new SQLTable(tablesResult.getString(1));
        ResultSet resultat = queryDatas(String.format("describe %s;", table.getName()));
        while (resultat.next()) {
          String name = resultat.getString(1); // get column name
          String type = resultat.getString(2); // get column type

          table.addField(new SQLField(name, type));
        }
        getTables().add(table);
      }
    }
    catch (SQLException e) {
      throw new ProcessingException("DESCRIBE_DB_ERROR");
    }

    disconnect();
    setDescribed(true);

  }

}
