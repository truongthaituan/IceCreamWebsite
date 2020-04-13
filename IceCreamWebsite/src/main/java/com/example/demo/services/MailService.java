package com.example.demo.services;

public interface MailService {
    public void send(String toAddress, String fromAddress, String subject, String content) throws Exception;
    public void sendHTML(String toAddress, String fromAddress, String subject, String content) throws Exception;

}
