<div class="modal fade" id="loginModal" role="dialog" aria-labelledby="loginModalWindow" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="loginModalTitle">
                    Login
                </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="far fa-times-circle"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <form action="?command=login" method="post">
                    <div class="form-group">
                        <label>
                            login
                        </label>
                        <input type="text" class="form-control" placeholder="enter login" name="login" required>
                    </div>
                    <div class="form-group">
                        <label>
                            password
                        </label>
                        <input type="password" class="form-control" placeholder="enter password" name="password" required>
                    </div>
                    <button type="submit" class="btn btn-success btn-lg btn-block">
                        Login
                    </button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-dark btn-lg btn-block" data-toggle="modal" data-target="#signUpModal" data-dismiss="modal">
                    Sign up
                </button>
            </div>
        </div>
    </div>
</div>
