package com.sample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.exception.ResourceNotFoundException;
import com.sample.model.Member;
import com.sample.repository.MemberRepository;


@RestController
@RequestMapping("/api/v1/")
public class MemberController {

	@Autowired
	private MemberRepository memberRepository;
	
	@GetMapping("/members")
	public List<Member> getAllMembers(){
		memberRepository.findAll().forEach((mem) -> mem.setPassword("Press New Pwd"));
		return memberRepository.findAll();
	}
	
	@PostMapping("/members")
	public Member createMember(@RequestBody Member member) {
		String pwd = new BCryptPasswordEncoder().encode(member.getPassword());
		member.setPassword(pwd);
		return memberRepository.save(member);
	}
	
	@GetMapping("/members/id/{id}")
	public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
		
		 Member member = memberRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Member Not Found"));
		 member.setPassword("Press New Pwd");
		 return ResponseEntity.ok(member);
	}
	
	@GetMapping("/members/userName/{userName}")
	public ResponseEntity<Member> getMemberByuserName(@PathVariable String userName) {
		 
		 Member member = memberRepository.findByuserName(userName).
				orElseThrow(() -> new ResourceNotFoundException("Member Not Found"));
		
		 member.setPassword("Press New Pwd");
		 
		 return ResponseEntity.ok(member);
	}
	
	@PutMapping("/members/{id}")
	public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member memberDetails){
		Member member = memberRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Member Not Found"));
		
		String encodePwd = new BCryptPasswordEncoder()
				.encode(memberDetails.getPassword());
		
		member.setPassword(encodePwd);
		member.setEmailId(memberDetails.getEmailId());
		member.setFirstName(memberDetails.getFirstName());
		member.setLastName(memberDetails.getLastName());
		member.setUserName(memberDetails.getUserName());
		
		Member updatedEmployee = memberRepository.save(member);
		return ResponseEntity.ok(updatedEmployee);
		
	}
	
	@DeleteMapping("/members/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Member member = memberRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Member NotFound"));
		memberRepository.delete(member);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}
	
	
	
}
