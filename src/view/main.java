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
package view;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zerossB
 */
public class main extends javax.swing.JFrame {
    //Conexão com o Banco Derby
    private java.sql.Connection connection;
    //Classe de acesso ao Banco
    private model.Access access;
    
    //Dialog Modal para receber a conexão
    private ConnDB dialog;

    //JTree com a Listagem das Tabelas e campos do Banco de Dados
    private javax.swing.JTree jTree;
    //Node Root da Jtree
    private javax.swing.tree.DefaultMutableTreeNode root;

    /**
     * Construtor da tela Main.
     */
    public main() {
        initComponents();

        epSQL.setEditable(false);
        bSQL.setEnabled(false);
        
        //Coloca o Icone do Derby como Icone do programa
        java.net.URL url = this.getClass().getResource("/images/db_derby.png");
        java.awt.Image imagemTitulo = java.awt.Toolkit.getDefaultToolkit()
                .getImage(url);
        this.setIconImage(imagemTitulo);
    }
    
    /**
     * Chamado quando o modal retornar a Conexão com o banco de dados
     * Seta a GUI as informações recebidas do Banco Derby.
     */
    private void setGuiInformation() {
        this.connection = dialog.getConnection();
        this.access = dialog.getAccess();

        this.setTitle(access.getUrl() + ":" + access.getDoor()
                + "/" + access.getBanco());

        controller.DBController dbc = new controller.DBController(connection);
        root = dbc.getNodeRoot(access);

        jTree = new javax.swing.JTree(root);
        jTree.treeDidChange();
        jTree.collapseRow(0);
        jTree.expandRow(0);

        jTree.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() >= 2) {
                    epSQL.setText("SELECT * FROM " + jTree.getSelectionPath()
                            .getPathComponent(1).toString());
                    bSQL.doClick();
                }
                if (jTree.getSelectionCount() == 1) {
                    if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {
                        javax.swing.JPopupMenu jpm = new javax.swing.JPopupMenu();
                        javax.swing.JMenuItem select
                                = new javax.swing.JMenuItem("SELECT");
                        javax.swing.JMenuItem insert
                                = new javax.swing.JMenuItem("INSERT");
                        javax.swing.JMenuItem update
                                = new javax.swing.JMenuItem("UPDATE");
                        javax.swing.JMenuItem delete
                                = new javax.swing.JMenuItem("DELETE");

                        jpm.add(select);
                        jpm.add(insert);
                        jpm.add(update);
                        jpm.add(delete);
                        
                        select.addActionListener((ActionEvent e1) -> {
                            epSQL.setText("SELECT * FROM " + jTree.getSelectionPath()
                                    .getPathComponent(1).toString());
                        });

                        insert.addActionListener((ActionEvent e1) -> {
                            epSQL.setText("INSERT INTO " + jTree.getSelectionPath()
                                    .getPathComponent(1).toString() + "() \n VALUES ()");
                        });

                        update.addActionListener((ActionEvent e1) -> {
                            epSQL.setText("UPDATE " + jTree.getSelectionPath()
                                    .getPathComponent(1).toString()
                                    +"SET key = value WHERE id =");
                        });

                        delete.addActionListener((ActionEvent e1) -> {
                            epSQL.setText("DELETE " + jTree.getSelectionPath()
                                    .getPathComponent(1).toString() +
                                    " WHERE id = ");
                        });

                        jpm.show(jTree, e.getX(), e.getY());
                    }
                }
            }
        });

        epSQL.setEditable(true);
        epSQL.setEnabled(true);
        bSQL.setEnabled(true);

        spArvore.setViewportView(jTree);
        repaint();
    }
    
    /**
     * Fecha Conexão com o Banco de Dados.
     */
    private void closeConnection() {
        if (this.connection != null) {
            if (javax.swing.JOptionPane.showConfirmDialog(this,
                    "Want close Connection with Database?", "Close Connection?",
                    javax.swing.JOptionPane.OK_CANCEL_OPTION)
                    == javax.swing.JOptionPane.OK_OPTION) {
                try {
                    this.connection.close();
                    epSQL.setText("");
                    epSQL.setEditable(false);
                    epSQL.setEnabled(false);
                    bSQL.setEnabled(false);
                    pArvore.removeAll();
                    pResultados.removeAll();
                    repaint();
                } catch (SQLException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE,
                            null, ex);
                }
            } else {
                try {
                    this.connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE,
                            null, ex);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMAction = new javax.swing.JToolBar();
        bNewCon = new javax.swing.JButton();
        bEditCon = new javax.swing.JButton();
        bCloseCon = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        epSQL = new javax.swing.JEditorPane();
        bSQL = new javax.swing.JButton();
        jDividerGui = new javax.swing.JSplitPane();
        pArvore = new javax.swing.JPanel();
        spArvore = new javax.swing.JScrollPane();
        pResultados = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        tResult = new javax.swing.JTable();
        jMB = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JDBManager");
        setExtendedState(MAXIMIZED_BOTH);
        setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jMAction.setRollover(true);
        jMAction.setMaximumSize(new java.awt.Dimension(46, 40));
        jMAction.setMinimumSize(new java.awt.Dimension(46, 40));

        bNewCon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/database_connect.png"))); // NOI18N
        bNewCon.setFocusable(false);
        bNewCon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bNewCon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bNewCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNewConActionPerformed(evt);
            }
        });
        jMAction.add(bNewCon);

        bEditCon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/database_edit.png"))); // NOI18N
        bEditCon.setFocusable(false);
        bEditCon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bEditCon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jMAction.add(bEditCon);

        bCloseCon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/database_delete.png"))); // NOI18N
        bCloseCon.setFocusable(false);
        bCloseCon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bCloseCon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bCloseCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseConActionPerformed(evt);
            }
        });
        jMAction.add(bCloseCon);
        jMAction.add(jSeparator1);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("SQL Comand Line"));

        epSQL.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        epSQL.setMargin(new java.awt.Insets(7, 7, 7, 7));
        jScrollPane2.setViewportView(epSQL);

        bSQL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock_data-new-sql-query-16.png"))); // NOI18N
        bSQL.setText("Execute");
        bSQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSQLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bSQL))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bSQL, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
        );

        jDividerGui.setDividerLocation(250);
        jDividerGui.setDividerSize(7);

        pArvore.setLayout(new java.awt.BorderLayout());
        pArvore.add(spArvore, java.awt.BorderLayout.CENTER);

        jDividerGui.setLeftComponent(pArvore);

        pResultados.setLayout(new java.awt.BorderLayout());

        tResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        spTable.setViewportView(tResult);

        pResultados.add(spTable, java.awt.BorderLayout.CENTER);

        jDividerGui.setRightComponent(pResultados);

        jMB.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jMenu2.setText("About");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/information.png"))); // NOI18N
        jMenuItem1.setText("JDBManager");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMB.add(jMenu2);

        setJMenuBar(jMB);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jMAction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jDividerGui, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jMAction, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDividerGui, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNewConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNewConActionPerformed
        dialog = new ConnDB(this);
        setGuiInformation();
    }//GEN-LAST:event_bNewConActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        closeConnection();
    }//GEN-LAST:event_formWindowClosing

    private void bCloseConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseConActionPerformed
        closeConnection();
    }//GEN-LAST:event_bCloseConActionPerformed

    private void bSQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSQLActionPerformed
        if (!epSQL.getText().isEmpty()) {
            //spTable.setViewportView(new javax.swing.JTable(new model.table.JTableModel(connection, epSQL.getText().toString())));
            controller.DBController dbc = new controller.DBController(connection);
            dbc.loadVectors(epSQL.getText());
            spTable.setViewportView(new javax.swing.JTable(dbc.getRows(), dbc.getColums()));
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Type something!");
        }
    }//GEN-LAST:event_bSQLActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new about(this);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCloseCon;
    private javax.swing.JButton bEditCon;
    private javax.swing.JButton bNewCon;
    private javax.swing.JButton bSQL;
    private javax.swing.JEditorPane epSQL;
    private javax.swing.JSplitPane jDividerGui;
    private javax.swing.JToolBar jMAction;
    private javax.swing.JMenuBar jMB;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JPanel pArvore;
    private javax.swing.JPanel pResultados;
    private javax.swing.JScrollPane spArvore;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JTable tResult;
    // End of variables declaration//GEN-END:variables
}
