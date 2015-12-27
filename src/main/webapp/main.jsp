<%request.setCharacterEncoding("UTF-8"); //scope="session"%>
<%@page pageEncoding="UTF-8" language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*"%>

<fmt:setLocale value="ru" />
<fmt:setBundle basename="text" />

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    </head>
    <body class="col-md-10 col-md-offset-1">
    <br/>   
    <br/>   
    <br/>   
    
    <h2>Поиск файлов</h2>
    <form role="form" action="main.jsp" method="GET">
        <label for="file_name" >Имя файла: </label>
        <input type="text" name="file_name">        
        <input type="submit" value="Поиск" />
    </form>
    <br/>   
    
    <h2>Скачивание файла</h2>
    <form role="form" action="download.jsp" method="GET">
        <label for="id" >Идетификатор: </label>
        <input type="text" name="id">        
        <input type="submit" value="Скачать" />
    </form>
    <br/>   
    
    <h2>Загрузка файла</h2>
    <form role="form" method="post" action="UploadServlet" enctype="multipart/form-data">
        Выберите файл для загрузки, максимальный размер 50MB:
        <input type="file" name="dataFile" id="fileChooser"/><br/>
        <input type="submit" value="Загрузить" />
    </form>
    <br/>
    
    <h2>Удаление файла</h2>
    <form role="form" action="delete.jsp" method="GET">
        <label for="id" >Идетификатор: </label>
        <input type="text" name="id">        
        <input type="submit" value="Удалить" />
    </form>
    <br/>
    <br/>
    
    <br/>
    <%
        if (request.getParameter("file_name") != null) {
        %>
            <table id="price-list" class="table table-hover table-bordered" >
            <tr class="info" >
              <td class="col-md-4" >Идентификатор</td>
              <td class="col-md-4" >Имя файла</td>
            </tr>
        <%
                List<String[]> list = fileserver.Utils.findFilesWithId(request.getParameter("file_name"));
                for (String[] e : list) {
        %>
                        <tr>
                            <td><%=e[0]%></td>
                            <td><%=e[1]%></td>
                        </tr>
        <%
                }
        %>

            </table>  
        <%
        }
    %>  
    </body>
</html>