<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<html>
<head>
    <title>Upload</title>
</head>
<body>

<form method="POST" action="/upload1" enctype="multipart/form-data">
    Name: <input type="text" name="name"/><br/>
    File: <input type="file" name="imageFile" accept="image/jpeg,image/png,image/gif"/><br/>
    <input type="submit" value="Submit"/>
</form>

<br/><br/><br/>

<form method="POST" action="/upload2" enctype="multipart/form-data">
    Name: <input type="text" name="name"/><br/>
    File: <input type="file" name="imageFile" accept="image/jpeg,image/png,image/gif"/><br/>
    <input type="submit" value="Submit"/>
</form>

<br/><br/><br/>

<form method="POST" action="/upload3" enctype="multipart/form-data">
    Name: <input type="text" name="name"/><br/>
    File: <input type="file" name="imageFile" accept="image/jpeg,image/png,image/gif"/><br/>
    <input type="submit" value="Submit"/>
</form>

</body>
</html>
