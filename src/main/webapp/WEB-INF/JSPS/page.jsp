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
        </div>
    </div>

    <!-- Main Content -->
    <div class="content">
        <div class="container-fluid">
            <div class="row" id="quizContainer">
                <!-- Quiz rows will be dynamically inserted here -->
            </div>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- Script for fetching and displaying quizzes -->
<script>
    $(document).ready(function() {
        // Function to fetch quizzes
        function fetchQuizzes() {
            $.ajax({
                url: "${pageContext.request.contextPath}/api/quiz/getAll",
                method: "GET",
                success: function(response) {
                    var quizContainer = $('#quizContainer');
                    quizContainer.empty();

                    // Display quizzes
                    if (response.data.length > 0) {
                        response.data.forEach(function(quiz, index) {
                            var rowHtml = 
                                "<div class='col-12 mb-4'>" +
                                    "<div class='card card-primary card-outline shadow-sm h-100'>" +
                                        "<div class='card-header'>" +
                                            "<h5 class='card-title font-weight-bold'>"+"<strong style='color:red'>Topic : </strong>" + quiz.title + "</h5>" +
                                        "</div>" +
                                        "<div class='card-body'>" +
                                            "<p class='card-text'>"+"<strong style='color:red'>Desc : </strong>"+ quiz.description + "</p>" +
                                            "<div class='d-flex flex-wrap justify-content-center mb-3'>" +
                                            "<a href='all-questions?id=" + quiz.id + "' class='btn btn-primary mx-2 mb-2'>" +
                                            "<i class='fas fa-eye'></i> View All Ques." +
                                        "</a>" +
                                                "<button type='button' class='btn btn-outline-info mx-2 mb-2'>" +
                                                    "<i class='fas fa-edit'></i> Max Marks: " + quiz.maxMarks +
                                                "</button>" +
                                                "<button type='button' class='btn btn-outline-info mx-2 mb-2'>" +
                                                    "<i class='fas fa-question'></i> Total Ques: " + quiz.numberOfQuestion +
                                                "</button>" +
                                                "<button type='button' class='btn btn-warning mx-2 mb-2'>" +
                                                    "<i class='fas fa-tags'></i> Course: " + quiz.course.name +
                                                "</button>" +
                                                "<button type='button' class='btn btn-success mx-2 mb-2' data-toggle='modal' data-target='#modal-lg' data-quiz-id='" + quiz.id + "'>" +
                                                    "<i class='fas fa-plus'></i> Add Ques." +
                                                "</button>" +
                                                "<button type='button' class='btn btn-danger mx-2 mb-2'>" +
                                                    "<i class='fas fa-trophy'></i> Quiz Attempts" +
                                                "</button>" +
                                                "<button type='button' class='btn btn-primary mx-2 mb-2'>" +
                                                    "<i class='fas fa-edit'></i> Edit Quiz" +
                                                "</button>" +
                                                "<div class='custom-control custom-switch ml-auto'>" +
                                                    "<input type='checkbox' class='custom-control-input' id='switch" + index + "' " + (quiz.isActive ? 'checked' : '') + ">" +
                                                    "<label class='custom-control-label' for='switch" + index + "'>" + (quiz.isActive ? 'Published' : 'Unpublished') + "</label>" +
                                                "</div>" +
                                            "</div>" +
                                        "</div>" +
                                    "</div>" +
                                "</div>";
                            quizContainer.append(rowHtml);
                        });
                    } else {
                        quizContainer.append("<div class='col-12'><p>No quizzes found</p></div>");
                    }
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

        // Fetch quizzes initially
        fetchQuizzes();

        // Add event listener to pass quiz ID to the modal
        $('#modal-lg').on('show.bs.modal', function(event) {
            var button = $(event.relatedTarget);
            var quizId = button.data('quiz-id');
            var modal = $(this);
            modal.find('.modal-title').text('Add Questions to Quiz ID: ' + quizId);
            // Initialize Summernote editor
            $('#summernote').summernote({
                height: 300
            });
        });

        // Clear Summernote editor when modal is hidden
        $('#modal-lg').on('hidden.bs.modal', function() {
            $('#summernote').summernote('destroy');
            $('#summernote').val('');
            $('#option1').val('');
            $('#option2').val('');
            $('#option3').val('');
            $('#option4').val('');
            $('#answer').val('');
        });

        // Handle Add Question button click
        $('#addQuestionButton').on('click', function() {
            // Get question and option values
            var id = $('#modal-lg .modal-title').text().split(' ').pop();
            var question = $('#summernote').val();
            var option1 = $('#option1').val();
            var option2 = $('#option2').val();
            var option3 = $('#option3').val();
            var option4 = $('#option4').val();
            var answer = $('#answer').val();

            var questionData = {
                content: question,
                option1: option1,
                option2: option2,
                option3: option3,
                option4: option4,
                correctAnswer: answer
            };

            // Example AJAX request to save the question
            $.ajax({
                url: "${pageContext.request.contextPath}/api/question/save/" + id, // Corrected URL format
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify(questionData),
                success: function(response) {
                    // Show success message
                    Swal.fire({
                        icon: 'success',
                        title: 'Success',
                        text: 'Question added successfully!'
                    });
					
                    clearData();
                    
                },
                error: function(xhr, status, error) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'Failed to add question. Please try again later.'
                    });
                }
            });
        });

        // Handle Clear button click
        $('#clearButton').on('click', function() {
            $('#summernote').val('');
            $('#option1').val('');
            $('#option2').val('');
            $('#option3').val('');
            $('#option4').val('');
            $('#answer').val('');
        });
        
        function clearData(){
            $('#summernote').val('');
            $('#option1').val('');
            $('#option2').val('');
            $('#option3').val('');
            $('#option4').val('');
            $('#answer').val('');
        }

        // Update answer dropdown dynamically when options change
        function updateAnswerDropdown() {
            var options = [];
            options.push($('#option1').val());
            options.push($('#option2').val());
            options.push($('#option3').val());
            options.push($('#option4').val());

            var answerDropdown = $('#answer');
            answerDropdown.empty();
            answerDropdown.append('<option value="">Select answer</option>');

            options.forEach(function(option, index) {
                if (option) {
                    answerDropdown.append('<option value="' + option + '">' + option + '</option>');
                }
            });
        }

        // Event listener to update answer dropdown as user types in options
        $('#option1, #option2, #option3, #option4').on('input', updateAnswerDropdown);
    });
</script>

<!-- Modal for Adding Questions -->
<div class="modal fade" id="modal-lg">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Add Question</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="summernote">Question Text</label>
                    <textarea id="summernote" class="form-control" placeholder="Enter question text"></textarea>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="option1">Option 1</label>
                            <input type="text" class="form-control" id="option1" placeholder="Enter option 1">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="option2">Option 2</label>
                            <input type="text" class="form-control" id="option2" placeholder="Enter option 2">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="option3">Option 3</label>
                            <input type="text" class="form-control" id="option3" placeholder="Enter option 3">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="option4">Option 4</label>
                            <input type="text" class="form-control" id="option4" placeholder="Enter option 4">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="answer">Answer</label>
                            <select id="answer" class="form-control">
                                <option value="">Select answer</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer justify-content-center">
                <button type="button" class="btn btn-secondary" id="clearButton">
                    <i class="fas fa-eraser"></i> Clear
                </button>
                <button type="button" class="btn btn-primary" id="addQuestionButton">
                    <i class="fas fa-plus-circle"></i> Add Question
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <i class="fas fa-times-circle"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footerMain.jsp"></jsp:include>
