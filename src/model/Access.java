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
package model;

/**
 * Classe modelo para acesso ao Banco de Dados
 * 
 * @author zerossB
 */
public class Access {
    private String banco;
    private String driver;
    private String url;
    private int    door;
    private String user;
    private String password;
    
    public static String drivers[] = {
        "org.apache.derby.jdbc.EmbeddedDriver",
        "org.apache.derby.jdbc.ClientDriver"
    };

    /***************************************
     *                                     *
     *            Construtores             *
     *                                     *
     ***************************************/
    
    public Access() {
    }

    public Access(String driver, String url, int door, String user, String password) {
        this.driver = driver;
        this.url = url;
        this.door = door;
        this.user = user;
        this.password = password;
    }
    
    /***************************************
     *                                     *
     *         Getters and Setters         *
     *                                     *
     ***************************************/
    
    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDoor() {
        return door;
    }

    public void setDoor(int door) {
        this.door = door;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
