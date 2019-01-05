<%@ page import="java.util.List" %>
<%@ page import="main.vo.Score" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="main.vo.Student" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>学生列表</title>
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
                    url: "addStudent",
                    data: {
                        'studentId': $('#studentId').val(),
                        'studentName': $('#studentName').val(),
                        'sex': $('input[type=radio][name=sex]:checked').val(),
                        'age': $('#age').val(),
                        'yearSchool': $('#yearSchool option:selected').val(),
                        'college': $('#yearSchool option:selected').val(),
                        'major': $('#major option:selected').val(),
                        'classes': $('#classes option:selected').val(),
                        'tel': $('#tel').val(),
                        'address': $('#address').val()

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

        function deleteTable(sid) {
            var student = {
                studentId: sid

            };
            //获取当前表格行号
            if (confirm("确认要删除吗？")) {
                window.event.returnValue = true;
            } else {
                window.event.returnValue = false;
            }
            if (window.event.returnValue == true) {

                $.ajax({
                    url: "../../deleteStudent",
                    data: student,
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

        //

        majors = new Object();
        majors ['计算机科学学院'] = new Array('软件工程', '计算机科学与技术（创新实验班）', '信息管理与信息系统', '计算机科学与技术（师范）');
        majors['数学与信息科学学院'] = new Array('应用数学', '统计学', '信息科学');

        function set_major(college, major) {
            var pv, cv;
            var i, ii;

            pv = college.value;
            cv = major.value;

            major.length = 1;

            if (pv == '0') return;
            if (typeof(majors[pv]) == 'undefined') return;

            for (i = 0; i < majors[pv].length; i++) {
                ii = i + 1;
                major.options[ii] = new Option();
                major.options[ii].text = majors[pv][i];
                major.options[ii].value = majors[pv][i];
            }

        }

        classes = new Object();
        classes ['软件工程'] = new Array('软工一班', '软工二班');
        classes ['计算机科学与技术（创新实验班）'] = new Array('计算机科学与技术（创新实验班）');
        classes ['信息管理与信息系统'] = new Array('信息管理与信息系统');
        classes['计算机科学与技术（师范）'] = new Array('计科一班', '计科二班');

        function set_class(major, clazz) {
            var pv, cv;
            var i, ii;

            pv = major.value;
            cv = clazz.value;

            clazz.length = 1;

            if (pv == '0') return;
            if (typeof(classes[pv]) == 'undefined') return;

            for (i = 0; i < classes[pv].length; i++) {
                ii = i + 1;
                clazz.options[ii] = new Option();
                clazz.options[ii].text = classes[pv][i];
                clazz.options[ii].value = classes[pv][i];
            }

        }


    </script>
</head>


<body>
<!-- 页面显示部分-->
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>学生列表</h2>
            <br>
            <div style="display: inline-block">

                　
                <form action="" id="studentinfo" method="get" style="">
                    <div style="text-align: left">
                        学号 : <input style="width: 132px" type="text" id="studentId" name="studentId">
                        姓名 : <input style="width: 132px" type="text" id="studentName" name="studentName">
                        性别 : <label style="margin-left: 30px;margin-right: 40px">
                        <input type="radio" name="sex" value="男" checked>男
                        <input type="radio" name="sex" value="女">女
                    </label>
                        年龄 : <input style="width: 100px" type="text" id="age" name="age">

                        年级 ：<select id="yearSchool" style="width: 100px; padding: 5px ">
                        <option value="2014">2014</option>
                        <option value="2015">2015</option>
                        <option value="2016" selected>2016</option>
                        <option value="2017">2017</option>
                        <option value="2018">2018</option>
                        <option value="2019">2019</option>
                    </select>
                    </div>

                    <br style="margin-top: 5px">
                    学院：<select id="college" name="college" style="padding: 5px;width: 130px"
                               onChange="set_major(this, this.form.major);">
                    <option value="0">选择学院</option>
                    <option value="计算机科学学院">计算机科学学院</option>
                    <option value="数学与信息科学学院">数学与信息科学学院</option>
                    <option value="体育学院">体育学院</option>
                    <option value="美术学院">美术学院</option>
                    <option value="音乐学院">音乐学院</option>
                    <option value="文学院">文学院</option>
                </select>
                    专业：<select id="major" name="major" style="padding: 5px;width: 130px" id="majors"
                               onChange="set_class(this, this.form.classes);">
                    <option value="0">选择专业</option>
                </select>
                    班级：<select name="classes" style="padding: 5px;width: 130px" id="classes">
                    <option value="0">选择班级</option>
                </select>
                    电话 : <input style="width: 100px" type="text" id="tel" name="tel">
                    地址 : <input style="width: 100px" type="text" id="address" name="address">

                </form>
                <input style="margin-top: 10px" type="button" class="btn-info" value="添加学生" onclick="addRow()">


            </div>


        </div>
        <div class="panel-body">

            <form action="/adminLookStudentByStudentId" method="post" style="display: inline-block;">
                按学号查看：<input type="text" name="selectStudentId">
                <input class="btn btn-info" type="submit" value="确定">
            </form>



            <form action="/UploadStudents" method="post" enctype="multipart/form-data" style="display: inline-block ;margin-left: 100px">
                excel导入学生:<input type="file" name="uploadFile" style="display: inline-block;width: 200px;">
                <input type="submit" class="btn-info" value="上传">
            </form>

        </div>
        <div class="panel-body">
            <table id="student" class="table table-hover">
                <thead>
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>专业</th>
                    <th>年级</th>
                    <th>电话</th>
                    <th>地址</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <%

                    List<Student> studentList = (List) session.getAttribute("studentList");
                    session.setAttribute("studentList", studentList);
                %>
                <c:forEach var="s" items="${studentList}">
                    <tr>
                        <td>${s.studentId}</td>
                        <td>${s.studentName}</td>
                        <td>${s.sex}</td>
                        <td>${s.age}</td>
                        <td>${s.major}</td>
                        <td>${s.yearSchool}</td>
                        <td>${s.telephone}</td>
                        <td>${s.address}</td>

                        <th>
                            <input type="button" class="btn btn-danger" value="删除"
                                   onclick="deleteTable(${s.studentId})">
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