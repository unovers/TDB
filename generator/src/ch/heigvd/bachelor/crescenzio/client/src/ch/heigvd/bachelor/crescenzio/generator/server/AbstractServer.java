/**
 * Nom du fichier         : AbstractServer.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit un serveur abstrait
 *
 * Historiques des modifications
 * -
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.server;

import org.eclipse.scout.commons.exception.ProcessingException;

import ch.heigvd.bachelor.crescenzio.generator.Project;

/**
 * Define an abstract server
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public abstract class AbstractServer {
  private String host; //l'hote
  private String rootFolder; //définit le répertoire ou sera placé les scripts sur le serveur

  protected AbstractServer(String host, String rootFolder) {
    this.host = host;
    this.rootFolder = rootFolder;
  }

  /**
   * @return the host
   */
  public String getHost() {
    return host;
  }

  /**
   * @param host
   *          the host to set
   */
  public void setHost(String host) {
    this.host = host;
  }

  /**
   * @return the root folder
   */
  public String getRootFolder() {
    return rootFolder;
  }

  /**
   * @param rootFolder
   *          the root folder to set
   */
  public void setRootFolder(String rootFolder) {
    this.rootFolder = rootFolder;
  }

  /**
   * Define how the script is generated for a project in a destination
   * 
   * @param project
   * @param destination
   * @throws ProcessingException
   */
  public abstract void generateScripts(Project project, String destination) throws ProcessingException;
}
