/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Ivan
 */
public class Init extends HttpServlet{
  
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); 
                        
        Utils.setDataDirectory(config.getInitParameter("dataDir"));
        //ttask1 
	//task 3
	//task 3
	//task 3
	//task 3
	//task 3
	//task 3

        Utils.init();
	//task 4
    }
    
}
