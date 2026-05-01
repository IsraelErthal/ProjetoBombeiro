package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BombeiroDTO;
import com.example.demo.service.BombeiroService;

@RestController
@RequestMapping("/bombeiros")
public class BombeiroController extends BaseController<BombeiroDTO> {

    private BombeiroService service;

    protected BombeiroController(BombeiroService service){
        super(service);
        this.service = service;
    }
}
