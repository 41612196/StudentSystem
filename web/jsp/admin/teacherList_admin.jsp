<%@ page import="java.util.List" %>
<%@ page import="main.vo.Score" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="main.vo.Student" %>
<%@ page import="main.vo.Teacher" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>教师列表</title>
    <%@include file="../common/head.jsp" %>
    <script type="text/javascript">
        function addRow() {

            if (confirm("确认要添加吗？")) {
                window.event.returnValue = true;
            } else {
                window.event.returnValue = false;
            }
            if (window.event.returnValue == true) {

                $.ajax({
                    url: "addTeacher",
                    data: {
                        'teacherId': $('#teacherId').val(),
                        'teacherName': $('#teacherName').val(),
                        'sex': $('input[type=radio][name=sex]:checked').val(),
                        'college': $('#college option:selected').val(),
                        'professionalTitle': $('#professionalTitle option:selected').val(),
                        'degree': $('#degree option:selected').val()
                    },
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

        function deleteTable(tid) {
            var teacher = {
                teacherId: tid

            };
            //获取当前表格行号
            if (confirm("确认要删除吗？")) {
                window.event.returnValue = true;
            } else {
                window.event.returnValue = false;
            }
            if (window.event.returnValue == true) {

                $.ajax({
                    url: "../../deleteTeacher",
                    data: teacher,
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

        }

    </script>
</head>


<body>
<!-- 页面显示部分-->
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>教师列表</h2>
            <br>
            <div style="display: inline-block">
                <form action="" id="teacherinfo">
                    <div style="margin-top: 10px;display: inline-block">
                        工号 : <input type="text" id="teacherId" name="teacherId">
                        姓名 : <input type="text" id="teacherName" name="teacherName">
                        性别 : <label>
                        <input type="radio"  name="sex" value="男" checked>男
                        <input type="radio"  name="sex" value="女">女
                    </label>
                    </div>
                    <div style="margin-top: 10px;display: inline-block">
                        <%--学院 : <input type="text" id="college" name="college">--%>
                        学院 : <select name="college" id="college" style="width: 179.27px ;height: 25.64px">
                                    <option value="计算机科学学院" selected>计算机科学学院</option>
                                    <option value="数学与信息科学学院">数学与信息科学学院</option>
                                    <option value="体育学院">体育学院</option>
                                    <option value="美术学院">美术学院</option>
                                    <option value="音乐学院">音乐学院</option>
                                    <option value="文学院">文学院</option>
                                </select>
                        <%--职称 : <input type="text" id="professionalTitle" name="professionalTitle">--%>
                        职称 : <select name="professionalTitle" id="professionalTitle" style="width: 179.27px; height: 25.64px">
                            <option value="教授" selected>教授</option>
                            <option value="副教授">副教授</option>
                            <option value="讲师">讲师</option>
                            <option value="其他">其他</option>
                        </select>

                        学位 :<select id="degree" style="width: 56.63px">

                            <option value="博士" selected>博士</option>
                            <option value="硕士">硕士</option>
                            <option value="本科">本科</option>
                            <option value="专科">专科</option>
                            <option value="其他">其他</option>
                        </select>
                    </div>
                </form>
                <div style="margin-top: 10px; display:inline-block ">
                    <input type="button" class="btn-info" value="添加教师" onclick="addRow()">
                </div>
            </div>

        </div>
        <div class="panel-body">
            <form style="display: inline" action="/adminLookTeacherByTeacherId" method="post">
                按工号查看：<input type="text" name="selectTeacherId">
                <input class="btn btn-info" type="submit" value="确定">
            </form>
        </div>
        <div class="panel-body">
            <table id="student" class="table table-hover">
                <thead>
                <tr>
                    <th>工号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>学院</th>
                    <th>职称</th>
                    <th>学位</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <%

                    List<Teacher> teacherList = (List) session.getAttribute("teacherList");
                    session.setAttribute("teacherList", teacherList);
                %>
                <c:forEach var="tl" items="${teacherList}">
                    <tr>
                        <td>${tl.teacherId}</td>
                        <td>${tl.teacherName}</td>
                        <td>${tl.sex}</td>
                        <td>${tl.college}</td>
                        <td>${tl.professionalTitle}</td>
                        <td>${tl.degree}</td>
                        <th>
                            <input type="button" class="btn btn-danger" value="删除"
                                   onclick="deleteTable(${tl.teacherId})">
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