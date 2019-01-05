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
    <script type="text/javascript" src="../../js/jquery-2.1.3.min.js"></script>
    <script type="text/javascript">
        $(function () {
                $("input:button").click(function() {
                    if ($(this).val() == "确定") {
                        var scores = {};
                        scores["studentId"] = $(this).parents("tr").children("#studentId").children('input').val();
                        scores["courseId"] = $(this).parents("tr").children("#courseId").children('input').val();
                        scores["studentName"] = $(this).parents("tr").children("#studentName").children('input').val();
                        scores["courseName"] = $(this).parents("tr").children("#courseName").children('input').val();
                        scores["grade"] = $(this).parents("tr").children("#grade").children('input').val();
                        $.ajax({
                            type: "post",
                            url: "updateScore",
                            data: {'studentId':$(this).parents("tr").children("#studentId").children('input').val(),'courseId':$(this).parents("tr").children("#courseId").children('input').val(),'grade':$(this).parents("tr").children("#grade").children('input').val()},
                            dataType: "json",
                            success: function (res) {
                                if (res == "1")
                                    alert("修改成功");
                            }
                        });
                    }


                    if($(this).val()=="修改"){
                        var str="确定"
                    }else if($(this).val()=="确定"){
                        var str="修改"
                    }else if($(this).val()=="删除"){
                        return;
                    }

                   // var str = $(this).val() == "修改" ? "确定" : "修改";
                    $(this).val(str);   // 按钮被点击后，在“修改”和“确定”之间切换
                    $(this).parent().siblings("td").each(function () {// 获取当前行的其他单元格
                        var obj_text = $(this).find("input:text");    // 判断单元格下是否有文本框
                        if (!obj_text.length)   // 如果没有文本框，则添加文本框使之可以编辑
                            $(this).html("<input type='text'  style='width: 70px' value='" + $(this).text() + "'>");
                        else
                            $(this).html(obj_text.val());
                    });
                })
        });
        function deleteTable(sid,cid) {
            var score = {
                studentId:sid,
                courseId:cid
            };
            //获取当前表格行号
            if(confirm("确认要删除吗？")){
                window.event.returnValue = true;
            }else{
                window.event.returnValue = false;
            }
            if(window.event.returnValue==true) {

                $.ajax({
                    url: "../../deleteScore",
                    data:score,
                    type:"post",
                    dataType:'text',
                    success:function (msg) {
                        if(msg=='1'){
                            alert('删除成功！');
                            window.location.reload();
                        }else {
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
            <h2>成绩单</h2>
        </div>
        <div class="panel-body">
            <form style="display: inline" action="/teacherLookScoreByCourse" method="post">
                按课程查看：<input type="text" name="selectCourseOne">
                <input class="btn btn-info" type="submit" value="确定">
            </form>
            <form style="display: inline" action="/teacherLookScoreByStudentId" method="post">
                按学号查看：<input type="text" name="selectStudentId">
                <input class="btn btn-info" type="submit" value="确定">
            </form>
        </div>
        <div class="panel-body">
            <table id="score" class="table table-hover">
                <thead>
                <tr>
                    <td>学号</td>
                    <td>课程号</td>
                    <td>姓名</td>
                    <td>课程名</td>
                    <td>分数</td>
                    <td>操作</td>
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
                        <td id="studentId"  >${sl.studentId}</td>
                        <td id="courseId"   >${sl.courseId}</td>
                        <td id="studentName">${sl.studentName}</td>
                        <td id="courseName" >${sl.courseName}</td>
                        <td id="grade"      >${sl.grade}</td>
                        <td>
                            <input id="update" type="button" class="btn btn-info" value="修改">

                            <input type="button" class="btn btn-danger" value="删除" onclick="deleteTable(${sl.studentId},${sl.courseId})">
                        </td>
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