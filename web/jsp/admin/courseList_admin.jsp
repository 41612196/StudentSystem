<%@ page import="java.util.List" %>
<%@ page import="main.vo.Student" %>
<%@ page import="main.vo.User" %>
<%@ page import="main.vo.Course" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>课程列表</title>
    <%@include file="../common/head.jsp" %>
    <script type="text/javascript">
        function addRow()
        {

            if (confirm("确认要添加吗？")) {
                window.event.returnValue = true;
            } else {
                window.event.returnValue = false;
            }
            if (window.event.returnValue == true) {

                $.ajax({
                    url: "addCourse",
                    data: {'courseId':$('#courseId').val(),'courseName':$('#courseName').val(),'credit':$('#credit').val( )},
                    type: "post",
                    dataType: 'text',
                    success: function (msg) {
                        if (msg == '1') {
                            alert('添加成功！');
                            window.location.reload();
                        } else {
                            alert('添加失败，请稍后再试');
                            return false;
                        }

                    }

                });
            }
        }
        function deleteTable(cid) {

            var course={courseId:cid};


            //获取当前表格行号
            if (confirm("确认要删除吗？")) {
                window.event.returnValue = true;
            } else {
                window.event.returnValue = false;
            }
            if (window.event.returnValue == true) {

                $.ajax({
                    url: "../../deleteCourse",
                    data: course,
                    type: "post",
                    dataType: 'text',
                    success: function (msg) {
                        if (msg == '1') {
                            alert('删除成功！');
                            window.location.reload();
                        } else {
                            alert('删除失败，请稍后再试');
                            return false;
                        }

                    }

                });
            }
//
        }

    </script>
</head>


<body>
<!-- 页面显示部分-->
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center" >
            <h2>课程列表</h2>
            <br>

            <div style="display: inline-block">
                <form action="" id="courseinfo">
                    课程号 : <input type="text" id = "courseId" name="courseId">
                    课程名 : <input type="text" id = "courseName" name="courseName">
                    学分   : <input type="text" id = "credit" name="credit">
                    <input type="button" class="btn-info" value="添加课程" onclick="addRow()">
                </form>
            </div>

        </div>


        <div class="panel-body">
            <table id="user" class="table table-hover">
                <thead>
                <tr>
                    <th>课程号 </th>
                    <th>课程名 </th>
                    <th>学分  </th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Course> courseList = (List) session.getAttribute("courseList");
                    session.setAttribute("courseList", courseList);
                %>
                <c:forEach var="cl" items="${courseList}">
                    <tr>
                        <td>${cl.courseId}</td>
                        <td>${cl.courseName}</td>
                        <td>${cl.credit}</td>
                        <th>
                            <input type="button" class="btn btn-danger" value="删除"
                                   onclick="deleteTable(${cl.courseId})">
                        </th>
                            <%--<th><input type="button" class="btn btn-danger" value="删除" onclick="deleteTable(${sl.studentId},${sl.courseId})"></th>--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>


    </div>
</div>
</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</html>