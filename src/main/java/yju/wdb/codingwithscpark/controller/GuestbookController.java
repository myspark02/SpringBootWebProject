package yju.wdb.codingwithscpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import yju.wdb.codingwithscpark.dto.GuestBookDTO;
import yju.wdb.codingwithscpark.dto.PageRequestDTO;
import yju.wdb.codingwithscpark.service.GuestBookService;

import  org.apache.logging.log4j.*;

@Controller
@RequestMapping("/guestbook")

public class GuestbookController {

	private static final Logger log = LogManager.getLogger(GuestbookController.class);
	
	@Autowired
	private GuestBookService service;
	
	@PostMapping("/remove")
	public String remove(long gno, RedirectAttributes redirectAttributes) {
		log.debug("[Post] remove:" + gno);
		service.remove(gno);
		redirectAttributes.addFlashAttribute("msg", gno);
		return "redirect:/guestbook/list";
	}
	
	@GetMapping({"/read", "/modify"})
	public void read(long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
		log.debug("[Get]read|modify [" + gno + "]");
		GuestBookDTO dto = service.read(gno);
		model.addAttribute("dto", dto);
	}
	
	@GetMapping("/")
	public String index() {
		return "redirect:/guestbook/list";
	}

	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		log.info("list..........." + pageRequestDTO);
		
		model.addAttribute("result", service.getList(pageRequestDTO));
	}
	
	@GetMapping("/register") 
	public void register() {
		log.info("register get ...");
	}

	@PostMapping("/register")
	public String registerPost(GuestBookDTO dto, RedirectAttributes redirectAttributes) {
		log.info("dto ..." + dto);
		
		Long gno = service.register(dto);
		
		redirectAttributes.addFlashAttribute("msg", gno);
		
		return "redirect:/guestbook/list";
		
	}
}


