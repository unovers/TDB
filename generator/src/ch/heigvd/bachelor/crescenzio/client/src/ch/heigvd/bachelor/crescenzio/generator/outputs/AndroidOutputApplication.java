package ch.heigvd.bachelor.crescenzio.generator.outputs;

import ch.heigvd.bachelor.crescenzio.generator.Project;

public class AndroidOutputApplication extends AbstractOutputApplication {

  /**
   * @param project
   * @param name
   */
  public AndroidOutputApplication(Project project, String name) {
    super(project, name);
  }

  @Override
  public void generate() {
  }

  @Override
  public AndroidOutputApplication clone() {
    //AndroidOutputApplication output = new AndroidOutputApplication(project, name);

    return null;
  }
}
