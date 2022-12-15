package currentUser;

import models.User;

public class CurrentUserManager {
    private static User currentUser = null;
    public static User getUser(){
        return currentUser;
    }
    public static void setUser(User user){
        currentUser = user;
    }
}
