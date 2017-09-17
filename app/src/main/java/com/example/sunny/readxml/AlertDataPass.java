package com.example.sunny.readxml;

/**
 * Pass message for dialog fragment (NotifyDialog)
 * to display from intent.
 *
 * Created by Sunny on 6/7/2017.
 */
public interface AlertDataPass {

    /**
     * Return message from intent to be displayed
     * when creating dialog fragment.
     *
     *  @return string message
     */
    public String getMessage();
}
