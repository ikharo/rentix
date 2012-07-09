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
 * Material entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MATERIAL", schema = "PUBLIC", catalog = "TESIS")
public class Material implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer materialType;
	private Set<Obj> objs = new HashSet<Obj>(0);

	// Constructors

	/** default constructor */
	public Material() {
	}

	/** full constructor */
	public Material(Integer materialType, Set<Obj> objs) {
		this.materialType = materialType;
		this.objs = objs;
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

	@Column(name = "MATERIAL_TYPE")
	public Integer getMaterialType() {
		return this.materialType;
	}

	public void setMaterialType(Integer materialType) {
		this.materialType = materialType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "material")
	public Set<Obj> getObjs() {
		return this.objs;
	}

	public void setObjs(Set<Obj> objs) {
		this.objs = objs;
	}

}