/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Data.QueryData;
import Modelo.Datos;
import Modelo.Model;
import Modelo.ModelQuery;
import Vista.Colores;
import Vista.Ventana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author BrunoDev
 */
public class QueryCtrl implements ActionListener, MouseListener {

    private Ventana ventana;
    private QueryData queryData;
    private Model model;
    private ModelQuery modelQuery;

    Colores colores = new Colores();

    public QueryCtrl(Ventana ventana, QueryData queryData, Model model, ModelQuery modelQuery) {
        this.ventana = ventana;
        this.queryData = queryData;
        this.model = model;
        this.modelQuery = modelQuery;

        ventana.getBtnEjecutar().addActionListener(this);
        ventana.getBtnCancelar().addActionListener(this);
        ventana.getTexQuerys().addMouseListener(this);
        ventana.getTable2().addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == ventana.getBtnEjecutar()) {
            Datos datos = new Datos();
            datos.setDatos(ventana, queryData);
            modelQuery.ejecutarQuery(ventana, queryData);
        }

        if (e.getSource() == ventana.getBtnCancelar()) {
            model.cancelarLimpiar(ventana);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (e.getSource() == ventana.getTexQuerys()) {
            ventana.getTexQuerys().setBorder(javax.swing.BorderFactory.createLineBorder(colores.getBorderAzul(), 3));
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == ventana.getTexQuerys()) {
            ventana.getTexQuerys().setBorder(javax.swing.BorderFactory.createLineBorder(colores.getGris3(), 3));
        }
    }

}
