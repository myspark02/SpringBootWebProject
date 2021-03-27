package yju.wdb.codingwithscpark.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import yju.wdb.codingwithscpark.dto.*;
import yju.wdb.codingwithscpark.service.BoardService;


@Controller
@RequestMapping("/board")
public class BoardController {
	private static final Logger log = LogManager.getLogger(GuestbookController.class);
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"/read", "/modify"})
	public void read(long bno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
		log.debug("[Get]read|modify [" + bno + "]");
		
		BoardDTO dto = boardService.get(bno);
		model.addAttribute("dto", dto);
	}
	
	
	@PostMapping("/remove")
	public String remove(long bno, RedirectAttributes redirectAttributes) {
		log.debug("[Post] remove:" + bno);
		
		boardService.removeWithReplies(bno);
		
		redirectAttributes.addFlashAttribute("msg", bno);
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/modify")
	public String modify(BoardDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, 
															RedirectAttributes redirectAttributes)  {
		log.debug("[Post] modify.................. : " + dto);
		
		boardService.modify(dto);
		
		redirectAttributes.addAttribute("page", requestDTO.getPage());
		redirectAttributes.addAttribute("bno", dto.getBno());
		redirectAttributes.addAttribute("type", requestDTO.getType());
		redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
		
		return "redirect:/board/read";
	}
		
	@GetMapping("/register")
	public void register() {
		log.debug("register form requested....");
	}
	
	@PostMapping("/register")
	public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes) {
		log.debug("BoardDTO ... :" + dto);
		
		Long bno = boardService.register(dto);
		log.info("inserted bno: " + bno);
		
		redirectAttributes.addFlashAttribute("msg", bno);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		log.debug("list........." + pageRequestDTO);
		model.addAttribute("result", boardService.getList(pageRequestDTO));
	}
	
}
