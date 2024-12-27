<jsp:include page="headerMain.jsp"></jsp:include>

<div class="content-wrapper">
	<!-- Content Header -->
	<div class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1 class="m-0">All Courses</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item active">All Courses</li>
					</ol>
				</div>
			</div>
		</div>
	</div>

	<!-- Main Content -->
	<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
						
							<div class="card">
								<div class="card-header">
									<h3 class="card-title">DataTable with default features</h3>
								</div>

								<div class="card-body">
									<table id="example1" class="table table-bordered table-striped">
										<thead>
									<tr>
										<th>Name</th>
										<th>Language</th>
										<th>Title</th>
										<th>Description</th>
										<th>Price</th>
										<th>Active</th>
									</tr>
								</thead>
								<tbody id="courseTableBody">
									<!-- Dynamic content will be inserted here -->
									
								</tbody>
								<tfoot>
									<tr>
										<th>Name</th>
										<th>Language</th>
										<th>Title</th>
										<th>Description</th>
										<th>Price</th>
										<th>Active</th>
									</tr>
								</tfoot>
							</table>
								</div>

							</div>

						</div>

					</div>

				</div>

			</section>
</div>

<jsp:include page="footerMain.jsp"></jsp:include>

<script src="plugins/datatables/jquery.dataTables.min.js"></script>
<script src="plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script
	src="plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
<script
	src="plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
<script src="plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
<script src="plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
<script src="plugins/jszip/jszip.min.js"></script>
<script src="plugins/pdfmake/pdfmake.min.js"></script>
<script src="plugins/pdfmake/vfs_fonts.js"></script>
<script src="plugins/datatables-buttons/js/buttons.html5.min.js"></script>
<script src="plugins/datatables-buttons/js/buttons.print.min.js"></script>
<script src="plugins/datatables-buttons/js/buttons.colVis.min.js"></script>

<script>
  
    
  
    
    // Fetch data from the API
    var settings = {
      "url": "${pageContext.request.contextPath}/api/course/get-all",
      "method": "GET",
      "timeout": 0,
    };

    $.ajax(settings).done(function (response) {
      console.log(response);
      var courseData = response.data.content;
      var courseTableBody = $('#courseTableBody');
      courseTableBody.empty(); // Clear any existing rows

      // Construct rows dynamically
      $.each(courseData, function(index, course) {
        var row = '<tr>' +
                    '<td>' + course.name + '</td>' +
                    '<td>' + course.courseLanguage + '</td>' +
                    '<td>' + course.courseTitle + '</td>' +
                    '<td>' + course.courseDescription + '</td>' +
                    '<td>' + course.coursePrice + '</td>' +
                    '<td>'+ "<div class='btn-group btn-group-sm'>"+
                    "<button  class='btn btn-info'><i class='fas fa-eye'></i></button>"+
                    "<button  class='btn btn-danger'><i class='fas fa-trash'></i></button>"+
                    "</div>"+ '</td>' +
                  '</tr>';
        courseTableBody.append(row);
      });
    });
    
 // Initialize DataTable
    $(function () {
     $("#example1").DataTable({
       "responsive": true,"paging":true,"searching":true,"ordering":true,"info":true, "lengthChange": false, "autoWidth": false,
       "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
     }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
   });
     
 
</script>
