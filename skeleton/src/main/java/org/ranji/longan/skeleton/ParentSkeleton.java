package org.ranji.longan.skeleton;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

/**
 * 创建父骨架的工具类
 * @author RanJi
 */
public class ParentSkeleton {
	/**
	 * 该方法将创建项目的根目录结构及相关的文件
	 * @param projectName 该项目名称为项目的全名称，必须为com.company.projectname的形式进行传递
	 * @param targetDir 该项目要放置的位置
	 */
	public static void createParentSkeleton(String projectName,String targetDir){
		//-- 1. 健壮性处理
		if(projectName==null || "".equals(projectName)||targetDir==null||"".equals(targetDir))return;
		String[] s = projectName.trim().split("\\.");
		if(s==null || s.length != 3)return;
		
		//-- 2. targetDir目录作为项目根目录的父目录,根据该目录创建项目目录
		File parentDir = new File(targetDir);
		File projectDir = new File(parentDir,s[2]);
		projectDir.mkdirs();
		
		//-- 3. 项目目录的根下建立.config配置文件
		createConfigFile(projectName, projectDir);
		
		//-- 4. 项目目录的根下建立.gitignore文件
		createGitIgnoreFile(projectDir);
		
		//-- 5. 项目目录的根下建立LICENSE.txt文件
		createLicenseFile(projectDir);
		
		//-- 6. 项目目录的根下建立README.md文件
		createReadMeFile(projectDir);
	}

	/**
	 * 在父骨架目录下生成.config文件
	 * @param projectName	项目全名称
	 * @param targetDir 项目所在目录的父目录
	 */
	private static void createConfigFile(String projectName,File projectDir){
		File f = new File(projectDir,".config");
		try {
			//-- 配置文件的内容暂时只有projectname=xxx.xxx.xxx
			FileUtils.writeStringToFile(f, "projectname="+projectName.trim());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 在项目目录下生成.gitignore文件
	 * @param projectDir 项目的根目录
	 */
	private static void createGitIgnoreFile(File projectDir){
		File gitignoreFile = new File(projectDir,".gitignore");
		createFileByTemplate("parent/gitignore.rj", gitignoreFile);
	}
	
	private static void createLicenseFile(File projectDir){
		File licenseFile = new File(projectDir,"LICENSE.txt");
		createFileByTemplate("parent/license.rj", licenseFile);
	}
	
	private static void createReadMeFile(File projectDir){
		File readmeFile = new File(projectDir,"README.md");
		createFileByTemplate("parent/readme.rj", readmeFile);
	}
	
	
	/**
	 * 根据加载模板的内容，创建目标文件
	 * @param templateFilePath 类加载的相对位置即可
	 * @param destFilePath
	 */
   private static void createFileByTemplate(String templateFilePath,File destFile){ 
	   URL url = ParentSkeleton.class.getClassLoader().getResource(templateFilePath);
		try {
			FileUtils.copyURLToFile(url, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
   }
  
   
	public static void main(String[] args) {
		createParentSkeleton("org.ranji.oa", "D:");
	}
}
