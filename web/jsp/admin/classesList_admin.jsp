<%@ page import="java.util.List" %>
<%@ page import="main.vo.Classes" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>班级列表</title>
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
                    url: "addClass",
                    data: {'classesId':$('#classesId').val(),'classesName':$('#classesName').val(),'ofCollege':$('#ofCollege').val( )},
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
            var classes = {
                classId: cid

            };
            //获取当前表格行号
            if (confirm("确认要删除吗？")) {
                window.event.returnValue = true;
            } else {
                window.event.returnValue = false;
            }
            if (window.event.returnValue == true) {

                $.ajax({
                    url: "../../deleteClass",
                    data: classes,
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
<!-- 页面显示部分-->
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>班级列表</h2>
            <br>
            <div style="display: inline-block">
                <form action="" id="classesinfo">
                    班号 : <input type="text" id = "classesId" name="classesId">
                    班名 : <input type="text" id = "classesName" name="classesName">
                    学院 : <input type="text" id = "ofCollege" name="ofCollege">
                    <input type="button" class="btn-info" value="添加班级" onclick="addRow()">
                </form>
            </div>
        </div>
        <div class="panel-body">
            <form style="display: inline" action="/adminLookClassByClassId" method="post">
                按班号查看：<input type="text" name="selectClassId">
                <input class="btn btn-info" type="submit" value="确定">
            </form>
        </div>
        <div class="panel-body">
            <table id="student" class="table table-hover">
                <thead>
                <tr>
                    <th>班号</th>
                    <th>班名</th>
                    <th>学院</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <%

                    List<Classes> classesList = (List) session.getAttribute("classesList");
                    session.setAttribute("classesList", classesList);
                %>
                <c:forEach var="cl" items="${classesList}">
                    <tr>
                        <td>${cl.classId}</td>
                        <td>${cl.className}</td>
                        <td>${cl.ofCollege}</td>
                        <th>
                            <input type="button" class="btn btn-danger" value="删除"
                                   onclick="deleteTable(${cl.classId})">&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/adminLookStudentByClass?classId=${cl.classId}" type="submit" class="btn btn-info" value="成员">成员</a>
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