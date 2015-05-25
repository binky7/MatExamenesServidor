/*
 * Copyright (C) 2015 Jesús Donaldo Osornio Hernández
 *
 * This file is part of MatExamenes.
 *
 * MatExamenes is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MatExamenes is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package modelo.dto;

import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javax.swing.table.AbstractTableModel;

/**
 * Esta clase es un modelo de una tabla donde se almacenan los datos para
 * generar estadísticas. Estos datos son de tipo real y pueden ser exportados a
 * datos de gráfica de barras por medio de sus métodos.
 *
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class TablaEstadisticas extends AbstractTableModel {

    /**
     * Los nombres del las columnas de la tabla
     */
    private final String[] columnas;
    /**
     * La matriz de datos de la tabla
     */
    private final Object[][] datos;

    /**
     * Los datos BarChart que se generan a partir de la matriz de datos
     */
    private ObservableList<BarChart.Series> bcDatos;

    /**
     * Crea un nuevo objeto TablaEstadísticas con las columnas y la matriz de
     * datos especificada.
     *
     * @param columnas los nombres de las columnas de esta tabla
     * @param datos la matriz de datos que contendrá esta tabla
     */
    public TablaEstadisticas(String[] columnas, Object[][] datos) {

        this.columnas = columnas;
        this.datos = datos;
    }

    /**
     * Este método transforma la matriz de datos de esta tabla en series de
     * BarChart que se pueden utilizar para graficarse en una Gráfica de Barras
     * 
     * @return una ObservableList<BarChart.Series> con los datos de la matriz
     * transformados
     */
    public ObservableList<BarChart.Series> getBarChartDatos() {

        if (bcDatos == null) {
            //Recorrer todas las celdas de la matriz para agregarlas a las series
            bcDatos = FXCollections.<BarChart.Series>observableArrayList();
            for (int fila = 0; fila < getRowCount(); fila++) {
                ObservableList<BarChart.Data> series
                        = FXCollections.<BarChart.Data>observableArrayList();
                for (int columna = 0; columna < getColumnCount(); columna++) {
                    series.add(new BarChart.Data(getColumnName(columna),
                            getValueAt(fila, columna)));
                }
                bcDatos.add(new BarChart.Series(series));
            }
        }
        return bcDatos;
    }

    /**
     * 
     * @return la unidad de medición de los datos.
     */
    public double getTickUnit() {
        return 1000;
    }

    /**
     * 
     * @return la lista de nombres de las columnas.
     */
    public List<String> getColumnas() {
        return Arrays.asList(columnas);
    }

    /**
     * 
     * @return el total de filas en esta tabla
     */
    @Override
    public int getRowCount() {
        return datos.length;
    }

    /**
     * 
     * @return el total de columnas en esta tabla
     */
    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    /**
     * Obtiene el dato especificado en la celda de la tabla según la fila y la
     * columna especificadas
     *
     * @param row la fila de la celda
     * @param column la columna de la celda
     * @return el objeto que se encuentra en la celda de la fila y columna
     * especificada
     */
    @Override
    public Object getValueAt(int row, int column) {
        return datos[row][column];
    }

    /**
     * Obtiene el nombre de la columna en el número especificado
     * 
     * @param column el índice de la columna
     * @return el nombre de la columna en el índice especificado o null si no
     * existe nombre para esa columna.
     */
    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    /**
     * Obtiene la Clase del objeto en la columna especificada
     * 
     * @param column el índice de la columna
     * @return un objeto Class del tipo de datos en la columna especificada
     */
    @Override
    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }

    /**
     * Método para saber si una celda dada de esta tabla es editable.
     * 
     * @param row la fila de la celda
     * @param column la columna de la celda
     * @return true si la celda es editable
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    /**
     * Almacena el valor indicado en la celda indicada de esta tabla
     * 
     * @param value el objeto a almacenar en la tabla
     * @param row la fila de la celda
     * @param column la columna de la celda
     */
    @Override
    public void setValueAt(Object value, int row, int column) {
        if (value instanceof Double) {
            datos[row][column] = (Double) value;
        }

        fireTableCellUpdated(row, column);
    }
}
