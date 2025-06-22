
package com.example.emailservice.service;

import com.example.emailservice.model.Email;

public interface EmailSender {
    boolean sendEmail(Email email);
}
