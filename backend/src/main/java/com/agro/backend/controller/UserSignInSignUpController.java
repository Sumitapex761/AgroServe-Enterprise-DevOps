package com.agro.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agro.backend.repositories.AgroUserRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserSignInSignUpController {

	private final AgroUserRepository agroUserRepository;
}
