package com.rentix.beans;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Acceleration entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACCELERATION", schema = "PUBLIC", catalog = "TESIS")
public class Acceleration implements java.io.Serializable {

	// Fields

	private Integer id;
	private String accel;
	private Set<Scene> scenes = new HashSet<Scene>(0);

	// Constructors

	/** default constructor */
	public Acceleration() {
	}

	/** minimal constructor */
	public Acceleration(String accel) {
		this.accel = accel;
	}

	/** full constructor */
	public Acceleration(String accel, Set<Scene> scenes) {
		this.accel = accel;
		this.scenes = scenes;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ACCEL", nullable = false, length = 25)
	public String getAccel() {
		return this.accel;
	}

	public void setAccel(String accel) {
		this.accel = accel;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "acceleration")
	public Set<Scene> getScenes() {
		return this.scenes;
	}

	public void setScenes(Set<Scene> scenes) {
		this.scenes = scenes;
	}

}