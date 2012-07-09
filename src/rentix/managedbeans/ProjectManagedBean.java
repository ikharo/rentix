package rentix.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;
import org.icefaces.ace.component.fileentry.FileEntryResults.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;

import com.icesoft.faces.component.ext.HtmlDataTable;
import com.rentix.beans.File;
import com.rentix.beans.Proyecto;
import com.rentix.beans.Resolution;
import com.rentix.beans.Scene;
import com.rentix.dao.ProyectoDAO;
import com.rentix.dao.ResolutionDAO;
import com.rentix.service.ServiceProjectImpl;
import com.rentix.utils.SceneVO;
import com.rentix.utils.UnZip2;
import com.rentix.utils.ZipTmp;

@ManagedBean(name="projectMB")
@SessionScoped
public class ProjectManagedBean implements Serializable {

	private Proyecto proyecto;
	private boolean renderTempScenes;
	private  ArrayList<SceneVO>tempScenes;
	private ArrayList<ZipTmp> iss;
	private HtmlDataTable scenesDT; 
	

	@ManagedProperty(value = "#{ResolutionDAO}")
	private ResolutionDAO resolutionDAO;
	

	@ManagedProperty(value = "#{ProjectService}")
	private ServiceProjectImpl projectService;
	

	public ProjectManagedBean() {
		
	}
	
	@PostConstruct
	public void initComponents(){
		renderTempScenes = false;
		proyecto   = new Proyecto("vacio");
		tempScenes = new ArrayList<SceneVO>();
	}
	
	
	
	public boolean isRenderTempScenes() {
		return renderTempScenes;
	}

	public void setRenderTempScenes(boolean renderTempScenes) {
		this.renderTempScenes = renderTempScenes;
	}

	public ArrayList<SceneVO> getTempScenes() {
		return tempScenes;
	}

	public void setTempScenes(ArrayList<SceneVO> tempScenes) {
		this.tempScenes = tempScenes;
	}

	public void ejemplo(ActionEvent evt){
		
		System.out.println("Sample");
		
	}
	
	public void saveProject(ActionEvent evt){
		
		projectService.createProject(this.proyecto, tempScenes,iss);

		
	}
	
	 public void fileListener(FileEntryEvent e) {
		 System.out.println("Subiendo Archivo");
		 SceneVO sceneVo = null;
		 File file = null;
		 
		 FileEntry fe = (FileEntry)e.getComponent();
	     FileEntryResults results = fe.getResults();
	     
	     
	     System.out.println("File to unzip: " + results.getFiles().get(0).getFile().getPath());
	     iss = UnZip2.unzipMemory(results.getFiles().get(0).getFile().getPath());
	     
	     for (ZipTmp tmp : iss){
	    	 if (tmp.getName().toLowerCase().contains(".obj") ){
	    		 System.out.println("Escena: "+ tmp.getName() );
	    		 sceneVo = new SceneVO();
	    		 
	    		 //sceneVo.setCameraPos("poscam");
	    		 //sceneVo.setName("Name");
	    		 
	    		 sceneVo.setFileName(tmp.getName());
	    		 //sceneVo.getFiles().add(new File(tmp.getName(),scene));
	    		 sceneVo.setResolution(-1);
	    		 //sceneVo.setProyecto(proyecto);
	    		 tempScenes.add(sceneVo);
	    		 
	    	 }else{
	    		 System.out.println("Archivo: "+ tmp.getName() );
	    		 proyecto.getFiles().add(new File(tmp.getName(),proyecto));
	    	 }
	     }
	     
	    
	     /*
	     for (FileInfo info : results.getFiles()){
	    	 
	    	 file = new File(info);
	    	 
	    	 if (!info.getContentType().equals("application/octet-stream")){
	    		 proyecto.getFiles().add(file);
	    	 }else{
	    		 scene = new Scene();
	    		 scene.setCameraPos("poscam");
	    		 scene.setName("Name");
	    		 scene.getFiles().add(file);
	    		 tempScenes.add(scene);
	    	 } 
	     }*/
	     
	     renderTempScenes = true;
		 
	 }
	
	public List<Resolution> getAllResolutions(){
		
		
		List<Resolution> resolutions= resolutionDAO.findAll();
		return resolutions;
		
	}
	 
	public com.rentix.beans.Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(com.rentix.beans.Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	
	public ResolutionDAO getResolutionDAO() {
		return resolutionDAO;
	}

	public void setResolutionDAO(ResolutionDAO resolutionDAO) {
		this.resolutionDAO = resolutionDAO;
	}
	public ServiceProjectImpl getProjectService() {
		return projectService;
	}

	public void setProjectService(ServiceProjectImpl projectService) {
		this.projectService = projectService;
	}

	public HtmlDataTable getScenesDT() {
		return scenesDT;
	}

	public void setScenesDT(HtmlDataTable scenesDT) {
		this.scenesDT = scenesDT;
	}

}
