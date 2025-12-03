package com.car_backend.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="job_card_evidence")
@AttributeOverride(name="id", column=@Column(name="evidence_id"))
@Getter
@Setter
public class JobCardEvidence extends BaseEntity{
	
	@Column(name="photo_url")
	private String photoUrl;
	
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="job_card_id")
	private JobCard jobCard;
}
