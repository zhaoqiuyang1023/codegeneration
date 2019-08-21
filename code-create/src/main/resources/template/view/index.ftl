<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>信息列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all"/>

    <style>
        .searDiv {
            display: -webkit-flex;
            display: flex;
            margin: 10px;
        }

        .flex-item {
            margin-left: 20px;
            justify-content: space-around;
        }
    </style>
</head>
<body class="childrenBody">


<fieldset class="layui-elem-field">
    <legend>用户信息检索</legend>
    <div class="layui-field-box">

        <form class="layui-form">
            <div class="searDiv">
                <div class="flex-item">
                    <div class="layui-form-item">
                        <label class="layui-form-label">时间</label>
                        <div class="layui-input-inline">
                            <input class="layui-input datetime" name="dateStart" id="dateStart" type="text"
                                   placeholder="yyyy-MM-dd HH:mm:ss">
                        </div>
                    </div>
                </div>

                <div class="flex-item">
                    <div class="layui-form-item">
                        <label class="layui-form-label">名称</label>
                        <div class="layui-input-inline">
                            <input type="text" name="tableName" id="tableName"
                                   placeholder="请输入名称"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>

                <div class="flex-item">
                    <div class="layui-form-item">
                        <div class="layui-input-inline">
                            <button class="layui-btn" id="searchForm">查询</button>
                        </div>
                    </div>
                </div>
            </div>

        </form>
    </div>

</fieldset>
<div class="layui-form users_list">
    <table class="layui-table" id="tables" lay-filter="tables"></table>

<#--    <script type="text/html" id="barOpt">-->

<#--        <a class="layui-btn layui-btn-xs layui-anim layui-anim-scaleSpring" lay-event="code" title="生成代码">生成代码</a>-->
<#--        <a class="layui-btn layui-btn-xs layui-anim layui-anim-scaleSpring" lay-event="del" title="删除">删除</a>-->
<#--        <a class="layui-btn layui-btn-xs layui-anim layui-anim-scaleSpring" lay-event="edit" title="编辑">编辑</a>-->
<#--    </script>-->
</div>
<div class="flex-item">
    <div class="layui-field-box">

        <div class="searDiv">
            <div class="flex-item">
                <div class="layui-form-item">
                    <label class="layui-form-label">包名</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="packageName" id="packageName" type="text" lay-verify="required"
                               placeholder="com.mysql.demo">
                    </div>
                </div>
            </div>

            <div class="flex-item">
                <div class="layui-form-item">
                    <label class="layui-form-label">作者</label>
                    <div class="layui-input-inline">
                        <input type="text" name="author" id="author"
                               placeholder="@auth" lay-verify="required"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>

            <div class="flex-item">
                <div class="layui-form-item">
                    <div class="layui-input-inline">
                        <button id="codeGeneration" class="layui-btn">生成</button>
                    </div>
                </div>
            </div>
        </div>

    </div>

</div>
<div id="page"></div>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['layer', 'laydate', 'form', 'table', 'element'], function () {
        var tableName = '';
        var dateStart = '';
        var dateEnd = '';
        let genConfig =
            {
                "packageName": "",
                "tableName": [],
                "author": "",
                "comments": ""
            };

        let layer = layui.layer,
            $ = layui.jquery,
            form = layui.form,
            element = layui.element,
            laydate = layui.laydate,
            table = layui.table;

        //日期时间选择器
        laydate.render({
            elem: '#dateStart'
            , range: '~',
            done: function (value, date) {
                console.log(value);
                var str = value.split('~');
                dateStart = str[0];
                dateEnd = str[1];
                console.log(dateStart);
                console.log(dateEnd)
            }
        });
        $(function () {
            $("#author").val(localStorage.getItem('author'));
            $("#packageName").val(localStorage.getItem('packageName'));
        });

        //监听工具条
        table.on('tool(tables)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                layer.open({
                    title: "编辑公司信息",
                    type: 2,
                    area: ['512px', '450px'],
                    content: "${base}/edit/" + data.id,
                    success: function (layero, index) {

                    }
                });
            }
            if (obj.event === 'code') {
                layer.open({
                    title: "编辑公司信息",
                    type: 2,
                    area: ['512px', '450px'],
                    content: "${base}/edit/" + data.id,
                    success: function (layero, index) {

                    }
                });
            }
            if (obj.event === "del") {

                layer.confirm("你确定要删除该公司信息么？", {btn: ['是的,我确定', '我再想想']},
                    function () {
                        $.post("${base}/del/" + data.id, function (res) {
                            if (res.success) {
                                $(obj).remove();
                                obj.del(); //删除对应行（tr）的DOM结构
                                layer.close();
                                layer.msg("删除成功", {time: 1000}, function () {

                                });
                            } else {
                                layer.msg(res.message);
                            }
                        });
                    }
                )
            }
        });


        table.render({
            elem: '#tables',
            url: '/generator/page',
            method: 'POST',
            parseData: function (res) { //res 即为原始返回的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.data.total, //解析数据长度
                    "data": res.data.records  //解析数据列表
                };
            },
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                groups: 7, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits: [3, 10, 20, 30]
            },
            cellMinWidth: 80, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                {type: 'checkbox', fixed: 'left', width: '20%'},
                {field: 'tableName', title: '名称', width: '20%'},
                {field: 'engine', title: '引擎类型', width: '20%'},
                {field: 'tableComment', title: '备注', width: '10%'},
                {field: 'dateCreate', title: '创建时间', width: '15%'},
                {fixed: 'right', title: '操作', width: '15%', align: 'center', toolbar: '#barOpt'}
            ]]
        });


        $('#searchForm').on('click',function () {
            table.reload('tables', {
                where: {
                    "tableName": $("#tableName").val(),
                    "dateStart": dateStart,
                    "dateEnd": dateEnd,
                },
                page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
            return false;
        });


        $("#codeGeneration").on("click", function () {
            //获取选中数据
            let checkItems = table.checkStatus('tables').data;
            for (let i = 0; i < checkItems.length; i++) {
                genConfig.tableName[i] = checkItems[i].tableName;
            }
            if (genConfig.tableName.length === 0) {
                layer.alert("请选择表");
                return false;
            }
            genConfig.author = $("#author").val();
            genConfig.packageName = $("#packageName").val();
            window.location.href = "${base}/generator/code/" + encodeURI(JSON.stringify(genConfig));

            localStorage.setItem('packageName', $("#packageName").val());
            localStorage.setItem('author', $("#author").val());
        });

    });
</script>


</body>
</html>