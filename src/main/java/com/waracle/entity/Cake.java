package com.waracle.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CAKE", uniqueConstraints = { @UniqueConstraint(columnNames = "ID") })
public class Cake implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6968810851822836518L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long cakeId;

	@Column(name = "TITLE", nullable = false, length = 100)
	private String title;

	@Column(name = "DESCRIPTION", nullable = false, length = 100)
	private String desc;

	@Column(name = "IMAGE", nullable = false, length = 300)
	private String image;

}
