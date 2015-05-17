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
    
    private final String[] columnas;
    private final Object[][] datos;
    
    private ObservableList<BarChart.Series> bcDatos;
//    private String[] names = {"Leonardo", "Angel", "Juan"};
// 
//    private Object[][] data = {
//	    {new Double(100)},
//            {new Double(70)},
//            {new Double(85)}
//        };

    public TablaEstadisticas(String[] columnas, Object[][] datos) {
        
        this.columnas = columnas;
        this.datos = datos;
    }

    public ObservableList<BarChart.Series> getBarChartDatos() {
        
        if (bcDatos == null) {
            bcDatos = FXCollections.<BarChart.Series>observableArrayList();
            for (int fila = 0; fila < getRowCount(); fila++) {
                ObservableList<BarChart.Data> series =
FXCollections.<BarChart.Data>observableArrayList();
                for (int columna = 0; columna < getColumnCount(); columna++) {
                    series.add(new BarChart.Data(getColumnName(columna),
getValueAt(fila, columna)));
                }
                bcDatos.add(new BarChart.Series(series));
            }
        }
        return bcDatos;
    }
    
    public double getTickUnit() {
        return 1000;
    }
    public List<String> getColumnas() {
        return Arrays.asList(columnas);
    }

    @Override
    public int getRowCount() {
        return datos.length;
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        return datos[row][column];
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
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
            datos[row][column] = (Double)value;
        }

        fireTableCellUpdated(row, column);
    }
}