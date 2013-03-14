<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="blog"/>
<title>Insert title here</title>
</head>
<body>
  <h4>Admin Menu</h4>
  <div class="body" style="padding: 10px; position: relative; left: 30px;">
  <ul>
  	<li><g:link controller="avatar" action="list">Add/Modify Avatars</g:link></li>
  	<li><g:link controller="user" action="list">View/Modify Users</g:link></li>
  </ul>
  </div>
</body>
</html>