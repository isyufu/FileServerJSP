<%
    if (fileserver.Utils.deleteFile(request.getParameter("id"))){
        session.setAttribute("result", "File deleted.");
    } else { 
        session.setAttribute("result", "File not found.");
    }        
    response.sendRedirect("main.jsp"); 
%>