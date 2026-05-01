package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CheckinDTO;
import com.example.demo.dto.CheckinResponseDTO;
import com.example.demo.dto.CheckoutDTO;
import com.example.demo.dto.CheckoutResponseDTO;
import com.example.demo.entity.Arquivo;
import com.example.demo.entity.Checkin;
import com.example.demo.entity.Checkout;
import com.example.demo.entity.Posto;
import com.example.demo.repository.CheckinRepository;
import com.example.demo.repository.CheckoutRepository;
import com.example.demo.repository.PostoRepository;

@Service
public class CheckService {     


    @Autowired
    private ArquivoService arquivoService;
    
    @Autowired
    private PostoRepository postoRepository;

    @Autowired
    private CheckinRepository checkinRepository;

    @Autowired
    private CheckoutRepository checkoutRepository;

    public CheckinResponseDTO checkin(CheckinDTO dto){
        Posto posto = postoRepository.findById(dto.getPostoId()).orElseThrow();

        Checkin checkin = new Checkin();

        checkin.setPosto(posto);

        Arquivo arquivo = arquivoService.upload(dto.getFoto());

        checkin.setFoto(arquivo);

        Checkin checkinSalvo = checkinRepository.save(checkin);

        CheckinResponseDTO crd = new CheckinResponseDTO();

        crd.setPosto(posto.getNome());
        crd.setHorario(checkinSalvo.getCreatedAt());

        return crd;
    }

     public CheckoutResponseDTO checkout(CheckoutDTO dto){
        Posto posto = postoRepository.findById(dto.getPostoId()).orElseThrow();

        Checkout checkout = new Checkout();

        checkout.setPosto(posto);

        Arquivo arquivo = arquivoService.upload(dto.getFoto());

        checkout.setFoto(arquivo);

        Checkout checkoutSalvo = checkoutRepository.save(checkout);

        CheckoutResponseDTO crd = new CheckoutResponseDTO();

        crd.setPosto(posto.getNome());
        crd.setHorario(checkoutSalvo.getCreatedAt());

        return crd;
    }
}
