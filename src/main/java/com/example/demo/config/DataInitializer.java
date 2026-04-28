package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.Pessoa;
import com.example.demo.enums.NivelAcesso;
import com.example.demo.repository.PessoaRepository;

@Configuration
public class DataInitializer {


    private final PasswordEncoder passwordEncoder;

    public DataInitializer(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner initDataBase(PessoaRepository repository){

        return args -> {
            if(repository.count() <= 0){
                Pessoa pessoa = new Pessoa();


                pessoa.setEmail("admin@admin.com");
                pessoa.setNivelAcesso(NivelAcesso.ADMIN);
                pessoa.setSenha(passwordEncoder.encode("123456789"));

                repository.save(pessoa);

                System.out.println("Usuário ADMIN criado com sucesso: admin@admin.com / 123456789");
            }else{
                System.out.println("Usuário ADMIN já existe");
            }
        };
    }
}
