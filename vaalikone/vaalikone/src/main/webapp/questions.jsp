<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>Questions</title>
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
        <div class="row">
            <div class="col-3"></div>
            <div class="col-6">
                <h1 style="text-align:center"> Election Machine </h1>
                <br>
                <h2 style="text-align:center"> Questions </h2>
            </div>
            <div class="col-3"></div>
        </div>

        <div class="row">
            <div class="col-2"></div>
            <div class="col-8">
                <div class="row">
                    <form action='/submit' method='GET'>
                        <input type='hidden' name='x' value='${x}'>
                        <input type='hidden' name='municipality' value='${municipality}'>
                        <input type='hidden' name='candidate_id' value='${candidate_id}'>
                        ${questions}
                        <div class='row mt-3'>
                            <div class='col-12 d-flex justify-content-center'>
                                <input class='btn-lg btn-primary mt-5 mb-5' type='submit' value='Submit your answers'>
                            </div>
                        </div>
                </div>
                </form>
            </div>
            <div class="col-2"></div>
        </div>
    </div>
</body>

</html>