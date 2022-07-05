package mv_project.DATA;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.board.Sns;
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
	
	//전체 데이터
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
	
	
	// 출연작 
	@GetMapping("/actor/{actor_id}")
	public ResponseDTO<movieData> listFilmography(@PathVariable String actor_id) {
		
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();
		 
		List<movieData> list =  new ArrayList<movieData>();
		List<movieData> filmoList = null; 
		try {
			filmoList = dao.getFilmography(actor_id);
			list.addAll(filmoList);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		responseDTO.setResultCode("0000");
		responseDTO.setResultMsg("정상");
		responseDTO.setResultData(list);
		return responseDTO;
	}
	
	
	// 장르별
	@GetMapping("/genre/{genre}")
	public ResponseDTO<movieData> listGenre(@PathVariable String genre) {
		
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();
		 
		//List<movieData> list =  new ArrayList<movieData>();
		List<movieData> GenreList = null; 
		try {
			GenreList = dao.getGenre(genre);
			//list.addAll(GenreList);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		responseDTO.setResultCode("0000");
		responseDTO.setResultMsg("정상");
		responseDTO.setResultData(GenreList);
		return responseDTO;
	}

	
	// 회사별
	@GetMapping("/company/{company}")
	public ResponseDTO<movieData> listCompany(@PathVariable String company){
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();
		 
		List<movieData> Companylist = null; 
		try {
			logger.info("listCompany");
			
			Companylist = dao.getNeflix(company);
			logger.info(Companylist.toString());
		}catch(Exception e) {
			e.printStackTrace();
			logger.warn("오류");
		}
		
		
		responseDTO.setResultCode("0000");
		responseDTO.setResultMsg("정상");
		responseDTO.setResultData(Companylist);
		return responseDTO;
	}
	
	// 나라별
	@GetMapping("/country/{country}")
	public ResponseDTO<movieData> listCountry(@PathVariable String country){
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();
		 
		List<movieData> Countrylist = null; 
		try {
			logger.info("listCountry");
			
			Countrylist = dao.getCountry(country);
			logger.info(Countrylist.toString());
		}catch(Exception e) {
			e.printStackTrace();
			logger.warn("오류");
		}
		
		
		responseDTO.setResultCode("0000");
		responseDTO.setResultMsg("정상");
		responseDTO.setResultData(Countrylist);
		return responseDTO;
	}
	
	// 런닝타임 ~ 59
		@GetMapping("/59")
		public ResponseDTO<movieData> listRunningTime59(){
			ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();
			 
			List<movieData> list = null; 
			try {
				logger.info("listRunningTime");
				
				list = dao.getRunningTime59();
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
		
	// 런닝타임 60~89
	@GetMapping("/89")
	public ResponseDTO<movieData> listRunningTime60(){
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();
		 
		List<movieData> list = null; 
		try {
			logger.info("listRunningTime");
			
			list = dao.getRunningTime60();
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
	
	// 런닝타임 90~119
		@GetMapping("/119")
		public ResponseDTO<movieData> listRunningTime90(){
			ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();
			 
			List<movieData> list = null; 
			try {
				logger.info("listRunningTime");
				
				list = dao.getRunningTime90();
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
		
		// 런닝타임 120
		@GetMapping("/120")
		public ResponseDTO<movieData> listRunningTime120(){
			ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();
			 
			List<movieData> list = null; 
			try {
				logger.info("listRunningTime");
				
				list = dao.getRunningTime120();
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