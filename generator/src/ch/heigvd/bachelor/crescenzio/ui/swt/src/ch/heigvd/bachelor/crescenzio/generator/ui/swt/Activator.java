package ch.heigvd.bachelor.crescenzio.generator.ui.swt;

import org.eclipse.scout.rt.ui.swt.ISwtEnvironment;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import ch.heigvd.bachelor.crescenzio.generator.client.ClientSession;
import ch.heigvd.bachelor.crescenzio.generator.ui.swt.perspective.Perspective;

public class Activator implements BundleActivator {

  // the plugin id
  public static final String BUNDLE_ID = "ch.heigvd.bachelor.crescenzio.generator.ui.swt";

  private ISwtEnvironment m_environment;

  // the shared instance
  private static Activator m_bundle;

  @Override
  public void start(BundleContext context) throws Exception {
    m_bundle = this;
    m_environment = new SwtEnvironment(context.getBundle(), Perspective.ID, ClientSession.class);
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    m_bundle = null;
  }

  public static Activator getDefault() {
    return m_bundle;
  }

  public ISwtEnvironment getEnvironment() {
    return m_environment;
  }
}
