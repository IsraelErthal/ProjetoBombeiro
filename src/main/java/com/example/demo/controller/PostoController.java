package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PostoDTO;
import com.example.demo.service.PostoService;

@RestController
@RequestMapping("/postos")
public class PostoController extends BaseController<PostoDTO> {

    private PostoService service;


    protected PostoController(PostoService service){
        super(service);

        this.service = service;
        
    }
}
