<%@ page import="java.util.List" %>
<%@ page import="main.vo.Student" %>
<%@ page import="main.vo.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户列表</title>
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
                    url: "addUser",
                    data: {'username':$('#userName').val(),'password':$('#passWord').val(),'superuser':$('#superUser').val( )},
                    type: "post",
                    dataType: 'text',
                    success: function (msg) {
                        if (msg == '1') {
                            alert('添加成功！');
                            window.location.reload();
                        } else {
                            alert('添加失败，该账号已被占用');
                            return false;
                        }

                    }

                });
            }
        }
        function deleteTable(sid) {

            var user={username:sid};


            //获取当前表格行号
            if (confirm("确认要删除吗？")) {
                window.event.returnValue = true;
            } else {
                window.event.returnValue = false;
            }
            if (window.event.returnValue == true) {

                $.ajax({
                    url: "../../deleteUser",
                    data: user,
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
            <h2>用户列表</h2>
            <br>

            <div style="display: inline-block">
                <form action="" id="userinfo">
                    账号 : <input type="text" id = "userName" name="username">
                    密码 : <input type="text" id = "passWord" name="password">
                    权限等级 : <input type="text" id = "superUser" name="superuser">
                    <input type="button" class="btn-info" value="添加用户" onclick="addRow()">
                </form>
            </div>

        </div>


        <div class="panel-body">
            <table id="user" class="table table-hover">
                <thead>
                <tr>
                    <th>账号</th>
                    <th>密码</th>
                    <th>权限等级</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<User> userList = (List) session.getAttribute("userList");
                    session.setAttribute("userList", userList);
                %>
                <c:forEach var="ul" items="${userList}">
                    <tr>
                        <td>${ul.username}</td>
                        <td>${ul.password}</td>
                        <td>${ul.superuser}</td>
                        <th>
                            <input type="button" class="btn btn-danger" value="删除"
                                   onclick="deleteTable(${ul.username})">
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