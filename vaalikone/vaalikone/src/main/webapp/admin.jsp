<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
        integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous">
    </script>
    <link rel="stylesheet" href="\style.css">
</head>

<body>
    <div class="flex-container">
        <div class="row mx-auto">
            <div class="col-4 float-left">
                <table class="table table-striped">
                    <tbody>${candidates}</tbody>
                </table>
            </div>
            <div class="col-2"></div>
            <div class="col-6 float-right">
                <form>
                    <div class="col">
                        <label for="identification" class="form-label">ID</label>
                        <input type="number" class="form-control" name="candidate_id" value="${candidate_id}">
                    </div>
                    <div class="col">
                        <label for="first" class="form-label">First Name</label>
                        <input type="text" class="form-control" name="first_name" value="${first_name}">
                    </div>
                    <div class="col">
                        <label for="last" class="form-label">Last Name</label>
                        <input type="text" class="form-control" name="last_name" value="${last_name}">
                    </div>
                    <div class="col">
                        <label for="party" class="form-label">Party</label>
                        <input type="text" class="form-control" name="party" value="${party}">
                    </div>
                    <div class="col">
                        <label for="municipality" class="form-label">Municipality</label>
                        <input type="text" class="form-control" name="municipality" value="${municipality}">
                    </div>
                    <div class="col">
                        <label for="age" class="form-label">Age</label>
                        <input type="text" class="form-control" name="age" value="${age}">
                    </div>
                    <div class="col">
                        <label for="description" class="form-label">Description </label>
                        <textarea class="form-control" name="description" rows="5" value="${description}"></textarea>
                    </div>
                    <div class="col form-check">
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>

</html>