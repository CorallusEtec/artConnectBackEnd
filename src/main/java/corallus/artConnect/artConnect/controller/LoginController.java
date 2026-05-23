package corallus.artConnect.artConnect.controller;


/**
 * CONTROLLER DEPRECIADA. USAR AuthController
 * 
 * 
 */

	
	
	/**
	 * 	@RestController
		@RequestMapping("/login")
		@CrossOrigin(origins = "*", allowedHeaders = "*")
		public class LoginController {
	 * 
	 * 
	 * 	@Autowired
		private LoginService loginService;
	 * 
	 * 
	 * 	@GetMapping("/logar") 
		public ResponseEntity<UsuarioLoginRequest> login(@RequestParam String email, @RequestParam String senha) {
			UsuarioLoginRequest usuario = loginService.login(email, senha);
			return ResponseEntity.ok(usuario);
		}
	 * 
	 * 
	 * }
	 */



