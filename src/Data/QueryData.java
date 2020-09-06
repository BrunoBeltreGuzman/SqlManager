/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author BrunoDev
 */
public class QueryData {

    private String query, tipoQuery, database, table;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTipoQuery() {
        return tipoQuery;
    }

    public void setTipoQuery(String tipoQuery) {
        this.tipoQuery = tipoQuery;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }  
}
