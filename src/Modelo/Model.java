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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BrunoDev
 */
public class Model extends Conexion {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Statement statement;

    private Ventana ventana;

    public Model(Ventana ventana) {
        this.ventana = ventana;
        cargarHistory(ventana);
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

    public void guardarHistory(QueryData queryData) {
        conetarConexion();
        if (!"".equals(queryData.getQuery())) {
            try {
                statement = connection.createStatement();
                statement.executeUpdate("insert into histoy (histoy) Values ('" + queryData.getQuery() + "')");
            } catch (Exception e) {
                errorCatch(e);
            } finally {
                cerrarConexion();
            }
        } else {

        }
    }

    public void cargarHistory(Ventana v) {
        conetarConexion();
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            v.getTable2().setModel(modelo);
            String sql = "select * from histoy order by id desc";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            ResultSetMetaData rsmd = resultSet.getMetaData();
            int cantidadDeCulumnas = rsmd.getColumnCount();

            modelo.addColumn("Id");
            modelo.addColumn("History");
            //...

            Colores colores = new Colores();
            v.getTable2().getTableHeader().setFont(new java.awt.Font("Arial", 0, 20));
            v.getTable2().getTableHeader().setOpaque(false);
            v.getTable2().getTableHeader().setBackground(colores.getColorPrymary());
            v.getTable2().getTableHeader().setForeground(Color.WHITE);
            v.getTable2().setRowHeight(24);
            v.getTable2().setBorder(null);
            v.getTable2().setFont(new java.awt.Font("Arial", 0, 18));
            v.getTable2().setForeground(Color.WHITE);
            v.getTable2().setBackground(colores.getGris1());
            v.getTable2().setRowHeight(20);

            int[] Ancho = {20, 500};

            for (int x = 0; x < cantidadDeCulumnas; x++) {
                v.getTable2().getColumnModel().getColumn(x).setPreferredWidth(Ancho[x]);
            }

            int result = 0;
            while (resultSet.next()) {
                Object[] fila = new Object[cantidadDeCulumnas];
                for (int i = 0; i < cantidadDeCulumnas; i++) {
                    fila[i] = resultSet.getObject(i + 1);
                    result++;
                }
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            errorCatch(e);
        } finally {
            cerrarConexion();
        }
    }

    public void cancelarLimpiar(Ventana ventana) {
        ventana.getTexQuerys().setText("");
        ventana.getEtqNotif().setText("");
        cargarHistory(ventana);
    }

}
