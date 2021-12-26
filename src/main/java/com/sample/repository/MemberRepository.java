package com.sample.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

	Optional<Member> findByuserName(String username);

}
