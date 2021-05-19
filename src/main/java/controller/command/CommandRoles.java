package controller.command;

public enum CommandRoles {
    ADMIN("Admin"),
    USER("User"),
    LIBRARIAN("Librarian");


    private final String role;

    CommandRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
