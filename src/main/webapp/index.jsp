<%--
  Created by IntelliJ IDEA.
  User: luo
  Date: 2021/7/27
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index.jsp</title>
</head>
<body>
<h2>Tomcat1!!!</h2>
<h2>Tomcat1!!!</h2>
<h2>Tomcat1!!!</h2>
springmvc上传文件
<form name="form1" action="/mmall/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file" />
    <input type="submit" value="springmvc上传文件" />
</form>

<br/>
<br/>

富文本图片上传文件
<form name="form2" action="/mmall/manage/product/richtext_img_upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file" />
    <input type="submit" value="富文本图片上传文件" />
</form>
</body>
</html>
