package com.ssafy.vue.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.MailDto;
import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.service.EmailService;
import com.ssafy.vue.model.service.JwtServiceImpl;
import com.ssafy.vue.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/member")
@Api("사용자 컨트롤러  API V1")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private EmailService emailService;

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if (loginUser != null) {
				String accessToken = jwtService.createAccessToken("userid", loginUser.getUserid());// key, data
				String refreshToken = jwtService.createRefreshToken("userid", loginUser.getUserid());// key, data
				memberService.saveRefreshToken(memberDto.getUserid(), refreshToken);
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.memberInfo(userid);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{userid}")
	public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleRefreshToken(userid); 
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, memberDto : {}", token, memberDto);
		if (jwtService.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(memberDto.getUserid()))) {
				String accessToken = jwtService.createAccessToken("userid", memberDto.getUserid());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원가입", notes = "입력된 회원 정보에 따라 회원가입을 한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> signup(@RequestBody @ApiParam(value = "회원가입", required = true) MemberDto memberDto) throws Exception {
		logger.info("signup - 호출");
		try {
			if (memberService.signup(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
		}catch (Exception e) {
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "회원가입 아이디 중복확인", notes = "중복된 아이디면 1 중복되지 않았으면 0을 반환한다.", response = Map.class)
	@PostMapping("/idCheck")
	public ResponseEntity<String> idCheck(
			@RequestBody @ApiParam(value = "회원가입 아이디 중복확인시 필요한 아이디", required = true) MemberDto memberDto) throws Exception {
		logger.info("idCheck - 호출 ", memberDto.getUserid());
		
		if (memberService.idCheck(memberDto.getUserid())==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
	
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "회원 정보 수정", notes = "수정할 회원 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyMember(@RequestBody @ApiParam(value = "수정할 회원 정보.", required = true) MemberDto memberDto) throws Exception {
		logger.info("modifyMember - 호출", memberDto);
		
		if (memberService.modifyMember(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원탈퇴", notes = "회원탈퇴 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{userid}")
	public ResponseEntity<String> deleteMember(@PathVariable("userid") @ApiParam(value = "살제할 글의 글번호.", required = true) String userid) throws Exception {
		logger.info("deleteMember - 호출");
		if (memberService.deleteMember(userid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	@ApiOperation(value = "아이디 찾기", notes = "맞는 정보가 없으면 fail 아니면 userid 반환", response = Map.class)
	@PostMapping("/idFind")
	public ResponseEntity<String> idFind(
			@RequestBody @ApiParam(value = "회원가입 아이디 중복확인시 필요한 아이디", required = true) MemberDto memberDto) throws Exception {
		MemberDto member = memberService.idFind(memberDto);
		String userid = member.getUserid();
		if(userid!=null) {
			return new ResponseEntity<String>(userid, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		
	}
	

	@ApiOperation(value = "아이디 찾기", notes = "맞는 정보가 없으면 fail 아니면 success 반환", response = Map.class)
	@PostMapping("/idFindCheck")
	public ResponseEntity<String> idFindCheck(
			@RequestBody @ApiParam(value = "회원가입 아이디 중복확인시 필요한 아이디", required = true) MemberDto memberDto) throws Exception {
		logger.info("idFindCheck - 호출 {}", memberDto.getEmail());
		
		if(memberService.idFindCheck(memberDto.getEmail())==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "비밀번호 찾기", notes = "아이디랑 이메일을 받는다. 임시 비밀번호를 메일로 보낸다. fail 아니면 success 반환", response = Map.class)
	@PostMapping("/passwordFind")
	public ResponseEntity<?> passwordFind (@RequestBody @ApiParam MemberDto memberDto) throws Exception {
		String getId = memberDto.getUserid();
		String getEmail = memberDto.getEmail();
		// 이 사람이 유효한 사용자인가 확인한다. 
		String getUsername = memberService.isValidMember(getId,getEmail); 
		System.out.println(getUsername);
		if(getUsername != null) {
			MailDto dto = emailService.createMailAndChangePassword(getUsername, getEmail);
			emailService.mailSend(dto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	

}
