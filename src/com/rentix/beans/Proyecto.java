package com.rentix.beans;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * Proyecto entity. @author MyEclipse Persistence Tools
 * 
 * 
 */

@NamedQueries({
	@NamedQuery(
			name = "findAllbyUser",
			query = "from Proyecto p where p.users = :actuaUser"
	)
})


@Entity
@Table(name = "PROYECTO", schema = "PUBLIC", catalog = "TESIS")
public class Proyecto implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private String name;
	private String path;
	private Set<File> files = new HashSet<File>(0);
	private Set<Scene> scenes = new HashSet<Scene>(0);

	// Constructors

	/** default constructor */
	public Proyecto() {
	}
	public Proyecto(String path) {
		this.path = path;
	}

	/** minimal constructor */
	public Proyecto(Users users, String name, String path) {
		this.users = users;
		this.name = name;
		this.path = path;
	}

	/** full constructor */
	public Proyecto(Users users, String name, String path, Set<File> files,
			Set<Scene> scenes) {
		this.users = users;
		this.name = name;
		this.path = path;
		this.files = files;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERID", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "NAME", nullable = false, length = 25)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PATH", nullable = false, length = 25)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<File> getFiles() {
		return this.files;
	}

	public void setFiles(Set<File> files) {
		this.files = files;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "proyecto")
	public Set<Scene> getScenes() {
		return this.scenes;
	}
	
	@Transient
	public int getScenesSize(){
		
		return this.scenes.size();
		
	}

	public void setScenes(Set<Scene> scenes) {
		this.scenes = scenes;
	}

}