<jsp:include page="header.jsp"></jsp:include>
    <div class="container-fluid bg-primary py-5 mb-5 page-header">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-10 text-center">
                    <h1 class="display-3 text-white animated slideInDown">Forget Password</h1>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb justify-content-center">
                            <li class="breadcrumb-item"><a class="text-white" href="#">Home</a></li>
                            <li class="breadcrumb-item text-white active" aria-current="page">Forget Password</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- Header End -->

    <!-- Forget Password Card Start -->
 <div class="container-xxl py-5 position-relative">
        <div class="card">
            <div class="card-body">
                <h3 class="card-title text-center">Forget Password</h3>
                <form id="forgetPasswordForm">
                    <div class="mb-3">
                        <label for="mobileNumber" class="form-label">Username</label>
                        <input type="tel" maxlength="10" class="form-control" id="mobileNumber" required>
                    </div>
                    <button type="button" class="btn btn-primary w-100" id="sendOtpButton">Send OTP</button>

                    <div class="mb-3 mt-3" id="otpSection" style="display: none;">
                        <label for="otp" class="form-label">OTP</label>
                        <input type="tel" maxlength="4" class="form-control" id="otp" required>
                        <div class="countdown" id="countdown"></div>
                        <button type="button" class="btn btn-secondary w-100 mt-2" id="resendOtpButton" style="display: none;">Resend OTP</button>
                    </div>
                    <button type="button" class="btn btn-primary w-100" id="validateOtpButton" style="display: none;">Validate OTP</button>

                    <div id="changePasswordFields" style="display: none;">
                        <div class="mb-3 mt-3">
                            <label for="newPassword" class="form-label">New Password</label>
                            <input type="password" class="form-control" id="newPassword" required>
                        </div>
                        <div class="mb-3">
                            <label for="confirmPassword" class="form-label">Confirm Password</label>
                            <input type="password" class="form-control" id="confirmPassword" required>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Change Password</button>
                    </div>
                </form>
            </div>
        </div>
        <jsp:include page="pageBackground.jsp"></jsp:include>
    </div>
     <style>
        .card {
            margin: 50px auto;
            max-width: 500px;
            padding: 20px;
        }
        .countdown {
            font-size: 14px;
            color: #ff0000;
        }
    </style>
    <!-- Forget Password Card End -->

    <script>
        let countdownInterval;

        document.getElementById('sendOtpButton').addEventListener('click', function() {
            // Simulate sending OTP and enable OTP field
            document.getElementById('mobileNumber').disabled = true;
            document.getElementById('otpSection').style.display = 'block';
            document.getElementById('sendOtpButton').style.display = 'none';
            document.getElementById('validateOtpButton').style.display = 'block';
            startCountdown(300); // 5 minutes countdown
        });

        document.getElementById('validateOtpButton').addEventListener('click', function() {
            // Simulate OTP validation and enable change password fields
            document.getElementById('otpSection').style.display = 'none';
            document.getElementById('validateOtpButton').style.display = 'none';
            document.getElementById('changePasswordFields').style.display = 'block';
            clearInterval(countdownInterval);
        });

        document.getElementById('resendOtpButton').addEventListener('click', function() {
            // Simulate resending OTP and restart countdown
            document.getElementById('otp').value = '';
            document.getElementById('resendOtpButton').style.display = 'none';
            startCountdown(300); // 5 minutes countdown
        });

        document.getElementById('forgetPasswordForm').addEventListener('submit', function(event) {
            event.preventDefault();
            // Handle password change logic
            alert('Password changed successfully!');
        });

        function startCountdown(seconds) {
            const countdownElement = document.getElementById('countdown');
            countdownElement.style.display = 'block';
            let timeLeft = seconds;
            countdownElement.textContent = formatTime(timeLeft);
            countdownInterval = setInterval(() => {
                if (timeLeft <= 0) {
                    clearInterval(countdownInterval);
                    countdownElement.textContent = 'OTP expired. Please resend OTP.';
                    document.getElementById('resendOtpButton').style.display = 'block';
                } else {
                    timeLeft--;
                    countdownElement.textContent = formatTime(timeLeft);
                }
            }, 1000);
        }

        function formatTime(seconds) {
            const minutes = Math.floor(seconds / 60);
            const secs = seconds % 60;
            return `Time left: ${minutes}:${secs < 10 ? '0' : ''}${secs}`;
        }
    </script>
<jsp:include page="footer.jsp"></jsp:include>
