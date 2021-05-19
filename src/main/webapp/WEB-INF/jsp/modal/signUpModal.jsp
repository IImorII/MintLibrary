<div class="modal fade" id="signUpModal" role="dialog" aria-labelledby="signUpModalWindow" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="signUpModalTitle">
                    Sign up
                </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="far fa-times-circle"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <form action="?command=sign_up" method="post">
                    <div class="form-group">
                        <label>
                            Name
                        </label>
                        <input type="text" class="form-control" aria-describedby="name" placeholder="Enter name" name="userName" minlength="3" maxlength="63" required>
                        <div class="valid-feedback">
                            correct name
                        </div>
                        <div class="invalid-feedback">
                            incorrect name
                        </div>
                    </div>
                    <div class="form-group">
                        <label>
                            Login
                        </label>
                        <input type="text" class="form-control" aria-describedby="login" placeholder="Enter login" name="login" minlength="3" maxlength="63" required>
                        <div class="valid-feedback">
                            correct login
                        </div>
                        <div class="invalid-feedback">
                            incorrect login
                        </div>
                    </div>
                    <div class="form-group">
                        <label>
                            Password
                        </label>
                        <input type="password" class="form-control" aria-describedby="password" placeholder="Enter password" name="password" minlength="8" maxlength="63" required>
                        <div class="valid-feedback">
                            correct password
                        </div>
                        <div class="invalid-feedback">
                            incorrect password
                        </div>
                    </div>
                    <button type="submit" class="btn btn-dark btn-lg btn-block">
                        Sign up
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
