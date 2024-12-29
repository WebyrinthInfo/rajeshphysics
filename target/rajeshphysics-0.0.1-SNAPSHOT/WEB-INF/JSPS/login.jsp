<jsp:include page="header.jsp"></jsp:include>

<!-- Header Start -->
<div class="container-fluid bg-primary py-5 mb-5 page-header">
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-lg-10 text-center">
                <h1 class="display-3 text-white animated slideInDown">Login</h1>
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb justify-content-center">
                        <li class="breadcrumb-item"><a class="text-white" href="">Home</a></li>
                        <li class="breadcrumb-item text-white active" aria-current="page">Login</li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>
</div>
<!-- Header End -->

<!-- Login Start -->
<div class="container-xxl py-5 position-relative">
    <!-- Login Form -->
    <div class="container">
        <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
            <h6 class="section-title bg-white text-center text-primary px-3">Welcome Back</h6>
            <h1 class="mb-5">Please Login</h1>
        </div>
        <div class="row justify-content-center">
            <div class="col-lg-6 col-md-8 wow fadeInUp" data-wow-delay="0.3s">
                <div class="card">
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/api/ajax/login" method="POST">
                            <div class="form-floating mb-3">
                                <input type="tel" maxlength="10" class="form-control" id="username" placeholder="Username" required>
                                <label for="username">Username</label>
                            </div>
                            <div class="form-floating mb-3 position-relative">
                                <input type="password" class="form-control" id="password" placeholder="Password" required>
                                <label for="password">Password</label>
                                <span class="position-absolute top-50 end-0 translate-middle-y me-3" onclick="togglePassword()">
                                    <i class="fa fa-eye" id="togglePasswordIcon"></i>
                                </span>
                            </div>
                            <button class="btn btn-primary w-100 py-3 loginBtn" id="loginBtn" type="button">Login</button>
                            <div class="d-flex justify-content-between mt-3">
                                <a href="register" class="btn btn-link">Register</a>
                                <a href="forget-password" class="btn btn-link">Forgot Password?</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="pageBackground.jsp"></jsp:include>
</div>
<!-- Login End -->

<script>
    $(document).ready(function() {
        $('#loginBtn').click(function(event) {
            event.preventDefault(); // Prevent default form submission
            var username = $("#username").val();
            var password = $("#password").val();
            
            // Disable the button to prevent multiple submissions
            $(this).attr("disabled", true).text("Logging in...");

            var settings = {
                "url": "${pageContext.request.contextPath}/api/ajax/login",
                "method": "POST",
                "timeout": 0,
                "headers": {
                    "Content-Type": "application/json"
                },
                "data": JSON.stringify({
                    "username": username,
                    "password": password
                }),
            };

            $.ajax(settings).done(function(response) {
                if (response.status === "SUCCESS") {
                	localStorage.setItem('isLogin', true);
                	localStorage.setItem('userInfo', response.data);
                	localStorage.setItem('jwtToken', response.data.jwtToken);
                	localStorage.setItem('userName', response.data.fullName);
                	localStorage.setItem('course', response.data.course);
                    window.location.href = 'dashboard';
                } else {
                    swal('Error',response.msg, "error");
                }
            }).fail(function(jqXHR, textStatus) {
                swal('An error Occurred', "Please try again later", "error");
            }).always(function() {
                $('#loginBtn').attr("disabled", false).text("Login");
            });
        });
    });

    function togglePassword() {
        var passwordField = document.getElementById('password');
        var togglePasswordIcon = document.getElementById('togglePasswordIcon');
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
