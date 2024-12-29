<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Rajesh Physics</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Favicon -->
<link rel="apple-touch-icon" sizes="57x57" href="faticon/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60" href="faticon/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72" href="faticon/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76" href="faticon/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114" href="faticon/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120" href="faticon/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144" href="faticon/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152" href="faticon/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180" href="faticon/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192"  href="faticon/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32" href="faticon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96" href="faticon/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16" href="faticon/favicon-16x16.png">
<link rel="manifest" href="faticon/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="faticon/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap"
	rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="lib/animate/animate.min.css" rel="stylesheet">
<link href="lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">

<!-- Customized Bootstrap Stylesheet -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Template Stylesheet -->
<link href="css/style.css" rel="stylesheet">
<link href="css/custom.css" rel="stylesheet">
<link href="css/animation.css" rel="stylesheet">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script src="https://www.google.com/recaptcha/api.js"></script>

</head>

<body>

<!-- Topbar Start -->
<div class="container-fluid d-none d-lg-block" style="border:2px solid #06BBCC; border-radius: 75px 75px 0px 0px;">
    <div class="row align-items-center py-1 px-xl-5">
        <div class="col-lg-3">
        
    
    <a href="" class="text-decoration-none">
        <img src="img/logo.jpg" alt="Rajesh Physics Logo" class="img-fluid">
    </a>
        </div>
        <div class="col-lg-3 text-right">
            <div class="d-inline-flex align-items-center">
                <i class="fa fa-2x fa-map-marker-alt text-primary mr-3"></i>
                <div class="text-left" style="margin-left: 25px">
                    <h6 class="font-weight-semi-bold mb-1">Our Office</h6>
                    <small>WD:18, Mussi Bira, Makhdumpur, Jehanabad, Bihar, India, 804422</small>
                </div>
            </div>
        </div>
        <div class="col-lg-3 text-right">
            <div class="d-inline-flex align-items-center">
                <i class="fa fa-2x fa-envelope text-primary mr-3"></i>
                <div class="text-left" style="margin-left: 25px">
                    <h6 class="font-weight-semi-bold mb-1">Email Us</h6>
                    <small>info.rajeshphysics@gmail.com</small>
                </div>
            </div>
        </div>
        <div class="col-lg-3 text-right">
            <div class="d-inline-flex align-items-center">
                <i class="fa fa-2x fa-phone text-primary mr-3"></i>
                <div class="text-left"style="margin-left: 25px">
                    <h6 class="font-weight-semi-bold mb-1">Call Us</h6>
                    <small>+91 91026-35151</small>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Topbar End -->

	<!-- Spinner Start -->
	<div id="spinner"
		class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
		<div class="spinner-border text-primary"
			style="width: 3rem; height: 3rem;" role="status">
			<span class="sr-only">Loading...</span>
		</div>
	</div>
	<!-- Spinner End -->

	<!-- Navbar Start -->
	<nav
		class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
		<a href=""
			class="navbar-brand d-flex align-items-center px-4 px-lg-5">
			<h2 class="m-0 text-primary">
			<marquee style="color:red">welcome to Rajesh physics</marquee>
			</h2>
		</a>
		<button type="button" class="navbar-toggler me-4"
			data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<div class="navbar-nav ms-auto p-4 p-lg-0">
				<a href="" class="nav-item nav-link active">Home</a> <a
					href="about" class="nav-item nav-link">About</a> <a
					href="course" class="nav-item nav-link">Courses</a>
				<div class="nav-item dropdown">
					<a href="#" class="nav-link dropdown-toggle"
						data-bs-toggle="dropdown">Pages</a>
					<div class="dropdown-menu fade-down m-0">
						<a href="team.html" class="dropdown-item">Our Team</a> <a
							href="testimonial.html" class="dropdown-item">Testimonial</a> <a
							href="404.html" class="dropdown-item">404 Page</a>
					</div>
				</div>
				<a href="contact" class="nav-item nav-link">Contact</a> <a
					href="register" class="nav-item nav-link">Registration</a>
			</div>
			<a href="login" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block">Login
				Here<i class="fa fa-arrow-right ms-3"></i>
			</a>
		</div>
	</nav>
	<!-- Navbar End -->