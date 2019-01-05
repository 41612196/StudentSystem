<%@ page import="java.util.List" %>
<%@ page import="main.vo.Score" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>学生成绩</title>
    <%@include file="../common/head.jsp" %>
</head>


<body>
<!-- 页面显示部分-->
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>录入成绩</h2>
        </div>

        <div class="panel-body">


            <form action="/addScore" method="post" style="display: inline-block;">
                按课程输入：<input type="text" name="selectCourse">
                <input class="btn btn-info" type="submit" value="确定">
            </form>


            <form method="post" action="/UploadServlet" enctype="multipart/form-data"
                  style="display: inline-block; margin-left: 100px">
                excel导入成绩:<input type="file" name="uploadFile" style="display: inline-block ;width: 200px">
                <input type="submit" value="上传" class="btn-info">
            </form>


            <form action="saveScore" method="post">
                <input class="btn btn-info" type="submit" value="保存">
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
                        // String username = (String) session.getAttribute("username");

                        List<Score> scoreList = (List) session.getAttribute("scoreList");
                        session.setAttribute("scoreList", scoreList);
                    %>
                    <c:forEach var="sl" items="${scoreList}">
                        <tr>
                            <td>${sl.studentId}</td>
                            <td>${sl.courseId}</td>
                            <td>${sl.studentName}</td>
                            <td>${sl.courseName}</td>
                            <td><input type="text" name="${sl.studentId}"></td>
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