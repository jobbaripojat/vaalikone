<%@page language ="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>Admin panel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous">
    </script>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=RocknRoll+One&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/style.css">
</head>

<body>
    <%
String userName = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("username")) userName = cookie.getValue();
}
}
if(userName == null) response.sendRedirect("login.jsp");
%>
    <div class="flex-container mt-3">
        <div class="row mx-auto">
            <div class="col-sm float-left">
                <table class="table">
                    <tbody>${candidates}</tbody>
                </table>
            </div>
            <div class="col"></div>
            <div class="col-6 float-right mt-5">
                <h1>Add a new candidate</h1>
                <br>
                <form method="GET" action="${form_action}">
                    <div class="row g-3 mt-3">
                        <div class="col-md-5">
                            <label for="first">First Name</label>
                            <input type="text" class="form-control" name="first_name" value="${first_name}"
                                placeholder="First name" required>
                        </div>
                        <div class="col-md-5">
                            <label for="last">Last Name</label>
                            <input type="text" class="form-control" name="last_name" value="${last_name}"
                                placeholder="Last name" required>
                        </div>
                        <div class="col-md-2">
                            <label for="age">Age</label>
                            <input type="text" class="form-control" name="age" value="${age}" placeholder="Age"
                                required>
                        </div>
                    </div>
                    <div class="row g-3 mt-3">
                        <div class="col-md-5">
                            <label for="municipality">Municipality</label>
                            <input type="text" class="form-control" name="municipality" value="${municipality}"
                                placeholder="Municipality" required>
                        </div>
                        <div class="col-md-5">
                            <label for="party">Party</label>
                            <input type="text" class="form-control" name="party" value="${party}" placeholder="Party"
                                required>
                        </div>
                        <div class="col-md-2">
                            <label for="identification">ID</label>
                            <input type="text" class="form-control" name="candidate_id" value="${candidate_id}"
                                placeholder="ID" required>
                            ${exists}
                        </div>
                    </div>
                    <br>
                    <div class="row g-3 mt-3">
                        <div class="col-md-12">
                            <label for="description">Description</label>
                            <textarea class="form-control" name="description" rows="4"
                                placeholder="Short description of candidate" required>${description}</textarea>
                        </div>
                    </div>
                    <div class="row g-3 mt-3">
                        <div class="col-md-12">
                            <br>
                            <button type="submit" class="btn btn-primary">Submit</button>
                            <a type="reset" class="float-end btn btn-secondary" href="/admin" Reset>Clear</a>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-1">
                <form action="/logout" method="POST">
                    <input class="btn btn-danger" type="submit" value="Logout">
                </form>
            </div>
        </div>
    </div>
</body>

</html>