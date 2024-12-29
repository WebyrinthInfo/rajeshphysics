<jsp:include page="headerMain.jsp"></jsp:include>

<div class="wrapper">
    <div class="content-wrapper">
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>User Search</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item active">Update User Token</li>
                        </ol>
                    </div>
                </div>
            </div>
        </section>

        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card card-primary">
                            <div class="card-header">
                                <h3 class="card-title">Search User</h3>
                            </div>
                            <div class="card-body">
                                <p>Search with username for update token:</p>
                                <div class="row">
                                    <div class="col-md-2 form-group">
                                        <input type="text" id="searchUsername" class="form-control" placeholder="Enter username">
                                    </div>
                                    <div class="col-md-2">
                                        <button type="button" id="searchBtn" class="btn btn-info btn-flat">Search</button>
                                    </div>

                                    <div class="col-md-2 form-group user-details">
                                        <input type="text" disabled="disabled" id="expTime" class="form-control" placeholder="Account Expired At">
                                    </div>
                                    
                                    <div class="col-md-2 form-group user-details">
                                        <input type="number" min="1" id="days" class="form-control" placeholder="Enter days">
                                    </div>

                                    <div class="col-md-2 form-group user-details">
                                        <select class="form-control" id="isPaid">
                                            <option selected> -- Select isPaid --</option>
                                            <option value="PAID">PAID</option>
                                            <option value="FREE">FREE</option>
                                        </select>
                                    </div>

                                    <div class="col-md-2 form-group user-details">
                                        <button type="button" id="updateBtn" class="btn btn-primary btn-flat">
                                            <i class="fas fa-edit"></i> Update
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>

<script>
$(document).ready(function() {
    // Hide the user details initially
    $('.user-details').hide();

    $('#searchBtn').click(function(event) {
        event.preventDefault();
        const username = $('#searchUsername').val().trim();

        if (!username) {
            Swal.fire("Info", "Username is required!", "info");
            return;
        }

        $.ajax({
            url: "${pageContext.request.contextPath}/api/user/get-userinfo?username="+username,
            method: "GET",
            timeout: 0,
            success: function(response) {
                if (response.status === "SUCCESS") {
                    $('.user-details').show();  // Show user details if user found
                    $('#expTime').val("Exp At: " + response.data.accountExpireAt);
                    $('#isPaid').val(response.data.isPaid);

                    // Disable the username input and hide the search button
                    $('#searchUsername').prop('disabled', true);
                    $('#searchBtn').hide();
                } else {
 $('.user-details').hide();  // Hide user details if username not found
                    Swal.fire("Info", "Username not found!", "info");
                }
            },
            error: function() {
                $('.user-details').hide();  // Hide user details on error
                Swal.fire("Error", "Something went wrong!", "error");
            }
        });
    });

    $('#updateBtn').click(function(event) {
        event.preventDefault();

        // Show SweetAlert confirmation before updating
        Swal.fire({
            title: "Are you sure?",
            text: "Do you want to update the token for this user?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "Yes, update it!",
            cancelButtonText: "No, cancel!"
        }).then((result) => {
            if (result.isConfirmed) {
                // Proceed with the update if the user confirms
                const username = $('#searchUsername').val();
                const days = $('#days').val();
                const isPaid = $('#isPaid').val();

                if (!days || !isPaid) {
                    Swal.fire("Error", "Please fill all the fields to update!", "error");
                    return;
                }
                
              

                // Perform the update using AJAX
                $.ajax({
                    url: "${pageContext.request.contextPath}/api/ajax/updateToken?username="+username+"&days="+days+"&isPaid="+isPaid,
                    method: "PUT",
                    success: function(response) {
                        if (response.status === "SUCCESS") {
                            Swal.fire("Success", "Token updated successfully!", "success").then(() => {
                                // Reset all fields after success
                                $('#searchUsername').val('').prop('disabled', false);
                                $('#days').val('');
                                $('#isPaid').val(''); // Reset the dropdown to default
                                $('.user-details').hide(); // Hide the user details section
                                $('#searchBtn').show(); // Show the search button again
                            });
                        } else {
                            Swal.fire("Error", "Failed to update token.", "error");
                        }
                    },
                    error: function() {
                        Swal.fire("Error", "Something went wrong!", "error");
                    }
                });
            } else {
                Swal.fire("Your token update has been canceled.");
            }
        });
    });
});
</script>

<jsp:include page="footerMain.jsp"></jsp:include>