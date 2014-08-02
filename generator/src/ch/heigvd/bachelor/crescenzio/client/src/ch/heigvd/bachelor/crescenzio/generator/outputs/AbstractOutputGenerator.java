/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.outputs;

import java.io.File;
import java.io.IOException;

/**
 * @author Fabio
 */
public abstract class AbstractOutputGenerator {
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

  private OutputApplication output;
  private String destinationPath;

  public AbstractOutputGenerator(OutputApplication output) {
    this.output = output;
  }

  public abstract void generate(File source, File destination) throws IOException;
}
