<%@ page import="java.util.List" %>
<%@ page import="main.vo.Score" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/jsp/common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>学生成绩</title>
    <%@include file="/jsp/common/head.jsp" %>
</head>


<body>
<!-- 页面显示部分-->
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>成绩单</h2>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                </thead>
            </table>
        </div>
        <div class="panel-body">
            <form action="SaveScore" method="post">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>学号</th>
                        <th>课程号</th>
                        <th>姓名</th>
                        <th>课程名</th>
                        <th>分数</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        String username = (String) session.getAttribute("username");

                        List<Score> scoreList = (List) session.getAttribute("scoreList");
                        session.setAttribute("scoreList", scoreList);
                    %>
                    <c:forEach var="sl" items="${scoreList}">
                        <tr>
                            <td>${sl.studentId}</td>
                            <td>${sl.courseId}</td>
                            <td>${sl.studentName}</td>
                            <td>${sl.courseName}</td>
                            <td>${sl.grade}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</html>