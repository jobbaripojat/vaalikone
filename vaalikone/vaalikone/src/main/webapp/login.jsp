<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>
    <title>Login</title>
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
    <div class="container">
        <div class="row align-items-center vh-100">
            <div class="col-4"></div>
            <div class="col-4 text-center">
                <form action="/login" method="POST">
                    <div class="row g-3">
                        <label for="name" class="control-label">USERNAME</label>
                        <input type="text" class="form-control" name="username" required>
                        <br>
                    </div>
                    <div class="row g-3">
                        <br>
                        <label for="password" class="control-label">PASSWORD</label>
                        <input type="password" class="form-control" name="userpass" required>
                        <br>
                    </div>
                    <br>
                    <input class="btn btn-primary" type="submit" value="Login">
                </form>
            </div>
            <div class="col-4"></div>
        </div>
    </div>
</body>

</html>