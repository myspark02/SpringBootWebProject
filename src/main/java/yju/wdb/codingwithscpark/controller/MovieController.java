package yju.wdb.codingwithscpark.controller;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import yju.wdb.codingwithscpark.dto.MovieDTO;
import yju.wdb.codingwithscpark.service.MovieService;

@Controller
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	private MovieService movieService; 
	
	private static final Logger log = LogManager.getLogger(GuestbookController.class);
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	
	@PostMapping("/register") 
	public String register(MovieDTO movieDTO, RedirectAttributes redirectAttributes) {
		log.info("movieDTO: " + movieDTO);
		
		Long mno = movieService.register(movieDTO);
		
		redirectAttributes.addFlashAttribute("msg", mno);
		
		return "redirect:/movie/list"; 
	}
}
