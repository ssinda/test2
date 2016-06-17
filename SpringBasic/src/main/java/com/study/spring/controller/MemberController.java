package com.study.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.spring.domain.MemberVO;
import com.study.spring.service.MemberService;

/**
 * Handles requests for the application home page.
 */

// 1. 사용자가 브라우저를 통해서 URI를 호출
// 2. 톰캣에 설치된 스프링 프레임웍이
//  -> Controller들을 검색해서 @RequestMapping 애너테이션을 찾는다
//  -> 애너테이션의 value의 값들을 URI와 매칭을 시킨다
// 3. URI와 패밍된 메서드에서 return 해주는 문자열과 이름이 같은 jsp 파일을 사용자에게 넘겨준다

@Controller
public class MemberController {
	
	@Inject
	private MemberService service;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public String member(Locale locale, Model model) {
		
		List<MemberVO> member_list = service.getList();
		
		model.addAttribute("member_list", member_list);
		
		return "member_list";
	}
	
	@RequestMapping(value = "/member", method = RequestMethod.POST)
	public String memberInsert(@RequestParam("CRUD") String CRUD, MemberVO vo, Model model){
	//@RequestParam("user_id") String user_id
		//String user_id = request.getParameter("user_id");
		System.out.println(CRUD);
		if(CRUD == "Insert" || "Insert".equals(CRUD)){
			service.insertMember(vo);
		}else if(CRUD == "Update" || "Update".equals(CRUD)){
			service.update(vo);
		}else{
			service.delete(vo.getUser_id());
		}
		
		return "redirect:/member";
	}
	
}
