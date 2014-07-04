package org.hejin.newapp.cmm.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 영어 - Locale.ENGLISH
 * 한국어 - Locale.KOREAN
 * 
 * 
 * @author hejin-com
 *
 */
public class FileSearch {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	public final static String LINE = "-------------------------------------------------------------------------------------";
	public final static String START = "[";
	public final static String END = "]";
	public final static String PROP_EXT = ".properties";
	
	public final static String TARGET_RESOURCE_NAME = "Cygnus_Resource";
	
	public static List<File> properties = new ArrayList<File>();
	public String[] excepts = { 
				"log4j", "config", "cache", "test-database", "sample-config", "gradle-wrapper", "quartz"
	};
	
	public String[] exceptsFolders = { 
			".git", ".gradle", ".settings", "build", "gradle"
	};
	
	public boolean isFiltered(String fileName){
		//순수 파일 명으로 만들기 (1).
		fileName = fileName.substring(0,fileName.lastIndexOf(PROP_EXT));
		
		//순수 파일 명으로 만들기 (2) ex) sample-config_ko -> sample-config
		if(fileName.indexOf("_")!=-1){
			fileName = fileName.substring(0,fileName.lastIndexOf("_"));
		}
		for(String filter : excepts){
			if(fileName.equalsIgnoreCase(filter))
				return false;
		}
		return true;
	}
	
	public boolean isFilteredFolders(String fileName){
		for(String filter : exceptsFolders){
			if(fileName.equalsIgnoreCase(filter))
				return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param searchFile
	 * @param language
	 */
	public void findProperties(File f, Locale locale){
		
		String searchProperties = PROP_EXT;
		if(locale!=null)//특정 Locale일 경우.
			searchProperties = "_"+locale.toString()+PROP_EXT;
		
		if(f.isDirectory() && !f.isHidden() && isFilteredFolders(f.getName())){
			File[] childrens = f.listFiles();
			for(File file : childrens){
				boolean isProperties = file.getName().endsWith(searchProperties);
				//boolean isTml = file.getName().endsWith(".tml");
				if(file.isDirectory()){
					findProperties(file, locale);
				}else if(isProperties){
					String fileName = file.getName();
					if(isFiltered(fileName))
						properties.add(file);
				}
			}//end for
		}
	}
	
	private void intergrationFile(String filePath, Locale locale) throws IOException{
		BufferedWriter bw = null;
		BufferedReader br = null;
		String propertiesExt = PROP_EXT;
		String tagetName = TARGET_RESOURCE_NAME;
		if(locale!=null){
			tagetName = TARGET_RESOURCE_NAME+"_"+locale.toString();
		}
		try{
			File propWriter = new File(filePath+File.separator+tagetName);
			if(!propWriter.exists())
				propWriter.createNewFile();
				
			bw = new BufferedWriter(new FileWriter(propWriter));
			for(File prop : properties){
				String propPath = prop.getParentFile().getAbsolutePath();
				String propName = prop.getName();
				//Properties 이름만 가져오기. 확장자 및 locale 제거.
				propName = propName.substring(0, propName.lastIndexOf(propertiesExt));
				
				//Develop Version. 만약 "_Locale"이 붙은거면 하위도 제거
				if(propName.indexOf("_")!=-1){
					propName = propName.substring(0,propName.lastIndexOf("_"));
				}
				
				bw.write(START); bw.newLine();
				bw.write(propPath); bw.newLine();
				bw.write(propName);bw.newLine();
				bw.newLine();
				bw.flush();
				br = new BufferedReader(new FileReader(prop));
				String line = br.readLine();
				while(line !=null){
					bw.write(line);
					if(!"".equals(line))
						bw.newLine();
					line = br.readLine();
				}
				bw.write(END); bw.newLine();
				bw.flush();
			}
		}catch(FileNotFoundException ex){
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bw!=null)
				bw.close();
		}
	}
	
	@Test
	public void fileSearch() throws IOException{
		String rootPath = "c:"+File.separator+"git"+File.separator+"cygnus";
		
		//log.debug(Locale.CHINESE.toString());
		File root = new File(rootPath);
		//1. Properties Search.
		//2. write one Properties.
		findProperties(root, Locale.ENGLISH);
		intergrationFile(rootPath, Locale.ENGLISH);
		//log.debug("Search Properties Files : "+properties.size());
		
		//writeFile(filePath, null);
		//log.debug("finished One Properties Files - "+filePath);
		
		//1. read one Properties.
		//2. write Properties in Individual Path.
		//parseResourceFile(rootPath, Locale.ENGLISH);
		
		//log.debug("finished Parse One Properties Files - "+filePath);
	}
	
	public void parseResourceFile(String resourceFilePath, Locale locale) throws IOException{
		BufferedWriter bw = null;
		BufferedReader br = null;
		String resourceName = TARGET_RESOURCE_NAME+"_"+locale.toString();
		try{
			br = new BufferedReader(new FileReader(new File(resourceFilePath+File.separator+resourceName)));
			//bw = new BufferedWriter(new FileWriter(new File(filePath2)));
			
			String line = br.readLine();
			while(line!=null){
				if(START.equalsIgnoreCase(line)){
					String individualFilePath = br.readLine();	//path
					String propFileName = br.readLine();	//fileName
					if(locale!=null)
						propFileName = propFileName +"_"+locale.toString()+PROP_EXT;
					else
						propFileName = propFileName +PROP_EXT;
					
					log.debug("individualFilePath : "+individualFilePath);
					log.debug("propFileName : "+propFileName);
					
					File propFile = new File(individualFilePath+File.separator+propFileName);
					if(!propFile.exists()){
						propFile.createNewFile();
					}
					bw = new BufferedWriter(new FileWriter(propFile));
					
					log.debug("value : ");
					line = br.readLine();
					while(!END.equalsIgnoreCase(line)){
						log.debug(line);
						bw.write(line); 
						if(!"".equals(line))
							bw.newLine();
						line = br.readLine();
					}
					bw.flush();
					if(bw!=null)
						bw.close();
					log.debug(LINE);
				}
				line = br.readLine();
			}
		}catch(FileNotFoundException ex){
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(br!=null)
				br.close();
			if(bw!=null)
				bw.close();
		}
	}
}
