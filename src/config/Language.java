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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package config;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author G
 */
public class Language {

    File file;
    SAXBuilder builder;
    Document document;
    Element root;
    Map<String, String> map;
    Iterator iterator;

    public Language(String lang) {
        try {
            file = new File("/conf/lang/" + lang + ".xml");
            builder = new SAXBuilder();
            document = builder.build(file);
            root = (Element) document.getRootElement();
            loadMap(root);
        } catch (JDOMException ex) {
            Logger.getLogger(Language.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Language.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadMap(Element e){
        
        
    }

}
