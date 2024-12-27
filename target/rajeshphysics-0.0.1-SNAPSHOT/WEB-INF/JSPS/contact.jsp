<jsp:include page="header.jsp"></jsp:include>
<!-- Header Start -->
<div class="container-fluid bg-primary py-5 mb-5 page-header">
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-lg-10 text-center">
                <h1 class="display-3 text-white animated slideInDown">Contact</h1>
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb justify-content-center">
                        <li class="breadcrumb-item"><a class="text-white" href="">Home</a></li>
                        <li class="breadcrumb-item text-white active" aria-current="page">Contact</li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>
</div>
<!-- Header End -->

<!-- Contact Start -->
<div class="container-xxl py-5">
    <div class="container">
        <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
            <h6 class="section-title bg-white text-center text-primary px-3">Contact Us</h6>
            <h1 class="mb-5">Contact For Any Query</h1>
        </div>
        <div class="row g-4">
            <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                <h5>Get In Touch</h5>
                <p class="mb-4">Rajesh Physics of Education is here to extend a helping hand to our lovely customers to ease your way through our portal and mobile application.</p>
                <div class="d-flex align-items-center mb-3">
                    <div class="d-flex align-items-center justify-content-center flex-shrink-0 bg-primary" style="width: 50px; height: 50px;">
                        <i class="fa fa-map-marker-alt text-white"></i>
                    </div>
                    <div class="ms-3">
                        <h5 class="text-primary">Office</h5>
                        <p class="mb-0">HN:23, Mussi Bira, Makhdumpur, Jehanabad, Bihar, India, 804422</p>
                    </div>
                </div>
                <div class="d-flex align-items-center mb-3">
                    <div class="d-flex align-items-center justify-content-center flex-shrink-0 bg-primary" style="width: 50px; height: 50px;">
                        <i class="fa fa-phone-alt text-white"></i>
                    </div>
                    <div class="ms-3">
                        <h5 class="text-primary">Mobile</h5>
                        <p class="mb-0">+91 91026-35151</p>
                    </div>
                </div>
                <div class="d-flex align-items-center">
                    <div class="d-flex align-items-center justify-content-center flex-shrink-0 bg-primary" style="width: 50px; height: 50px;">
                        <i class="fa fa-envelope-open text-white"></i>
                    </div>
                    <div class="ms-3">
                        <h5 class="text-primary">Email</h5>
                        <p class="mb-0">info.rajeshphysics@gmail.com</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
            <iframe class="position-relative rounded w-100 h-100" src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d460531.9765862976!2d85.14304295!3d25.6080374!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2sin!4v1734706630745!5m2!1sen!2sin" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
            </div>
            <div class="col-lg-4 col-md-12 wow fadeInUp" data-wow-delay="0.5s">
                <form id="contactForm">
                    <div class="row g-3">
                        <div class="col-md-6">
                            <div class="form-floating">
                                <input type="text" class="form-control" id="name" placeholder="Your Name"> 
                                <label for="name">*Your Name</label>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-floating">
                                <input type="tel" maxlength="10" class="form-control" id="mobile" placeholder="Your Mobile"> 
                                <label for="mobile">*Your Mobile</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <textarea class="form-control" placeholder="Leave a message here" id="message" style="height: 150px"></textarea>
                                <label for="message">*Message</label>
                            </div>
                        </div>
                        <div class="col-12">
                       
                        </div>
                        <div class="col-12">
                            <button class="btn btn-primary w-100 py-3" id="contactUsBtn" type="submit">Send Message</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Contact End -->

<script>
    $(document).ready(function() {
        $('#contactUsBtn').click(function(event) {
            event.preventDefault();

            var name = $("#name").val();
            var mobile = $("#mobile").val();
            var message = $("#message").val();
          

            if (name == null || name == "") {
                swal("Info", "Name is required!", "info");
                return;
            }
            if (mobile == null || mobile == "") {
                swal("Info", "Mobile no is required!", "info");
                return;
            }
            if (message == null || message == "") {
                swal("Info", "Message is required!", "info");
                return;
            }
        

            var settings = {
                "url": "${pageContext.request.contextPath}/api/contact-us/add",
                "method": "POST",
                "timeout": 0,
                "headers": {
                    "Content-Type": "application/json"
                },
                "data": JSON.stringify({
                    "name": name,
                    "mobile": mobile,
                    "message": message
                
                }),
            };

            $.ajax(settings).done(function(response) {
                if (response.status === "SUCCESS") {
                    swal("Good Job", response.msg, "success");
                    $("#name").val();
                    $("#mobile").val();
                    $("#message").val();
                } else {
                    swal("Info", response.msg, "info");
                }
            }).fail(function(jqXHR, textStatus) {
                swal("Error", "Something went wrong!", "error");
            });

        });
    });
</script>

<jsp:include page="footer.jsp"></jsp:include>
