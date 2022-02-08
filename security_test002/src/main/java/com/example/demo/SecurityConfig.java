package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


//인증과 인가를 위해서
//스프링시큐리티가 제공하는 WebSecurityConfigurerAdapter를 상속받은 클래스를 만듦
//configure라는 메소드를 오버라이딩해서 시큐리티 환경설정을 해줌
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(http);
		
		//configure메소드의 HttpSecurity의 매개변수(http)를 통해서 환경설정을 함
		http.authorizeHttpRequests()
		//인증(로그인)을 하지 않아도 될 서비스들을 설정
	 
		//모든 서비스 설정
		//.mvcMatchers("/","/join","/login","/all/**").permitAll()
		.mvcMatchers("/","/join","/login").permitAll()
		//admin네임스페이스에 있는 모든 요청은 admin권한이 있어야 가능함을 요청(인가)
		.mvcMatchers("/admin/**").hasRole("admin")		 
		 
		//그 나머지 모든 요청은 인증(로그인)을 해야 서비스를 받을 수 있음을 설정
		.anyRequest().authenticated();
		
		
		//스프링시큐리티를 사용하겠다고 의존관계를 설정하면
		//스프링시큐리티가 로그인폼을 제공해줌
		//마약, 자신이 만든 로그인폼을 사용하려면 다음과 같이 설정하면 됨
		http.formLogin().loginPage("/login") 
		.defaultSuccessUrl("/loginOK");//로그인성공시 이동하는 컨트롤러에서 로그인한 회원의 정보를 상태유지 코드를 작성함
		//로그인을 위한 get방식에 대한 컨트롤러와 viewPage까지만 만들고
			//post방식에 대한 건 설정x->시큐리티가 알아서 해줌
			//login.jsp에서 name: 아이디를 username, 비밀번호는 password로 설정필수 
					//이때 MemberService의 UserDetailsService인터페이스를 loadUserByUsername작동함
		
		//로그아웃을 위하여 서비스명만 설정하면 실제 로그아웃에 스핑시큐리티가 알아서 해줌
		http.logout()
		//로그아웃을 위한 서비스명 설정
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.invalidateHttpSession(true)//로그아웃이후 세션을 파기
		.logoutSuccessUrl("/login");
		
		http.httpBasic();
	}	
}












