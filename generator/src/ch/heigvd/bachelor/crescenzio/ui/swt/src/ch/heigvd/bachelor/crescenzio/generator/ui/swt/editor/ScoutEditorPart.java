package ch.heigvd.bachelor.crescenzio.generator.ui.swt.editor;

import org.eclipse.scout.rt.ui.swt.ISwtEnvironment;
import org.eclipse.scout.rt.ui.swt.window.desktop.editor.AbstractScoutEditorPart;

import ch.heigvd.bachelor.crescenzio.generator.ui.swt.Activator;

public class ScoutEditorPart extends AbstractScoutEditorPart {

  @Override
  protected ISwtEnvironment getSwtEnvironment() {
    return Activator.getDefault().getEnvironment();
  }
}
