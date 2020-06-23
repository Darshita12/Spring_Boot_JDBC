package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ProductBean;
import com.dao.ProductDao;

@RestController
@RequestMapping(value="/product")
public class ProductController {

	/*
	 * @RequestMapping(value="/addProduct",method=RequestMethod.POST) public
	 * ProductBean addProduct() { return null; }
	 */
	@Autowired
	ProductDao productDao;
	
	@PostMapping(value="/addProduct")
	public ProductBean addProduct(ProductBean productBean)
	{
		int res=productDao.addProduct(productBean);
		System.out.println(res);
		return productBean;
	}
	@GetMapping(value="/displayProduct")
	public List<ProductBean> displayProduct()
	{
		List<ProductBean> list=new ArrayList<ProductBean>();
		list=productDao.displayDetails();
		return list;
	}
	@DeleteMapping(value="/deleteProduct/{pid}")
	public ResponseEntity deleteProduct(@PathVariable int pid)
	{
		int res=productDao.deleteProduct(pid);
		if(res>0)
		{
			return new ResponseEntity("deleted",HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
	}
	@PostMapping(value="/updateProduct")
	public ResponseEntity updateProduct(ProductBean productBean)
	{
		int res=productDao.updateProduct(productBean);
		if(res>0)
		{
			return new ResponseEntity("updated",HttpStatus.ACCEPTED);
		}
		return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
	}
	@PatchMapping(value="/getDataById/{pid}")
	public List<ProductBean> getDataById(@PathVariable int pid)
	{
		List<ProductBean> list=new ArrayList<ProductBean>();
		list=productDao.getDataById(pid);
		return list;
		
	}
	
}
