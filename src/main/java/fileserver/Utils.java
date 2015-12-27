/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

/**
 *
 * @author Ivan
 */
public class Utils {
    final static Logger logger = Logger.getLogger(Utils.class);
    
    private static String dataDirectory;
    
    //id,name
    private static final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    
    public static void init(){
        File folder = new File(dataDirectory);
        File[] listOfFiles = folder.listFiles();
        for (File f : listOfFiles) {
            if (f.isFile()) {
                String id = calcID(f);
                map.put(id, f.getName());
            }
        }
    }
    
    /**
     * Compute unique identificator uploaded file
     * @param is - InputStream
     * @return SHA1 Hex string
     */
    public static String calcID(InputStream is) {
        try {
            return DigestUtils.sha1Hex(is);
        } catch (IOException ex) {
            logger.error("calcId", ex);
            return "";
        }
    }
    
    /**
     * Compute unique identificator uploaded file
     * @param fi - FileItem
     * @return SHA1 Hex string
     */
    public static String calcID(FileItem fi) {
        try {
            return DigestUtils.sha1Hex(fi.getInputStream());
        } catch (IOException ex) {
            logger.error("calcId", ex);
            return "";
        }
    }
    
    /**
     * Compute unique identificator uploaded file
     * @param f - File
     * @return SHA1 Hex string
     */
    public static String calcID(File f) {
        try {
            return calcID(new FileInputStream(f));
        } catch (IOException ex) {
            logger.error("calcId", ex);
            return "";
        }
    }
    
    /**
     * Check exist file.
     * @param fi - FileItem
     * @return true - exist, else - false
     */
    public static boolean isFileExist(FileItem fi) {
        try {
            String id = calcID(fi.getInputStream());
            return isFileExist(id);
        } catch (IOException ex) {
            logger.error("is file exist", ex);
            return false;
        }
    }
    
    /**
     * Check exist file.
     * @param id - unique identificator
     * @return true - exist, else - false
     */
    public static boolean isFileExist(String id) {
        return map.containsKey(id);
    }
    
   /**
     * Find fie name by unique identificator.
     * @param id - unique identificator
     * @return - file name, else empty string
     */
    public static String getFileById(String id) {
        if(map.containsKey(id))
            return map.get(id);
        else
            return "";
    }
    
    /**
     * Save file on disk.
     * Advise: Before, check exist file on disk - isFileExist(FileItem fi).
     * @param fi - uploaded file.
     * @param id - file id
     * @return true - file had saved, false - file not uploaded or error's save on disk.
     */
    public static boolean saveFile(FileItem fi, String id) {
        try {
            map.put(id, fi.getName());
            String filePath = dataDirectory + File.separator + fi.getName();
            File uploadedFile = new File(filePath);
            fi.write(uploadedFile);
            return true;
        } catch (Exception ex) {
            logger.error("save file", ex);
            return false;
        }
    }
    
    /**
     * Save file on disk.
     * @param id - uploaded file.
     * @return true - file had saved, false - file not uploaded or error's save on disk.
     */
    public static boolean deleteFile(String id) {
        if (isFileExist(id)) {
            try {
                String filePath = dataDirectory + File.separator + map.get(id);
                map.remove(id);
                File uploadedFile = new File(filePath);
                return uploadedFile.delete();
            } catch (Exception ex) {
                logger.error("delete file", ex);
                return false;
            }
        } else
            return false;
    }
    
    /**
     * Find all files by name.
     * @param wildcard - name.
     * @return list of file names
     */
    public static List<String> findFiles(String wildcard) {
        String regex = wildcard.replace("?", ".?").replace("*", ".*?");
        List<String> list = map.values().stream()
                .filter(x -> x.matches(regex))
                .collect(Collectors.toList());
        return list;
    }
    
    /**
     * Find all files with ids by name.
     * @param wildcard - name.
     * @return array of [id, name] rows
     */    
    public static List<String[]> findFilesWithId(String wildcard) {
        String regex = wildcard.replace("?", ".?").replace("*", ".*?");
        List<String[]> list = new ArrayList<>();
        
        for (Map.Entry<String,String> e : map.entrySet()) {
            if(e.getValue().matches(regex)) {
                list.add(new String[] {e.getKey(), e.getValue()});
            }
            if(list.size() == 25)
                break;
        }
        
        return list;
    }

    public static ConcurrentHashMap<String, String> getMap() {
        return map;
    }

    public static String getDataDirectory() {
        return dataDirectory;
    }

    public static void setDataDirectory(String dataDirectory) {
        Utils.dataDirectory = dataDirectory;
    }
    
}
