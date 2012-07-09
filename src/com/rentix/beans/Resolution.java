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
 * Resolution entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RESOLUTION", schema = "PUBLIC", catalog = "TESIS")
public class Resolution implements java.io.Serializable {

	// Fields

	private Integer id;
	private String resolution;
	private Set<Scene> scenes = new HashSet<Scene>(0);

	// Constructors

	/** default constructor */
	public Resolution() {
	}

	/** minimal constructor */
	public Resolution(String resolution) {
		this.resolution = resolution;
	}
	public Resolution(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Resolution(String resolution, Set<Scene> scenes) {
		this.resolution = resolution;
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

	@Column(name = "RESOLUTION", nullable = false, length = 25)
	public String getResolution() {
		return this.resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "resolution")
	public Set<Scene> getScenes() {
		return this.scenes;
	}

	public void setScenes(Set<Scene> scenes) {
		this.scenes = scenes;
	}

}