<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Rajesh Physics | Dashboard</title>

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&amp;display=fallback">

<link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

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


<link rel="stylesheet" href="plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" href="plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet" href="plugins/datatables-buttons/css/buttons.bootstrap4.min.css">

<link rel="stylesheet" href="plugins/summernote/summernote-bs4.min.css">
<link rel="stylesheet" href="plugins/select2/css/select2.min.css">
<link rel="stylesheet" href="plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">



<link rel="stylesheet" href="css/adminlte.min2167.css?v=3.2.0">
<script nonce="3767116d-345d-4b1e-8213-7e4f38c3e8e4">(function(w,d){!function(di,dj,dk,dl){di[dk]=di[dk]||{};di[dk].executed=[];di.zaraz={deferred:[],listeners:[]};di.zaraz.q=[];di.zaraz._f=function(dm){return function(){var dn=Array.prototype.slice.call(arguments);di.zaraz.q.push({m:dm,a:dn})}};for(const dp of["track","set","debug"])di.zaraz[dp]=di.zaraz._f(dp);di.zaraz.init=()=>{var dq=dj.getElementsByTagName(dl)[0],dr=dj.createElement(dl),ds=dj.getElementsByTagName("title")[0];ds&&(di[dk].t=dj.getElementsByTagName("title")[0].text);di[dk].x=Math.random();di[dk].w=di.screen.width;di[dk].h=di.screen.height;di[dk].j=di.innerHeight;di[dk].e=di.innerWidth;di[dk].l=di.location.href;di[dk].r=dj.referrer;di[dk].k=di.screen.colorDepth;di[dk].n=dj.characterSet;di[dk].o=(new Date).getTimezoneOffset();if(di.dataLayer)for(const dw of Object.entries(Object.entries(dataLayer).reduce(((dx,dy)=>({...dx[1],...dy[1]})))))zaraz.set(dw[0],dw[1],{scope:"page"});di[dk].q=[];for(;di.zaraz.q.length;){const dz=di.zaraz.q.shift();di[dk].q.push(dz)}dr.defer=!0;for(const dA of[localStorage,sessionStorage])Object.keys(dA||{}).filter((dC=>dC.startsWith("_zaraz_"))).forEach((dB=>{try{di[dk]["z_"+dB.slice(7)]=JSON.parse(dA.getItem(dB))}catch{di[dk]["z_"+dB.slice(7)]=dA.getItem(dB)}}));dr.referrerPolicy="origin";dr.src="../../cdn-cgi/zaraz/sd0d9.js?z="+btoa(encodeURIComponent(JSON.stringify(di[dk])));dq.parentNode.insertBefore(dr,dq)};["complete","interactive"].includes(dj.readyState)?zaraz.init():di.addEventListener("DOMContentLoaded",zaraz.init)}(w,d,"zarazData","script");})(window,document);</script>
</head>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">

		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light">

			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
					href="#" role="button"><i class="fas fa-bars"></i></a></li>
				<li class="nav-item d-none d-sm-inline-block"><a
					href="index3.html" class="nav-link">Home</a></li>
				<li class="nav-item d-none d-sm-inline-block"><a href="#"
					class="nav-link">Contact</a></li>
			</ul>

			<ul class="navbar-nav ml-auto">

				<li class="nav-item dropdown"><a class="nav-link"
					data-toggle="dropdown" href="#"> <i class="far fa-bell"></i> <span
						class="badge badge-warning navbar-badge">15</span>
				</a>
					<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
						<span class="dropdown-header">15 Notifications</span>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <i
							class="fas fa-envelope mr-2"></i> 4 new messages <span
							class="float-right text-muted text-sm">3 mins</span>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <i
							class="fas fa-users mr-2"></i> 8 friend requests <span
							class="float-right text-muted text-sm">12 hours</span>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <i class="fas fa-file mr-2"></i>
							3 new reports <span class="float-right text-muted text-sm">2
								days</span>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item dropdown-footer">See All
							Notifications</a>
					</div></li>
				<li class="nav-item"><a class="nav-link"
					data-widget="fullscreen" href="#" role="button"> <i
						class="fas fa-expand-arrows-alt"></i>
				</a></li>

			</ul>
		</nav>


		<aside class="main-sidebar sidebar-dark-primary elevation-4">

			<a href="index3.html" class="brand-link"> <img
				src="img/logowy.jpg" alt="Logo"
				class="brand-image img-circle elevation-3" style="opacity: .8">
				<span class="brand-text font-weight-light">Rajesh Physics</span>
			</a>

			<div class="sidebar">

				<div class="user-panel mt-3 pb-3 mb-3 d-flex">
 					<div class="image">
						<img src="img/hi.gif" class="img-circle elevation-2"
							alt="User Image">
					</div> 
					<div class="info">
						<strong> <h5 class="d-block" id="username" style="color:white"> </h5></strong>
					</div>
				</div>

				<div class="form-inline">
					<div class="input-group" data-widget="sidebar-search">
						<input class="form-control form-control-sidebar" type="search"
							placeholder="Search" aria-label="Search">
						<div class="input-group-append">
							<button class="btn btn-sidebar">
								<i class="fas fa-search fa-fw"></i>
							</button>
						</div>
					</div>
				</div>

				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column"
						data-widget="treeview" role="menu" data-accordion="false">

						<li class="nav-item menu-close"><a href="#"
							class="nav-link active"> <i
								class="nav-icon fas fa-tachometer-alt"></i>
								<p>
									Dashboard <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="index-2.html"
									class="nav-link active"> <i class="far fa-circle nav-icon"></i>
										<p>Dashboard v1</p>
								</a></li>
								
							</ul></li>
							
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-image"></i>
								<p>
									Banner <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="pages/charts/chartjs.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>Upload Banner</p>
								</a></li>
								<li class="nav-item"><a href="pages/charts/flot.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>All Banners</p>
								</a></li>
								
							</ul></li>

						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-edit"></i>
								<p>
									Course <i class="fas fa-angle-left right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="add-course" class="nav-link">
										<i class="far fa-circle nav-icon"></i>
										<p>Add Course</p>
								</a></li>
								<li class="nav-item"><a href="get-AllCourses"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>All Courses</p>
								</a></li>

							</ul></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-table"></i>
								<p>
									Batch <i class="fas fa-angle-left right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="pages/tables/simple.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>Add Batch</p>
								</a></li>
								<li class="nav-item"><a href="pages/tables/data.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>All Batches</p>
								</a></li>
							</ul></li>

						<li class="nav-item"><a href="pages/widgets.html"
							class="nav-link"> <i class="nav-icon fas fa-users"></i>
								<p>
									User <i class="fas fa-angle-left right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="pages/tables/simple.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>All User</p>
								</a></li>
								<li class="nav-item"><a href="update-token"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>Update Token</p>
								</a></li>
							</ul></li>
						
						</li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-chart-pie"></i>
								<p>
									Stream<i class="fas fa-angle-left right"></i> 
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="add-category"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>Create Stream</p>
								</a></li>
								
								<li class="nav-item"><a href="pages/layout/boxed.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>All Stream</p>
								</a></li>
							
							</ul></li>
							
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-clipboard-check"></i>
								<p>
									Quiz<i class="fas fa-angle-left right"></i> 
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="add-quiz"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>Add Auiz</p>
								</a></li>
								
								<li class="nav-item"><a href="get-allQuiz"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>All Quizzes</p>
								</a></li>
							
							</ul></li>
							
						
						<li class="nav-item"><a href="pages/calendar.html"
							class="nav-link"> <i class="nav-icon far fa-id-card"></i>
								<p>
									Contacts Us 
								</p>
						</a></li>
						<li class="nav-item"><a href="pages/calendar.html"
							class="nav-link"> <i class="nav-icon far fa-envelope"></i>
								<p>
									NewsLetter 
								</p>
						</a></li>
						<li class="nav-item" id="logoutBtn" ><a href=""
							class="nav-link"> <i class="nav-icon far fa-times-circle"></i> 
								<p>LOGOUT</p>
						</a></li>
						

					</ul>
				</nav>

			</div>

		</aside>