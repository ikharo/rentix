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
 * Status entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "STATUS", schema = "PUBLIC", catalog = "TESIS")
public class Status implements java.io.Serializable {

	// Fields

	private Integer id;
	private String description;
	private Set<Scene> scenes = new HashSet<Scene>(0);

	// Constructors

	/** default constructor */
	public Status() {
	}

	/** minimal constructor */
	public Status(String description) {
		this.description = description;
	}

	/** full constructor */
	public Status(String description, Set<Scene> scenes) {
		this.description = description;
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

	@Column(name = "DESCRIPTION", nullable = false, length = 25)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "status")
	public Set<Scene> getScenes() {
		return this.scenes;
	}

	public void setScenes(Set<Scene> scenes) {
		this.scenes = scenes;
	}

}