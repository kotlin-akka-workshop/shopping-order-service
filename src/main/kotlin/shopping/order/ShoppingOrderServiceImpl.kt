package shopping.order

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import shopping.order.proto.OrderRequest
import shopping.order.proto.OrderResponse
import shopping.order.proto.ShoppingOrderService
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage


class ShoppingOrderServiceImpl : ShoppingOrderService {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun order(input: OrderRequest): CompletionStage<OrderResponse> {
        var total = 0
        for (item in input.itemsList) {
            total += item.quantity
        }
        logger.info("Order {} items from cart {}.", total, input.cartId)
        val response = OrderResponse.newBuilder().setOk(true).build()
        return CompletableFuture.completedFuture(response)
    }
}