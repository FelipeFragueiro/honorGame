package com.appaloossa.honorgame.manager;

import android.content.Context;

public class UserManager {

    private static UserManager instance = null;

    //a private constructor so no instances can be made outside this class
    private UserManager() {}

    //Everytime you need an instance, call this
    //synchronized to make the call thread-safe
    public static synchronized UserManager getInstance() {
        if(instance == null)
            instance = new UserManager();

        return instance;
    }

    //Initialize this or any other variables in probably the Application class
    public void init(Context context) {}

    public boolean isUserLoggedIn() {
        return false;
    }

}