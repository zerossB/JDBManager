/*
 * Copyright (C) 2015 zerossB
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Developer: zerossB - https://github.com/zerossB
 */
package controller;

/**
 * Classe responsável pela conexão e desconexão com o SGBD
 *
 * @author jsqlgen - https://github.com/zerossB/JSQLGen
 */
public class DBAccess {

    private java.sql.Connection connection;
    
    /**
     * Construtor
     *
     * @throws ClassNotFoundException throws when JDBC Driver is not found
     * @throws java.sql.SQLException throws when Driver Manager can't connect to
     * Database
     */
    public DBAccess(model.Access access)throws
            ClassNotFoundException, java.sql.SQLException {
        String drv = access.getDriver();
        String url = "jdbc:derby://"+access.getUrl()+":"+access.getDoor()
                +"/"+access.getBanco()+";territory=pt_BR;create=TRUE";
        String user = access.getUser();
        String password = access.getPassword();
        connect(drv, url, user, password);
    }

    /**
     * Conecta no Banco de Dados
     *
     * @param drv
     * @param url
     * @param user
     * @param password
     * @throws ClassNotFoundException throws when JDBC Driver is not found
     * @throws java.sql.SQLException throws when Driver Manager can't connect to
     * Database
     */
    private void connect(String drv, String url, String user, String password) throws ClassNotFoundException, java.sql.SQLException {
        Class.forName(drv);
        connection = java.sql.DriverManager.getConnection(url, user, password);
    }
    
    /**
     * Desconecta no Banco de Dados
     *
     * @throws java.sql.SQLException throws when Driver Manager can't disconnect
     * from Database
     */
    public void disconnect() throws java.sql.SQLException {
        connection.commit();
        connection.close();
    }

    /**
     * @return the connection
     */
    public java.sql.Connection getConnection() {
        return connection;
    }

    /**
     * Cria tabelas
     *
     * @throws java.sql.SQLException throws when Driver Manager can't disconnect
     * from Database
     */
    private void createTables() throws java.sql.SQLException {
        String sql = "SELECT COUNT(*) FROM SYS.SYSTABLES WHERE TABLETYPE='T'";
        java.sql.Statement statement = connection.createStatement();
        java.sql.ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            if (resultSet.getInt(1) == 0) {
                if (javax.swing.JOptionPane.showConfirmDialog(null, "O sistema criará o banco de dados.\nClique em <OK> para continuar.", "Aviso", javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE) == javax.swing.JOptionPane.OK_OPTION) {

                } else {
                    statement.close();
                    this.disconnect();
                    System.exit(1);
                }
            }
        }
        statement.close();
    }
}
