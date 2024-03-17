package com.SpringBoot.GharBhada.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment {

	@Id
	private String comment_id;

	@Column(length = 300)
	private String content;

	
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	
	@ManyToOne
	@JoinColumn(name = "home_id")
	private Home home;

}
