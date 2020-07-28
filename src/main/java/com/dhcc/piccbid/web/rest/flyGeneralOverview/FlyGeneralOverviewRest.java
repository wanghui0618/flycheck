package com.dhcc.piccbid.web.rest.flyGeneralOverview;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.dhcc.piccbid.dto.unreasonableAdmission.UnreasonableAdmissionDto;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.flyGeneralOverview.FlyGeneralOverviewBlh;
import com.dhcc.piccbid.dto.dict.DictDto;
import com.dhcc.piccbid.dto.flyGeneralOverview.FlyGeneralOverviewDto;
import com.dhcc.piccbid.dto.indicator.DiseasesAnalysisDto;
import com.dhcc.piccbid.entity.page.Page;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liufaxing
 * @date 2019-11-24 12:40:05
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/flyGeneralOverview/flyGeneralOverview")
public class FlyGeneralOverviewRest {

	@Resource
	private FlyGeneralOverviewBlh flyGeneralOverviewBlh;

	@RequestMapping("exportExcel")
	public void exportExcel(HttpServletResponse response, FlyGeneralOverviewDto dto) throws IOException {
		SXSSFWorkbook wb =flyGeneralOverviewBlh.getData(dto);
		OutputStream out = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=FlyGeneralOverviewVo.xls");
		response.setContentType("application/msexcel");
		try {
			wb.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			out.close();
		}
	}

	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) FlyGeneralOverviewDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(FlyGeneralOverviewDto dto) {
		flyGeneralOverviewBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value="save",consumes="application/json")
	public FlyGeneralOverviewDto saveRest(@RequestBody FlyGeneralOverviewDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public FlyGeneralOverviewDto save(FlyGeneralOverviewDto dto) {
		flyGeneralOverviewBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public FlyGeneralOverviewDto deleteRest(@RequestBody FlyGeneralOverviewDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public FlyGeneralOverviewDto delete(FlyGeneralOverviewDto dto) {
		flyGeneralOverviewBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public FlyGeneralOverviewDto findByIdRest(@RequestBody FlyGeneralOverviewDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public FlyGeneralOverviewDto findById(FlyGeneralOverviewDto dto) {
		flyGeneralOverviewBlh.findById(dto);
		return dto;
	}
	@PostMapping(value="flyCheckfind",consumes="application/json")
	public Page FlyGeneralOverviewDtoFindRest(FlyGeneralOverviewDto dto) {
		return this.FlyGeneralOverviewDtoFind(dto);
	}
	@RequestMapping("flyCheckfind")
	public Page FlyGeneralOverviewDtoFind(FlyGeneralOverviewDto dto) {
		try{
			if(dto.getFlyGeneralOverviewVo()!=null) {
			if(dto.getFlyGeneralOverviewVo().getHospitalName()!= null && !dto.getFlyGeneralOverviewVo().getHospitalName().equals("")) {
				String hospitalName = URLDecoder.decode(dto.getFlyGeneralOverviewVo().getHospitalName(), "UTF-8");
				dto.getFlyGeneralOverviewVo().setHospitalName(hospitalName);
			}
			if(dto.getFlyGeneralOverviewVo().getVisitingRoute()!= null && !dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("")) {
				String visitingRoute = URLDecoder.decode(dto.getFlyGeneralOverviewVo().getVisitingRoute(), "UTF-8");
				visitingRoute.trim();
				dto.getFlyGeneralOverviewVo().setVisitingRoute(visitingRoute);
			}
		}
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		flyGeneralOverviewBlh.FlyGeneralOverviewDtoFind(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	@RequestMapping("flyCheckfind1")
	public Page FlyGeneralOverviewDtoFind1(FlyGeneralOverviewDto dto) {
		try{
			if(dto.getFlyGeneralOverviewVo()!=null) {
			   if(dto.getFlyGeneralOverviewVo().getVisitingRoute()!= null && !dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("")) {
				String visitingRoute = URLDecoder.decode(dto.getFlyGeneralOverviewVo().getVisitingRoute(), "UTF-8");
				dto.getFlyGeneralOverviewVo().setVisitingRoute(visitingRoute);
			}
		}
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		flyGeneralOverviewBlh.FlyGeneralOverviewDtoFind1(dto);
		Page page = new Page();
		page.setMsg(String.valueOf(dto.getPageModel().getPageNo()));
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
//		page.setCount("0");
		return page;
	}
	
	@PostMapping(value="flyCheckfind2",consumes="application/json")
	public Page FlyGeneralOverviewDtoFindRest2(@RequestBody(required=false) FlyGeneralOverviewDto dto) {
		return this.FlyGeneralOverviewDtoFind2(dto);
	}
	@RequestMapping("flyCheckfind2")
	public Page FlyGeneralOverviewDtoFind2(FlyGeneralOverviewDto dto) {
		try{
			if(dto.getFlyGeneralOverviewVo()!=null) {
			if(dto.getFlyGeneralOverviewVo().getHospitalName()!= null && !dto.getFlyGeneralOverviewVo().getHospitalName().equals("")) {
				String hospitalName = URLDecoder.decode(dto.getFlyGeneralOverviewVo().getHospitalName(), "UTF-8");
				dto.getFlyGeneralOverviewVo().setHospitalName(hospitalName);
			}
			if(dto.getFlyGeneralOverviewVo().getVisitingRoute()!= null && !dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("")) {
				String visitingRoute = URLDecoder.decode(dto.getFlyGeneralOverviewVo().getVisitingRoute(), "UTF-8");
				dto.getFlyGeneralOverviewVo().setVisitingRoute(visitingRoute);
			}
		}
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		flyGeneralOverviewBlh.FlyGeneralOverviewDtoFind2(dto);
		Page page = new Page();
		page.setMsg(String.valueOf(dto.getPageModel().getPageNo()));
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
//		page.setCount("0");
		return page;
	}
	
	@PostMapping(value="flyCheckfind3",consumes="application/json")
	public Page FlyGeneralOverviewDtoFindRest3(@RequestBody(required=false) FlyGeneralOverviewDto dto) {
		return this.FlyGeneralOverviewDtoFind3(dto);
	}
	@RequestMapping("flyCheckfind3")
	public Page FlyGeneralOverviewDtoFind3(FlyGeneralOverviewDto dto) {
		try{
			if(dto.getFlyGeneralOverviewVo()!=null) {
			if(dto.getFlyGeneralOverviewVo().getHospitalName()!= null && !dto.getFlyGeneralOverviewVo().getHospitalName().equals("")) {
				String hospitalName = URLDecoder.decode(dto.getFlyGeneralOverviewVo().getHospitalName(), "UTF-8");
				dto.getFlyGeneralOverviewVo().setHospitalName(hospitalName);
			}
			if(dto.getFlyGeneralOverviewVo().getVisitingRoute()!= null && !dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("")) {
				String visitingRoute = URLDecoder.decode(dto.getFlyGeneralOverviewVo().getVisitingRoute(), "UTF-8");
				dto.getFlyGeneralOverviewVo().setVisitingRoute(visitingRoute);
			}
		}
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		flyGeneralOverviewBlh.FlyGeneralOverviewDtoFind3(dto);
		Page page = new Page();
		page.setMsg(String.valueOf(dto.getPageModel().getPageNo()));
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
//		page.setCount("0");
		return page;
	}
	
	@PostMapping(value="flyCheckfind4",consumes="application/json")
	public Page FlyGeneralOverviewDtoFindRest4(@RequestBody(required=false) FlyGeneralOverviewDto dto) {
		return this.FlyGeneralOverviewDtoFind4(dto);
	}
	@RequestMapping("flyCheckfind4")
	public Page FlyGeneralOverviewDtoFind4(FlyGeneralOverviewDto dto) {
		try{
			if(dto.getFlyGeneralOverviewVo()!=null) {
			if(dto.getFlyGeneralOverviewVo().getHospitalName()!= null && !dto.getFlyGeneralOverviewVo().getHospitalName().equals("")) {
				String hospitalName = URLDecoder.decode(dto.getFlyGeneralOverviewVo().getHospitalName(), "UTF-8");
				dto.getFlyGeneralOverviewVo().setHospitalName(hospitalName);
			}
			if(dto.getFlyGeneralOverviewVo().getVisitingRoute()!= null && !dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("")) {
				String visitingRoute = URLDecoder.decode(dto.getFlyGeneralOverviewVo().getVisitingRoute(), "UTF-8");
				dto.getFlyGeneralOverviewVo().setVisitingRoute(visitingRoute);
			}
		}
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		flyGeneralOverviewBlh.FlyGeneralOverviewDtoFind4(dto);
		Page page = new Page();
		page.setMsg(String.valueOf(dto.getPageModel().getPageNo()));
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
//		page.setCount("0");
		return page;
	}
	
	@PostMapping(value="flyCheckfind5",consumes="application/json")
	public Page FlyGeneralOverviewDtoFindRest5(@RequestBody(required=false) FlyGeneralOverviewDto dto) {
		return this.FlyGeneralOverviewDtoFind5(dto);
	}
	@RequestMapping("flyCheckfind5")
	public Page FlyGeneralOverviewDtoFind5(FlyGeneralOverviewDto dto) {
		try{
			if(dto.getFlyGeneralOverviewVo()!=null) {
			if(dto.getFlyGeneralOverviewVo().getHospitalName()!= null && !dto.getFlyGeneralOverviewVo().getHospitalName().equals("")) {
				String hospitalName = URLDecoder.decode(dto.getFlyGeneralOverviewVo().getHospitalName(), "UTF-8");
				dto.getFlyGeneralOverviewVo().setHospitalName(hospitalName);
			}
			if(dto.getFlyGeneralOverviewVo().getVisitingRoute()!= null && !dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("")) {
				String visitingRoute = URLDecoder.decode(dto.getFlyGeneralOverviewVo().getVisitingRoute(), "UTF-8");
				dto.getFlyGeneralOverviewVo().setVisitingRoute(visitingRoute);
			}
		}
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		flyGeneralOverviewBlh.FlyGeneralOverviewDtoFind5(dto);
		Page page = new Page();
		page.setMsg(String.valueOf(dto.getPageModel().getPageNo()));
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
//		page.setCount("0");
		return page;
	}
	
	@PostMapping(value="flyCheckfind6",consumes="application/json")
	public Page FlyGeneralOverviewDtoFindRest6(@RequestBody(required=false) FlyGeneralOverviewDto dto) {
		return this.FlyGeneralOverviewDtoFind6(dto);
	}
	@RequestMapping("flyCheckfind6")
	public Page FlyGeneralOverviewDtoFind6(FlyGeneralOverviewDto dto) {
		try{
			if(dto.getFlyGeneralOverviewVo()!=null) {
				if(dto.getFlyGeneralOverviewVo().getHospitalName()!= null && !dto.getFlyGeneralOverviewVo().getHospitalName().equals("")) {
					String hospitalName = URLDecoder.decode(dto.getFlyGeneralOverviewVo().getHospitalName(), "UTF-8");
					dto.getFlyGeneralOverviewVo().setHospitalName(hospitalName);
				}
				if(dto.getFlyGeneralOverviewVo().getVisitingRoute()!= null && !dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("")) {
					String visitingRoute = URLDecoder.decode(dto.getFlyGeneralOverviewVo().getVisitingRoute(), "UTF-8");
					dto.getFlyGeneralOverviewVo().setVisitingRoute(visitingRoute);
				}
			}
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		flyGeneralOverviewBlh.FlyGeneralOverviewDtoFind6(dto);
		Page page = new Page();
		page.setMsg(String.valueOf(dto.getPageModel().getPageNo()));
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
//		page.setCount("0");
		return page;
	}
	@PostMapping(value="findMedicalSumAmount",consumes="application/json")
	public Page FindMedicalSumAmountRest(@RequestBody(required=false) FlyGeneralOverviewDto dto) {
		return this.FindMedicalSumAmount(dto);
	}
	@RequestMapping("findMedicalSumAmount")
	public Page FindMedicalSumAmount(FlyGeneralOverviewDto dto) {
		try{
				if (dto.getFlyGeneralOverviewVo() != null) {
					if (dto.getFlyGeneralOverviewVo().getHospitalName() != null && !dto.getFlyGeneralOverviewVo().getHospitalName().equals("")) {
						String hospitalName = URLDecoder.decode(dto.getFlyGeneralOverviewVo().getHospitalName(), "UTF-8");
						dto.getFlyGeneralOverviewVo().setHospitalName(hospitalName);
					}
					if (dto.getFlyGeneralOverviewVo().getVisitingRoute() != null && !dto.getFlyGeneralOverviewVo().getVisitingRoute().equals("")) {
						String visitingRoute = URLDecoder.decode(dto.getFlyGeneralOverviewVo().getVisitingRoute(), "UTF-8");
						dto.getFlyGeneralOverviewVo().setVisitingRoute(visitingRoute);
					}
				}
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		flyGeneralOverviewBlh.FindMedicalSumAmount(dto);
		Page page = new Page();
		page.setMsg(String.valueOf(dto.getPageModel().getPageNo()));
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
//		page.setCount("0");
		return page;
	}



	@RequestMapping("findHospitalName")
    public FlyGeneralOverviewDto findHospitalName(FlyGeneralOverviewDto dto) { 
	    this.flyGeneralOverviewBlh.findHospitalName(dto);
        return dto;
    }
	
	@RequestMapping("findHospitalId")
    public FlyGeneralOverviewDto findHospitalId(FlyGeneralOverviewDto dto) { 
	    this.flyGeneralOverviewBlh.findHospitalId(dto);
        return dto;
    }
}
