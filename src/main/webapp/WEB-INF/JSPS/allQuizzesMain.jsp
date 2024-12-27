<jsp:include page="headerMain.jsp"></jsp:include>

<!-- Content Wrapper -->
<div class="content-wrapper">
    <!-- Content Header -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0">All Quizzes</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">All Quizzes</li>
                    </ol>
                </div>
            </div>
            <!-- Search Box -->
            <div class="row">
                <div class="col-sm-6">
                    <input type="text" id="searchBox" class="form-control" placeholder="Search quizzes..." />
                </div>
            </div>
        </div>
    </div>

    <!-- Main Content -->
    <div class="content">
        <div class="container-fluid">
            <div class="row" id="quizContainer">
                <!-- Quiz rows will be dynamically inserted here -->
            </div>
            <!-- Pagination -->
            <div class="row">
                <div class="col-sm-12">
                    <ul id="pagination" class="pagination justify-content-center">
                        <!-- Pagination buttons will be dynamically inserted here -->
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Include jQuery, Popper.js, Bootstrap, and SweetAlert library -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<!-- Script for fetching and displaying quizzes with pagination and search -->
<script>
    $(document).ready(function() {
        var currentPage = 0;  // Start at the first page
        var pageSize = 2;  // Page size
        var searchQuery = "";
        var sortBy = "id";  // Default sort field
        var sortDir = "desc";  // Default sort direction

        // Function to fetch quizzes with pagination and search
        function fetchQuizzes(page, searchQuery, sortBy, sortDir) {
            $.ajax({
                url: "${pageContext.request.contextPath}/api/quiz/getAll",
                method: "GET",
                data: {
                    search: searchQuery,
                    pageNumber: page,
                    pageSize: pageSize,
                    sortBy: sortBy,
                    sortDir: sortDir
                },
                success: function(response) {
                    var quizContainer = $('#quizContainer');
                    quizContainer.empty();
                    var pagination = $('#pagination');
                    pagination.empty();

                    // Display quizzes
                    if (response.data.length > 0) {
                        response.data.forEach(function(quiz, index) {
                            var rowHtml = 
                                "<div class='col-12 mb-4'>"+
                                    "<div class='card card-primary card-outline shadow-sm h-100'>"+
                                    "<div class='card-header'>"+
                                    "<h5 class='card-title font-weight-bold'>"+quiz.title+"</h5>"+
                                    "</div>"+
                                        "<div class='card-body'>"+
                                            "<p class='card-text'>"+quiz.description+"</p>"+
                                            "<div class='d-flex flex-wrap justify-content-center mb-3'>"+
                                                "<button type='button' class='btn btn-primary mx-2 mb-2'>"+
                                                    "<i class='fas fa-eye'></i> View All Ques."+
                                                "</button>"+
                                                "<button type='button' class='btn btn-outline-info mx-2 mb-2'>"+
                                                    "<i class='fas fa-edit'></i> Max Marks: "+quiz.maxMarks+
                                                "</button>"+
                                                "<button type='button' class='btn btn-outline-info mx-2 mb-2'>"+
                                                    "<i class='fas fa-question'></i> Total Ques: "+quiz.numberOfQuestion+
                                                "</button>"+
                                                "<button type='button' class='btn btn-warning mx-2 mb-2'>"+
                                                    "<i class='fas fa-tags'></i> Course: "+quiz.course.title+
                                                "</button>"+
                                                "<a href='' class='btn btn-success mx-2 mb-2'>"+
                                                    "<i class='fas fa-plus'></i> Add Ques."+
                                                "</a>"+
                                                "<button type='button' class='btn btn-danger mx-2 mb-2'>"+
                                                    "<i class='fas fa-trophy'></i> Quiz Attempts"+
                                                "</button>"+
                                                "<button type='button' class='btn btn-primary mx-2 mb-2'>"+
                                                "<i class='fas fa-edit'></i> Edit Quiz"+
                                            "</button>"+
                                                "<div class='custom-control custom-switch ml-auto'>"+
                                                    "<input type='checkbox' class='custom-control-input' id='switch"+index+"' "+(quiz.isActive ? 'checked' : '')+">"+
                                                    "<label class='custom-control-label' for='switch"+index+"'>"+(quiz.isActive ? 'Published' : 'Unpublished')+"</label>"+
                                                "</div>"+
                                            "</div>"+
                                        "</div>"+
                                    "</div>"+
                                "</div>";
                            quizContainer.append(rowHtml);
                        });
                    } else {
                        quizContainer.append("<div class='col-12'><p>No quizzes found</p></div>");
                    }

                    // Pagination controls
                    var totalPages = response.totalPages;
                    var paginationHtml = "";

                    // Previous button
                    if (currentPage > 0) {
                        paginationHtml += "<li class='page-item'><a class='page-link' href='#' onclick='changePage(" + (currentPage - 1) + ")'>Previous</a></li>";
                    }

                    // Page numbers
                    for (var i = 0; i < totalPages; i++) {
                        paginationHtml += "<li class='page-item " + (i === currentPage ? "active" : "") + "'><a class='page-link' href='#' onclick='changePage(" + i + ")'>" + (i + 1) + "</a></li>";
                    }

                    // Next button
                    if (currentPage < totalPages - 1) {
                        paginationHtml += "<li class='page-item'><a class='page-link' href='#' onclick='changePage(" + (currentPage + 1) + ")'>Next</a></li>";
                    }

                    pagination.html(paginationHtml);
                },
                error: function(xhr, status, error) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'Failed to fetch quizzes. Please try again later.'
                    });
                }
            });
        }

        // Function to handle page change
        window.changePage = function(page) {
            currentPage = page;
            fetchQuizzes(currentPage, searchQuery, sortBy, sortDir);
        };

        // Fetch quizzes initially
        fetchQuizzes(currentPage, searchQuery, sortBy, sortDir);

        // Search functionality
        $('#searchBox').on('input', function() {
            searchQuery = $(this).val();
            currentPage = 0; // Reset to the first page on search
            fetchQuizzes(currentPage, searchQuery, sortBy, sortDir);
        });
    });
</script>

<jsp:include page="footerMain.jsp"></jsp:include>
