/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saurabh
 */
public class trySQL {

    public static void main(String[] args) {
        SQLMethods s = new SQLMethods();
        //ResultSet r = s.searchPendingWithID("2014-12-15_Wilson_Alex_Preferences.xml");
        ResultSet r = s.runQuery("SELECT * FROM completedJobs");
        try {
            while (r.next()) {
                System.out.println(r.getString("fileName"));
                System.out.println(r.getString("buildName"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(trySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
