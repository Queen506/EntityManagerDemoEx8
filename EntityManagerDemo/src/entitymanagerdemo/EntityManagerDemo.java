/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitymanagerdemo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Address;
import model.Customer;

/**
 *
 * @author sarun
 */
public class EntityManagerDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Customer customer = new Customer(1L, "Antony", "Balla", "tballa@mail.com"); 
        //Address address = new Address(1L, "Ritherdon Rd", "London", "8QE", "UK"); 
        //address.setCustomerFk(customer);
        //customer.setAddressId(address); 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        //Customer customer = em.getReference(Customer.class, 1L);
        Customer customer = em.find(Customer.class, 1L);
        //customer.setFirstname("Prayuth");
        //customer.setAddressId(null);
        //Address address = em.find(Address.class, 1L);
        //address.setCustomerFk(customer);
        //Address address = customer.getAddressId();
        //address.setCity("Gotham");
        //address.setCustomerFk(null);
        
        em.getTransaction().begin();
        try {
            //em.persist(address);
            //em.persist(customer);
            //em.flush();
            //em.persist(address);
            //em.persist(customer);
            //em.refresh(customer);
            //em.detach(customer);
            //em.persist(customer);
            //em.merge(customer);
            //em.remove(customer);
            //em.persist(customer);
            //em.remove(address);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
       private static void createData(EntityManager em) {
        em.getTransaction().begin();

        try {
            Address address1 = new Address(1L, "123/4 Viphavadee Rd.", "Bangkok", "10900", "TH");
            Address address2 = new Address(2L, "123/5 Viphavadee Rd.", "Bangkok", "10900", "TH");
            Address address3 = new Address(3L, "543/21 Fake Rd.", "Nonthaburi", "20900", "TH");
            Address address4 = new Address(4L, "678/90 Unreal Rd.", "Pathumthani", "30500", "TH");

            Customer customer1 = new Customer(1L, "John", "Henry", "jh@mail.com");
            Customer customer2 = new Customer(2L, "Marry", "Jane", "mj@mail.com");
            Customer customer3 = new Customer(3L, "Peter", "Parker", "pp@mail.com");
            Customer customer4 = new Customer(4L, "Bruce", "Wayn", "bw@mail.com");

            customer1.setAddressId(address1);
            customer2.setAddressId(address2);
            customer3.setAddressId(address3);
            customer4.setAddressId(address4);

            em.persist(address1);
            em.persist(address2);
            em.persist(address3);
            em.persist(address4);
            em.persist(customer1);
            em.persist(customer2);
            em.persist(customer3);
            em.persist(customer4);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    private static void printAllCustomers(EntityManager em) {
        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
        List<Customer> customers = query.getResultList();

        for (Customer customer : customers) {
            System.out.println("First Name: " + customer.getFirstname());
            System.out.println("Last Name: " + customer.getLastname());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Street: " + customer.getAddressId().getStreet());
            System.out.println("City: " + customer.getAddressId().getCity());
            System.out.println("Country: " + customer.getAddressId().getCountry());
            System.out.println("Zip Code: " + customer.getAddressId().getZipcode());
            System.out.println("------------------------------------------");
        }
    }

    private static void printCustomersByCity(EntityManager em, String city) {
        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.addressId.city = :city", Customer.class);
        query.setParameter("city", city);
        List<Customer> customers = query.getResultList();

        for (Customer customer : customers) {
            System.out.println("First Name: " + customer.getFirstname());
            System.out.println("Last Name: " + customer.getLastname());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Street: " + customer.getAddressId().getStreet());
            System.out.println("City: " + customer.getAddressId().getCity());
            System.out.println("Country: " + customer.getAddressId().getCountry());
            System.out.println("Zip Code: " + customer.getAddressId().getZipcode());
            System.out.println("------------------------------------------");
        }
    }
}
