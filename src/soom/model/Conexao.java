//package soom.model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class Conexao {
//
//    private static String NOME_BANCO="banco.sqlite";
//
//    private static String USER ="user";
//    private static String PASSWORD ="password";
//    private static String SERVIDOR="IP_SERVIDOR";
//
//
//    private  static String CON_STR_SQLITE="jdbc:sqlite"+NOME_BANCO;
//
//    private static String CON_STR_MYSQL="jdbc:mysql://"+SERVIDOR+"/"+NOME_BANCO;
//
//    private static String CON_STR=CON_STR_SQLITE;
//
//    private static int MAX_CON=5;
//
//    private  static  Connection[] connections = new Connection[MAX_CON];
//
//    public static Connection getConnection() throws SQLException{
//
//        for (int i=0; i<MAX_CON;i++){
//            if (connections[i]==null || connections[i].isClosed()){
//                connections[i] = DriverManager.getConnection(CON_STR);
//                return connections[i];
//            }
//        }
//        throw new SQLException("Muitas conexoes abertas!");
//
//    }
//
//
//}
//
//
//
//
//






package soom.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static String NOME_BANCO="banco.sqlite";

//    private static String USER ="user";
//    private static String PASSWORD ="password";
//    private static String SERVIDOR="IP_SERVIDOR";


    private  static String CON_STR="jdbc:sqlite:"+NOME_BANCO;

    //private static String CON_STR_MYSQL="jdbc:mysql://"+SERVIDOR+"/"+NOME_BANCO;

    //private static String CON_STR=CON_STR_SQLITE;

    private static int MAX_CON=5;

    private  static  Connection[] connections = new Connection[MAX_CON];

    public static Connection getConnection() throws SQLException {

        for (int i=0; i<MAX_CON;i++){
            if (connections[i]==null || connections[i].isClosed()){
                connections[i] = DriverManager.getConnection(CON_STR);
                return connections[i];
            }
        }
        throw new SQLException("Muitas conexoes abertas!");

    }

}





