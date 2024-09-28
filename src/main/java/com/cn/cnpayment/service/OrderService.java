package com.cn.cnpayment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.cnpayment.dal.OrderDal;
import com.cn.cnpayment.dal.PaymentDAL;
import com.cn.cnpayment.entity.Orders;
import com.cn.cnpayment.entity.Payment;

import jakarta.transaction.Transactional;

/**
 Complete the OrderService class as mentioned below:

 a. Autowire OrderDal

 b. Complete the following methods:

 1. getOrderById(int id): This method fetches an Order for a specific Id.

 2. getAllOrders(): This method fetches the list of Orders.

 3. saveOrder(Orders newOrder): This method saves an Order.

 4. delete(int id): This method deletes an Order for a specific Id.
 **/


@Service
public class OrderService {

    // Autowire the OrderDal object.
	@Autowired
	private OrderDal orderDal;
	
	@Autowired
	private PaymentDAL paymentDAL;

    /**
     1. Complete the method body for getOrderById() by adding proper arguments.
     2. add proper annotation for registering this method as a Transaction
     * @param id 
     **/
	@Transactional
    public Orders getOrderById(int id) {
		return orderDal.getById(id);
    }


    /**
     1. Complete the method body for getAllOrders().
     2. add proper annotation for registering this method as a Transaction
     **/
	@Transactional
    public List<Orders> getAllOrders() {
		return orderDal.getAllOrders();
    }


    /**
     1. Complete the method body for saveOrder() method by adding proper arguments.
     2. add proper annotation for registering this method as a Transaction
     * @param order 
     **/
    @Transactional
	public void saveOrder(Orders order) {
		Orders saveOrder = new Orders();
		saveOrder.setAmount(order.getAmount());
		saveOrder.setCategory(order.getCategory());
		saveOrder.setName(order.getName());
		saveOrder.setQuantity(order.getQuantity());
		List<Payment> payments = new ArrayList<>();
		for (Payment paym : order.getPayments()) {
			Payment payment = paymentDAL.getById(paym.getId());
			payments.add(payment);
		}
		saveOrder.setPayments(payments);
		orderDal.save(saveOrder);
	}


    /**
     1. Complete the method body for delete() method by adding proper arguments.
     2. add proper annotation for registering this method as a Transaction
     * @param id 
     **/
    @Transactional
    public void delete(int id) {
    	orderDal.delete(id);
    }


}
