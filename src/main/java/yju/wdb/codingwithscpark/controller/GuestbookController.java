package yju.wdb.codingwithscpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import yju.wdb.codingwithscpark.dto.PageRequestDTO;
import yju.wdb.codingwithscpark.service.GuestBookService;

import  org.apache.logging.log4j.*;

@Controller
@RequestMapping("/guestbook")

public class GuestbookController {

	private static final Logger log = LogManager.getLogger(GuestbookController.class);
	
	@Autowired
	private GuestBookService service;
	
	@GetMapping("/")
	public String index() {
		return "redirect:/guestbook/list";
	}

	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		log.info("list..........." + pageRequestDTO);
		
		model.addAttribute("result", service.getList(pageRequestDTO));
	}

}

