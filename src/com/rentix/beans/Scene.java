package com.rentix.beans;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
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

import com.rentix.utils.SceneVO;

/**
 * Scene entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SCENE", schema = "PUBLIC", catalog = "TESIS")
public class Scene implements java.io.Serializable {

	// Fields

	private Integer id;
	private Acceleration acceleration;
	private Status status;
	private Proyecto proyecto;
	private Resolution resolution;
	private String name;
	private Integer numVertices;
	private Integer numNormals;
	private Integer numTexcoords;
	private Integer numFacetnorms;
	private Integer numTriangles;
	private Integer numMaterials;
	private Integer numGroups;
	private Double renderEstTime;
	private BigDecimal renderTime;
	private BigDecimal renderEstMem;
	private BigDecimal renderMem;
	private String cameraPos;
	private Set<File> files = new HashSet<File>(0);
	private Set<Obj> objs = new HashSet<Obj>(0);

	// Constructors

	/** default constructor */
	public Scene() {
	}
	
	public Scene(SceneVO escenaVO,Proyecto proyecto,File file){
		
		this.proyecto = proyecto;
		this.cameraPos = escenaVO.getCameraPos();
		
		file.setScene(this);
		this.getFiles().add(file);
		
		this.name = escenaVO.getName();
		this.resolution = new Resolution(escenaVO.getResolution());
		
	}

	/** minimal constructor */
	public Scene(Proyecto proyecto, String name) {
		this.proyecto = proyecto;
		this.name = name;
	}

	/** full constructor */
	public Scene(Acceleration acceleration, Status status, Proyecto proyecto,
			Resolution resolution, String name, Integer numVertices,
			Integer numNormals, Integer numTexcoords, Integer numFacetnorms,
			Integer numTriangles, Integer numMaterials, Integer numGroups,
			Double renderEstTime, BigDecimal renderTime,
			BigDecimal renderEstMem, BigDecimal renderMem, String cameraPos,
			Set<File> files, Set<Obj> objs) {
		this.acceleration = acceleration;
		this.status = status;
		this.proyecto = proyecto;
		this.resolution = resolution;
		this.name = name;
		this.numVertices = numVertices;
		this.numNormals = numNormals;
		this.numTexcoords = numTexcoords;
		this.numFacetnorms = numFacetnorms;
		this.numTriangles = numTriangles;
		this.numMaterials = numMaterials;
		this.numGroups = numGroups;
		this.renderEstTime = renderEstTime;
		this.renderTime = renderTime;
		this.renderEstMem = renderEstMem;
		this.renderMem = renderMem;
		this.cameraPos = cameraPos;
		this.files = files;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCEL_TYPE")
	public Acceleration getAcceleration() {
		return this.acceleration;
	}

	public void setAcceleration(Acceleration acceleration) {
		this.acceleration = acceleration;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STATUS")
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECTID", nullable = false)
	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RESOLUTION")
	public Resolution getResolution() {
		return this.resolution;
	}

	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}

	@Column(name = "NAME", nullable = false, length = 25)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NUM_VERTICES")
	public Integer getNumVertices() {
		return this.numVertices;
	}

	public void setNumVertices(Integer numVertices) {
		this.numVertices = numVertices;
	}

	@Column(name = "NUM_NORMALS")
	public Integer getNumNormals() {
		return this.numNormals;
	}

	public void setNumNormals(Integer numNormals) {
		this.numNormals = numNormals;
	}

	@Column(name = "NUM_TEXCOORDS")
	public Integer getNumTexcoords() {
		return this.numTexcoords;
	}

	public void setNumTexcoords(Integer numTexcoords) {
		this.numTexcoords = numTexcoords;
	}

	@Column(name = "NUM_FACETNORMS")
	public Integer getNumFacetnorms() {
		return this.numFacetnorms;
	}

	public void setNumFacetnorms(Integer numFacetnorms) {
		this.numFacetnorms = numFacetnorms;
	}

	@Column(name = "NUM_TRIANGLES")
	public Integer getNumTriangles() {
		return this.numTriangles;
	}

	public void setNumTriangles(Integer numTriangles) {
		this.numTriangles = numTriangles;
	}

	@Column(name = "NUM_MATERIALS")
	public Integer getNumMaterials() {
		return this.numMaterials;
	}

	public void setNumMaterials(Integer numMaterials) {
		this.numMaterials = numMaterials;
	}

	@Column(name = "NUM_GROUPS")
	public Integer getNumGroups() {
		return this.numGroups;
	}

	public void setNumGroups(Integer numGroups) {
		this.numGroups = numGroups;
	}

	@Column(name = "RENDER_EST_TIME", precision = 65535, scale = 32767)
	public Double getRenderEstTime() {
		return this.renderEstTime;
	}

	public void setRenderEstTime(Double renderEstTime) {
		this.renderEstTime = renderEstTime;
	}

	@Column(name = "RENDER_TIME", precision = 25, scale = 0)
	public BigDecimal getRenderTime() {
		return this.renderTime;
	}

	public void setRenderTime(BigDecimal renderTime) {
		this.renderTime = renderTime;
	}

	@Column(name = "RENDER_EST_MEM", precision = 25, scale = 0)
	public BigDecimal getRenderEstMem() {
		return this.renderEstMem;
	}

	public void setRenderEstMem(BigDecimal renderEstMem) {
		this.renderEstMem = renderEstMem;
	}

	@Column(name = "RENDER_MEM", precision = 25, scale = 0)
	public BigDecimal getRenderMem() {
		return this.renderMem;
	}

	public void setRenderMem(BigDecimal renderMem) {
		this.renderMem = renderMem;
	}

	@Column(name = "CAMERA_POS", length = 25)
	public String getCameraPos() {
		return this.cameraPos;
	}

	public void setCameraPos(String cameraPos) {
		this.cameraPos = cameraPos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "scene")
	public Set<File> getFiles() {
		return this.files;
	}

	public void setFiles(Set<File> files) {
		this.files = files;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "scene")
	public Set<Obj> getObjs() {
		return this.objs;
	}

	public void setObjs(Set<Obj> objs) {
		this.objs = objs;
	}
	
	@Transient
	public File getFirstFile(){
		
		File file = null;
		
		Iterator<File> it = this.files.iterator();
		
		
		if (it!=null && it.hasNext()) file = it.next();
		
	
		return file;
	}

	
	

}