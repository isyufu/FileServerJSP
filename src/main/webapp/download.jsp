<%  
String s = fileserver.Utils.getFileById(request.getParameter("id"));
if (s != null && s != "") {
    String filename = s; 
    String filepath = fileserver.Utils.getDataDirectory(); 
    response.setContentType("APPLICATION/OCTET-STREAM"); 
    response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\""); 
    java.io.FileInputStream fileInputStream = new java.io.FileInputStream(filepath+"/" + filename);

    int i; 
    while ((i=fileInputStream.read()) != -1) {
      out.write(i); 
    } 
    fileInputStream.close(); 
}else {
    request.setAttribute("result", "File not found.");
    response.sendRedirect("main.jsp"); 
}
%> 