package com.example.demo.controller;

import javax.servlet.http.HttpSession;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.MemberVO;

import lombok.Setter;

@Controller
@Setter
public class MemberController {

	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/join",method = RequestMethod.GET)
	public void join_form() {
		
	}
	
	@RequestMapping(value = "/join",method = RequestMethod.POST)
	public void join_submit(MemberVO m) {
		m.setPwd(   passwordEncoder.encode(  m.getPwd()   ) );
		int re  = dao.insert(m);
		if(re ==1 ) {
			System.out.println("등록성공");
		}else {
			System.out.println("등록실패");
		}
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login() {
	}
	
	@RequestMapping("/loginOK") //시큐리티 환경설정파일에서 로그인을 성공시 여기로 오도록 설정
	public void  loginOK(HttpSession session) {
		//인증정보를 얻어옴
		//로그인한 회원의 정보를 브라우저 닫기전까지 유지하기 위해 session을 매개변수로 설정
		//스프링 시큐리티에서는 우리가 로그인폼만 만들었고 로그인 처리를 시큐리티가 해주었음
		//로그인한 회원의 정보를 파악하기 위해서는 다음과 같이 시큐리티에게 요청함
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		//시큐리티가 인증된(로그인된) 사용자 정보를 얻어올 수 있음
		User user = (User)authentication.getPrincipal();
		
		//인증된 user객체의 id를 뽑아옴
		String id = user.getUsername();
		
		//인증된 user를 통해서만 아이디를 알아올 수 있음
		//로그인한 회원의 다른 정보도 필요하다면 db로 갖고올 수 있음
		MemberVO m = dao.findById(id);
		
		//db로부터 읽어온 회원 객체를 세션에 상태유지
		//세션에 상태유지를 하면 브라우저를 닫기전까지(로그아웃 하기전까지)상태유지가 가능함
		session.setAttribute("m", m); 		 
	}
	
	
	@RequestMapping("/list")
	@ResponseBody
	public void list() {
		 
	}
}



















