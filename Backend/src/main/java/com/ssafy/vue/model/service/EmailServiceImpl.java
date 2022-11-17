package com.ssafy.vue.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.MailDto;
import com.ssafy.vue.model.MemberDto;


@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	MemberService memberService;
	
	

    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "heiditty@naver.com";
    
	@Override
	public MailDto createMailAndChangePassword(String email, String username) throws Exception {
		String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(email);
        dto.setTitle(username+"님의 HOTTHINK 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. HOTTHINK 임시비밀번호 안내 관련 이메일 입니다." + "[" + username + "]" +"님의 임시 비밀번호는 "
        + str + " 입니다.");
        System.out.println(str);
        updatePassword(str, email, username);
     
        return dto;
	}

	@Override
	public void updatePassword(String str, String email, String username) throws Exception {
		String pw = str;
		MemberDto member = new MemberDto(); 
		member.setEmail(email);
		member.setUsername(username);
        System.out.println( memberService.idFind(member).getUserid());
    
//        memberService.passwordUpdate(id,pw);
		
	}

	@Override
	public String getTempPassword() {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
	}
	
	@Override
  public void mailSend(MailDto mailDto){
        System.out.println("이멜 전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(EmailServiceImpl.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        mailSender.send(message);
    }

}
