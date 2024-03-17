package com.SpringBoot.GharBhada.Entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Table(name = "home")
public class Home {

	@Id
	private String home_id;

	private String title;

	private double latitude;

	private double longitude;

	private String district;

	private String city;

	private double price;

	private double area;

	private long bedroom;

	private long baths;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}

	@Column(length = 300)
	private String description;
	
	@Column(length = 300)
	private String amenities;

	private boolean rented;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	private Person person;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;


	@OneToMany(mappedBy = "home", cascade = CascadeType.ALL)
	private List<Rating> ratings;

	@OneToMany(mappedBy = "home", cascade = CascadeType.ALL)
	private List<Comment> comments;

	@OneToMany(mappedBy = "home", cascade = CascadeType.ALL)
	private List<Image> images;

}
