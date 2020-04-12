package life.lovestudy.controller;

import life.lovestudy.entity.Menu;
import life.lovestudy.entity.Role;
import life.lovestudy.service.MenuService;
import life.lovestudy.service.RoleService;
import life.lovestudy.utils.ResponseCodeEnum;
import life.lovestudy.utils.TreeMenu;
import life.lovestudy.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MenuService menuService;
	
	@PostMapping("/saveOne")
	public ResultVO parentMenu(@RequestBody Role role){
		int count = roleService.saveOne(role);
		if(count > 0){
			return new ResultVO(ResponseCodeEnum.SUCCESS.getCode(), "保存成功", null);
		}else {
			return new ResultVO(ResponseCodeEnum.FAIL.getCode(), "保存失败", null);
		}
	}
	
	@GetMapping("/all")
	public ResultVO all(){
		List<Role> all = roleService.all();
		return new ResultVO(ResponseCodeEnum.SUCCESS.getCode(), "保存成功", all);
	}
	
	@GetMapping("/delete/{id}")
	public ResultVO delete(@PathVariable int id){
		int count = roleService.deleteById(id);
		if(count == 1){
			return new ResultVO(ResponseCodeEnum.SUCCESS.getCode(), "删除成功", null);
		}else {
			return new ResultVO(ResponseCodeEnum.FAIL.getCode(), "删除失败", null);
		}
	}
	
	@GetMapping("/updateRoleToMenu")
	public ResultVO updateRoleToMenu(int roleId, int[] menuIds){
		int count = roleService.updateRoleToMenu(roleId, menuIds);
		if(count > 0){
			return new ResultVO(ResponseCodeEnum.SUCCESS.getCode(), "更新成功", null);
		}else {
			return new ResultVO(ResponseCodeEnum.FAIL.getCode(), "更新失败", null);
		}
	}
	
	@GetMapping("/getTreeMenuByRoleId")
	public ResultVO getMenuByRoleId(int roleId){
		List<TreeMenu> treeMenus = menuService.treeMenu(roleId);
		return new ResultVO(ResponseCodeEnum.SUCCESS.getCode(), "查询成功", treeMenus);
	}
	
	@GetMapping("/getMenuIdByRoleId")
	public ResultVO getMenuIdByRoleId(int roleId){
		Integer[] menuIdByRoleId = roleService.getMenuIdByRoleId(roleId);
		return new ResultVO(ResponseCodeEnum.SUCCESS.getCode(), "查询成功", menuIdByRoleId);
	}
	
}
