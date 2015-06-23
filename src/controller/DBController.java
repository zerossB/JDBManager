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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.error;

/**
 * Classe responsavel por fazer o trabalho pesado no Banco de dados
 *  - Pega as informações do Banco
 *  - Da as informações para popular a View main.java
 *  - Faz os Selects da vida
 * 
 * @author zerossB
 */
public class DBController {
    //Conexão com o Banco de dados
    private final java.sql.Connection connection;
    
    //Vetores com as informações de colunas e Linhas resultantes
    //da pesquisa das tabelas no Banco de Dados
    private final java.util.Vector colums;
    private final java.util.Vector rows;
    
    /**
     * Contrutor
     * @param connection - Conexão com o Banco de Dados.
     */
    public DBController(Connection connection) {
        this.connection = connection;
        //Instancia as linhas e colunas como 0
        colums = new java.util.Vector<>();
        rows = new java.util.Vector<>();
    }
    
    /**
     * Lista as Tabelas que existe no Banco de dados
     * @return - Lista de Tabelas do Banco de Dados
     */
    public List<String> getTables() {
        //Lista de tabelas
        List<String> list = new ArrayList<>();
        
        //Pega os nomes das tabelas do Banco
        try {
            java.sql.DatabaseMetaData md = connection.getMetaData();
            java.sql.ResultSet rs = md.getTables(null, null, "%", null);
            while (rs.next()) {
                //Adicona o nome das tabelas na lista
                list.add(rs.getString(3));
            }
        } catch (SQLException ex) {
            new error(null, "SQLException", ex.getMessage());
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Retorna a lista populada
        return list;
    }
    
    /**
     * Metodo para popular Jtree com tabelas e colunas do Banco
     * @param a - modelode acesso ao Banco
     * @return node populada com Tabelas do Banco
     */
    public javax.swing.tree.DefaultMutableTreeNode getNodeRoot(model.Access a) {
        javax.swing.tree.DefaultMutableTreeNode root
                = new javax.swing.tree.DefaultMutableTreeNode(a.getBanco());

        try {
            final String query = "SELECT SYS.SYSTABLES.TABLENAME, "
                    + "SYS.SYSCOLUMNS.COLUMNNUMBER, "
                    + "SYS.SYSCOLUMNS.COLUMNNAME, "
                    + "SYS.SYSCOLUMNS.COLUMNDATATYPE, "
                    + "SYS.SYSCOLUMNS.AUTOINCREMENTSTART, "
                    + "SYS.SYSCOLUMNS.AUTOINCREMENTINC "
                    + "FROM SYS.SYSCOLUMNS, SYS.SYSTABLES "
                    + "WHERE SYS.SYSCOLUMNS.REFERENCEID = SYS.SYSTABLES.TABLEID "
                    + "AND SYS.SYSTABLES.TABLETYPE = 'T' "
                    + "ORDER BY SYS.SYSTABLES.TABLENAME, SYS.SYSCOLUMNS.COLUMNNUMBER";
            java.sql.PreparedStatement st = connection.prepareStatement(query);
            java.sql.ResultSet rs = st.executeQuery();

            String tableName = "";
            javax.swing.tree.DefaultMutableTreeNode tableNode
                    = new javax.swing.tree.DefaultMutableTreeNode(a.getBanco());
            
            while (rs.next()) {
                String name = rs.getString(1);
                if (tableName.compareTo(name) != 0) {
                    tableName = name;
                    tableNode = new javax.swing.tree.DefaultMutableTreeNode(tableName);
                    root.add(tableNode);
                }
                javax.swing.tree.DefaultMutableTreeNode colNode 
                        = new javax.swing.tree.DefaultMutableTreeNode(rs.getString(3));
                tableNode.add(colNode);
            }

            root.add(tableNode);
        } catch (SQLException ex) {
            new error(null, "SQLException", ex.getMessage());
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return root;
    }
    
    /**
     * Retorna vetor de Colunas
     * @return  Vector
     */
    public Vector getColums() {
        return colums;
    }
    
    /**
     * Retorna vetor de Linhas
     * @return Vector
     */
    public Vector getRows() {
        return rows;
    }
    
    /**
     * Carrega os Vetores com as informações do banco de dados
     * @param sql - Comando sql para executar população
     */
    public void loadVectors(String sql) {
        try {
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            if (ps.execute()) { //Select
                java.sql.ResultSet rs = ps.getResultSet();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    colums.add(rs.getMetaData().getColumnName(i));
                }

                while (rs.next()) {
                    java.util.Vector line = new java.util.Vector<>();
                    //Verifica o tipo de cada Linha da tabela
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        switch (rs.getMetaData().getColumnType(i)) {
                            case java.sql.Types.DATE:
                                line.addElement(rs.getDate(i));
                                break;
                            case java.sql.Types.TIME:
                                line.addElement(rs.getTime(i));
                                break;
                            case java.sql.Types.BOOLEAN:
                            case java.sql.Types.BIT:
                                line.addElement(rs.getBoolean(i));
                                break;
                            case java.sql.Types.NUMERIC:
                            case java.sql.Types.DECIMAL:
                            case java.sql.Types.FLOAT:
                            case java.sql.Types.REAL:
                                line.addElement(new Float(rs.getFloat(i)));
                                break;
                            case java.sql.Types.DOUBLE:
                                line.addElement(new Double(rs.getDouble(i)));
                                break;
                            case java.sql.Types.TINYINT:
                                line.addElement(new Byte(rs.getByte(i)));
                                break;
                            case java.sql.Types.INTEGER:
                                line.addElement(new Integer(rs.getInt(i)));
                                break;
                            case java.sql.Types.BIGINT:
                                line.addElement(new Long(rs.getLong(i)));
                                break;
                            default:
                                line.addElement(rs.getString(i));
                                break;
                        }
                    }
                    rows.add(line);
                }
            }
        } catch (SQLException ex) {
            new error(null, "SQLException", ex.getMessage());
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
