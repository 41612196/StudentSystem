<%@ page import="main.vo.Classes" %>
<%@ page import="java.util.List" %>
<%@ page import="main.vo.Course" %>
<%@ page import="main.vo.Teacher" %>
<%@ page import="main.vo.CourseArranged" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../common/tag.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程安排</title>
    <%@include file="../common/head.jsp" %>
    <script type="text/javascript">
        function arrange() {
            if (confirm("确认安排？")) {
                window.event.returnValue = true;
            } else {
                window.event.returnValue = false;
            }
            if (window.event.returnValue == true) {

                $.ajax({
                    url: "arrangeCourse",
                    data: {
                        'classesId': $('#classes option:selected') .val(),
                        'courseId': $('#course option:selected').val(),
                        'teacherId': $('#teacher option:selected').val()
                    },
                    type: "post",
                    dataType: 'text',
                    success: function (msg) {
                        if (msg == '1') {
                            alert('安排成功！');
                            window.location.reload();
                        } else {
                            alert('安排失败，请稍后再试');
                            return false;
                        }

                    }

                });
            }
        }
        function deleteTable(cid,couid,tid) {
            var arrangedCourse = {
                classId: cid,
                courseId:couid,
                teacherId:tid

            };
            //获取当前表格行号
            if (confirm("确认要删除吗？")) {
                window.event.returnValue = true;
            } else {
                window.event.returnValue = false;
            }
            if (window.event.returnValue == true) {

                $.ajax({
                    url: "../../deleteArrangedCourse",
                    data: arrangedCourse,
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
            var i = r.parentNode.parentNode.rowIndex;
            //调用deleteRow()删除本行
            document.getElementById('score').deleteRow(i);
        }
    </script>
</head>
<body>


<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>课程安排</h2>
            <br>
            <div style="display: inline-block">
                <%
                    List<Classes> classesList = (List) session.getAttribute("classesList");
                    List<Course> courseList = (List) session.getAttribute("courseList");
                    List<Teacher> teacherList = (List) session.getAttribute("teacherList");
                    session.setAttribute("classesList",classesList);
                    session.setAttribute("courseList",courseList);
                    session.setAttribute("teacherList",teacherList);

                %>
                <form action="">
                    班级：<select id="classes" name="classes" style="width: 170px;height: 25px">
                    <option>请选择...</option>
                    <c:forEach items="${classesList}" var="classes">
                        <option value="${classes.classId}">${classes.className}</option>
                    </c:forEach>
                </select>
                    课程：<select id="course"  style="width: 170px;height: 25px">
                    <option>请选择...</option>
                    <c:forEach items="${courseList}" var="course">
                        <option value="${course.courseId}">${course.courseName}</option>
                    </c:forEach>
                </select>
                    教师：<select id="teacher"  style="width: 170px;height: 25px">
                    <option>请选择...</option>
                    <c:forEach items="${teacherList}" var="teacher">
                        <option value="${teacher.teacherId}">${teacher.teacherName}</option>
                    </c:forEach>
                </select>
                    <input type="button" class="btn-info" value="安排" onclick="arrange()">
                </form>
            </div>
        </div>
        <div class="panel-body">
            <table id="course_arrange" class="table table-hover">
                <thead>
                <tr>
                    <th>班级号</th>
                    <th>班级名</th>
                    <th>课程号</th>
                    <th>课程名</th>
                    <th>教师号</th>
                    <th>教师名</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <%

                    List<CourseArranged> courseArrangedList = (List) session.getAttribute("courseArrangedList");
                    session.setAttribute("courseArrangedList",courseArrangedList);
                %>
                <c:forEach var="cal" items="${courseArrangedList}">
                    <tr>
                        <td>${cal.classId}</td>
                        <td>${cal.className}</td>
                        <td>${cal.courseId}</td>
                        <td>${cal.courseName}</td>
                        <td>${cal.teacherId}</td>
                        <td>${cal.teacherName}</td>
                        <th>
                            <input type="button" class="btn btn-danger" value="删除"
                                   onclick="deleteTable(${cal.classId},${cal.courseId},${cal.teacherId})">
                        </th>
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