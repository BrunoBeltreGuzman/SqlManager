/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Controlador.QueryCtrl;
import Data.QueryData;
import Modelo.Model;
import Modelo.ModelQuery;
import Vista.Ventana;

/**
 *
 * @author BrunoDev
 */
public class App {
    
    public static void iniciar(){
        Ventana ventana = new Ventana();
        QueryData query = new QueryData();
        Model model = new Model(ventana);
        ModelQuery modelQuery = new ModelQuery(ventana, model);
        QueryCtrl queryCtrl = new QueryCtrl(ventana, query, model, modelQuery);
        ventana.setVisible(true);
    }
    
    public static void main(String[] args) {
        
        iniciar();

    }
}
