<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    
</head>

<body>
    <div class="container">
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
                <form action ='/submit' method='GET'>
                    <input type='hidden' name='x' value='${x}'>
                    <input type='hidden' name='candidate_id' value='${candidate_id}'>
                    <br>
                    ${questions}
                    <br>
                    <input type='submit' value='send'>
                </form>
            </div>
            <div class="col-2"></div>
        </div>
    </div>
</body>
</html>