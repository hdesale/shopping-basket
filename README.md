# shopping-basket

_JDK Version_:- `Java 8`

_How to run_:- run `mvn exec:java` at project root dir

---

### Description:

* `Main` - Sample class to show the usage of this module.

* `Basket` - Basket can contain multiple items and could be used to calculate the total price of items.

* `Item` - Represents an item in the basket and is identified by item name(`String`)

* `ItemPriceCalculator` - Interface for calculating price of an item. Concrete implementations -
    * `BasicItemPriceCalculator`
    * `TwoForOneItemPriceCalculator`
    * `ThreeForTwoItemPriceCalculator`

* `BasketPriceCalculator` - Interface for calculating price of a basket. Concrete implementations -
    * `BasicBasketPriceCalculator`