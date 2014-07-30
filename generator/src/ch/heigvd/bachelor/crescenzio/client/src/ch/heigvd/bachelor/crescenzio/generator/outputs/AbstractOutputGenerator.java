/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.outputs;

/**
 * @author Fabio
 */
public abstract class AbstractOutputGenerator {
  private OutputApplication output;

  public AbstractOutputGenerator(OutputApplication output) {
    this.output = output;
  }

  public abstract void generate();
}
