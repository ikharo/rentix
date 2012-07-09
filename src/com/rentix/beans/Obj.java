package com.rentix.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Obj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OBJ", schema = "PUBLIC", catalog = "TESIS")
public class Obj implements java.io.Serializable {

	// Fields

	private Integer id;
	private Scene scene;
	private Material material;
	private String name;
	private Integer numTriangles;

	// Constructors

	/** default constructor */
	public Obj() {
	}

	/** minimal constructor */
	public Obj(Scene scene) {
		this.scene = scene;
	}

	/** full constructor */
	public Obj(Scene scene, Material material, String name, Integer numTriangles) {
		this.scene = scene;
		this.material = material;
		this.name = name;
		this.numTriangles = numTriangles;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCENEID", nullable = false)
	public Scene getScene() {
		return this.scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAL_TYPE")
	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	@Column(name = "NAME", length = 25)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NUM_TRIANGLES")
	public Integer getNumTriangles() {
		return this.numTriangles;
	}

	public void setNumTriangles(Integer numTriangles) {
		this.numTriangles = numTriangles;
	}

}