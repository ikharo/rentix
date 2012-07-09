package rentix.managedbeans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.icesoft.faces.component.ext.HtmlDataTable;
import com.rentix.beans.Proyecto;
import com.rentix.beans.Scene;
import com.rentix.beans.Users;
import com.rentix.dao.ProyectoDAO;
import com.rentix.dao.UsersDAO;
import com.rentix.utils.SceneVO;

@ManagedBean(name="historyMB")
@RequestScoped
public class HistoryManagedBean {
	private List<Proyecto> proyectos;
    private HtmlDataTable dt;
    
    @ManagedProperty(value = "#{ProyectoDAO}")
    private ProyectoDAO projectDAO;
    
    @ManagedProperty(value = "#{UsersDAO}")
    private UsersDAO userDAO;
    
	@PostConstruct
	public void initComponents(){
		

	}

	public List<Proyecto> getAllProjects(){
		
		 User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
		 Users actualUser = userDAO.findByUsername(user.getUsername()).get(0);
		 
		 proyectos=  projectDAO.findAllbyUser(actualUser);
		 
		 
		 
		
		return proyectos;
	}
	
	public void exec(ActionEvent evt){
		

		String comando = null;
		String s = null;
		Process p;
		
		Proyecto item = (Proyecto) getDt().getRowData();
		System.out.println("Enviando a ejecutar " + item.getId());
		//Poner a todas las escenas en espera
		
		
		
		ArrayList<String> executions = generateExcecutions(item);
		
		
		
		
		try {
			
			
			for (String execution: executions){
				
				System.out.println("Ejecutando:\n");
				System.out.println(execution);
				
				 p = Runtime.getRuntime().exec(execution);
				 
				BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
				BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
				
				// Leemos la salida del comando
	            System.out.println("Ésta es la salida standard del comando:\n");
	            while ((s = stdInput.readLine()) != null) {
	                    System.out.println(s);
	            }
	
	            // Leemos los errores si los hubiera
	            System.out.println("Ésta es la salida standard de error del comando (si la hay):\n");
	            while ((s = stdError.readLine()) != null) {
	                    System.out.println(s);
	            }
            
			}

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	public ArrayList<String> generateExcecutions(Proyecto item){
		
		ArrayList<String> executions = new ArrayList<String>();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		
		for (Scene escena : item.getScenes()){
			
			executions.add(generatePath(escena,(String)session.getAttribute("ImagePath")));
		}
		
		return executions;
		
	}
	
	
	public String generatePath(Scene scene,String imagePath){
		
		 
		
		StringBuffer buffer = new StringBuffer("/home/lucy/optix/Tesis/build/bin/new_render -o ");
		buffer.append(scene.getFirstFile().getPath() + " -BB -s=\"");
		buffer.append(imagePath + "/"+scene.getName() + "\" ");
		
		buffer.append(scene.getCameraPos().isEmpty() ? "" : "-p \"" + scene.getCameraPos()+ "\" ");
		
		if(scene.getResolution().getResolution().equals("HFD")){
			buffer.append(   "-" + scene.getResolution().getResolution());
		}
		return buffer.toString();
	}


	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public ProyectoDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(ProyectoDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	public UsersDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UsersDAO userDAO) {
		this.userDAO = userDAO;
	}

	public HtmlDataTable getDt() {
		return dt;
	}


	public void setDt(HtmlDataTable dt) {
		this.dt = dt;
	}
	
	
}
