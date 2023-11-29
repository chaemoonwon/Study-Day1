package com.bookshop01.common.base;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.bookshop01.goods.vo.ImageFileVO;

//추상 클래스
public abstract class BaseController  {
	
	
	//이미지 파일 경로 상수 필드
	//고정된 경로 사용!!!!!!!!!!!!
	//CURR_IMAGE_REPO_PATH 현재 이미지 저장소의 경로를 표현할 상수의 이름
	//"C:\\shopping\\file_repo" 실제 파일 저장소의 경로
	private static final String CURR_IMAGE_REPO_PATH = "C:\\shopping\\file_repo";
	
	
	
	
	//MultipartHttpServletRequest을 사용하여 파일 업로드를 처리하는 메서드
	protected List<ImageFileVO> upload(MultipartHttpServletRequest multipartRequest) throws Exception{
		
		
//		ImageFileVO 객체를 저장하는 ArrayList 생성
//		이 리스트는 파일 업로드 과정에서 각 파일에 대한 정보를 담을 목적으로 사용된다.
		List<ImageFileVO> fileList= new ArrayList<ImageFileVO>();
		
		
//		multipartRequest.getFileNames은 MultipartHttpServletRequest 객체에서 	
//		모든 파일의 이름을 가져오는 메서드
//		Iterator<String>을 반환하며 각 String은 업로드된 파일의 이름 / Iterator를 통해
//		각 파일의 이름에 접근한 후 파일을 하나씩 처리한다.
//		fileNames 변수는 파일 이름들을 담고 있는 Iterator<String> 객체  
		Iterator<String> fileNames = multipartRequest.getFileNames();
		
		
//		다음에 가져올 파일이 있는지 확인한다.
		while(fileNames.hasNext()){
			
			//현재 처리중인 파일에 대한 정보를 담을 ImageFileVO 객체를 생성한다. 
			ImageFileVO imageFileVO =new ImageFileVO();
			
			//다음 파일의 이름을 가져온다.
			String fileName = fileNames.next();
			
			//imageFileVO 객체에 파일 타입 설정
			imageFileVO.setFileType(fileName);
			
			//현재 파일에 대한 MultipartFile 객체 얻기
			MultipartFile mFile = multipartRequest.getFile(fileName);
			
			//OriginalFilename 파일의 원본 이름 가져오기
			String originalFileName=mFile.getOriginalFilename();
			
			//imageFileVO 객체에 파일의 원본 이름을 설정
			imageFileVO.setFileName(originalFileName);
			
			//imageFileVO 객체를 리스트에 추가한다.
			fileList.add(imageFileVO);
			
			//저장될 실제 파일을 나타내는 File 객체를 생성한다.
			File file = new File(CURR_IMAGE_REPO_PATH +"\\"+ fileName);
			
			//파일이 업로드 되었는지 확인한다.
			if(mFile.getSize()!=0){ //File Null Check
				
				//만일 파일이 존재하지 않으면
				if(! file.exists()){ //경로상에 파일이 존재하지 않을 경우
					
					//파일의 부모 디렉토리를 생성하고
					if(file.getParentFile().mkdirs()){ //경로에 해당하는 디렉토리들을 생성
						
							//새 파일 생성하기
							file.createNewFile(); //이후 파일 생성
					}
				}
				
				//만약 파일이 존재하거나 위에서 생성되었으면 업로드 된 파일을 실제 파일로 전송한다.
				mFile.transferTo(new File(CURR_IMAGE_REPO_PATH +"\\"+"temp"+ "\\"+originalFileName)); //임시로 저장된 multipartFile을 실제 파일로 전송
			}
			
			// = 업로드 된 각 파일에 대한 정보를 ImageFileVO의 객체로 만들고
			// 실제 파일로 저장하는 작업
			
		}
		
		//업로드 된 파일들에 대한 정보를 담은(add 된) ImageFileVO 객체들의 리스트를 반환한다.
		return fileList;
		
	}
	
	
	
	
	//파일 삭제 실행 기능 (fileName 필요)
	//주어진 파일 이름에 해당하는 파일을 삭제한다.
	private void deleteFile(String fileName) {
		
		//삭제할 파일을 나타내는 File 객체를 생성한다.
		//경로는 CURR_IMAGE_REPO_PATH 와 파일 이름인 fileName 을 조합해 만든다.
		File file =new File(CURR_IMAGE_REPO_PATH+"\\"+fileName);
		
		try{
			
			//실행에 문제가 없다면 File 객체가 나타내는 파일을 삭제한다.
			file.delete();
			
		}catch(Exception e){
			
			//실행에 예외가 발생한다면 예외 코드를 출력한다.
			e.printStackTrace();
		}
	}
	
	
	
	//확장자가 .do로 끝나는 모든 요청에 대해 처리하도록 지정된 상태
	//메서드가 GET, POST 요청 모두를 처리할 수 있도록 지정되어 있다.
	@RequestMapping(value="/*.do" ,method={RequestMethod.POST,RequestMethod.GET})
	
	//ModelAndView 모델과 뷰의 정보를 함께 가지고 있는 SpringMVC 에서 사용되는 클래스
	//리턴 타입이 ModelAndView 인 viewForm
	protected  ModelAndView viewForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//요청 객체에서 viewName 이라는 속성을 가져와 viewName 변수에 저장한다.
		//컨트롤러에서 반환할 뷰의 이름 
		String viewName=(String)request.getAttribute("viewName");
		
		//가져온 viewName을 사용해 ModelAndView 객체를 생성한다.
		//ModelAndView 는 모델과 뷰의 정보를 함께 저장할때 사용
		ModelAndView mav = new ModelAndView(viewName);
		
		//생성한 ModelAndView 객체를 반환한다.
		//이후 뷰를 렌더링하고 클라이언트에 응답을 보낸다.
		return mav;
	}
	
	
	
	
	//fixedSearchPeriod 에 따라서 검색 기간을 계산하는 메서드
	protected String calcSearchPeriod(String fixedSearchPeriod){
		
		//검색 기간의 시작일과 종료일, 각각의 연, 월, 일을 저장하기 위한 문자열 변수
		String beginDate=null;
		String endDate=null;
		String endYear=null;
		String endMonth=null;
		String endDay=null;
		String beginYear=null;
		String beginMonth=null;
		String beginDay=null;
		
		
		//날짜의 한 자리 숫자를 두자리로 수정하기 위한 DecimalFormat객체를 생성
		//1월이면 01로 수정
		DecimalFormat df = new DecimalFormat("00");
		
		
		//Calendar 기본 자바 내장
		//현재의 날짜와 시간 정보를 가지고 있는 Calendar 객체 생성 
		Calendar cal=Calendar.getInstance();
		
		
		//현재 날짜를 기준으로 위의 cal 객체에서 연도를 가져온 뒤
		//해당 연도를 문자열로 변환하여 저장하는 부분
		//(cal.get(Calendar.YEAR) 은 cal 객체에서 현재 연도를 가져오는 메서드
		//Integer.toString은 정수를 문자열로 변환하는 메서드
		
		// = 현재시간 나타내는 cal에서 연도 가져와 endYear에 문자열로 저장
		endYear   = Integer.toString(cal.get(Calendar.YEAR));
		
		
		//상수는 0부터 시작해 11까지의 값을 가지기 때문에 0은 1월을 나타낸다.
		// 1을 더해 실제 월의 값을 얻는다.
		endMonth  = df.format(cal.get(Calendar.MONTH) + 1);
		
		endDay   = df.format(cal.get(Calendar.DATE));
		endDate = endYear +"-"+ endMonth +"-"+endDay;
		
		
		
		//fixedSearchPeriod 검색 기간
		
		//fixedSearchPeriod가 null 이면 검색 기간을 4개월 전으로 설정한다.
		if(fixedSearchPeriod == null) {
			//현재 월에서 4 마이너스
			cal.add(cal.MONTH,-4);
			
			
			
		//검색기간이 one_week면 검색기간을 일주일 전으로 설정한다.
		}else if(fixedSearchPeriod.equals("one_week")) {
			//현재 시간에서 7일 마이너스
			cal.add(Calendar.DAY_OF_YEAR, -7);
			
		
			
		//검색기간이 one_week면 검색기간을 일주일 전으로 설정한다.
		}else if(fixedSearchPeriod.equals("two_week")) {
			//현재 시간에서 7일 마이너스
			cal.add(Calendar.DAY_OF_YEAR, -14);
			
			

		//검색 기간이 one_month면 검색기간을 1달전으로 설정한다.
		}else if(fixedSearchPeriod.equals("one_month")) {
			//현재 시간에서 1개월 마이너스
			cal.add(cal.MONTH,-1);
			
			
			
		//검색 기간이 two_month면 검색기간을 2달전으로 설정한다.
		}else if(fixedSearchPeriod.equals("two_month")) {
			//현재 시간에서 2개월 마이너스
			cal.add(cal.MONTH,-2);
			
			
			
		//검색 기간이 three_month면 검색기간을 3달전으로 설정한다.
		}else if(fixedSearchPeriod.equals("three_month")) {
			//현재 시간에서 3개월 마이너스
			cal.add(cal.MONTH,-3);
			
			
			
			//검색 기간이 four_month면 검색기간을 4달전으로 설정한다.
		}else if(fixedSearchPeriod.equals("four_month")) {
			//현재 시간에서 4개월 마이너스
			cal.add(cal.MONTH,-4);
		}
		
//		= fixedSearchPeriod에 따라 검색 기간 설정
		
		
		
		
		
		//검색 기간의 시작 일자 구하는 코드
		
		//cal 객체에서 현재 연도를 가져와 문자열로 변환한뒤 beginYear 에 저장 
		beginYear   = Integer.toString(cal.get(Calendar.YEAR));
		
		//cal 에서 현재 월 구하고 beginMonth 에 저장 
		beginMonth  = df.format(cal.get(Calendar.MONTH) + 1);
		
		//cal 에서 현재 일 구하고 beginDay 에 저장
		beginDay   = df.format(cal.get(Calendar.DATE));
		
		//위에서 구한 변수들 전부 모아서 날짜 출력
		beginDate = beginYear +"-"+ beginMonth +"-"+beginDay;
		
		
		//검색 시작하는 날짜 검색 끝나는 날짜
		return beginDate+","+endDate;
	}
	
}
