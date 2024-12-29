
<jsp:include page="headerMain.jsp"></jsp:include>

<div class="wrapper">
    <div class="content-wrapper">
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>Add Question</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item active">Add Question</li>
                        </ol>
                    </div>
                </div>
            </div>
        </section>

        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="card card-outline card-info">
                        <div class="card-header">
                            <h3 class="card-title">Add Question</h3>
                        </div>
                        <div class="card-body">
                            <textarea id="summernote"></textarea>
                        </div>
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="card card-outline card-info">
                        <div class="card-header">
                            <h3 class="card-title">Options</h3>
                        </div>
                        <div class="card-body">
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
                            </div>
                            <div class="row">
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
                            </div>
                            <div class="form-group">
                                <label for="correctAnswer">Correct Answer</label>
                                <select class="form-control" id="correctAnswer">
                                    <option value="1">Option 1</option>
                                    <option value="2">Option 2</option>
                                    <option value="3">Option 3</option>
                                    <option value="4">Option 4</option>
                                </select>
                            </div>
                        </div>
                        <div class="card-footer">
                            <button type="button" class="btn btn-primary" id="saveQuiz">Add Question</button>
                            <button type="button" class="btn btn-secondary" id="clearForm">Clear</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>

<script src="plugins/jquery/jquery.min.js"></script>
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="js/adminlte.min.js"></script>
<script src="plugins/summernote/summernote-bs4.min.js"></script>

<script>
$(function () {
    // Initialize Summernote
    $('#summernote').summernote();

    // Function to populate correct answer options dynamically
    function populateCorrectAnswerOptions() {
        const correctAnswer = $('#correctAnswer');
        correctAnswer.empty();
        for (let i = 1; i <= 4; i++) {
            const optionValue = $('#option' + i).val();
            if (optionValue.trim() !== '') {
                correctAnswer.append(new Option(optionValue, i));
            }
        }
    }

    // Event listener for option input changes
    $('#option1, #option2, #option3, #option4').on('input', populateCorrectAnswerOptions);

    // Add Question button click event
    $('#saveQuiz').on('click', function () {
        const question = $('#summernote').val();
        const options = [
            $('#option1').val(),
            $('#option2').val(),
            $('#option3').val(),
            $('#option4').val()
        ];
        const correctAnswer = $('#correctAnswer').val();

        if (question.trim() === '' || options.some(option => option.trim() === '') || !correctAnswer) {
            alert('Please fill out all fields');
            return;
        }

        // Send data to the server (use your own API endpoint)
        $.ajax({
            url: '/api/quiz/add',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                question: question,
                options: options,
                correctAnswer: correctAnswer
            }),
            success: function (response) {
                alert('Question added successfully');
                // Clear the form
                $('#summernote').summernote('reset');
                $('#option1, #option2, #option3, #option4').val('');
                $('#correctAnswer').empty();
            },
            error: function (xhr, status, error) {
                alert('Failed to add question. Please try again.');
            }
        });
    });

    // Clear button click event
    $('#clearForm').on('click', function () {
        $('#summernote').summernote('reset');
        $('#option1, #option2, #option3, #option4').val('');
        $('#correctAnswer').empty();
    });
});
</script>
</body>
</html>
