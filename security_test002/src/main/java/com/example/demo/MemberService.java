package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.MemberVO;

import lombok.Setter;


//스프링 시큐리티 환경에서 "로그인"처리를 위해서는 
//UserDetailService를 구현한 클래스를 만들고
//loadUserByUsername메소드를 오버라이딩하여 
//매개변수로 전달받은 username(아이디)에 해당하는 회원정보를 db로부터 검색하여 
//UserDatils객체로 반환하도록 함
//이렇게만 해주면 우리가 암호가 일치하는지, role무엇인지 따로 처리하지 않아도
//스프링시큐리티가 알아서 인증과 인가에 대한 처리를 해줌
@Service
@Setter
public class MemberService implements UserDetailsService{
	
	@Autowired
	private MemberDAO dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		//매개변수로 전달받은 username(아이디)에 해당하는 회원의 정보를 db로부터 갖고 옴		
		MemberVO m= dao.findById(username);
		
		//만약에 존재하지 않는 아이디라면 예외를 발생시킵니다.
		if(m == null) {
			throw new UsernameNotFoundException(username);
		}
		
		//db로 부터 읽어온 회원의 정보를 UserDetails객체로 생성하여 반환합니다.
		UserDetails details = User.builder().username(username).password(m.getPwd()).roles(m.getRole()).build();		
		return details;
	}

}










