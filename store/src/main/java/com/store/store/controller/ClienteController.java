package com.store.store.controller;

import com.store.store.model.ClientsBean;
import com.store.store.model.LoginBean;
import com.store.store.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClientsRepository clientsRepository;

    @PostMapping("/login")
    public ResponseEntity<ClientsBean> login(@RequestBody LoginBean loginBean) {

        ClientsBean client = clientsRepository.findByUser(loginBean.getUser());
        if (loginBean.getPssw().equals(client.getPssw()))
            return new ResponseEntity<ClientsBean>(client, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    //--------------------- IMAGEN --------------------------------------
    @PostMapping(value = "/image/{id}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<Void> uploadImage(@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable("id") String id) throws IOException {
        try {
            ClientsBean clientsBean = clientsRepository.findById(id).get();
            clientsBean.setImage(file.getBytes());
            clientsRepository.save(clientsBean);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "image/{id}", produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> returnImage(@PathVariable("id") String id){
        return new ResponseEntity<>(clientsRepository.findById(id).get().getImage(),HttpStatus.OK);
    }


    // --------------------------- CAMBIOS EN BD -------------------------------
    @PostMapping("/")
    public ResponseEntity<Void> insertClient(@RequestBody ClientsBean clientsBean){
        try{
            clientsRepository.save(clientsBean);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/nPssw")
    public ResponseEntity<Void> updatePassword(@RequestBody LoginBean loginBean){
        try{
            ClientsBean clientsBean = clientsRepository.findById(loginBean.getUser()).get();
            clientsBean.setPssw(loginBean.getPssw());
            clientsRepository.save(clientsBean);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
