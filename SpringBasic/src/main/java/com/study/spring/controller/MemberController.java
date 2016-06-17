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

// 1. ����ڰ� �������� ���ؼ� URI�� ȣ��
// 2. ��Ĺ�� ��ġ�� ������ �����ӿ���
//  -> Controller���� �˻��ؼ� @RequestMapping �ֳ����̼��� ã�´�
//  -> �ֳ����̼��� value�� ������ URI�� ��Ī�� ��Ų��
// 3. URI�� �йֵ� �޼��忡�� return ���ִ� ���ڿ��� �̸��� ���� jsp ������ ����ڿ��� �Ѱ��ش�

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
