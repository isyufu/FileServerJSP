package fileserver;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

/**
task 5
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(UploadServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (!isMultipart) {
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        
        try {
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (!item.isFormField()) {
                    String id = Utils.calcID(item);
                    if (!Utils.isFileExist(id) && Utils.saveFile(item, id)){
                        request.setAttribute("result", "Файл загружен.");                        
                    } else {
                        request.setAttribute("result", "Не удалось загрузить файл.");
                    }
                }
            }

            getServletContext().getRequestDispatcher("/main.jsp").forward(
                    request, response);

        } catch (Exception ex) {
            logger.error("load file", ex);
        }
    }

}
