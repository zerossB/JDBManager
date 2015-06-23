/*
 * Copyright (C) 2015 G
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author G
 */
public class ConfigDAO {

    private Properties prop;
    private FileOutputStream fos;
    private FileInputStream fis;

    private static final String CONF = "/conf/conf.prop";

    public ConfigDAO() {
    }

    public int writeConf(ConFile cFile) {
        int result = 0;

        try {
            prop.setProperty("language", cFile.getLanguage());

            fos = new FileOutputStream(CONF);
            prop.store(fos, null);
        } catch (FileNotFoundException ex) {
            result = 1;
            Logger.getLogger(ConfigDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            result = 1;
            Logger.getLogger(ConfigDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public ConFile readFile() {
        ConFile cf = new ConFile();

        try {
            fis = new FileInputStream(new File(CONF));
            prop.load(fis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        cf.setLanguage(prop.getProperty("language"));

        return cf;
    }

    public final class ConFile {

        private String language;

        public String[] lang = {
            "pt_BR",
            "en_EN",
            "en_CN"
        };

        public ConFile() {
            this.language = "";
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }
    }
}
