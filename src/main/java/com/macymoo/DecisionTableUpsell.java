package com.macymoo;

import com.macymoo.domain.Customer;
import com.macymoo.domain.DateUtil;
import com.macymoo.domain.Product;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Set;

/**
 * This is a sample class to launch a decision table.
 */
public class DecisionTableUpsell {

    KieSession knowledgeSession = null;

    DecisionTableUpsell(){

        // load up the knowledge base
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        knowledgeSession = kContainer.newKieSession("ksession-rules");
    }

    /**
     * Customer customer = new Customer("Brian Sam-Bodden", 1300.00, DateUtil.getDate("2007-06-21"));
     *
     * @param customer
     * @return
     */
    public Customer getRecommendation(Customer customer) {

        try {

            // go !
            customer.addProducts(Product.CHECKING_ACCOUNT);
            knowledgeSession.insert(customer);
            knowledgeSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
//        } finally {
//            knowledgeSession.dispose();
        }


        return customer;
    }
}
