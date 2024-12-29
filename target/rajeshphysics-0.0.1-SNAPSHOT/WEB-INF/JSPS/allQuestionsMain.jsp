<jsp:include page="headerMain.jsp"></jsp:include>

<!-- Content Wrapper -->
<div class="content-wrapper">
    <!-- Content Header -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0">Questions for Quiz ID: ${param.id}</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="home">Home</a></li>
                        <li class="breadcrumb-item active">Questions</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>

    <!-- Main Content -->
    <div class="content">
        <div class="container-fluid">
            <div class="row" id="questionContainer">
                <!-- Question rows will be dynamically inserted here -->
            </div>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


<!-- Custom CSS for better appearance -->
<style>
    .option-card {
        border: 1px solid #ddd;
        border-radius: 8px;
        margin-bottom: 10px;
        padding: 10px;
        transition: background-color 0.3s;
    }
    .option-card:hover {
        background-color: #f8f9fa;
    }
    .correct-answer {
        background-color: #d4edda;
        border-color: #c3e6cb;
    }
    .card-header h5 {
        font-size: 1.25rem;
    }
    .btn-custom {
        margin-right: 10px;
    }
</style>

<!-- Script for fetching and displaying questions -->
<script>
    $(document).ready(function() {
        // Function to fetch questions based on quiz ID
        function fetchQuestions() {
            var id = new URLSearchParams(window.location.search).get('id');

            $.ajax({
                url: "${pageContext.request.contextPath}/api/question/getAll/sublisted/" + id,
                method: "GET",
                success: function(response) {
                    var questionContainer = $('#questionContainer');
                    questionContainer.empty();

                    // Display questions
                    if (response.data.length > 0) {
                        response.data.forEach(function(question, index) {
                            var rowHtml = 
                                "<div class='col-12 mb-4'>" +
                                    "<div class='card card-primary card-outline shadow-sm h-100'>" +
                                        "<div class='card-header'>" +
                                            "<h5 class='card-title font-weight-bold'>" + (index + 1) + ") " + question.content + "</h5>" +
                                        "</div>" +
                                        "<div class='card-body'>" +
                                            "<div class='row'>" +
                                                "<div class='col-md-6'>" +
                                                    "<div class='option-card'>" +
                                                        "<p class='card-text'>Option 1: " + question.option1 + "</p>" +
                                                    "</div>" +
                                                "</div>" +
                                                "<div class='col-md-6'>" +
                                                    "<div class='option-card'>" +
                                                        "<p class='card-text'>Option 2: " + question.option2 + "</p>" +
                                                    "</div>" +
                                                "</div>" +
                                            "</div>" +
                                            "<div class='row'>" +
                                                "<div class='col-md-6'>" +
                                                    "<div class='option-card'>" +
                                                        "<p class='card-text'>Option 3: " + question.option3 + "</p>" +
                                                    "</div>" +
                                                "</div>" +
                                                "<div class='col-md-6'>" +
                                                    "<div class='option-card'>" +
                                                        "<p class='card-text'>Option 4: " + question.option4 + "</p>" +
                                                    "</div>" +
                                                "</div>" +
                                            "</div>" +
                                            "<div class='row'>" +
                                                "<div class='col-12'>" +
                                                    "<div class='option-card correct-answer'>" +
                                                        "<p class='card-text font-weight-bold'>Correct Answer: " + question.correctAnswer + "</p>" +
                                                    "</div>" +
                                                "</div>" +
                                            "</div>" +
                                            "<div class='row'>" +
                                                "<div class='col-12'>" +
                                                    "<button class='btn btn-custom edit-button' style='background:#6610f2; color:white;' data-id='" + question.id + "'>" +
                                                        "<i class='fas fa-edit'></i> Edit" +
                                                    "</button>" +
                                                    "<button class='btn btn-custom delete-button' style='background:#d81b60; color:white;' data-id='" + question.id + "'>" +
                                                        "<i class='fas fa-trash'></i> Delete" +
                                                    "</button>" +
                                                "</div>" +
                                            "</div>" +
                                        "</div>" +
                                    "</div>" +
                                "</div>";
                            questionContainer.append(rowHtml);
                        });

                        // Attach event handlers for edit and delete buttons
                        $('.edit-button').click(function() {
                            var questionId = $(this).data('id');
                            // Handle edit logic here
                            Swal.fire('Edit', 'Edit button clicked for question ID: ' + questionId, 'info');
                        });

                        $('.delete-button').click(function() {
                            var questionId = $(this).data('id');
                            // Handle delete logic here
                            Swal.fire({
                                title: 'Are you sure?',
                                text: "You won't be able to revert this!",
                                icon: 'warning',
                                showCancelButton: true,
                                confirmButtonText: 'Yes, delete it!',
                                cancelButtonText: 'No, cancel!'
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    // Perform the delete operation (AJAX request or form submission)
                                    $.ajax({
                                        url: "${pageContext.request.contextPath}/api/question/delete/" + questionId,
                                        method: "DELETE",
                                        success: function(response) {
                                            Swal.fire('Deleted!', 'The question has been deleted.', 'success');
                                            fetchQuestions(); // Refresh the questions list
                                        },
                                        error: function(xhr, status, error) {
                                            Swal.fire('Error', 'Failed to delete the question. Please try again later.', 'error');
                                        }
                                    });
                                }
                            });
                        });
                    } else {
                        questionContainer.append("<div class='col-12'><p>No questions found</p></div>");
                    }
                },
                error: function(xhr, status, error) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'Failed to fetch questions. Please try again later.'
                    });
                }
            });
        }

        // Fetch questions initially
        fetchQuestions();
    });
</script>

<jsp:include page="footerMain.jsp"></jsp:include>
