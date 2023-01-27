package org.perscholas.sba;

import org.perscholas.sba.controller.TableConnection;
import org.perscholas.sba.utils.JdbcConfigurator;

import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class SMSRunner
{
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        System.out.println("\nSchool Management System.......");
        System.out.println("Clean database......");
        TableConnection create = new TableConnection();
        create.dropTables();
        System.out.println("\nCreating Tables.......");
        create.createTables();
        System.out.println("\nInserting Records.......");
        JdbcConfigurator connect = new JdbcConfigurator();
        connect.executeQuery();

    }
}
