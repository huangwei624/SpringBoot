package life.lovestudy.controller;

import life.lovestudy.entity.Menu;
import life.lovestudy.service.MenuService;
import life.lovestudy.utils.ResponseCodeEnum;
import life.lovestudy.vo.MenuVO;
import life.lovestudy.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@GetMapping("/pageMenu/{page}/{pageCount}")
	public ResultVO getMenuListByPage(@PathVariable int page, @PathVariable int pageCount){
		return null;
	}
	
	@GetMapping("/list")
	public ResultVO list(){
		List<MenuVO> allMenu = menuService.getAllMenu();
		return new ResultVO(ResponseCodeEnum.SUCCESS.getCode(), "查询成功", allMenu);
	}
	
	@PostMapping("/save")
	public ResultVO list(@RequestBody Menu menu){
		int count = menuService.saveOne(menu);
		if(count > 0){
			return new ResultVO(ResponseCodeEnum.SUCCESS.getCode(), "添加成功", null);
		}else {
			return new ResultVO(ResponseCodeEnum.FAIL.getCode(), "添加失败", null);
		}
	}
	
	@GetMapping("/parentMenu")
	public ResultVO parentMenu(){
		List<Menu> allMenu = menuService.getParentMenu();
		return new ResultVO(ResponseCodeEnum.SUCCESS.getCode(), "获取成功", allMenu);
	}
	
	@PostMapping("/update")
	public ResultVO update(@RequestBody Menu menu){
		int count = menuService.updateMenu(menu);
		if(count > 0){
			return new ResultVO(ResponseCodeEnum.SUCCESS.getCode(), "更新成功", null);
		}else {
			return new ResultVO(ResponseCodeEnum.FAIL.getCode(), "更新失败", null);
		}
	}
	
	@GetMapping("/delete/{id}")
	public ResultVO delete(@PathVariable int id){
		int count = menuService.deleteById(id);
		if(count > 0){
			return new ResultVO(ResponseCodeEnum.SUCCESS.getCode(), "删除成功", null);
		}else {
			return new ResultVO(ResponseCodeEnum.FAIL.getCode(), "删除失败", null);
		}
	}
	
}
