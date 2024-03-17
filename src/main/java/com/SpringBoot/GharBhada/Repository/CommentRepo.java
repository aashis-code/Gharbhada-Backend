package com.SpringBoot.GharBhada.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringBoot.GharBhada.Entity.Comment;
import com.SpringBoot.GharBhada.Entity.Home;

@Repository
public interface CommentRepo extends JpaRepository<Comment, String> {
	
	//FInd by Home
	List<Comment> findByHome(Home homeResponseToHome);

}
