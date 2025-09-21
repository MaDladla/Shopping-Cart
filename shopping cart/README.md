# Shopping Cart Assessment

This project improves a basic shopping cart implementation.  
The goal was to make the code **cleaner, more maintainable, and correct** while keeping the same business logic.

## Note
- This project is not a full Spring Boot application so it will not run as is.
- The focus is on **code readability, maintainability, and correctness**.
- This code will run on a springboot project

## Key Improvements
- Introduced helper classes (`Cart` and `CartItem`) to make the code easier to understand and extend.
- Used `BigDecimal` for prices instead of `double` to avoid rounding errors.
- Replaced manual cart checks with `computeIfAbsent` for cleaner cart creation.
- Moved total calculation into the `Cart` class, keeping logic in one place.
- Kept `@RestController` annotations to show how this could look in a real Spring Boot API.

## Example Features
- **Add items** to a cart (`/shop/addItem`).
- **Get cart total** (`/shop/getTotal`).
- Easy to extend with other new features

## Libraries
- import org.springframework.web.bind.annotation.* is included for annotations like @RestController, @PostMapping, and @GetMapping.
- These annotations would only work in a real Spring Boot application with the spring-boot-starter-web dependency.
- For this assessment, the annotations are shown only to illustrate how this logic would be exposed as REST endpoints in a web service. 

- As the instuction mentioned I did not create a full spring boot applicatio. The code is not intended to be run directly.
