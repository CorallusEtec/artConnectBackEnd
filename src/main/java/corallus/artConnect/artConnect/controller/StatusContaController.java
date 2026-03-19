package corallus.artConnect.artConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.entity.StatusConta;
import corallus.artConnect.artConnect.service.StatusContaService;

@RestController
@RequestMapping("/statusConta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StatusContaController {
	@Autowired
	private StatusContaService statusContaService;
	
	@GetMapping("/todos")
	public ResponseEntity<List<StatusConta>> todos() {
		try {
			List<StatusConta> status = this.statusContaService.todos();
			return new ResponseEntity<>(status, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/findByName")
	public ResponseEntity<StatusConta> findByName(@RequestParam String name) {
		try {
			StatusConta status = this.statusContaService.findByName(name);
			return new ResponseEntity<>(status, HttpStatus.FOUND);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<StatusConta> findById(@PathVariable Long id) {
		try {
			StatusConta status = this.statusContaService.findById(id);
			return new ResponseEntity<>(status, HttpStatus.FOUND);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody StatusConta status) {
		try {
			String msg = this.statusContaService.save(status);
			return new ResponseEntity<>(msg, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Erro ao cadastrar", HttpStatus.BAD_REQUEST);
		}
	}
}
