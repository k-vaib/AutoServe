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
@Table(name="job_card_item")
@AttributeOverride(name="id", column=@Column(name="item_id"))
@Getter
@Setter

public class JobCardItem extends BaseEntity{
	
	private int quantity;
	
	@Column(name="snapshot_price")
	private Double snapshotPrice;
	
	@Column(name= "snapshot_item_name")
	private String snapshotItemName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="job_card_id")
	private JobCard jobCard;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_id")
	private Inventory inventory;
	
}
