package com.ssafy.vue.model.service;

import com.ssafy.vue.model.MailDto;

public interface EmailService {

   
    public MailDto createMailAndChangePassword(String useremail, String username) throws Exception;
    public void updatePassword(String str, String email, String username) throws Exception;
    public String getTempPassword() throws Exception;
    public void mailSend(MailDto mailDto) throws Exception;

}
