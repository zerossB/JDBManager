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

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zerossB
 */
public class Splash extends javax.swing.JWindow {
    //Duração da SplashScreen
    private int duration;
    
    /**
     * Construtor
     * 
     * @param duration - Duração da SplashScreen
     */
    public Splash(int duration) {
        this.duration = duration;
    }
    
    /**
     * Cria e exibe o SplashScreen
     */
    private void showSplash() {
        //Seta tamanho e posição da Janela
        javax.swing.JPanel jp = (javax.swing.JPanel) getContentPane();
        //Seta a cor de Background como Preto
        jp.setBackground(java.awt.Color.BLACK);
        
        //Tamanho da janela
        int width = 500;
        int height = 334;
        java.awt.Dimension screen = java.awt.Toolkit.getDefaultToolkit()
                .getScreenSize();
        
        //Deixa a SplashScreen no meio da tela
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        //Constrói o SplashScreen
        javax.swing.JLabel jl
                = new javax.swing.JLabel(new javax.swing.ImageIcon(getClass()
                                .getResource("/images/JDBManager.png")));
        //Deixa a imagem no centro da JWindow
        jp.add(jl, java.awt.BorderLayout.CENTER);
        //Deixa visivel
        setVisible(true);
        
        //Espera por "duration" segundos
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
            Logger.getLogger(Splash.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Sai da Exibição da Tela
        setVisible(false);
    }
    
    /**
     * Metodo onde coloca a Splash screen na tela
     * Seta o LookAndFell como Metal
     * Coloca o main.java em ver.
     */
    public void showSplashAndMain() {
        //Seta LookAndFell
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info
                    : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        //Metodo da SplashScreen
        showSplash();
        //Chama o metodo do Main.
        new main().setVisible(true);
    }
    
    /**
     * Metodo principal
     * Instancia o Splash na memória para inciar o Programa
     * @param args 
     */
    public static void main(String[] args) {
        Splash s = new Splash(5000);
        s.showSplashAndMain();
    }
}
