/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Connection.Conexion;
import Data.QueryData;
import Vista.Colores;
import Vista.Ventana;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BrunoDev
 */
public class ModelQuery extends Conexion {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Statement statement;
    
    private Ventana ventana;
    private Model model; 
    

    public ModelQuery(Ventana ventana, Model model) {
        this.ventana = ventana;
        this.model = model;
        
        model.cargarHistory(ventana);
    }

    public void conetarConexion() {
        connection = getConexion();
    }

    public void cerrarConexion() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cerrar conexion", " ", ERROR_MESSAGE);
                JOptionPane.showMessageDialog(null, "Error: " + ex, " ", ERROR_MESSAGE);
            }
        }
    }

    private void errorCatch(Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al ejecutar Query", "ERROR SQL", ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null, "ERROR: " + e, "ERROR SQL", ERROR_MESSAGE);
        ventana.getEtqNotif().setText("Error al ejecutar Query");
    }

    
    public void select(QueryData query, Ventana v) {
        conetarConexion();
        try {

            DefaultTableModel modelo = new DefaultTableModel();
            v.getTable().setModel(modelo);

            preparedStatement = connection.prepareStatement(query.getQuery());
            resultSet = preparedStatement.executeQuery();

            ResultSetMetaData rsmd = resultSet.getMetaData();
            int cantidadDeCulumnas = rsmd.getColumnCount();

            modelo.addColumn("colum1");
            modelo.addColumn("colum2");
            modelo.addColumn("colum3");
            modelo.addColumn("colum4");
            modelo.addColumn("colum5");
            modelo.addColumn("colum6");
            modelo.addColumn("colum7");
            //...
            
            Colores colores = new Colores();
            
            v.getTable().getTableHeader().setFont(new java.awt.Font("Arial", 0, 20));
            v.getTable().getTableHeader().setOpaque(false);
            v.getTable().getTableHeader().setBackground(colores.getColorPrymary());
            v.getTable().getTableHeader().setForeground(Color.WHITE);
            v.getTable().setRowHeight(24);
            v.getTable().setBorder(null);
            v.getTable().setFont(new java.awt.Font("Arial", 0, 18));
            v.getTable().setForeground(Color.WHITE);
            v.getTable().setBackground(colores.getGris1());
            v.getTable().setRowHeight(20);

            int[] Ancho = {36, 160, 160, 160, 160, 160, 160};
            //...

            for (int x = 0; x < cantidadDeCulumnas; x++) {
                v.getTable().getColumnModel().getColumn(x).setPreferredWidth(Ancho[x]);
            }
            int result = 0;
            while (resultSet.next()) {
                Object[] fila = new Object[cantidadDeCulumnas];
                for (int i = 0; i < cantidadDeCulumnas; i++) {
                    fila[i] = resultSet.getObject(i + 1);
                    result++;
                }
                modelo.addRow(fila);
                v.getEtqNotif().setText("Query ejecuatada correctamente, resultados: " + result);

            }
            if (result == 0) {
                JOptionPane.showMessageDialog(null, "No se encontraron registros", " ", WARNING_MESSAGE);
            }
            model.guardarHistory(query);
        } catch (Exception e) {
            errorCatch(e);
        } finally {
            cerrarConexion();
        }
    }

    public void update(QueryData queryData, Ventana v) {
        conetarConexion();
        try {
            statement = connection.createStatement();
            int result = statement.executeUpdate(queryData.getQuery());
            if (result > 0) {
                v.getEtqNotif().setText("Query ejecuatada correctamente");
                model.guardarHistory(queryData);
            }
            JOptionPane.showMessageDialog(null, "Query ejecuatada correctamente");
            v.getEtqNotif().setText("Filas afectadas: " + result);
        } catch (Exception e) {
            errorCatch(e);
        } finally {
            cerrarConexion();
        }
    }
    
      public void ejecutarQuery(Ventana ventana, QueryData queryData) {

        if (queryData.getQuery().startsWith("s")) {
            select(queryData, ventana);
        } else {
            if(!"".equals(queryData.getQuery())){
             update(queryData, ventana);   
            }
        }
        model.cargarHistory(ventana);
    }
      
}
