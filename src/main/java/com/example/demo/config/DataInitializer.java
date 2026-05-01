package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.Bombeiro;
import com.example.demo.enums.NivelAcesso;
import com.example.demo.repository.BombeiroRepository;


@Configuration
public class DataInitializer {


    private final PasswordEncoder passwordEncoder;

    public DataInitializer(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner initDataBase(BombeiroRepository repository){

        return args -> {
            if(repository.count() <= 0){
                Bombeiro bombeiro = new Bombeiro();


                bombeiro.setEmail("admin@admin.com");
                bombeiro.setNivelAcesso(NivelAcesso.ADMIN);
                bombeiro.setSenha(passwordEncoder.encode("123456789"));

                repository.save(bombeiro);

                System.out.println("Usuário ADMIN criado com sucesso: admin@admin.com / 123456789");
            }else{
                System.out.println("Usuário ADMIN já existe");
            }

            if(repository.count() <= 0){
                Bombeiro bombeiro = new Bombeiro();

                bombeiro.setEmail("bombeiro@bombeiro.com");
                bombeiro.setNivelAcesso(NivelAcesso.PADRAO);
                bombeiro.setSenha(passwordEncoder.encode("987654321"));
            }
        };

    }
}
