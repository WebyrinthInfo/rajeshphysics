<jsp:include page="headerMain.jsp"></jsp:include>

<!-- Content Wrapper -->
<div class="content-wrapper">
	<!-- Content Header -->
	<div class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1 class="m-0">All Test Papers</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item active">All Test Papers</li>
					</ol>
				</div>
			</div>
		</div>
	</div>

	<!-- Main Content -->
	<div class="content">
		<div class="container-fluid">
			<div class="row" id="testPaperContainer">
				<!-- Test Paper rows will be dynamically inserted here -->
			</div>
		</div>
	</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- Script for fetching and displaying test papers -->
<script>
    $(document).ready(function() {
        // Retrieve courseName from localStorage
        const courseName = localStorage.getItem("course");
        
        if (courseName) {
            // Function to fetch test papers
            function fetchTestPapers() {
                $.ajax({
                    url: "${pageContext.request.contextPath}/api/quiz/getAll/actived",
                    method: "GET",
                    data: { courseName: courseName },
                    success: function(response) {
                        var testPaperContainer = $('#testPaperContainer');
                        testPaperContainer.empty();

                        // Display test papers
                        if (response.data.length > 0) {
                            response.data.forEach(function(testPaper) {
                                var rowHtml = 
                                    "<div class='col-md-4 col-sm-6 mb-4'>" +
                                        "<div class='card card-primary card-outline shadow-sm h-100'>" +
                                            "<div class='card-header'>" +
                                                "<h5 class='card-title font-weight-bold'>" + testPaper.title + "</h5>" +
                                            "</div>" +
                                            "<div class='card-body'>" +
                                                "<p class='card-text'>" + testPaper.description + "</p>" +
                                                "<div class='row'>" +
                                                    "<div class='col-6'>" +
                                                        "<p class='card-text'><strong>Questions: </strong>" + testPaper.numberOfQuestion + "</p>" +
                                                    "</div>" +
                                                    "<div class='col-6'>" +
                                                        "<p class='card-text'><strong>Max Marks: </strong>" + testPaper.maxMarks + "</p>" +
                                                    "</div>" +
                                                "</div>" +
                                                "<div class='row'>" +
                                                    "<div class='col-6'>" +
                                                        "<a href='startTest?id=" + testPaper.id + "' class='btn btn-success btn-block btn-sm'>" +
                                                            "<i class='fas fa-play'></i> Start" +
                                                        "</a>" +
                                                    "</div>" +
                                                    "<div class='col-6'>" +
                                                        "<a href='viewTest?id=" + testPaper.id + "' class='btn btn-primary btn-block btn-sm'>" +
                                                            "<i class='fas fa-eye'></i> View" +
                                                        "</a>" +
                                                    "</div>" +
                                                "</div>" +
                                            "</div>" +
                                        "</div>" +
                                    "</div>";
                                testPaperContainer.append(rowHtml);
                            });
                        } else {
                            testPaperContainer.append("<div class='col-12'><p>No test papers found</p></div>");
                        }
                    },
                    error: function(xhr, status, error) {
                        Swal.fire({
                            icon: 'error',
                            title: 'Error',
                            text: 'Failed to fetch test papers. Please try again later.'
                        });
                    }
                });
            }

            // Fetch test papers initially
            fetchTestPapers();
        } else {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Course information not found. Please select a course.'
            });
        }
    });
</script>

<jsp:include page="footerMain.jsp"></jsp:include>
