<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/7
  Time: 9:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="icon" href="https://jscdn.com.cn/highcharts/images/favicon.ico">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        /* css 代码  */
    </style>
    <script src="/static/js/jquery-3.5.0.min.js"></script>
    <script src="https://code.highcharts.com.cn/highcharts/highcharts.js"></script>
    <script src="https://code.highcharts.com.cn/highcharts/modules/exporting.js"></script>
    <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
    <script src="https://code.highcharts.com.cn/highcharts/themes/dark-unica.js"></script>
</head>
<body>
<div id="container" style="min-width:400px;height:400px"></div>
<input type="hidden" id="jiezhishijian" value="${indexinfo.date}">
<script>
    // JS 代码
    $(function () {
       var d=$("#jiezhishijian").val();
       $.ajax({
           url: "/brrowBook/tongjizongshu.ajax",
           type: "get",
           dataType: "json",
           success: function (data) {
               var a=new Array();
               for (var i = 0; i <data.length ; i++) {
                   var b=new Array();
                   b[0]=data[i].bookname;
                    b[1]=data[i].sum;
                    a[i]=b;

               }
               var chart = Highcharts.chart('container', {
                   chart: {
                       type: 'column'
                   },
                   title: {
                       text: '各类图书数量排行'
                   },
                   subtitle: {
                       text: '数据截止：' + d + '  来源：吴朋桂手工统计'
                   },
                   xAxis: {
                       type: 'category',
                       labels: {
                           rotation: -45  // 设置轴标签旋转角度
                       }
                   },
                   yAxis: {
                       min: 0,
                       title: {
                           text: '数量 (本)'
                       }
                   },
                   legend: {
                       enabled: false
                   },
                   tooltip: {
                       pointFormat: '图书总量: <b>{point.y:.0f} 本</b>'
                   },
                   series: [{
                       name: '库存图书',
                       data: a,
                       dataLabels: {
                           enabled: true,
                           rotation: 0,
                           color: '#FFFFFF',
                           align: 'right',
                           format: '{point.y:.0f}', // :.1f 为保留 1 位小数
                           y: 10
                       }
                   }]
               });
           }
       })
    })

</script>
</body>
</html>
