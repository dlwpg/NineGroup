<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/3
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <script src="/static/js/jquery-3.5.0.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
</head>
<body onload="load(1)">
<h4>
    <button onclick="ruturnallbook()">懒鬼，点我可以一键还书了！！！</button>
</h4>
<table class="table table-hover">
    <thead>
    <tr>
        <th style="text-align: center"><input type="checkbox" name="allcheck" id="all" onclick="checkedall()">全选</th>
        <th style="text-align: center">书名</th>
        <th style="text-align: center">作者</th>
        <th style="text-align: center">出版社</th>
        <th style="text-align: center">价格</th>
        <th style="text-align: center">封面</th>
        <th style="text-align: center">借书时间</th>
        <th style="text-align: center">操作</th>
    </tr>
    </thead>
    <tbody id="tbody">

    </tbody>
</table>

<center>
    <li>
        <a href="javascript:void(0)" onclick="firstpage()">首页</a>
        <a href="javascript:void(0)" onclick="prepage()">上一页</a>
        第<span id="span"></span>页
        <a href="javascript:void(0)" onclick="nextpage()">下一页</a>
        <a href="javascript:void(0)" onclick="endpage()">尾页</a>
    </li>
</center>
<input type="hidden" id="hhh" value="${userinfo.userid}">
</body>
<script>
    var fpage;
    var epage;
    var ppage;
    var npage;
    var tpage;
    var userid = $("#hhh").val();
    var count = 0;


    function firstpage() {
        load(fpage);
    }

    //上一页
    function prepage() {
        load(ppage);
    }

    function nextpage() {
        load(npage);
    }

    function endpage() {
        load(epage);
    }

    function load(thispage) {

        console.log(ppage);
        console.log(thispage);
        console.log(npage);

        $.ajax({
            url: "/brrowBook/brrowbook.ajax",
            type: "post",
            data: {"page": thispage, "userid": userid},
            dataType: "json",
            success: function (data) {
                console.log(data.list)
                ppage = data.prepage;
                npage = data.nextpage;
                fpage = data.firstpage;
                epage = data.endpage;
                tpage = data.thispage;
                var html = "";
                for (var i = 0; i < data.list.length; i++) {

                    html += "<tr>" +
                        "<td align='center'><input onclick='tongshixuanzhong(this)' name='allid' type='checkbox' value='" + data.list[i].id + "'/></td>" +
                        "<td align='center'><input name='bookid' type='checkbox' value='" + data.list[i].book.bookid + "'/></td>" +
                        "<td align='center'>" + data.list[i].book.bookname + "</td>" +
                        "<td align='center'>" + data.list[i].book.author + "</td>" +
                        "<td align='center'>" + data.list[i].book.press + "</td>" +
                        "<td align='center'>" + data.list[i].book.price + "</td>" +
                        "<td align='center'>" + data.list[i].book.picturepath + "</td>" +
                        "<td align='center'>" + data.list[i].date + "</td>" +
                        "<td align='center'><button type='button' onclick='returnbook(this)'>点我还书</button><button><a href='#'>查看详情</a></button></td>" +
                        "</tr>"
                }
                $("#tbody").html(html);
                $("#span").html(tpage);
                 //隐藏第二列
                $("#tbody tr").find("td:eq(1)").hide();
            }

        })

    }


    function returnbook(obj) {

        var info = confirm("确定还书?还书之前请确定已将图书归还！");
        if (info) {
            var id = $(obj).parent().parent().find("td").find("input").eq(0).val();

            console.log(id);
            $.ajax({
                url: "/brrowBook/returnbook.ajax?bookid=" + id + "&userid=" + userid,
                type: "post",
                dataType: "text",
                success: function (data) {

                    if (data > 0) {
                        load(tpage);
                    }
                }
            })

        }
    }

    function checkedall() {

        $("input[name='allid']").prop("checked", $("#all").prop("checked"));
        //隐藏框选中与全选一致
        $("input[name='bookid']").prop("checked", $("#all").prop("checked"));
    }

    function tongshixuanzhong(obj) {
        $(obj).parent().parent().find("td").find("input").eq(1).prop("checked", $(obj).prop("checked"));
        // console.log($(obj).parent().parent().find("td").find("input").eq(1).prop("checked"))
    }

    function ruturnallbook() {
        var info = confirm("确定还书?还书之前请确定已将图书归还！");
        if (info) {
            var ids = "";
            $("input[name='allid']:checked").each(function () {
                count++;
                ids += $(this).val() + ",";
            })
            //图书id
            var bookids = "";
            $("input[name='bookid']:checked").each(function () {
                bookids += $(this).val() + ",";
            })

            console.log(bookids);

            $.ajax({
                url: "/brrowBook/returnAllBook.ajax?userid=" + userid,
                type: "get",
                data: {"id": ids, "count": count, "bookids": bookids},
                dataType: "json",
                success: function (data) {
                    if (data > 0) {
                        load(1);
                    }

                }
            })

        }

    }

</script>
</html>
