package com.rentix.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.rentix.beans.Proyecto;
import com.rentix.beans.Scene;
import com.rentix.beans.Users;
import com.rentix.dao.ProyectoDAO;
import com.rentix.dao.UsersDAO;
import com.rentix.utils.SceneVO;
import com.rentix.utils.ZipTmp;

public class ServiceProjectImpl {
	
	private ProyectoDAO proyectoDAO;
	private UsersDAO userDAO;
	
	String filePath="/home/lucy/s";
	
	public void createProject(Proyecto project, ArrayList<SceneVO> escenas,ArrayList<ZipTmp> iss){
		Scene escenaBean;
		com.rentix.beans.File file;
		String userPath;
		String projectPath;
		String projectFiles;
		String ImagePath;
		
		try{
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			
			
			 User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			project.setUsers(userDAO.findByUsername(user.getUsername()).get(0));
			
			userPath = filePath + "/" + project.getUsers().getUsername();
			projectPath = userPath +"/" + project.getName();
			projectFiles = projectPath + "/projectFiles";
			ImagePath  = projectPath + "/images";
			
			session.setAttribute("filePath", filePath);
			session.setAttribute("userPath", userPath);
			session.setAttribute("projectPath", projectPath);
			session.setAttribute("projectFiles", projectFiles);
			session.setAttribute("ImagePath", ImagePath);
			
			
			
			project.setPath(projectPath);
			
			//Carpeta de usuario
			if(!verifyFolder(userPath)){
				createFolder(userPath);				
			}
			//Carpeta proyecto
			if(!verifyFolder(projectPath)){
				createFolder(projectPath);				
			}
			// carpeta ProjectFiles
			if(!verifyFolder(projectFiles)){
				createFolder(projectFiles);				
			}
			// carpeta images
			if(!verifyFolder(ImagePath)){
				createFolder(ImagePath);				
			}
			
			for (ZipTmp zFile : iss){
				for ( Object obj :  project.getFiles().toArray()){
					
					file = (com.rentix.beans.File) obj;
					if (file.getName().equals(zFile.getName()) && !file.getName().toLowerCase().contains(".obj")  ){
						createFile(zFile, projectFiles);
						file.setPath(projectFiles + "/" + zFile.getName());	
					}
				}
				
				for (SceneVO escena: escenas){
					if (escena.getFileName().equals(zFile.getName())){
						
						createFile(zFile, projectFiles);
						
						file = new com.rentix.beans.File(zFile.getName(),projectFiles + "/" + zFile.getName());
						
						escenaBean = new Scene(escena,project,file);
						
						project.getScenes().add(escenaBean);
						
					}
				}
				
			}
			
			proyectoDAO.save(project);
			

		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	public void createFile(ZipTmp iss, String path){
		
		  
		   try{
			   FileOutputStream fos=null;
			   BufferedOutputStream dest = null;
			   
				   int count;
		           byte data[] = new byte[2048];
		           
		           fos = new FileOutputStream(new File( path + "/" + iss.getName()));
		           dest = new BufferedOutputStream(fos, 2048);
		           
		           while ((count = iss.getIs().read(data, 0, 2048)) != -1) {
		              dest.write(data, 0, count);
		           }

		           dest.flush();
		           dest.close();
		           iss.getIs().close();
			   
		   }catch(Exception ex){
			   ex.printStackTrace();
		   }
		
	}
	
	public boolean createFolder(String path){
		File f = null;
		boolean status=false;
		f = new File(path);
		status = f.mkdir();
		return status;
	}
	
	public boolean verifyFolder(String path){
	
		File f = null;
		f = new File(path);
		return f.exists();
		
	}
	
	public void verifyPath(){
		
		
	}
	

	public ProyectoDAO getProyectoDAO() {
		return proyectoDAO;
	}

	public void setProyectoDAO(ProyectoDAO proyectoDAO) {
		this.proyectoDAO = proyectoDAO;
	}

	public UsersDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UsersDAO userDAO) {
		this.userDAO = userDAO;
	}

}
