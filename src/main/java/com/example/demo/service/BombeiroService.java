package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.BombeiroDTO;
import com.example.demo.entity.Bombeiro;
import com.example.demo.repository.BombeiroRepository;

@Service
public class BombeiroService extends BaseService<Bombeiro, BombeiroDTO> {

      private BombeiroRepository repository;

    protected BombeiroService(BombeiroRepository repository){
        super(repository);

        this.repository = repository;
    }
}
