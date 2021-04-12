<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>Welcome</title>
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
			<div class="col"></div>
			<div class="col-5">
				<h1 class="text-center">ELECTION MACHINE 2021</h1>
				<br>
				<h5>Welcome to [client]'s Election Machine, please select your municipality from the list below!</h5>
				<br>
				<div class="mx-auto text-center">
					<form action="/questions?weComeFromAdmin=0" method="get">
						<select id="municipalities" name="municipality" class="form-select">
							<option value='*' selected>All municipalities</option>
							${municipalities}
						</select>
						<br>
						<input type="submit" class="btn btn-primary" value="Go to questions!">
					</form>
				</div>
			</div>
			<div class="col"></div>
		</div>
	</div>
</body>

</html>