/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author Jesus Donaldo
 */
public class TablaEstadisticas extends AbstractTableModel {
    
    private final String[] names;
    private final Object[][] data;
    
    private ObservableList<BarChart.Series> bcData;
//    private String[] names = {"Leonardo", "Angel", "Juan"};
// 
//    private Object[][] data = {
//	    {new Double(100)},
//            {new Double(70)},
//            {new Double(85)}
//        };

    public TablaEstadisticas(String[] columns, Object[][] data) {
        
        names = columns;
        this.data = data;
    }

    public ObservableList<BarChart.Series> getBarChartData() {
        
        if (bcData == null) {
            bcData = FXCollections.<BarChart.Series>observableArrayList();
            for (int row = 0; row < getRowCount(); row++) {
                ObservableList<BarChart.Data> series =
FXCollections.<BarChart.Data>observableArrayList();
                for (int column = 0; column < getColumnCount(); column++) {
                    series.add(new BarChart.Data(getColumnName(column),
getValueAt(row, column)));
                }
                bcData.add(new BarChart.Series(series));
            }
        }
        return bcData;
    }
    
    public double getTickUnit() {
        return 1000;
    }
    public List<String> getColumnNames() {
        return Arrays.asList(names);
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return names.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        return data[row][column];
    }

    @Override
    public String getColumnName(int column) {
        return names[column];
    }

    @Override
    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        if (value instanceof Double) {
            data[row][column] = (Double)value;
        }

        fireTableCellUpdated(row, column);
    }
}