package test.com.itravel.server.services;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Optional;
import com.itravel.admin.services.managers.OrderManager;
import com.itravel.server.dal.entities.OrderEntity;
import com.itravel.server.dal.managers.OrderEntityManager;
import com.itravel.server.services.aos.OrderBean;
import com.itravel.server.services.rest.utils.OrderState;

public class OrderTest {
	private OrderEntityManager manager = new OrderEntityManager();
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		manager.truncate();
	}

	/**
	 * 测试插入order Entity
	 */
//	@Test
	public void tc_1() {
		OrderEntity entity = new OrderEntity();
		entity.setBookDate(new Date());
		entity.setContactorMail("");
		entity.setContactorName("");
		entity.setContactorPhone("");
		long id = System.nanoTime();
		entity.setId(id);
		manager.saveOrder(entity);
		OrderEntity qEntity = manager.get(id);
		Assert.assertNotNull(qEntity);
		Assert.assertTrue(entity.getId() == qEntity.getId()  );
	}
	/**
	 * 测试通过OrderBean 生成 entity
	 */
	@Test
	public void tc_2() {
		OrderBean bean = new OrderBean();
		bean.setBookDate(new Date());
		bean.setContactorMail("");
		bean.setContactorName("");
		bean.setContactorPhone("");
		OrderEntity entity = Optional.fromNullable(bean).transform(OrderBean.TO_ENTITY).get();
		manager.saveOrder(entity);
		OrderEntity qEntity = manager.get(bean.getId());
		Assert.assertNotNull(qEntity);
		Assert.assertTrue(bean.getId() == qEntity.getId()  );
		
	}
	@Test
	public void tc_3(){
		OrderEntity entity = new OrderEntity();
		entity.setBookDate(new Date());
		entity.setContactorMail("");
		entity.setContactorName("");
		entity.setContactorPhone("");
		long id = System.nanoTime();
		entity.setId(id);
		manager.saveOrder(entity);
		OrderEntity qEntity = manager.get(id);
		OrderBean bean = Optional.fromNullable(qEntity).transform(OrderBean.FROM_ENTITY).get();
		Assert.assertNotNull(bean);
		Assert.assertTrue(qEntity.getId() == bean.getId());
	}
	
	@Test
	public void tc_4(){
		OrderBean bean = new OrderBean();
		bean.setBookDate(new Date());
		bean.setContactorMail("");
		bean.setContactorName("");
		bean.setContactorPhone("");
		OrderManager manager = OrderManager.from(bean);
		manager.pay();
		
	
	}

}
