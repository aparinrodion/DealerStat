package org.leverx.dealerstat.controllers;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.models.User;
import org.leverx.dealerstat.services.UserService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@ComponentScan("org.leverx.dealerstat")
public class Controller {

}
