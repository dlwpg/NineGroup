<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/7
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"><link rel="icon" href="https://jscdn.com.cn/highcharts/images/favicon.ico">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        /* css 代码  */
    </style>
    <script src="/static/js/jquery-3.5.0.min.js"></script>
    <script src="https://code.highcharts.com.cn/highcharts/highcharts.js"></script>
    <script src="https://code.highcharts.com.cn/highcharts/modules/exporting.js"></script>
    <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
</head>
<body>
<div id="container" style="min-width:400px;height:400px"></div>
<script>
    // JS 代码
    $(function () {
        $.ajax({
            url:"/brrowBook/tongjinannv.ajax",
            type:"post",
            datatype:"json",
            success:function (data) {
                Highcharts.chart('container', {
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false,
                        type: 'pie'
                    },
                    title: {
                        text: '快乐图书馆男女比例图'
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                                style: {
                                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                }
                            }
                        }
                    },
                    series: [{
                        name: '比例',
                        colorByPoint: true,
                        data: [{
                            name: '男性',
                            y: data[0],
                            sliced: true,
                            selected: true
                        }, {
                            name: '女性',
                            y: data[1]
                        }]
                    }]
                });
            }
        })

    })

</script>
</body>
</html>
