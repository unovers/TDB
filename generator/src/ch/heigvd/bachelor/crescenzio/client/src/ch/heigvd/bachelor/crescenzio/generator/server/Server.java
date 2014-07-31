package ch.heigvd.bachelor.crescenzio.generator.server;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.Project;

public abstract class Server {
  private String host;
  private String rootFolder;

  protected Server(String host, String rootFolder) {
    this.host = host;
    this.rootFolder = rootFolder;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getRootFolder() {
    return rootFolder;
  }

  public void setRootFolder(String rootFolder) {
    this.rootFolder = rootFolder;
  }

  public abstract void generateScripts(Project project, String destination) throws ProcessingException;
}
