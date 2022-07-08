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

	// 전체 데이터
	@GetMapping
	public ResponseDTO<movieData> listMovie() {
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();

		List<movieData> list = null;
		try {
			logger.info("listMovie");

			list = dao.getAllPost();
			logger.info(list.toString());
		} catch (Exception e) {
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

		List<movieData> list = new ArrayList<movieData>();
		List<movieData> filmoList = null;
		try {
			filmoList = dao.getFilmography(actor_id);
			list.addAll(filmoList);
		} catch (Exception e) {
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

		// List<movieData> list = new ArrayList<movieData>();
		List<movieData> GenreList = null;
		try {
			GenreList = dao.getGenre(genre);
			// list.addAll(GenreList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		responseDTO.setResultCode("0000");
		responseDTO.setResultMsg("정상");
		responseDTO.setResultData(GenreList);
		return responseDTO;
	}

	// 회사별
	@GetMapping("/company/{company}")
	public ResponseDTO<movieData> listCompany(@PathVariable String company) {
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();

		List<movieData> Companylist = null;
		try {
			logger.info("listCompany");

			Companylist = dao.getCompany(company);
			logger.info(Companylist.toString());
		} catch (Exception e) {
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
	public ResponseDTO<movieData> listCountry(@PathVariable String country) {
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();

		List<movieData> Countrylist = null;
		try {
			logger.info("listCountry");

			Countrylist = dao.getCountry(country);
			logger.info(Countrylist.toString());
		} catch (Exception e) {
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
	public ResponseDTO<movieData> listRunningTime59() {
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();

		List<movieData> list = null;
		try {
			logger.info("listRunningTime");

			list = dao.getRunningTime59();
			logger.info(list.toString());
		} catch (Exception e) {
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
	public ResponseDTO<movieData> listRunningTime60() {
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();

		List<movieData> list = null;
		try {
			logger.info("listRunningTime");

			list = dao.getRunningTime60();
			logger.info(list.toString());
		} catch (Exception e) {
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
	public ResponseDTO<movieData> listRunningTime90() {
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();

		List<movieData> list = null;
		try {
			logger.info("listRunningTime");

			list = dao.getRunningTime90();
			logger.info(list.toString());
		} catch (Exception e) {
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
	public ResponseDTO<movieData> listRunningTime120() {
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();

		List<movieData> list = null;
		try {
			logger.info("listRunningTime");

			list = dao.getRunningTime120();
			logger.info(list.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("오류");
		}

		responseDTO.setResultCode("0000");
		responseDTO.setResultMsg("정상");
		responseDTO.setResultData(list);
		return responseDTO;
	}

	// 개봉년도 검색
	@GetMapping("/year/{release_year}")
	public ResponseDTO<movieData> listYear(@PathVariable String release_year) {
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();

		List<movieData> Yearlist = null;
		try {
			logger.info("listCountry");

			Yearlist = dao.getYear(release_year);
			logger.info(Yearlist.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("오류");
		}

		responseDTO.setResultCode("0000");
		responseDTO.setResultMsg("정상");
		responseDTO.setResultData(Yearlist);
		return responseDTO;
	}

	// 2000년대 개봉작
	@GetMapping("/00")
	public ResponseDTO<movieData> listYear2000() {
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();

		List<movieData> list = null;
		try {
			logger.info("listYear00");

			list = dao.getYear2000();
			logger.info(list.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("오류");
		}

		responseDTO.setResultCode("0000");
		responseDTO.setResultMsg("정상");
		responseDTO.setResultData(list);
		return responseDTO;
	}

	// 2010년대 개봉작
	@GetMapping("/10")
	public ResponseDTO<movieData> listYear2010() {
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();

		List<movieData> list = null;
		try {
			logger.info("listYear10");

			list = dao.getYear2010();
			logger.info(list.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("오류");
		}

		responseDTO.setResultCode("0000");
		responseDTO.setResultMsg("정상");
		responseDTO.setResultData(list);
		return responseDTO;
	}

	// 2020년대 개봉작
	@GetMapping("/20")
	public ResponseDTO<movieData> listYear2020() {
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();

		List<movieData> list = null;
		try {
			logger.info("listYear20");

			list = dao.getYear2020();
			logger.info(list.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("오류");
		}

		responseDTO.setResultCode("0000");
		responseDTO.setResultMsg("정상");
		responseDTO.setResultData(list);
		return responseDTO;
	}

	
	// 전체 관람가
	@GetMapping("/age/all")
	public ResponseDTO<movieData> listAgeAll() {
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();

		List<movieData> list = null;
		try {
			

			list = dao.getAgeAll();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("오류");
		}

		responseDTO.setResultCode("0000");
		responseDTO.setResultMsg("정상");
		responseDTO.setResultData(list);
		return responseDTO;
	}
	
	
	// 12세 관람가
	@GetMapping("/age/12")
	public ResponseDTO<movieData> listAge12() {
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();

		List<movieData> list = null;
		try {
			
			list = dao.getAge12();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("오류");
		}

		responseDTO.setResultCode("0000");
		responseDTO.setResultMsg("정상");
		responseDTO.setResultData(list);
		return responseDTO;
	}


	// 15세 관람가
	@GetMapping("/age/15")
	public ResponseDTO<movieData> listAge15() {
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();

		List<movieData> list = null;
		try {
			

			list = dao.getAge15();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("오류");
		}

		responseDTO.setResultCode("0000");
		responseDTO.setResultMsg("정상");
		responseDTO.setResultData(list);
		return responseDTO;
	}

	
	// 청불
	@GetMapping("/age/19")
	public ResponseDTO<movieData> listAge19() {
		ResponseDTO<movieData> responseDTO = new ResponseDTO<movieData>();

		List<movieData> list = null;
		try {
			

			list = dao.getAge19();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("오류");
		}

		responseDTO.setResultCode("0000");
		responseDTO.setResultMsg("정상");
		responseDTO.setResultData(list);
		return responseDTO;
	}

}
