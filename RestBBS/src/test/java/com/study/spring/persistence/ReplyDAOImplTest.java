package com.study.spring.persistence;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.springrest.domain.ReplyVO;
import com.study.springrest.domain.RestVO;
import com.study.springrest.persistence.ReplyDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class ReplyDAOImplTest {

	@Inject
	private ReplyDAO dao;
	
	@Test
	public void test() { System.out.println("Test DAO = " + dao); }
	
	@Test 
	public void insert() throws Exception{
		ReplyVO vo = new ReplyVO();
		vo.setContent("테스트내용");
		vo.setUser_name("테스트유저");
		vo.setBoard_no(1);
		dao.insert(vo);
	}
	
	@Test
	public void get() throws Exception{
		int reply_no = 1;
		ReplyVO vo = dao.get(reply_no);
		System.out.println("이름 : " + vo.getUser_name());
	}
	
	@Test
	public void getList() throws Exception{
		List<ReplyVO> list = dao.getList(2);
		Iterator<ReplyVO> it = list.iterator();
		while(it.hasNext()){
			ReplyVO vo = it.next();
			System.out.println("user_name : " + vo.getUser_name());
		}
	}
	
	@Test
	public void update() throws Exception{
		ReplyVO vo = new ReplyVO();
		vo.setContent("수정내용");
		vo.setUser_name("수정유저");
		vo.setReply_no(3);
		dao.update(vo);
	}
	
	@Test
	public void delete() throws Exception{
		dao.delete(2);
	}
}
