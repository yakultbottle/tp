package seedu.pill.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents an order in the inventory management system.
 * An order is a collection of items that are either being received into inventory (purchase orders)
 * or being dispensed out of inventory (dispense orders). Each order has a unique identifier,
 * tracks its creation and fulfillment times, maintains a status, and can include notes.
 */
public class Order {
    private final UUID id;
    private final OrderType type;
    private final LocalDateTime creationTime;
    private LocalDateTime fulfillmentTime;
    private OrderStatus status;
    private final List<OrderItem> items;
    private final String notes;

    /**
     * Defines the types of orders that can be created in the system.
     * PURCHASE - orders represent incoming inventory from suppliers.
     * DISPENSE - orders represent outgoing inventory to end users or departments.
     */
    public enum OrderType {
        PURCHASE,
        DISPENSE
    }

    /**
     * Defines the possible states of an order in the system.
     * PENDING   - indicates the order is awaiting processing.
     * FULFILLED - indicates the order has been successfully processed and completed.
     * CANCELLED - indicates the order was terminated before completion.
     */
    public enum OrderStatus {
        PENDING,
        FULFILLED,
        CANCELLED
    }

    /**
     * Creates a new Order with the specified type and notes.
     * The order is automatically assigned a unique identifier and initialized
     * with a PENDING status and the current timestamp.
     *
     * @param type  - The type of order (PURCHASE or DISPENSE)
     * @param notes - Additional information or comments about the order
     */
    public Order(OrderType type, String notes) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.creationTime = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
        this.items = new ArrayList<>();
        this.notes = notes;
    }

    /**
     * Adds an item to this order with the specified name and quantity.
     * Multiple items can be added to a single order.
     *
     * @param itemName - The name of the item to add
     * @param quantity - The quantity of the item to add
     */
    public void addItem(String itemName, int quantity) {
        items.add(new OrderItem(itemName, quantity));
    }

    /**
     * Marks this order as fulfilled, updating its status and recording
     * the fulfillment timestamp.
     */
    public void fulfill() {
        this.status = OrderStatus.FULFILLED;
        this.fulfillmentTime = LocalDateTime.now();
    }

    /**
     * Marks this order as cancelled, preventing it from being fulfilled.
     */
    public void cancel() {
        this.status = OrderStatus.CANCELLED;
    }

    /**
     * Returns the unique identifier for this order.
     *
     * @return - The UUID assigned to this order
     */
    public UUID getId() {
        return id;
    }

    /**
     * Returns the type of this order (PURCHASE or DISPENSE).
     *
     * @return - The OrderType of this order
     */
    public OrderType getType() {
        return type;
    }

    /**
     * Returns the timestamp when this order was created.
     *
     * @return - The creation timestamp
     */
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    /**
     * Returns the timestamp when this order was fulfilled, if applicable.
     *
     * @return - The fulfillment timestamp, or null if the order is not fulfilled
     */
    public LocalDateTime getFulfillmentTime() {
        return fulfillmentTime;
    }

    /**
     * Returns the current status of this order.
     *
     * @return - The current OrderStatus
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Returns a copy of the list of items in this order.
     * The returned list is a new ArrayList to prevent modification of the original items.
     *
     * @return - A new ArrayList containing the OrderItems in this order
     */
    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Returns the notes associated with this order.
     *
     * @return - The notes string provided during order creation
     */
    public String getNotes() {
        return notes;
    }
}