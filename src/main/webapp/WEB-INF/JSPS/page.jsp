<jsp:include page="headerMain.jsp"></jsp:include>

<!-- Content Wrapper -->
<div class="content-wrapper">
    <!-- Content Header -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0">Quiz</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">Add Quiz</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>

    <!-- Main Content -->
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card card-primary card-outline">
                        <!-- Card Header -->
                        <div class="card-header">
                            <h5 class="m-0">Add Quiz</h5>
                        </div>
                        <!-- Card Body -->
                        <div class="card-body">
                            <!-- Form Start -->
                            <form id="addQuizForm">
                                <div class="row">
                                    <!-- Left Column -->
                                    <div class="col-lg-6">
                                        <!-- Title Field -->
                                        <div class="form-group row">
                                            <label for="title" class="col-sm-4 col-form-label">Title</label>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" id="title" placeholder="Title" required>
                                            </div>
                                        </div>
                                        <!-- Max Marks Field -->
                                        <div class="form-group row">
                                            <label for="maxMarks" class="col-sm-4 col-form-label">Max Marks</label>
                                            <div class="col-sm-8">
                                                <input type="number" class="form-control" id="maxMarks" placeholder="Max Marks" required>
                                            </div>
                                        </div>
                                        <!-- Number of Questions Field -->
                                        <div class="form-group row">
                                            <label for="numberOfQuestions" class="col-sm-4 col-form-label">Number of Questions</label>
                                            <div class="col-sm-8">
                                                <input type="number" class="form-control" id="numberOfQuestions" placeholder="Number of Questions" required>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Right Column -->
                                    <div class="col-lg-6">
                                        <!-- Description Field -->
                                        <div class="form-group row">
                                            <label for="description" class="col-sm-4 col-form-label">Description</label>
                                            <div class="col-sm-8">
                                                <textarea class="form-control" id="description" rows="3" placeholder="Description" required></textarea>
                                            </div>
                                        </div>
                                        <!-- Is Active Field -->
                                        <div class="form-group row">
                                            <label for="isActive" class="col-sm-4 col-form-label">Publish</label>
                                            <div class="col-sm-8">
                                                <select class="form-control" id="isActive" required>
                                                    <option value="0">NO</option>
                                                    <option value="1">YES</option>
                                                </select>
                                            </div>
                                        </div>
                                        <!-- Course Field -->
                                        <div class="form-group row">
                                            <label for="course" class="col-sm-4 col-form-label">Course</label>
                                            <div class="col-sm-8">
                                                <select class="form-control" id="course" required>
                                                    <!-- Courses will be populated here -->
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Buttons -->
                                <div class="form-group row">
                                    <div class="col-sm-12 text-center">
                                        <button type="submit" id="addQuizBtn" class="btn btn-primary">
                                            <i class="fas fa-plus"></i> Add Quiz
                                        </button>
                                        <button type="reset" class="btn btn-secondary">
                                            <i class="fas fa-eraser"></i> Clear
                                        </button>
                                    </div>
                                </div>
                            </form>
                            <!-- Form End -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Script for handling form submission, validation, and AJAX request -->
<script>
    $(document).ready(function() {
        // Fetch courses on page load
        $.ajax({
            url: "${pageContext.request.contextPath}/api/course/get-all",
            method: "GET",
            success: function(response) {
                var courseSelect = $('#course');
              
                response.data.content.forEach(function(course) {
                    courseSelect.append(new Option(course.name+" -- "+course.courseLanguage, course.id));
                });
            },
            error: function(jqXHR, textStatus, errorThrown) {
                swal("Error", "Failed to fetch courses. Please try again later.", "error");
            }
        });
        
        $('#addQuizBtn').click(function(e) {
            e.preventDefault();
            
           	swal("info", "working", "info");  
        });
        
    });
</script>

<jsp:include page="footerMain.jsp"></jsp:include>
