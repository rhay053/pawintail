package com.pawintail.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.pawintail.service.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	MemberService memberService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//순서주의(로그인 할 때 사진 보지 않기 위함)
		http.authorizeHttpRequests()
			.mvcMatchers("/","/members/**","/item/**","/home/**", "/event/**","/mypet/**","/donation/**").permitAll()
			.mvcMatchers("/admin/**").hasRole("ADMIN") 
			.mvcMatchers("/user/cart/**", "/user/order/**","/user/cartItem/**").hasAnyRole("ADMIN","USER")//사용자,관리자 주문, 장바구니 추가
			.mvcMatchers("/mypage/**").hasAnyRole("ADMIN","USER") //관리자, 유저 로그인 후 마이페이지 열람
			.anyRequest().authenticated();
		
		http.formLogin()
			.loginPage("/members/login")
			.defaultSuccessUrl("/")
			.usernameParameter("email")
			.failureUrl("/members/login/error")
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
			.logoutSuccessUrl("/");
		
		http.exceptionHandling()
		.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
		
		//우리집 멍냥이 자랑, 로그인은 csrf 토큰 비활성화, 장바구니 및 주문하기는 csrf 토큰 활성화 필요
		http.csrf().ignoringAntMatchers("/mypet/**","/admin/**","/members/**","/item/**","/cart/**","/order/**","/admin/events/**");
		
		//Security 환경에서 PostMapping하기 위함
//		http.csrf().disable();
		//장바구니, 주문하기 실행 --> csrf 토큰 활성화?
//		http.csrf();
		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/vendor/**",
//				"/vendor/bootstrap/css/**","/vendor/bootstrap/js/**",
//				"/assets/**","/assets/img/**", "/vendor/jquery/**","/img/**");
		
		web.ignoring().antMatchers("/vendor/**",
				"/vendor/bootstrap/css/**","/vendor/bootstrap/js/**",
				"/assets/**","/assets/img/**", "/vendor/jquery/**", "/files/mypet/**","/images/item/**","/images/event/**","/img/**"
	);
		
		//web.ignoring().antMatchers("/resources/**", "/static/**", 
		//		"/vendor/**",
		//		"/vendor/bootstrap/css/**","/vendor/bootstrap/js/**",
		//				"/assets/**","/assets/img/**", "/vendor/jquery/**");
		// web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
		 
	}

	//UserDetail 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberService)
			.passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
