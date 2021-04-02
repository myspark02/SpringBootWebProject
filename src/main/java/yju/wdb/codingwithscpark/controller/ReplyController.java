package yju.wdb.codingwithscpark.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import yju.wdb.codingwithscpark.dto.ReplyDTO;
import yju.wdb.codingwithscpark.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {
	@Autowired
	private ReplyService replyService;
	
	private static final Logger log = LogManager.getLogger(ReplyController.class);
	
	@PutMapping("/{rno}")
	public ResponseEntity<String> modify(@RequestBody ReplyDTO replyDTO) {
		log.info("reply update: " + replyDTO);
		replyService.modify(replyDTO);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
		
	@DeleteMapping("/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
		log.info("Delete RNO:" + rno);
		
		replyService.remove(rno);
		
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	 
	@PostMapping("/")
	public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO) {
		log.info("reply post : " + replyDTO);
		Long rno = replyService.register(replyDTO);
		
		return new ResponseEntity<>(rno, HttpStatus.OK);
	}
	
	@GetMapping(value="/board/{bno}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReplyDTO>> getListByBoard(@PathVariable("bno") Long bno) {
		List<ReplyDTO> replyList = replyService.getList(bno);
		// HTTP 상태코드와 data를 함께 전달.
		return new ResponseEntity<>(replyList, HttpStatus.OK);
	}
}
