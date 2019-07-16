package com.store.store.controller;

import java.security.Principal;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.store.model.ClientsBean;
import com.store.store.model.LoginBean;
import com.store.store.repositories.ClientsRepository;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClientsRepository clientsRepository;

	@PostMapping("/login")
	public ResponseEntity<Void> login(@RequestBody LoginBean loginBean) {
		try {
		ClientsBean cliente = clientsRepository.findByUsername(loginBean.getUser());
		
		if(cliente != null && loginBean.getPssw().equals(cliente.getPassword()))
			return new ResponseEntity<>(HttpStatus.OK);

			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@GetMapping("/user")
	public Principal user(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
		return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientsBean> getCliente(@PathVariable("id") String id) {
		return new ResponseEntity<ClientsBean>(clientsRepository.findById(id).get(), HttpStatus.OK);
	}

	// --------------------- IMAGEN --------------------------------------
//    @PostMapping(value = "/image", produces = {MediaType.IMAGE_JPEG_VALUE})
//    public ResponseEntity<Void> uploadImage(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
//        try {
//            ClientsBean clientsBean = clientsRepository.findByUsername().get();
//            clientsBean.setImage(file.getBytes());
//            clientsRepository.save(clientsBean);
//            return new ResponseEntity(HttpStatus.OK);
//        }catch(Exception e){
//            e.printStackTrace();
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//    }

	@GetMapping(value = "image/{id}", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	public ResponseEntity<byte[]> returnImage(@PathVariable("id") String id) {
		return new ResponseEntity<>(clientsRepository.findById(id).get().getImage(), HttpStatus.OK);
	}

	// --------------------------- CAMBIOS EN BD -------------------------------
	@PostMapping("/")
	public ResponseEntity<Void> insertClient(@RequestBody ClientsBean clientsBean) {
		try {
			clientsRepository.save(clientsBean);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/nPssw")
	public ResponseEntity<Void> updatePassword(@RequestBody LoginBean loginBean) {
		try {
			ClientsBean clientsBean = clientsRepository.findById(loginBean.getUser()).get();
			clientsBean.setPassword(loginBean.getPssw());
			clientsRepository.save(clientsBean);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
