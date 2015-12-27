/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileserver;

import java.util.List;
import java.util.Map;
import org.junit.Test;
import static junit.framework.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Ivan
 */
public class Test1 {
    @BeforeClass
    public static void init() {
        Map m = Utils.getMap();
        m.put("111", "test1.txt");
        m.put("112", "test1.java");
        m.put("113", "t2st1.txt");
        m.put("114", "test55.txt");
    }
    
    @Test
    public void testFindFiles() {
        List<String> l = Utils.findFiles("test*.txt");
        assertEquals(2, l.size());
        assertTrue(l.contains("test1.txt"));
        assertTrue(l.contains("test55.txt"));
        
        
        List<String> l2 = Utils.findFiles("t?st1.txt");
        assertEquals(2, l2.size());
        assertTrue(l2.contains("test1.txt"));
        assertTrue(l2.contains("t2st1.txt"));       
    }
    
    @Test
    public void testGetNameById() {
        assertEquals("test1.txt", Utils.getFileById("111"));
        assertEquals("test55.txt", Utils.getFileById("114"));
        assertEquals("test1.java", Utils.getFileById("112"));        
    }
  
}
