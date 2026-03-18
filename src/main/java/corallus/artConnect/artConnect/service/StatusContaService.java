package corallus.artConnect.artConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.StatusConta;
import corallus.artConnect.artConnect.repository.StatusContaRepository;

@Service
public class StatusContaService {

	@Autowired
	private StatusContaRepository statusContaRepository;
	
	public List<StatusConta> todos() {
		try {
			List<StatusConta> status = this.statusContaRepository.findAll();
			return status;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public StatusConta findByName(String name) {
		try {
			StatusConta alvo = null;
			 List<StatusConta> todosStatus = this.statusContaRepository.findAll();
			 for(int i=0; i<todosStatus.size(); i++) {
				 if(todosStatus.get(i).getNomeStatus().equalsIgnoreCase(name)) {
					 alvo = todosStatus.get(i);
					 break;
				 }
			 }
			 return alvo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public StatusConta findById(Long id) {
		try {
			StatusConta alvo = this.statusContaRepository.findById(id).get();
			 return alvo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public String save(StatusConta status) {
		try {
			statusContaRepository.save(status);
			return "Status criado com sucesso";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
