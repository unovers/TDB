/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.outputs;

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

  /**
   * @return the destinationPath
   */
  public String getDestinationPath() {
    return destinationPath;
  }

  /**
   * @param destinationPath
   *          the destinationPath to set
   */
  public void setDestinationPath(String destinationPath) {
    this.destinationPath = destinationPath;
  }

  private OutputApplication output;
  private String destinationPath;

  public AbstractOutputGenerator(OutputApplication output, String destinationPath) {
    this.output = output;
    this.destinationPath = destinationPath;
  }

  public abstract void generate(String destination) throws IOException;
}
