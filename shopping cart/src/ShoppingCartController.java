import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/shop")
public class ShoppingCartController {

    private final Map<String, Cart> carts = new HashMap<>();

    @PostMapping("/addItem")
    public String addItem(@RequestParam("cartId") String cartId,
                          @RequestParam("itemName") String itemName,
                          @RequestParam("price") BigDecimal price,
                          @RequestParam("quantity") int quantity) {

        Cart cart = carts.computeIfAbsent(cartId, id -> new Cart());
        cart.addItem(itemName, price, quantity);

        return "Item added. Total: " + cart.getTotal();
    }

    @GetMapping("/getTotal")
    public String getTotal(@RequestParam("cartId") String cartId) {
        Cart cart = carts.get(cartId);

        if (Objects.isNull(cart)) {
            return "Cart not found";
        }

        return "Total: " + cart.getTotal();
    }

    static class Cart {
        private final Map<String, CartItem> items = new HashMap<>();

        public void addItem(String name, BigDecimal price, int quantity) {
            String key = name.toLowerCase();
            CartItem item = items.get(key);

            if (item != null && item.getPrice().compareTo(price) == 0) {
                item.addQuantity(quantity);
            } else {
                items.put(key, new CartItem(name, price, quantity));
            }
        }

        public BigDecimal getTotal() {
            return items.values().stream()
                    .map(CartItem::getTotalPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

    static class CartItem {
        private final String name;
        private final BigDecimal price;
        private int quantity;

        public CartItem(String name, BigDecimal price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public void addQuantity(int qty) {
            this.quantity += qty;
        }

        public BigDecimal getTotalPrice() {
            return price.multiply(BigDecimal.valueOf(quantity));
        }

        public BigDecimal getPrice() {
            return price;
        }
    }
}
