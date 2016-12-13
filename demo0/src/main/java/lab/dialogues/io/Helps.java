/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab.dialogues.configs.Configs;

/**
 *
 * @author s1420211
 */
public class Helps {

    public static void helps() {

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("readme.txt")), Configs.ENCODING));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(Helps.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
