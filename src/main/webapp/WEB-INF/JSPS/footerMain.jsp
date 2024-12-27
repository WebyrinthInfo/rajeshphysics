<footer class="main-footer">

	<div class="float-right d-none d-sm-inline">Anything you want</div>

	<strong>Copyright &copy;<a
		href="http://webyrinth.in/">Webyrinth (P) LTD.</a>
	</strong> All rights reserved.
</footer>
</div>


<script type="text/javascript">
        $(document).ready(function() {
            var userName = localStorage.getItem("userName");
            if (userName) {
                $("#username").text(userName);
            }
        });
</script>

<script type="text/javascript">
        $(document).ready(function() {
            $("#logoutBtn").click(function(event) {
                event.preventDefault(); 
                
                Swal.fire({
                    title: 'Are you sure?',
                    text: "Do you want to logout?",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Yes, logout!',
                    cancelButtonText: 'Cancel'
                }).then((result) => {
                    if (result.isConfirmed) {
                        localStorage.clear(); // Clear all localStorage items
                        window.location.href = 'login'; // Redirect to login page
                    } 
                });
            });
        });
    </script>

<script src="plugins/summernote/summernote-bs4.min.js"></script>
<script src="plugins/jquery/jquery.min.js"></script>

<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

<script src="js/adminlte.min2167.js?v=3.2.0"></script>
</body>

</html>