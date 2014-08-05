/**
 * Nom du fichier         : AbstractOutputGenerator.java
 * Version                : 1.0
 * Auteur                 : Crescenzio Fabio
 *
 * Date dernière révision : 30.07.2014
 *
 * Commentaires           : Définit un génerateur abstrait pour une application mobile
 *
 * Historiques des modifications
 * -
 */
package ch.heigvd.bachelor.crescenzio.generator.outputs;

import java.io.File;
import java.io.IOException;

/**
 * Define an abstact output generator for an application
 *
 * @author Fabio CRESCENZIO
 * @version 1.0
 */
public abstract class AbstractOutputGenerator {
  private OutputApplication output;

  public AbstractOutputGenerator(OutputApplication output) {
    this.output = output;
  }

  /**
   * @return the output
   */
  public OutputApplication getOutput() {
    return output;
  }

  /**
   * @param output
   *          the output to set
   */
  public void setOutput(OutputApplication output) {
    this.output = output;
  }

  public abstract void generate(File source, File destination) throws IOException;
}
