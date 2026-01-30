package com.union.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dropdown")
public class DropdownController {
    //1. 대학 드롭다운 /api/dropdown/university?q=이화


    //2. field, role 드롭다운 /api/dropdown/role?front


    //3. field, skill 드롭다운 /api/dropdown/skill?figma


    //4. domain 드롭다운 /api/dropdown/domain?q=IT
}
