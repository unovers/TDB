/**
 * 
 */
package ch.heigvd.bachelor.crescenzio.generator.shared;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 * 
 * @generated
 */
@Generated(value = "org.eclipse.scout.sdk.workspace.dto.pagedata.PageDataDtoUpdateOperation", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class OutputsTablePageData extends AbstractTablePageData {

  private static final long serialVersionUID = 1L;

  public OutputsTablePageData() {
  }

  @Override
  public OutputsTableRowData addRow() {
    return (OutputsTableRowData) super.addRow();
  }

  @Override
  public OutputsTableRowData addRow(int rowState) {
    return (OutputsTableRowData) super.addRow(rowState);
  }

  @Override
  public OutputsTableRowData createRow() {
    return new OutputsTableRowData();
  }

  @Override
  public Class<? extends AbstractTableRowData> getRowType() {
    return OutputsTableRowData.class;
  }

  @Override
  public OutputsTableRowData[] getRows() {
    return (OutputsTableRowData[]) super.getRows();
  }

  @Override
  public OutputsTableRowData rowAt(int index) {
    return (OutputsTableRowData) super.rowAt(index);
  }

  public void setRows(OutputsTableRowData[] rows) {
    super.setRows(rows);
  }

  public static class OutputsTableRowData extends AbstractTableRowData {

    private static final long serialVersionUID = 1L;

    public OutputsTableRowData() {
    }
  }
}