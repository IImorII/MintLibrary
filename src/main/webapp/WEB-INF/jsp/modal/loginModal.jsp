<div class="modal" id="loginModal" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">
                    sign in
                </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"></span>
                </button>
            </div>
            <div class="modal-body">
                <form action="?command=login" method="post">
                    <div class="form-group">
                        <label>
                            login
                        </label>
                        <input type="text" class="form-control" aria-describedby="emailHelp" placeholder="enter login" name="userName" required>
                    </div>
                    <div class="form-group">
                        <label>
                            password
                        </label>
                        <input type="password" class="form-control" placeholder="enter password" name="userPassword" required>
                    </div>
                    <button type="submit" class="btn btn-success btn-lg btn-block">
                        login
                    </button>
                </form>
            </div>
            <div class="modal-footer">

            </div>
        </div>
    </div>
</div>
