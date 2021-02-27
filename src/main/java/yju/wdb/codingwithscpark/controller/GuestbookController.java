package yju.wdb.codingwithscpark.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import  org.apache.logging.log4j.*;

@Controller
@RequestMapping("/guestbook")

public class GuestbookController {

	private static final Logger log = LogManager.getLogger(GuestbookController.class);

	@GetMapping({"/", "/list"})
	public String list() {
		log.info("list...........");
		
		return "/guestbook/list";
	}

}
