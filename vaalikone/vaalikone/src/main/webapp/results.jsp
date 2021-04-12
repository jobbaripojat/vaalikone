<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>Results</title>
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
    <div class="container mt-5">
		<div class="row align-items-center vh-100">
            <div class="col-3"></div>
            <div class="col-6">
                <div class="row">
                    <div class="col-12">
                        <h1 style="text-align:center"> Your results for ${municipality} </h1>
                    </div>
                    <div class="col-2"></div>
                    <div class="col-8 text-center">
                        <div>
                            <br>
                            <p style="text-align:center;font-size: 1.5rem;">${match_text}</p>
                            <br>
                            <form action="/index" method="get">
                                <input style="text-align:center" class='btn-lg btn-primary mt-5 mb-5' type='submit'
                                    value='Go back to main page'>
                            </form>
                            <br>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>

            </div>
            <div class="col-3"></div>
        </div>
    </div>
</body>

</html>