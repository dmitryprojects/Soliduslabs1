package com.soliduslabs.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*
 * a simple domain entity doubling as a DTO
 */

@AllArgsConstructor
@Entity
@Table(name = "Message")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {


    @Id
    @Column(nullable = false)
    @Getter
    @Setter
    private String message;


    public Message() {

    }


    @Override
    public String toString() {
        boolean isError = message.indexOf("404") !=-1? true: false;
        if (isError) {
            return "{" +
                    "\"error_msg\": Message not found" + '\'' +
                    '}';
        }
        return "{" +
                "\"message\": " + message + '\'' +
                '}';
    }


    public String toDigestString(String digest) {
        return "{" +
                "\"digest\":\"" + digest + '"' +
                '}';
    }
}
