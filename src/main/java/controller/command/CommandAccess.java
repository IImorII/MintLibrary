package controller.command;

public enum CommandAccess {
    ;


    private final CommandRoles role;

    public String getRole() {
        return role.getRole();
    }

    CommandAccess(CommandRoles role) {
        this.role = role;
    }
}
