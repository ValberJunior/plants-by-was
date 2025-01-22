@Stateless
public class CustomerMgr {
    @PersistenceContext(unitName = "PBW")
    private EntityManager em;

    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO);
        em.persist(customer);
        return customer;
    }

    public Customer getCustomer(String customerID) {
        return em.find(Customer.class, customerID);
    }
}