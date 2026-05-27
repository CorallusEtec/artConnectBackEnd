package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.dto.response.admin.RelatorioResponse;
import corallus.artConnect.artConnect.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
        @Autowired
        private AdminService adminService;

        @GetMapping("/relatorio")
        public ResponseEntity<RelatorioResponse> getRelatorio() {
            var rel = this.adminService.getRelatorio();

            return new ResponseEntity<>(rel, HttpStatus.OK);
        }
}
