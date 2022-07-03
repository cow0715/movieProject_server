package mv_project.DATA;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.dto.ResponseDTO;

@RestController
@RequestMapping("/api/movie")
public class movieController {
	
	final movieDAO dao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${file.imgPath}")
	String restDir; 
	
	@Autowired
	public movieController(movieDAO dao) {
		this.dao = dao;
			
	}
	
	@GetMapping
	public ResponseDTO<movieData> listMovie(){
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();
		 
		List<movieData> list = null; 
		try {
			logger.info("listMovie");
			
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
	
	
	
	
	
	
	
	
	
	
	
	
}