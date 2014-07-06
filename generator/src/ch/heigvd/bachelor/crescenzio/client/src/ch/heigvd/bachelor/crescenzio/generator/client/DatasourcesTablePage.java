/**
 * 
 */
package ch.heigvd.bachelor.crescenzio.generator.client;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.annotations.PageData;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.extension.client.ui.desktop.outline.pages.AbstractExtensiblePageWithTable;
import org.eclipse.scout.rt.shared.TEXTS;

import ch.heigvd.bachelor.crescenzio.generator.client.DatasourcesTablePage.Table;
import ch.heigvd.bachelor.crescenzio.generator.shared.DatasourcesTablePageData;

/**
 * @author Fabio
 */
@PageData(DatasourcesTablePageData.class)
public class DatasourcesTablePage extends AbstractExtensiblePageWithTable<Table> {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Datasources");
  }

  @Order(10.0)
  public class Table extends AbstractExtensibleTable {
  }
}
