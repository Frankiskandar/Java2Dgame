/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Frank
 */
public class Utils {

    
    public static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch(NumberFormatException ex) {
            ex.printStackTrace();
            return 0;// so the program doesnt crash
        }
    
    }

    public static String loadFileAsString(String path) {
         //allows you to add charactor to string easily
        StringBuilder builder = new StringBuilder();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;// current line of file that we will be working on
            while((line = br.readLine()) !=null) //as long as is not EOF
                builder.append(line + "\n");
            
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return builder.toString();
    }
}
