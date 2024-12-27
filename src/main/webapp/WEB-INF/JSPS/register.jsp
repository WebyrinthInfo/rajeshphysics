<jsp:include page="header.jsp"></jsp:include>

<!-- Header Start -->
<div class="container-fluid bg-primary py-5 mb-5 page-header">
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-lg-10 text-center">
                <h1 class="display-3 text-white animated slideInDown">Register</h1>
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb justify-content-center">
                        <li class="breadcrumb-item"><a class="text-white" href="#">Home</a></li>
                        <li class="breadcrumb-item text-white active" aria-current="page">Register</li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>
</div>
<!-- Header End -->

<!-- Register Start -->
<div class="container-xxl py-5 position-relative">
    <!-- Register Form -->
    <div class="container">
        <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
            <h6 class="section-title bg-white text-center text-primary px-3">Join Us</h6>
            <h1 class="mb-5">Create Your Account</h1>
        </div>
        <div class="row justify-content-center">
            <div class="col-lg-8 col-md-10 wow fadeInUp" data-wow-delay="0.3s">
                <div class="card">
                    <div class="card-body">
                        <form id="registrationForm">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="fullName" placeholder="Full Name" required> 
                                        <label for="fullName">*Full Name</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="address" placeholder="Address" required> 
                                        <label for="address">*Address</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-floating mb-3">
                                        <input type="tel" maxlength="10" class="form-control" id="mobile" placeholder="Mobile" required> 
                                        <label for="mobile">*Mobile</label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-floating mb-3">
                                        <select class="form-select" id="course" aria-label="Course" required>
                                            <!-- Options will be populated by JavaScript -->
                                        </select> 
                                        <label for="course">*Course</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-floating mb-3">
                                        <select class="form-select" id="batch" aria-label="Batch" required>
                                        	<option selected>-- Select Batch --</option>
                                            <!-- Options will be dynamically populated based on selected course -->
                                        </select> 
                                        <label for="batch">*Batch</label>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-floating mb-3">
                                        <select class="form-select" id="courseType" aria-label="Course Type" required>
                                            <option selected>Select Course Type</option>
                                            <option value="FREE">FREE</option>
                                            <option value="PAID">PAID</option>
                                        </select> 
                                        <label for="courseType">*Course Type</label>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-floating mb-3 position-relative">
                                        <input type="password" class="form-control" id="password" placeholder="Password" required> 
                                        <label for="password">*Password</label>
                                        <span class="position-absolute top-50 end-0 translate-middle-y me-3" onclick="togglePassword('password', 'togglePasswordIcon')">
                                            <i class="fa fa-eye" id="togglePasswordIcon"></i>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-floating mb-3 position-relative">
                                        <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm Password" required> 
                                        <label for="confirmPassword">*Confirm Password</label>
                                        <span class="position-absolute top-50 end-0 translate-middle-y me-3" onclick="togglePassword('confirmPassword', 'toggleConfirmPasswordIcon')">
                                            <i class="fa fa-eye" id="toggleConfirmPasswordIcon"></i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <button class="btn btn-primary w-100 py-3" id="registerBtn">Register</button>
                            <div class="d-flex justify-content-between mt-3">
                                <a href="login" class="btn btn-link">Login Here..</a>
                            </div>
                            <p>* By registering you agree to the terms and conditions, copyright policy, and privacy policy.</p>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div id="course-list"></div>
    </div>
    <jsp:include page="pageBackground.jsp"></jsp:include>
</div>
<!-- Register End -->

<script>
    $(document).ready(function() {
        const settings = {
            url: "${pageContext.request.contextPath}/api/course/get-all",
            method: "GET",
            timeout: 0
        };

        $.ajax(settings).done(function(response) {
            if (response.status === "SUCCESS") {
                const courses = response.data.content;
                let courseOptions = '<option selected>-- Select Course --</option>';
                
                console.log(response.data.content);
                // Iterate through courses to build course options
                courses.forEach(course => {
                    courseOptions += "<option value='"+course.id+"'>"+course.name+" -- "+course.courseLanguage+"</option>";
                });

                // Append the options to the course select dropdown
                $('#course').html(courseOptions);
                

                // Fetch batches based on selected course
                $('#course').change(function() {
                    let selectedCourseId = $(this).val();
                    if (selectedCourseId) {
                        const course = courses.find(c => c.id == selectedCourseId);
                       
                        let batchOptions = '<option selected>-- Select Batch --</option>';
                        course.batches.forEach(batch => {
                            batchOptions += "<option value='"+batch.batchCode+"'>"+batch.batchCode+"</option>";
                        });
                        $('#batch').html(batchOptions);
                    }
                });

            } else {
                console.error('Failed to fetch data:', response.msg);
                swal("Info", "Failed to fetch data: " + response.msg, "info");
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
            console.error('AJAX request failed:', textStatus, errorThrown);
            swal("Error", "Error fetching data. Please try again later.", "error");
        });
    });

    $('#registerBtn').click(function(event) {
        event.preventDefault(); // Prevent default form submission
        
        // Form validation
        const fullName = $('#fullName').val();
        const address = $('#address').val();
        const mobile = $('#mobile').val();
        const course = $('#course').val();
        const batch = $('#batch').val();
        const courseType = $('#courseType').val();
        const password = $('#password').val();
        const confirmPassword = $('#confirmPassword').val();

        if (!fullName){
            swal("Info", "FullName is Required!", "info");
            return;
        }
        if(!address){
        	swal("Info", "Address is Required!", "info");
        }
        if(!mobile){
        	swal("Info", "Mobile is Required!", "info");
        }
        if(!course){
        	swal("Info", "Course is Required!", "info");
        }
        if(!batch){
        	swal("Info", "Batch is Required!", "info");
        }
        if(!courseType){
        	swal("Info", "CourseType is Required!", "info");
        }
        
        if(!password){
        	swal("Info", "Password is Required!", "info");
        }
        
        if(!confirmPassword){
        	swal("Info", "ConfirmPassword is Required!", "info");
        }

        if (mobile.length !== 10) {
            swal("Info", "Mobile number must be 10 digits!", "info");
            return;
        }

        if (password !== confirmPassword) {
            swal("Info", "Passwords do not match!", "info");
            return;
        }

        const registrationData = {
            fullName: fullName,
            address: address,
            mobile: mobile,
            courseType: courseType,
            batchCode: batch,
            password: password
        };
        
     
        
        // Submit registration data (assuming an API endpoint is available)
        $.ajax({
            url: "${pageContext.request.contextPath}/api/ajax/registration?courseId="+course,
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(registrationData),
            success: function(response) {
                if (response.status === "SUCCESS") {
                    swal("Success", "Registration successful!", "success")
                        .then(() => {
                            window.location.href = "login"; // Redirect to login page
                        });
                } else {
                    swal("Error", "Registration failed: " + response.msg, "error");
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                swal("Error", "Error registering. Please try again later.", "error");
            }
        }); 
    });

    function togglePassword(fieldId, iconId) {
        var passwordField = document.getElementById(fieldId);
        var togglePasswordIcon = document.getElementById(iconId);
        if (passwordField.type === 'password') {
            passwordField.type = 'text';
            togglePasswordIcon.classList.remove('fa-eye');
            togglePasswordIcon.classList.add('fa-eye-slash');
        } else {
            passwordField.type = 'password';
            togglePasswordIcon.classList.remove('fa-eye-slash');
            togglePasswordIcon.classList.add('fa-eye');
        }
    }
</script>

<jsp:include page="footer.jsp"></jsp:include>
