package org.perscholas.sba.utils;

import org.perscholas.sba.SMSRunner;
import org.perscholas.sba.controller.TableConnection;
import org.perscholas.sba.utils.FileReader;
import org.perscholas.sba.utils.DirectoryReference;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;

public class JdbcConfigurator {
    public static void executeQuery() throws SQLException, FileNotFoundException {
        TableConnection connection = new TableConnection();
        connection.initialize();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded.....");
        }
        catch(Exception e)
        {
            System.out.println("Error : "+e.toString());
            e.printStackTrace();
        }
        executeSqlFiles("populate-course.sql");
        executeSqlFiles("populate-student.sql");
    }
    /*private static void executeSqlFile(String fileName) throws SQLException, FileNotFoundException {
        File creationStatementFile = DirectoryReference.RESOURCE_DIRECTORY.getFileFromDirectory(fileName);
        FileReader fileReader = new FileReader(creationStatementFile.getAbsolutePath());
        System.out.println(creationStatementFile.getAbsolutePath());
        String[] statements = fileReader.toString().split(";");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms", "abi","123");
        Statement stmt = conn.createStatement();

        for(int i = 0; i<statements.length; i++)
        {
            // we ensure that there is no spaces before or after the request string
            // in order to not execute empty statements
            if(!statements[i].trim().equals(""))
            {
                stmt.executeUpdate(statements[i]);
                System.out.println(">>"+statements[i]);
            }
        }
    }
    */
    private static void executeSqlFiles(String fileName) {
        String path = SMSRunner.class.getClassLoader().getResource(fileName).getPath();
        System.out.println(path);
        String s = new String();
        StringBuffer sb = new StringBuffer();


        try
        {
            File creationStatementFile = new File((new StringBuilder()
                    .append(System.getProperty("user.dir")) // local directory
                    .append("/src/main/resources/")
                    .append(fileName))
                    .toString());
            //File creationStatementFile = new File(filePath);
            //File creationStatementFile = DirectoryReference.RESOURCE_DIRECTORY.getFileFromDirectory(fileName);
            //File creationStatementFile = ge
            FileReader fileReader = new FileReader(creationStatementFile.getAbsolutePath());
            System.out.println(creationStatementFile.getAbsolutePath());
            //FileReader fr = new FileReader(new File(path));
            // be sure to not have line starting with "--" or "/*" or any other non aplhabetical character

            //BufferedReader br = new BufferedReader(fileReader);

            //while((s = br.readLine()) != null)
            //{
            //    sb.append(s);
            //}
            //br.close();

            // here is our splitter ! We use ";" as a delimiter for each request
            // then we are sure to have well formed statements
            String[] statements = fileReader.toString().split(";");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms", "abi","123");
            Statement stmt = conn.createStatement();

            for(int i = 0; i<statements.length; i++)
            {
                // we ensure that there is no spaces before or after the request string
                // in order to not execute empty statements
                if(!statements[i].trim().equals(""))
                {
                    stmt.executeUpdate(statements[i]);
                    System.out.println(">>"+statements[i]);
                }
            }

        }
        catch(Exception e)
        {
            System.out.println("*** Error : "+e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
            System.out.println("################################################");
            System.out.println(sb.toString());
        }
    }
}
