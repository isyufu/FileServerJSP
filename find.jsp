<%@page pageEncoding="UTF-8" language="java" contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="utils" class="fileserver.Utils"/>
<%@page import="java.util.*"%>

<html>
<head>
    <title>Файловый сервер</title>
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
</head>
<body>
    <center>
    <h1>Результат поиска файлов</h1><br/>
    <b>Запрос: </b><%= request.getParameter("file_name")%><br/>
       
    <a class="btn btn-default" href="PriceList.jsp">На главную</a>
    <br/>
    <br/>
    
    <table id="price-list" class="table table-hover table-bordered" >
       <tr class="info" >
         <td class="col-md-4" >Идентификатор</td>
         <td class="col-md-4" >Имя файла</td>
       </tr>
         <%
            List<String[]> list = utils.findFilesWithId2(request.getParameter("file_name"));
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
</body>
</html>