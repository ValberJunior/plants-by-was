@Stateful
@SessionScoped
public class ShoppingCartBean {
    @PersistenceContext(unitName = "PBW")
    private EntityManager em;

    private List<Inventory> items = new ArrayList<>();

    public void addItem(Inventory newItem) {
        items.stream()
            .filter(item -> item.getID().equals(newItem.getID()))
            .findFirst()
            .ifPresentOrElse(
                item -> item.setQuantity(item.getQuantity() + newItem.getQuantity()),
                () -> items.add(newItem)
            );
    }

    public void removeItem(Inventory item) {
        items.removeIf(i -> i.equals(item));
    }
}