/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.outputs;

/**
 * @author Fabio
 */
public abstract class AbstractOutputGenerator {
  private OutputApplication output;
  private String destinationPath;

  public AbstractOutputGenerator(OutputApplication output, String destinationPath) {
    this.output = output;
    this.destinationPath = destinationPath;
  }

  public abstract void generate();
}
