package corallus.artConnect.artConnect.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.entity.Admin;
import corallus.artConnect.artConnect.entity.ContatoAdmin;
import corallus.artConnect.artConnect.service.AdminService;
import corallus.artConnect.artConnect.service.ContatoAdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {
    @Autowired
    private ContatoAdminService contAdminService;
    @Autowired
    private AdminService adminService;

    //criar contato do admin
    @PostMapping("/criar-contato/{id}")
    public ResponseEntity<String> cadastrarContato(@PathVariable Long id, @RequestBody ContatoAdmin contato) {
        try {
            contato.setIdAdmin(id);
            String msg = contAdminService.cadastrarContato(contato);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Erro: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

 

    //Deletar contato
    @DeleteMapping("/deletar-contato/{id}")
    public ResponseEntity<String> deletarContato(@PathVariable Long id) {
    	try {
    		String msg = this.contAdminService.deletarContato(id);
    		return new ResponseEntity<>(msg, HttpStatus.OK);
    	} catch (Exception e) {
    		e.printStackTrace();
			return new ResponseEntity<>("Erro"+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    @GetMapping("/todos")
    public ResponseEntity<List<Admin>> todos() {
    	try {
    		List<Admin> lista = this.adminService.todos();
    		return new ResponseEntity<>(lista, HttpStatus.FOUND);
    	} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
    }
}
