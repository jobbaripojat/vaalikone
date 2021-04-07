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
    <div class="flex-container mt-3">
        <div class="row mx-auto">
            <div class="col float-left">
                <table class="table table-striped">
                    <tbody>${candidates}</tbody>
                </table>
            </div>
            <div class="col"></div>
            <div class="col-6 float-right mt-sm-5">
                <form method="GET" action="${form_action}">
                    <div class="form-row">
                        <div class="form-group col-md-5">
                            <label for="first">First Name</label>
                            <input type="text" class="form-control" name="first_name" value="${first_name}" placeholder="First name" required>
                        </div>
                        <div class="form-group col-md-5">
                            <label for="last">Last Name</label>
                            <input type="text" class="form-control" name="last_name" value="${last_name}" placeholder="Last name" required>
                        </div>
                        <div class="form-group col-md-1">
                            <label for="age">Age</label>
                            <input type="text" class="form-control" name="age" value="${age}" placeholder="Age" required>
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-5">
                            <label for="municipality">Municipality</label>
                            <input type="text" class="form-control" name="municipality" value="${municipality}" placeholder="Municipality" required>
                        </div>
                        <div class="form-group col-md-5">
                            <label for="party">Party</label>
                            <input type="text" class="form-control" name="party" value="${party}" placeholder="Party" required>
                        </div>
                        <div class="form-group col-md-1">
                            <label for="identification">ID</label>
                            <input type="text" class="form-control" name="candidate_id" value="${candidate_id}" placeholder="ID" required>
                            ${exists}
                        </div>
                        <div class="col-md-1"></div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-11">
                            <label for="description">Description</label>
                            <textarea class="form-control" name="description" rows="4" placeholder="Short description of candidate" required>${description}</textarea>
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-11">
                            <br>
                            <button type="submit" class="btn btn-primary">Submit</button>
                            <a type="reset" class="float-right btn btn-secondary" href="/admin" Reset>Clear</a>
                        </div>
                        <div class="col-md-1"></div>
                    </div>


                    <!-- <div class="form-group row">
                        <label for="identification" class="col-sm-2 col-form-label">ID</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="candidate_id" value="${candidate_id}" placeholder="Candidate ID">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="first" class="col-sm-2 col-form-label">First Name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="first_name" value="${first_name}" placeholder="First name">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="last" class="col-sm-2 col-form-label">Last Name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="last_name" value="${last_name}" placeholder="Last name">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="party" class="col-sm-2 col-form-label">Party</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="party" value="${party}" placeholder="Party">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="municipality" class="col-sm-2 col-form-label">Municipality</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="municipality" value="${municipality}" placeholder="Municipality">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="age" class="col-sm-2 col-form-label">Age</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="age" value="${age}" placeholder="Age">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="description" class="col-sm-2 col-form-label">Description </label>
                        <div class="col-sm-10">
                            <textarea class="form-control" name="description" rows="4" placeholder="Description">${description}</textarea>
                            <br>
                            <button type="submit" class="btn btn-primary">Submit</button>
                            <a type="reset" class="float-right btn btn-danger" href="/admin" Reset>Clear</a>
                        </div>
                    </div> -->


                </form>
            </div>
            <div class="col-1"></div>
        </div>
    </div>
</body>

</html>