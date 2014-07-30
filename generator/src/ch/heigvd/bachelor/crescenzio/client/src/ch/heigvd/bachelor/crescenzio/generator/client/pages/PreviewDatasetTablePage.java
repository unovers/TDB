package ch.heigvd.bachelor.crescenzio.generator.client.pages;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.IColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import ch.heigvd.bachelor.crescenzio.generator.datasources.AbstractDataset;

public class PreviewDatasetTablePage extends AbstractPageWithTable<PreviewDatasetTablePage.Table> {
  private List<IColumn<?>> m_injectedColumns;
  private AbstractDataset dataset;

  public PreviewDatasetTablePage(AbstractDataset dataset) {
    super(false);
    this.dataset = dataset;
    callInitializer();
  }

  @Override
  protected Object[][] execLoadTableData(SearchFilter filter) throws ProcessingException {
    updateDynamicColumns();
    return dataset.getDatas();
  }

  private void updateDynamicColumns() throws ProcessingException {
    Table table = getTable();
    m_injectedColumns = new ArrayList<IColumn<?>>();
    for (Object o : dataset.getHeaders()) {
      m_injectedColumns.add(createDynamicStringColumn((String) o));
    }
    table.resetColumnConfiguration();
  }

  private IColumn<?> createDynamicStringColumn(final String label) {
    return new AbstractStringColumn() {
      @Override
      protected String getConfiguredHeaderText() {
        return label;
      }

      @Override
      public String getColumnId() {
        return label;
      }

      @Override
      protected int getConfiguredWidth() {
        return 120;
      }
    };
  }

  @Order(10.0f)
  public class Table extends AbstractTable {

    @Override
    protected boolean getConfiguredSortEnabled() {
      return false;
    }

    @Override
    protected void injectColumnsInternal(List<IColumn<?>> columnList) {
      if (m_injectedColumns != null) {
        columnList.addAll(m_injectedColumns);
      }
    }
  }

}
