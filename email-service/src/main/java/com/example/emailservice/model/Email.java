
package com.example.emailservice.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    private String to;
    private String subject;
    private String body;
}
