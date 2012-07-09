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
import org.icefaces.ace.component.fileentry.FileEntryResults.FileInfo;

/**
 * File entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FILE", schema = "PUBLIC", catalog = "TESIS")
public class File implements java.io.Serializable {

	// Fields

	private Integer id;
	private Scene scene;
	private Proyecto proyecto;
	private String name;
	private String path;

	// Constructors

	/** default constructor */
	public File() {
	}
	
	public File(FileInfo info){
		
		this.name=info.getFileName();
		this.path = info.getFile().getAbsolutePath();	
		
	}
	public File(String name,Proyecto proyecto) {
		this.name = name;
		this.proyecto = proyecto;
	}
	public File(String name,Scene scene) {
		this.name = name;
		this.scene = scene;
	}

	/** minimal constructor */
	public File(String name, String path) {
		this.name = name;
		this.path = path;
	}

	/** full constructor */
	public File(Scene scene, Proyecto proyecto, String name, String path) {
		this.scene = scene;
		this.proyecto = proyecto;
		this.name = name;
		this.path = path;
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
	@JoinColumn(name = "SCENEID")
	public Scene getScene() {
		return this.scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECTID")
	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	@Column(name = "NAME", nullable = false, length = 25)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PATH", nullable = false, length = 100)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}