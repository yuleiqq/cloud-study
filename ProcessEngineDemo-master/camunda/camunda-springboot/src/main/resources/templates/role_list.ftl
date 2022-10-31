<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>FreeMarker</title>
</head>
<body>

<h1 align="center">用户管理>>职务列表</h1>
<table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#FFFFEE" class="newfont03">
    <tr bgcolor="#EEEEEE">
        <td width="15%" height="30">职务编号</td>
        <td width="40%">职务名称</td>
        <td width="">操作</td>
    </tr>

    <#list roleList as role>
        <tr>
            <td height="30">${role.roleId}</td>
            <td height="30">${role.roleName}</td>
            <td><a href="/role/delete/${role.roleId}">删除</a></td>
        </tr>
    </#list>

    <tr>
        <td><input type="button" name="Submit2" value="返回" class="button" onclick="window.history.go(-1);"></td>
    </tr>

</table>
</body>
</html>