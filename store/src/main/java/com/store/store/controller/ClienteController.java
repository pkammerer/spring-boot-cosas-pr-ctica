package com.store.store.controller;

import com.store.store.model.ClientsBean;
import com.store.store.model.LoginBean;
import com.store.store.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClientsRepository clientsRepository;

    @PostMapping("/")
    public ResponseEntity<ClientsBean> login(@RequestBody LoginBean loginBean){

        ClientsBean client = clientsRepository.findByUser(loginBean.getUser());
        if(loginBean.getPssw().equals(client.getPssw()))
            return new ResponseEntity<ClientsBean>(client,HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(value = "/image", produces = {MediaType.IMAGE_JPEG_VALUE} )
    public ResponseEntity<byte[]> returnImage(@RequestParam(value = "file", required = false) MultipartFile file){

        return new ResponseEntity<>(file, HttpStatus.OK);

    }
}
