<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>成绩分析统计</title>
    <meta chartset="utf-8">

    <%@include file="../common/head.jsp" %>
    <script src="../../js/echarts.min.js"></script>
    <script src="../../js/echarts.js"></script>

    <script src="../../js/echarts-liquidfill.min.js"></script>
    <script src="../../js/echarts-liquidfill.js"></script>

    <%--<script src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>--%>
    <script src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
    <script src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
    <script src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
    <script src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
    <script src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
    <script src="https://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
    <script src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
    <script src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>


    <script type="text/javascript" src="../../js/jquery-2.1.3.min.js"></script>

</head>


<body>
<!-- 页面显示部分-->
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>成绩分析</h2>
        </div>
    </div>
    <form action="../../addCourseToSession" method="post">
        输入课程名称：<input type="text" name="courseName">
        <input class="btn btn-info" type="submit" value="确定">
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 450px;height:350px; display: inline-block"></div>
    <div id="water" style="width: 450px;height: 350px;display: inline-block"></div>
    <div id="all" style="width: 900px;height: 500px"></div>



    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        var myChart1 = echarts.init(document.getElementById('water'));
        var myChart2 = echarts.init(document.getElementById('all'));


        var app = {};
        option2 = null;
        //  var passRate=0;//及格率

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '各分数段的人数'
            },
            tooltip: {},
            legend: {
                data: ['人数']
            },
            xAxis: {
                data: []
            },
            yAxis: {},
            series: [{
                name: '人数',
                type: 'bar',
                data: []
            }]
        };

        var option1 = {
            title: {
                text: '该门课及格率'
            },
            series: [{
                type: 'liquidFill',
                data: [],
                label: {
                    normal: {
                        textStyle: {
                            fontSize: 10
                        }
                    }
                }
            }]
        };

        //综合分析
        var posList = [
            'left', 'right', 'top', 'bottom',
            'inside',
            'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
            'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
        ];

        app.configParameters = {
            rotate: {
                min: -90,
                max: 90
            },
            align: {
                options: {
                    left: 'left',
                    center: 'center',
                    right: 'right'
                }
            },
            verticalAlign: {
                options: {
                    top: 'top',
                    middle: 'middle',
                    bottom: 'bottom'
                }
            },
            position: {
                options: echarts.util.reduce(posList, function (map, pos) {
                    map[pos] = pos;
                    return map;
                }, {})
            },
            distance: {
                min: 0,
                max: 100
            }
        };

        app.config = {
            rotate: 90,
            align: 'left',
            verticalAlign: 'middle',
            position: 'insideBottom',
            distance: 15,
            onChange: function () {
                var labelOption = {
                    normal: {
                        rotate: app.config.rotate,
                        align: app.config.align,
                        verticalAlign: app.config.verticalAlign,
                        position: app.config.position,
                        distance: app.config.distance
                    }
                };
                myChart2.setOption({
                    series: [{
                        label: labelOption
                    }, {
                        label: labelOption
                    }, {
                        label: labelOption
                    }, {
                        label: labelOption
                    }]
                });
            }
        };


        var labelOption = {
            normal: {
                show: true,
                position: app.config.position,
                distance: app.config.distance,
                align: app.config.align,
                verticalAlign: app.config.verticalAlign,
                rotate: app.config.rotate,
                formatter: '{c}  {name|{a}}',
                fontSize: 16,
                rich: {
                    name: {
                        textBorderColor: '#fff'
                    }
                }
            }
        };

        option2 = {
            color: ['#003366', '#006699', '#4cabce', '#e5323e'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            legend: {
                data: ['最高分', '最低分', '平均分','mmm']
            },
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    mark: {show: true},
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    axisTick: {show: false},
                    data: ['数学', '英语', '高数', '高级英语']
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '最高分',
                    type: 'bar',
                    barGap: 0,
                    label: labelOption,
                    data: [99, 89, 97, 90]
                },
                {
                    name: '最低分',
                    type: 'bar',
                    label: labelOption,
                    data: [56, 60, 70, 44]
                },
                {
                    name: '平均分',
                    type: 'bar',
                    label: labelOption,
                    data: [78, 80, 85, 63]
                }
            ]
        };


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        myChart1.setOption(option1);
        myChart2.setOption(option2);


        var scoreSections = [];//类别数组（用来盛放X轴的坐标值）
        var nums = [];//销量数组（用来盛放Y坐标值）


        $.ajax({
            type: "post",
            async: true,//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url: "../../analyzeScores",
            data: {},
            dataType: "json",    //返回的数据形式为json
            success: function (result) {
                if (result) {
                    for (var i = 0; i < result.length; i++) {
                        scoreSections.push(result[i].scoreSection);


                    }
                    for (var j = 0; j < result.length; j++) {
                        nums.push(result[j].num);
                    }

                    var passRate1 = result[0].passRate;

                    // myChart.hideLoading();//隐藏加载动画
                    myChart.setOption({//加载数据图表
                        xAxis: {
                            data: scoreSections
                        },
                        series: [{
                            name: '人数',
                            data: nums
                        }]
                    });
                    myChart1.setOption({
                        series: [{
                            data: [passRate1]
                        }]
                    });
                }
            },
            error: function (errorMsg) {
                alert("图表请求数据失败！");
                // myChart.hideLoading();

            }
        })


    </script>
</div>
</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</html>