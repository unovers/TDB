package ch.heigvd.bachelor.crescenzio.generator.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.dataset.SQLField;

public class MySQLDatasource extends SQLDatasource {
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
      }
      catch (SQLException e) {
        throw new ProcessingException();
      }
      setConnectionOpenStatus(true);
    }

    return true;
  }

  @Override
  public boolean disconnect() {
    if (connexion != null) try {
      connexion.close();
    }
    catch (SQLException ignore) {
      System.out.println(ignore);
    }
    setConnectionOpenStatus(false);
    return true;
  }

  @Override
  public void query(String query) {
    System.out.println(query);
    try {
      statement = connexion.createStatement();
      statement.executeQuery(query);
    }
    catch (SQLException e) {
      System.out.println(e);
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
        System.out.println("Table : " + tablesResult.getString(1));
        SQLTable table = new SQLTable(tablesResult.getString(1));
        ResultSet resultat = queryDatas(String.format("describe %s;", table.getName()));
        while (resultat.next()) {
          String name = resultat.getString(1); // get column name
          String type = resultat.getString(2); // get column type
          System.out.println("column : " + name + "  " + type);

          table.addField(new SQLField(name, "string"));
        }
        getTables().add(table);
      }
    }
    catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    disconnect();
    setDescribed(true);

  }

}
