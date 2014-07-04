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
public class ResourceExecutor {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	public final static String LINE = "-------------------------------------------------------------------------------------";
	public final static String START = "[";
	public final static String END = "]";
	public final static String PROP_EXT = ".properties";
	
	public final static String TARGET_RESOURCE_NAME = "Cygnus_Resource";
	
	public final static Locale DEFAULT_LOCALE = Locale.ENGLISH;
	
	public List<File> properties = null;
	public String[] excepts = { 
				"log4j", "config", "cache", "test-database", "sample-config", "gradle-wrapper", "quartz"
	};
	
	public String[] exceptsFolders = { 
			".git", ".gradle", ".settings", "build", "gradle"
	};
	
	public ResourceExecutor() {
		this.properties = new ArrayList<File>();
	}
	
	private String getPureFileName(String fileFullName){
		fileFullName = fileFullName.substring(0,fileFullName.lastIndexOf(PROP_EXT));
		//순수 파일 명으로 만들기 (2) ex) sample-config_ko -> sample-config
		if(fileFullName.indexOf("_")!=-1){
			fileFullName = fileFullName.substring(0,fileFullName.lastIndexOf("_"));
		}
		return fileFullName;
	}
	public boolean isFiltered(String fileName){
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
	 * 만약 영어일 경우, *.
	 * @param locale
	 * @param fileName
	 * @return
	 */
	public boolean isProperties(Locale locale, String fileName){
		if(locale.equals(DEFAULT_LOCALE)){//Default locale 일 경우, "_언어.properties" 파일 제외.
			return fileName.endsWith(PROP_EXT) && fileName.indexOf("_")==-1;
		}
		//그외의 경우, "_언어.properties" 파일 검색
		return fileName.endsWith("_"+locale.toString()+PROP_EXT);
	}
	
	/**
	 * 
	 * @param searchFile
	 * @param language
	 */
	public void findProperties(File f, Locale locale){
		if(f.isDirectory() && !f.isHidden() && isFilteredFolders(f.getName())){
			File[] childrens = f.listFiles();
			for(File file : childrens){
				boolean isProperties = isProperties(locale, file.getName());
				//boolean isTml = file.getName().endsWith(".tml");
				if(file.isDirectory()){
					findProperties(file, locale);
				}else if(isProperties){
					String fileName = getPureFileName(file.getName());
					if(isFiltered(fileName))
						properties.add(file);
				}
			}//end for
		}
	}
	
	private void intergrationFile(String filePath, Locale locale) throws IOException{
		BufferedWriter bw = null;
		BufferedReader br = null;
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
				propName = getPureFileName(prop.getName());
				
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
	
	public void importResourceFile(String resourceFilePath, Locale locale) throws IOException{
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
					if(DEFAULT_LOCALE.equals(locale)){
						propFileName = propFileName +PROP_EXT;
					}else{
						propFileName = propFileName +"_"+locale.toString()+PROP_EXT;
					}
					
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
	
	
	private void tempDelete(){
		for(File file : properties){
			File temp = new File(file.getParentFile().getAbsolutePath()+File.separator+file.getName());
			boolean result = temp.delete();
			log.debug("Deleted("+result+") : "+temp.getAbsolutePath());
		}
	}
	
	/**
	 *  List<File>을 Static으로 처리하니 삭제가 안되서, 객체 생성방식으로 하니 삭제가 됨.
	 *  DEFAULT_LOCALE 을 지정할 경우, 해당 locale은 default Properties ( "_Locale명"이 붙지 않음)
	 *  
	 * 실행 방법
	 *  1) Export
	 *   //해당 locale Properties 파일을 검색
	 *   executor.findProperties(root, locale);
	 *    
	 *   //해당 locale Properties파일을 하나로 통합.
	 *   executor.intergrationFile(rootPath, locale);
	 *   
	 *  2) Import
	 *   //통합된 Properties파일을 각각의 경로로 분할 시켜줌.
	 *   executor.importResourceFile(rootPath, locale); 
	 *   
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException{
		String rootPath = "c:"+File.separator+"git"+File.separator+"cygnus";//+File.separator+"cygnus-core";
		Locale locale = Locale.KOREAN;
		
		ResourceExecutor executor = new ResourceExecutor();
		File root = new File(rootPath);
		
		//Export
		executor.findProperties(root, locale);
		executor.intergrationFile(rootPath, locale);
		
		//Import
		//executor.importResourceFile(rootPath, locale);
	}
	
	
}

