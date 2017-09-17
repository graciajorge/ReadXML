package com.example.sunny.readxml;

/**
 * Pass username and password to signon dialog
 * to be used to pass username and password
 * between dialog and activity.
 *
 * Created by Sunny on 6/5/2017.
 */

public interface SignOnDataPass {

    /**
     * set activity password by passing pswd as string
     *
     *  @param pswd as string
     */
    public void passPassword(String pswd);


    /**
     * set activity username with username as string
     *
     * @param user as string
     */
    public void passUsername(String user);


    /**
     * get password from activity
     *
     * @return password as string
     */
    public String getPassword();


    /**
     * get username from activity as string
     *
     * @return username as string
     */
    public String getUsername();

}
