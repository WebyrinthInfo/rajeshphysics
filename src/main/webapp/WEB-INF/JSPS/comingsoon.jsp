<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Rajesh Physics - Coming Soon</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABQqXb7A8U3o7k3e+L2oJ0Jna6rfqQ+ovc9mFU5oP8hFjjcWJqDIEA9" crossorigin="anonymous">
    
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
    <!-- Custom Styles -->
    <style>
        body {
            background-color: #f0f8ff; /* Light background color */
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .card {
            width: 100%;
            max-width: 600px;
            padding: 30px;
            text-align: center;
            border-radius: 15px;
            background-color: #fff; /* White background */
            border: 2px solid #8e24aa; /* Purple border outline */
            box-shadow: 0 4px 12px rgba(142, 36, 170, 0.2); /* Purple shadow effect */
            color: #333;
            transition: all 0.3s ease;
        }
        .card:hover {
            box-shadow: 0 6px 18px rgba(142, 36, 170, 0.3);
            transform: translateY(-5px);
        }
        h1 {
            font-size: 2.5rem;
            margin-bottom: 20px;
            color: #7b1fa2; /* Purple color */
        }
        p {
            font-size: 1.2rem;
            margin-bottom: 30px;
            color: #7b1fa2; /* Purple color */
        }
        .countdown {
            font-size: 1.8rem;
            margin: 20px 0;
            font-weight: bold;
            color: #1e88e5; /* Blue color */
        }
        .subscribe-form input[type="tel"] {
            width: 100%;
            max-width: 300px;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: transparent;
            color: #333;
            font-size: 1rem;
        }
        .subscribe-form button {
            width: 100%;
            max-width: 300px;
            background-color: #1e88e5; /* Blue button */
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            font-size: 1.1rem;
            cursor: pointer;
            box-shadow: 0 0 10px rgba(30, 136, 229, 0.8), 0 0 20px rgba(30, 136, 229, 0.6);
        }
        .subscribe-form button:hover {
            background-color: #1976d2; /* Darker blue color on hover */
            box-shadow: 0 0 15px rgba(30, 136, 229, 1), 0 0 25px rgba(30, 136, 229, 0.8);
        }
        .success-message {
            color: #4caf50;
            font-size: 1rem;
            margin-top: 20px;
        }
        .error-message {
            color: #f44336;
            font-size: 1rem;
            margin-top: 20px;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .card {
                padding: 20px;
                width: 90%;
            }
            h1 {
                font-size: 2rem;
            }
            .countdown {
                font-size: 1.5rem;
            }
        }
    </style>
</head>
<body>

    <!-- Content Card -->
    <div class="card">
        <h1>Rajesh Physics - Coming Soon!</h1>
        <p>We are working hard to bring you something great. Stay tuned for the launch on <span id="launchDate">${launchDate}</span>!</p>
        
        <!-- Countdown Timer -->
        <div class="countdown" id="countdown"></div>
        
        <!-- Mobile Subscription Form -->
        <div class="subscribe-form">
            <h3>Stay Updated</h3>
            <form id="subscribeForm">
                <input type="tel" name="mobile" placeholder="Enter your mobile number" required>
                <button type="submit">Notify Me</button>
            </form>
        </div>

        <!-- Success and Error Messages -->
        <div class="success-message" id="successMessage" style="display: none;">
            Thank you for subscribing! We'll notify you once Rajesh Physics is live.
        </div>
        <div class="error-message" id="errorMessage" style="display: none;">
            There was an issue with your subscription. Please try again.
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-GLhlTQ8iRABQqXb7A8U3o7k3e+L2oJ0Jna6rfqQ+ovc9mFU5oP8hFjjcWJqDIEA9" crossorigin="anonymous"></script>

    <!-- Countdown Script -->
    <script>
        // Get the launch date from the page
        var launchDateText = document.getElementById('launchDate').innerText;
        var launchDate = new Date(launchDateText).getTime();

        // Update the countdown every 1 second
        var x = setInterval(function() {
            var now = new Date().getTime();
            var distance = launchDate - now;

            // Time calculations for days, hours, minutes, and seconds
            var days = Math.floor(distance / (1000 * 60 * 60 * 24));
            var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
            var seconds = Math.floor((distance % (1000 * 60)) / 1000);

            // Display the result in the element with id="countdown"
            document.getElementById("countdown").innerHTML = days + " Days " + hours + "h " + minutes + "m " + seconds + "s ";

            // If the countdown is over, write some text
            if (distance < 0) {
                clearInterval(x);
                document.getElementById("countdown").innerHTML = "EXPIRED";
            }
        }, 1000);

        // Handle form submission
        $('#subscribeForm').submit(function(event) {
            event.preventDefault(); // Prevent the default form submission
            
            var mobile = $('input[name="mobile"]').val(); // Get the mobile number value
            var baseUrl = "${pageContext.request.contextPath}"; // Get the base URL from the server

            var settings = {
                "url": baseUrl + "/api/newsletter/add",
                "method": "POST",
                "timeout": 0,
                "headers": {
                    "Content-Type": "application/json"
                },
                "data": JSON.stringify({
                    "mobile": mobile
                }),
            };

            $.ajax(settings).done(function (response) {
                if (response.status) {
                    $('#successMessage').show(); // Show success message
                    $('#errorMessage').hide(); // Hide error message
                    $('input[name="mobile"]').val(''); // Set the mobile input to null
                } else {
                    $('#errorMessage').show(); // Show error message
                    $('#successMessage').hide(); // Hide success message
                }
            }).fail(function () {
                $('#errorMessage').show(); // Show error message
                $('#successMessage').hide(); // Hide success message
            });
        });
    </script>

</body>
</html>
