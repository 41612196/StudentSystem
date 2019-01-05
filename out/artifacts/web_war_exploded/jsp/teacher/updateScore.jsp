<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <title>修改分数</title>
</head>
<body>
<h3>修改分数</h3>
<br>
<script>
<%

String studentId=request.getParameter("studentId");
String courseId=request.getParameter("courseId");
session.setAttribute("studentId",studentId);
session.setAttribute("courseId",courseId);

%>
</script>
<form action="/updateScore" method="post">
    <table>
        <tr>
            <td><label>学号：</label><%=request.getParameter("studentId")%></td>
            <%--<td><input type="text" id="studentId"  name="studentId"></td>--%>
        </tr>
        <tr>
            <td><label>姓名：</label><%=request.getParameter("studentName")%></td>
            <%--<td><input type="text" id="studentId"  name="studentId"></td>--%>
        </tr>
        <tr>
            <td><label>课程号：</label><%=request.getParameter("courseId")%></td>
            <%--<td><input type="text" id="courseId" name="courseId"></td>--%>
        </tr>
        <tr>
            <td><label>课程名：</label><%=request.getParameter("courseName")%></td>
            <%--<td><input type="text" id="studentId"  name="studentId"></td>--%>
        </tr>
        <tr>
            <td><label>分数：</label></td>
            <td><input type="text" id="grade" name="grade"></td>
        </tr>
        <tr>
            <td><input class="btn btn-info" type="submit" id="submit" value="保存"></td>
            <a class="btn btn-info" href="/jsp/teacher/studentScoreList_teacher.jsp">返回</a>
        </tr>
    </table>
</form>
</body>
<html>
