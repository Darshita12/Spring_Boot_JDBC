package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bean.ProductBean;

//spring bean
@Repository
public class ProductDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	public int addProduct(ProductBean productBean)
	{
		return jdbcTemplate.update("insert into product(pname,pprice)values('"+productBean.getPname()+"',"+productBean.getPprice()+")");
	}
	private final static class ProductMapper implements RowMapper<ProductBean>
	{
		
		@Override
		public ProductBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductBean productBean=new ProductBean();
			productBean.setPid(rs.getInt("pid"));
			productBean.setPname(rs.getString("pname"));
			productBean.setPprice(rs.getInt("pprice"));
			return productBean;
			
		}
	}
	public List displayDetails()
	{
		return jdbcTemplate.query("select * from product", new ProductMapper());
	}
	public int deleteProduct(int pid)
	{
		return jdbcTemplate.update("delete from product where pid="+pid+"");
	}
	public int updateProduct(ProductBean productBean)
	{
		return jdbcTemplate.update("update product set pname='"+productBean.getPname()+"',pprice='"+productBean.getPprice()+"' where pid="+productBean.getPid()+"");
	}

	public List<ProductBean> getDataById(int pid)
	{
		return jdbcTemplate.query("select * from product where pid="+pid+"",new ProductMapper());
	}
}
