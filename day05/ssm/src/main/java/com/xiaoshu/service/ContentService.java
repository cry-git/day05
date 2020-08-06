package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.CategoryMapper;
import com.xiaoshu.dao.ContentMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Category;
import com.xiaoshu.entity.Content;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class ContentService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	private ContentMapper cm;
	
	@Autowired
	private CategoryMapper cgm;
	
	// 查询所有
	public List<User> findUser(User t) throws Exception {
		return userMapper.select(t);
	};

	// 数量
	public int countUser(User t) throws Exception {
		return userMapper.selectCount(t);
	};

	// 通过ID查询
	public User findOneUser(Integer id) throws Exception {
		return userMapper.selectByPrimaryKey(id);
	};

	// 新增
	public void addContent(Content content) throws Exception {
		cm.insert(content);
	};

	// 修改
	public void updateContent(Content content) throws Exception {
		cm.updateByPrimaryKey(content);
	};

	// 删除
	public void deleteContent(Integer id) throws Exception {
		cm.deleteByPrimaryKey(id);
	};

	// 登录
	public User loginUser(User user) throws Exception {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andPasswordEqualTo(user.getPassword()).andUsernameEqualTo(user.getUsername());
		List<User> userList = userMapper.selectByExample(example);
		return userList.isEmpty()?null:userList.get(0);
	};

	// 通过用户名判断是否存在，（新增时不能重名）
	public Content existFindByName(String contenttitle) throws Exception {
		List<Content> cList = cm.findByName(contenttitle);
		return cList.isEmpty()?null:cList.get(0);
	};

	// 通过角色判断是否存在
	public User existUserWithRoleId(Integer roleId) throws Exception {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleidEqualTo(roleId);
		List<User> userList = userMapper.selectByExample(example);
		return userList.isEmpty()?null:userList.get(0);
	}

	public PageInfo<Content> findContentPage(Content content, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Content> cList = cm.findAll(content);
		PageInfo<Content> pageInfo = new PageInfo<Content>(cList);
		return pageInfo;
	}

	public List<Category> findAllCategory() {
		// TODO Auto-generated method stub
		return cgm.selectAll();
	}

	public List<Content> findAll() {
		// TODO Auto-generated method stub
		List<Content> cList = cm.findAll(null);
		return cList;
	}


}
