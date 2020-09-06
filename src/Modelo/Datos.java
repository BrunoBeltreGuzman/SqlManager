/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Data.QueryData;
import Vista.Ventana;

/**
 *
 * @author BrunoDev
 */
public class Datos{
    
    public void setDatos(Ventana ventana, QueryData queryData){
        queryData.setQuery(ventana.getTexQuerys().getText().trim().toString());
    }
}
