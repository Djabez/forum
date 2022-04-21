package com.danli.service;

/**
 * @author: fanfanli
 * @date: 2021/8/10
 */public interface MailService {

    /**
     * send normal email
     *
     * @param toAccount
     * @param subject
     * @param content
     */
    void sendSimpleMail(String toAccount, String subject, String content);

    /**
     *  send HTML
     *
     * @param toAccount
     * @param subject
     * @param content
     */
    void sendHtmlMail(String toAccount, String subject, String content);


}

