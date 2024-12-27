<jsp:include page="headerMain.jsp"></jsp:include>

<!-- Content Wrapper -->
<div class="content-wrapper">
    <!-- Content Header -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0">Course</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">Add Course</li>
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
                            <h5 class="m-0">Add Course</h5>
                        </div>
                        <!-- Card Body -->
                        <div class="card-body">
                            <!-- Form Start -->
                            <form id="addCourseForm">
                                <div class="row">
                                    <!-- Left Column -->
                                    <div class="col-lg-6">
                                        <!-- Name Field -->
                                        <div class="form-group row">
                                            <label for="name" class="col-sm-4 col-form-label">Name</label>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" id="name" placeholder="Name">
                                            </div>
                                        </div>
                                        <!-- Course Title Field -->
                                        <div class="form-group row">
                                            <label for="courseTitle" class="col-sm-4 col-form-label">Course Title</label>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" id="courseTitle" placeholder="Course Title">
                                            </div>
                                        </div>
                                        <!-- Course Price Field -->
                                        <div class="form-group row">
                                            <label for="coursePrice" class="col-sm-4 col-form-label">Course Price</label>
                                            <div class="col-sm-8">
                                                <input type="number" class="form-control" id="coursePrice" placeholder="Course Price">
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Right Column -->
                                    <div class="col-lg-6">
                                        <!-- Course Language Field -->
                                        <div class="form-group row">
                                            <label for="courseLanguage" class="col-sm-4 col-form-label">Course Language</label>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" id="courseLanguage" placeholder="Course Language">
                                            </div>
                                        </div>
                                        <!-- Course Description Field -->
                                        <div class="form-group row">
                                            <label for="courseDescription" class="col-sm-4 col-form-label">Course Description</label>
                                            <div class="col-sm-8">
                                                <textarea class="form-control" id="courseDescription" rows="3" placeholder="Course Description"></textarea>
                                            </div>
                                        </div>
                                        <!-- Is Active Field -->
                                        <div class="form-group row">
                                            <label for="isActive" class="col-sm-4 col-form-label">Is Active</label>
                                            <div class="col-sm-8">
                                                <select class="form-control" id="isActive">
                                                    <option value="yes">Yes</option>
                                                    <option value="no">No</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Submit Button -->
                                <div class="form-group row">
                                    <div class="col-sm-12 text-center">
                                        <button type="submit" id="addCourseBtn" class="btn btn-primary">
                                            <i class="fas fa-plus"></i> Add Course
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

<!-- Include SweetAlert library -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- Script for handling form submission, validation, and AJAX request -->
<script>
    $(document).ready(function() {
        $('#addCourseForm').submit(function(event) {
            event.preventDefault(); // Prevent the form from submitting traditionally

            // Collect form values
            var name = $('#name').val().trim();
            var courseTitle = $('#courseTitle').val().trim();
            var coursePrice = $('#coursePrice').val().trim();
            var courseLanguage = $('#courseLanguage').val().trim();
            var courseDescription = $('#courseDescription').val().trim();
            var isActive = $('#isActive').val();

            // Form validation
            if (name === ''){
            	Swal.fire({
                    icon: 'info',
                    title: 'Info',
                    text: 'Name are required!'
                });
                return; // Exit if validation fails
            } 
            if (courseTitle === ''){
            	Swal.fire({
                    icon: 'info',
                    title: 'Info',
                    text: 'courseTitle are required!'
                });
                return; // Exit if validation fails
            } 
            if (coursePrice === ''){
            	Swal.fire({
                    icon: 'info',
                    title: 'Info',
                    text: 'coursePrice are required!'
                });
                return; // Exit if validation fails
            } 
            if (courseLanguage === ''){
            	Swal.fire({
                    icon: 'info',
                    title: 'Info',
                    text: 'courseLanguage are required!'
                });
                return; // Exit if validation fails
            } 
            if (courseDescription === ''){
            	Swal.fire({
                    icon: 'info',
                    title: 'Info',
                    text: 'courseDescription are required!'
                });
                return; // Exit if validation fails
            } 
            
       

            // Prepare the data to send in the AJAX request
            var formData = {
                name: name,
                courseTitle: courseTitle,
                coursePrice: coursePrice,
                courseLanguage: courseLanguage,
                courseDescription: courseDescription,
                isActive: isActive
            };

            // AJAX request
            var settings = {
                "url": "${pageContext.request.contextPath}/api/course/add",
                "method": "POST",
                "timeout": 0,
                "headers": {
                    "Content-Type": "application/json",
                    "Cookie": "JSESSIONID=61EF180831F84DB6512C7D2FBD797868" // Make sure to update this if needed
                },
                "data": JSON.stringify(formData)
            };

            $.ajax(settings).done(function(response) {
                console.log(response);
                // Show success message with SweetAlert
                Swal.fire({
                    icon: 'success',
                    title: 'Success',
                    text: 'Course added successfully!'
                });
                // Optionally reset the form after success
                $('#addCourseForm')[0].reset();
            }).fail(function(xhr, status, error) {
                // Show error message with SweetAlert
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Failed to add course. Please try again later.'
                });
            });
        });
    });
</script>

<jsp:include page="footerMain.jsp"></jsp:include>
