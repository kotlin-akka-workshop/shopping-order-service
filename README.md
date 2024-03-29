
## Run the infra

The infrastructure must be started from the docker compose provided in `shopping-cart-service`

## Running the sample code

1. Start a node:

    ```
    ./mvnw compile exec:exec -DAPP_CONFIG=local1.conf
    ```

2. Check for service readiness

    ```
    curl http://localhost:9301/ready
    ```

3. Try it with [grpcurl](https://github.com/fullstorydev/grpcurl):

    ```
    grpcurl -d '{"cartId":"cart1", "items":[{"itemId":"socks", "quantity":3}, {"itemId":"t-shirt", "quantity":2}]}' -plaintext 127.0.0.1:8301 shoppingorder.ShoppingOrderService.Order
    ```
