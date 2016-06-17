package com.study.spring.persistence;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.springrest.domain.RestVO;
import com.study.springrest.persistence.RestDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class RestDAOImplTest {
	
	@Inject
	private RestDAO dao;
	
	@Test
	public void test() { System.out.println("Test DAO = " + dao); }
	
	@Test 
	public void insert() throws Exception{
		RestVO vo = new RestVO();
		vo.setTitle("�׽�ƮŸ��Ʋ");
		vo.setContent("�׽�Ʈ����");
		vo.setUser_name("�׽�Ʈ����");
	}
	
	@Test
	public void get() throws Exception{
		int board_no = 1;
		RestVO vo = dao.get(board_no);
		System.out.println("�̸� : " + vo.getUser_name());
	}
	
	@Test
	public void getList() throws Exception{
		List<RestVO> list = dao.getList();
		Iterator<RestVO> it = list.iterator();
		while(it.hasNext()){
			RestVO vo = it.next();
			System.out.println("user_name : " + vo.getUser_name());
		}
	}
	
	@Test
	public void update() throws Exception{
		RestVO vo = new RestVO();
		vo.setTitle("����Ÿ��Ʋ");
		vo.setContent("��������");
		vo.setUser_name("��������");
		vo.setBoard_no(3);
		dao.update(vo);
	}
	
	@Test
	public void delete() throws Exception{
		dao.delete(2);
	}
}
