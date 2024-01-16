package shopping.order

import akka.actor.typed.ActorSystem
import akka.actor.typed.javadsl.Behaviors
import akka.management.cluster.bootstrap.ClusterBootstrap
import akka.management.javadsl.AkkaManagement
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import shopping.order.ShoppingOrderServer.start
import shopping.order.proto.ShoppingOrderService


object Main {
    private val logger: Logger = LoggerFactory.getLogger(Main::class.java)

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val system = ActorSystem.create(Behaviors.empty<Void>(), "ShoppingOrderService")
        try {
            init(system)
        } catch (e: Exception) {
            logger.error("Terminating due to initialization failure.", e)
            system.terminate()
        }
    }

    fun init(system: ActorSystem<Void>) {
        AkkaManagement.get(system).start()
        ClusterBootstrap.get(system).start()

        val config = system.settings().config()
        val grpcInterface = config.getString("shopping-order-service.grpc.interface")
        val grpcPort = config.getInt("shopping-order-service.grpc.port")
        val grpcService: ShoppingOrderService = ShoppingOrderServiceImpl()
        start(grpcInterface, grpcPort, system, grpcService)
    }
}
