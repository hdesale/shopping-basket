# shopping-basket

_JDK Version_:- `Java 8`

_How to run_:- run `mvn exec:java` at project root dir

---

### Description:

* `Main` - Sample class to show the usage of this module.

* `Basket` - Basket contains multiple items and calculates the total price of items.

* `BasketPriceCalculator` - Interface for calculating price of a basket.
    Basket is always associated with a `BasketPriceCalculator` and uses it while
    calculating the total price. This interface would help when there are multiple
    basket price calculations.

    Concrete implementations -
    * `BasicBasketPriceCalculator` - Simply sums up the total price per item.

* `Item` - Represents an item in the basket and is identified by item name(`String`).
    It calculates the total price of item for a supplied quantity.

* `ItemPriceCalculator` - Interface for calculating price of an item.
    Item is always associated with a `ItemPriceCalculator` and uses it while
    calculating the total price of item. This interface helps to facilitate multiple
    item price calculators.

    Concrete implementations -
    * `BasicItemPriceCalculator` - Simply applies the same price for each item.
    * `DiscountedItemPriceCalculator` - This is a decorator class which wraps an
        underlying `ItemPriceCalculator` e.g. `BasicItemPriceCalculator` and adds
        some discount to the price calculated by the underlying calculator.

        * `BuyOneGetOneFreeItemPriceCalculator` - Applies Buy 1 Get 1 Free discount
        * `ThreeForTwoItemPriceCalculator` - Applies 3 for 2 discount.