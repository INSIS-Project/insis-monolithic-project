package com.isep.acme.model;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserView {
    UUID userId;

    String username;

    String fullName;
}
