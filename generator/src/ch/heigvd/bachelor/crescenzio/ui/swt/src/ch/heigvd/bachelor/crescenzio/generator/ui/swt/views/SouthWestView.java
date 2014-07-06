package ch.heigvd.bachelor.crescenzio.generator.ui.swt.views;

import org.eclipse.scout.rt.ui.swt.ISwtEnvironment;
import org.eclipse.scout.rt.ui.swt.window.desktop.view.AbstractScoutView;

import ch.heigvd.bachelor.crescenzio.generator.ui.swt.Activator;

public class SouthWestView extends AbstractScoutView {

  public SouthWestView() {
  }

  @Override
  protected ISwtEnvironment getSwtEnvironment() {
    return Activator.getDefault().getEnvironment();
  }
}
