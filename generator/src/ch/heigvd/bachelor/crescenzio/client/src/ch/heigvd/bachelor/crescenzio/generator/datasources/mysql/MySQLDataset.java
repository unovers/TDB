package ch.heigvd.bachelor.crescenzio.generator.datasources.mysql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.datasources.sql.AbstractSQLDataset;

public class MySQLDataset extends AbstractSQLDataset {

  public MySQLDataset(MySQLDatasource datasource, String name, String query) {
    super(datasource, name, query);
  }

  @Override
  public void preview() throws ProcessingException {
    Object[][] datas;
    Object[] headers;

    int rowsNr = 1;
    MySQLDatasource datasource = (MySQLDatasource) getDatasource();
    datasource.connect();
    if (datasource.connect()) {
      ResultSet result = datasource.queryDatas(getQuery());
      ResultSetMetaData rsmd;
      try {
        rsmd = result.getMetaData();

        if (result.last()) {
          result.getRow();
          rowsNr = result.getRow();
          // Move to beginning
          result.beforeFirst();
        }
        datas = new Object[rowsNr][rsmd.getColumnCount()];

        //header
        headers = new Object[rsmd.getColumnCount()];
        for (int i = 0; i < datas[0].length; i++) {
          headers[i] = rsmd.getColumnName(i + 1);
        }
        //datas
        while (result.next()) {
          datas[result.getRow()] = new Object[rsmd.getColumnCount()];
          for (int i = 0; i < datas[0].length; i++) {
            datas[result.getRow() - 1][i] = result.getObject(i + 1);
          }
        }

        datasource.disconnect();
      }
      catch (SQLException e1) {
        throw new ProcessingException("PREVIEW_QUERY_ERROR");
      }
      setHeaders(headers);
      setDatas(datas);
      setDescribed(true);
    }
    else setDescribed(false);

  }
}
