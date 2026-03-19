package corallus.artConnect.artConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.Admin;
import corallus.artConnect.artConnect.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	public List<Admin> todos() {
		try {
			List<Admin> lista = this.adminRepository.findAll();
			return lista;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
