package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CheckinDTO;
import com.example.demo.entity.Arquivo;
import com.example.demo.entity.Checkin;
import com.example.demo.entity.Posto;
import com.example.demo.repository.CheckinRepository;
import com.example.demo.repository.PostoRepository;

@Service
public class CheckService {

    @Autowired
    private ArquivoService arquivoService;
    
    @Autowired
    private PostoRepository postoRepository;

    @Autowired
    private CheckinRepository checkinRepository;

    public void checkin(CheckinDTO dto){

          //ID sendo transfomrado em entidade
        Posto posto = postoRepository.findById(dto.getPostoId()).orElseThrow();

        Checkin checkin = new Checkin();

        checkin.setPosto(posto);

        Arquivo arquivo = arquivoService.upload(dto.getFoto());

        checkin.setFoto(arquivo);

        checkinRepository.save(checkin);
    }
}
