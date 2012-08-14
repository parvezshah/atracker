/**
 * 
 */
package gabriel.atrack.client.gwt.homepage.client;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author parvezshah
 *
 */
public class HomePageActivityStreamWidget implements EntryPoint {

	// The list of data to display.
	  private static final List<String> DAYS = Arrays.asList("Sunday", "Monday",
	      "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");

	  @Override
	  public void onModuleLoad() {
	    // Create a cell to render each value in the list.
	    TextCell textCell = new TextCell();

	    // Create a CellList that uses the cell.
	    CellList<String> cellList = new CellList<String>(textCell);

	    // Set the total row count. This isn't strictly necessary, but it affects
	    // paging calculations, so its good habit to keep the row count up to date.
	    cellList.setRowCount(DAYS.size(), true);

	    // Push the data into the widget.
	    cellList.setRowData(0, DAYS);

	    // Add it to the root panel.
	    RootPanel.get().add(cellList);
	  }


}
