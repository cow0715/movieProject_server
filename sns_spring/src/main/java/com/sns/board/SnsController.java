package com.sns.board;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.dto.ResponseDTO;



@RestController
@RequestMapping("/api/sns")
public class SnsController {
	
	final SnsDAO dao; 
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${file.imgPath}")
	String restDir; 
	
	@Autowired
	public SnsController(SnsDAO dao) {
		this.dao = dao;

	}
	
	@GetMapping
	public ResponseDTO<Sns> listSns( ){
		ResponseDTO<Sns> responseDTO = new ResponseDTO<Sns>();
		 
		List<Sns> list = null; 
		try {
			logger.info("listSns");
			
			list = dao.getAllPost();
			logger.info(list.toString());
		}catch(Exception e) {
			e.printStackTrace();
			logger.warn("오류");
		}
		
		
		responseDTO.setResultCode("0000");
		responseDTO.setResultMsg("정상");
		responseDTO.setResultData(list);
		return responseDTO;
	}
	
	@GetMapping("/v2")
	public ResponseDTO<Sns2> listSnsV2( ){
		ResponseDTO<Sns2> responseDTO = new ResponseDTO<Sns2>();
		 
		List<Sns2> list = null; 
		try {
			logger.info("listSns");
			
			list = dao.getAllPostV2();
			logger.info(list.toString());
		}catch(Exception e) {
			e.printStackTrace();
			logger.warn("오류");
		}
		
		
		responseDTO.setResultCode("0000");
		responseDTO.setResultMsg("정상");
		responseDTO.setResultData(list);
		return responseDTO;
	}
	
	
	@GetMapping("{sid}")
	public ResponseDTO<Sns> getSns(@PathVariable int sid) {
		
		ResponseDTO<Sns> responseDTO = new ResponseDTO<Sns>();
		 
		List<Sns> list =  new ArrayList<Sns>();
		Sns sns = null; 
		try {
			sns = dao.getPost(sid);
			list.add(sns);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		responseDTO.setResultCode("0000");
		responseDTO.setResultMsg("정상");
		responseDTO.setResultData(list);
		return responseDTO;
	}

	@DeleteMapping("{sid}")
	public ResponseDTO<Sns> deleteSns(@PathVariable int sid){
		
		ResponseDTO<Sns> responseDTO = new ResponseDTO<Sns>();
		String resultMsg = "정상";
		String resultCode ="0000";
		try {
			dao.delPost(sid);
		}catch(Exception e) {
			e.printStackTrace();
			resultMsg = e.getLocalizedMessage(); 
			resultCode = "1111";
		}
		
		responseDTO.setResultCode(resultCode);
		responseDTO.setResultMsg(resultMsg);
		
		return responseDTO;
	}
	
	
	@PatchMapping("{id}")
	public ResponseDTO<Sns>  updateSns(@PathVariable int id, @RequestBody Sns sns) {
		ResponseDTO<Sns> responseDTO = new ResponseDTO<Sns>();
		String resultMsg = "정상";
		String resultCode ="0000";
		
		try {
			dao.updatePost(sns, id);
		}catch(Exception e) {
			e.printStackTrace();
			resultMsg = e.getLocalizedMessage(); 
			resultCode = "1111";
		}
		
		responseDTO.setResultCode(resultCode);
		responseDTO.setResultMsg(resultMsg);
		
		return responseDTO;
	}
	
	@PostMapping 
	public ResponseDTO<Sns> addSns(@RequestBody Sns sns) {
		ResponseDTO<Sns> responseDTO = new ResponseDTO<Sns>();
		String resultMsg = "정상";
		String resultCode ="0000";
		
		try {
			dao.addPost(sns);
			
		}catch(Exception e) {
			e.printStackTrace();
			
			resultMsg = e.getLocalizedMessage(); 
			resultCode = "1111";
		}
		
		responseDTO.setResultCode(resultCode);
		responseDTO.setResultMsg(resultMsg);
		
		
		return responseDTO;
	}
	
	@PostMapping("/m")
	public String addSnsWithFile(@ModelAttribute Sns sns, ArrayList<MultipartFile> files) {
		logger.info(restDir);
		String fileName = "";
//		sns title=mapping content=mapping  img = fcode/      // files > 3장
		try {
			//파일들 고유코드 생성
			String fcode = UUID.randomUUID().toString(); 
			
			//sns 등록
			sns.setImg(fcode);
			Long sid = dao.addPost(sns);
			
			//----------------------------------------------
			//경로 생성 (디렉토리 생성)
			File dir = new File(restDir +"/"+fcode );
			dir.mkdir(); //생성
			
			int i = 0;
			for(MultipartFile file : files) {
				i++;
				fileName = fcode+"_"+ Long.toString(i)+"_"+sid+"."+FilenameUtils.getExtension(file.getOriginalFilename());
				
				logger.info(fileName);
				
				File dest = new File(restDir+"/"+fcode+"/"+fileName);
				file.transferTo(dest);
				
				dao.addFile(fileName, fcode);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return "1111";
		}
		
		return "0000";
	}
	
	@PostMapping("/m/upload2")
	public ResponseDTO<Object> uploadimages (@RequestParam("files") ArrayList<MultipartFile> files)  {
		logger.info("uploadImg222 등록");
		String filename = "";
		String result = "";
		
		try {

			int i=0;
			//업로드한 파일들 저장 
			for(MultipartFile file : files) {
				logger.info("1." + file.getName());
				logger.info("2." + file.getOriginalFilename());
				
				filename = file.getOriginalFilename();
				String fileNamePath[] = filename.split("_");
				
				if (i == 0) {
					//경로 생성 
					logger.info("경로 생성"+fileNamePath[0]);
				
					File dir = new File(restDir+"/"+fileNamePath[0]); // 111
					dir.mkdir(); // 디렉토리 생성 
				}
			
				
				filename = fileNamePath[0]+"_" + Long.toString(i)+"."+FilenameUtils.getExtension(file.getOriginalFilename());  
				
				logger.info(filename); //111_0.png
				// 저장 파일 객체 생성
				File dest = new File(restDir+"/"+fileNamePath[0]+"/"+filename); 
			
				logger.info(dest.getName());
				
				// 파일 저장
				file.transferTo(dest);
				
				dao.addFile(filename, fileNamePath[0]);
				i++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("addPost 오류");
			result =  "addPost 오류 ";
		}
	
	
		ResponseDTO<Object> responseDTO = new ResponseDTO<Object>();

		
		
		result = "addPost 완료";
		responseDTO.setResultCode("0000");
		responseDTO.setResultMsg(result);
		return responseDTO;
		
	}
	
	
	@GetMapping("/img/{filename}")
	public ResponseEntity<Resource> getImge(@PathVariable String filename, HttpServletRequest request){
		
		
		
		//filename  UUID_1.png  
		String fileNamePath[] = filename.split("_");
        
        for(int i=0 ; i<fileNamePath.length ; i++)
        {
        	logger.info("fileNamePath["+i+"] : "+fileNamePath[i]);
        }
        String uuid = fileNamePath[0];
        String idx = fileNamePath[1];
        String imgName;

        imgName = uuid +"_"+idx;
        
		Path filePath = Paths.get(restDir+"/"+uuid+"/"+imgName);
		
		logger.info(filePath.toString());
		Resource resource = null;
		
		try {
			resource = new UrlResource(filePath.toUri());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
	     
	}
	
	
	
	
	@GetMapping("/m/{sid}")
	public HashMap<String,Object> getSnsWithFile(@PathVariable int sid){
		
		HashMap<String, Object> hasmap = new HashMap<>();
		
		try {
			Sns sns = dao.getPost(sid);
			
			List<String> list = dao.getImgaes(sns.getImg());
			
			hasmap.put("sns", sns);
			hasmap.put("imglist", list);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return hasmap;
//		sns 상세 내용 + 파일들
	}
}




