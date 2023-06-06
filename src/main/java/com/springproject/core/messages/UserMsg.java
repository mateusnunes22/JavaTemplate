package com.springproject.core.messages;

public class UserMsg {
    private UserMsg() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ERROR_DELETE_USER = "Error to delete a User!";
    public static final String ERROR_USER_NOT_FOUND = "User not found";
    public static final String ERROR_SAVE_USER = "Error to create a new User.";
    public static final String ERROR_SAVE_USERID_IN_USER_ROLE = "Error to index the user Id on User Role";
    public static final String ERROR_USERNAME_PASSWORD = "username or password have been wrong!";
    public static final String ERROR_REFRESH_TOKEN = "Error to try get a valid token.";
    public static final String ERROR_INVALIDATE_SESSION = "Error to try to finish the session and invalidate token";
}
