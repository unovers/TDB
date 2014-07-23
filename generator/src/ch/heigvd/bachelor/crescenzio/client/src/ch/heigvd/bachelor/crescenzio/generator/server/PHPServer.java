package ch.heigvd.bachelor.crescenzio.generator.server;

import java.util.LinkedList;

import ch.heigvd.bachelor.crescenzio.generator.Field;
import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDatasource;

public class PHPServer extends Server {

  public PHPServer(String host, String rootFolder) {
    super(host, rootFolder);
  }

  @Override
  public void generateScripts(LinkedList<AbstractDatasource> datasources, LinkedList<Field> fields) {

    StringBuffer output = new StringBuffer();
    output.append("<?php\n");

//		for(int i = 0; i < datasources.size(); ++i){
//			Datasource ds = datasources.get(0);
//			Class<?> act = Class.forName("ch.heigvd.bachelor.crescenzio.server.");
//			output.append("$datasource"+i+" = " act.connection(ds.get, port, login, password))
//		}

    output.append("?>");

    System.out.println(output);
  }

}
