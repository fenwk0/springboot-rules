package com.macymoo;

import com.macymoo.domain.Customer;
import com.macymoo.domain.DateUtil;
import com.macymoo.domain.Product;
import org.assertj.core.util.Compatibility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fenwk0 on 05/03/17.
 */
public class DecisionTableUpsellTest {
    DecisionTableUpsell decisionTableUpsell = new DecisionTableUpsell();
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getRecommendation() throws Exception {
        Customer customer = new Customer("Brian Sam-Bodden", 1300.00, DateUtil.getDate("2007-06-21"));
        customer = decisionTableUpsell.getRecommendation(customer);
        System.out.println("DecisionTableUpsellTest.getRecommendation:"+customer);
    }

}