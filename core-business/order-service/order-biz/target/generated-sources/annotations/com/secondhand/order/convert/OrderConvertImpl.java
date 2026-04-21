package com.secondhand.order.convert;

import com.secondhand.item.dto.ItemSkuDTO;
import com.secondhand.item.dto.ItemSpuDTO;
import com.secondhand.order.dto.OrderPayNotifyDTO;
import com.secondhand.order.pojo.context.OrderCreateContext;
import com.secondhand.order.pojo.context.OrderStateContext;
import com.secondhand.order.pojo.domain.TradeOrder;
import com.secondhand.order.pojo.dto.OrderCancelDTO;
import com.secondhand.order.pojo.dto.OrderCreateReqDTO;
import com.secondhand.order.pojo.dto.OrderDeliverDTO;
import com.secondhand.order.pojo.dto.OrderReceiveDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-14T21:20:12+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class OrderConvertImpl implements OrderConvert {

    @Override
    public TradeOrder toTradeOrder(OrderCreateContext context) {
        if ( context == null ) {
            return null;
        }

        TradeOrder tradeOrder = new TradeOrder();

        tradeOrder.setBuyerId( context.getBuyerId() );
        tradeOrder.setOrderNo( context.getOrderNo() );
        tradeOrder.setSellerId( contextSpuSellerId( context ) );
        tradeOrder.setItemSpuId( contextSpuId( context ) );
        tradeOrder.setItemSkuId( contextSkuId( context ) );
        tradeOrder.setOfferId( contextReqOfferId( context ) );
        tradeOrder.setTotalAmount( context.getTotalAmount() );
        tradeOrder.setFreightAmount( context.getFreightAmount() );
        tradeOrder.setOfferAmount( context.getOfferAmount() );
        tradeOrder.setPayAmount( context.getPayAmount() );

        tradeOrder.setStatus( com.secondhand.order.enums.OrderStatusEnum.WAIT_PAY );

        return tradeOrder;
    }

    @Override
    public OrderStateContext fromPay(OrderPayNotifyDTO dto, TradeOrder tradeOrder) {
        if ( dto == null && tradeOrder == null ) {
            return null;
        }

        OrderStateContext.OrderStateContextBuilder orderStateContext = OrderStateContext.builder();

        if ( dto != null ) {
            orderStateContext.orderNo( dto.getOrderNo() );
            orderStateContext.payAmount( dto.getPayAmount() );
        }
        orderStateContext.tradeOrder( tradeOrder );

        return orderStateContext.build();
    }

    @Override
    public OrderStateContext fromCancel(OrderCancelDTO dto, TradeOrder tradeOrder) {
        if ( dto == null && tradeOrder == null ) {
            return null;
        }

        OrderStateContext.OrderStateContextBuilder orderStateContext = OrderStateContext.builder();

        if ( dto != null ) {
            orderStateContext.orderId( dto.getId() );
            orderStateContext.cancelReason( dto.getCancelReason() );
        }
        if ( tradeOrder != null ) {
            orderStateContext.tradeOrder( tradeOrder );
            orderStateContext.orderNo( tradeOrder.getOrderNo() );
            orderStateContext.payAmount( tradeOrder.getPayAmount() );
        }

        return orderStateContext.build();
    }

    @Override
    public OrderStateContext fromDeliver(OrderDeliverDTO dto, TradeOrder tradeOrder) {
        if ( dto == null && tradeOrder == null ) {
            return null;
        }

        OrderStateContext.OrderStateContextBuilder orderStateContext = OrderStateContext.builder();

        if ( dto != null ) {
            orderStateContext.orderId( dto.getId() );
        }
        if ( tradeOrder != null ) {
            orderStateContext.tradeOrder( tradeOrder );
            orderStateContext.orderNo( tradeOrder.getOrderNo() );
            orderStateContext.payAmount( tradeOrder.getPayAmount() );
        }

        return orderStateContext.build();
    }

    @Override
    public OrderStateContext fromReceive(OrderReceiveDTO dto, TradeOrder tradeOrder) {
        if ( dto == null && tradeOrder == null ) {
            return null;
        }

        OrderStateContext.OrderStateContextBuilder orderStateContext = OrderStateContext.builder();

        if ( dto != null ) {
            orderStateContext.orderId( dto.getId() );
        }
        if ( tradeOrder != null ) {
            orderStateContext.tradeOrder( tradeOrder );
            orderStateContext.orderNo( tradeOrder.getOrderNo() );
            orderStateContext.payAmount( tradeOrder.getPayAmount() );
        }

        return orderStateContext.build();
    }

    private Long contextSpuSellerId(OrderCreateContext orderCreateContext) {
        if ( orderCreateContext == null ) {
            return null;
        }
        ItemSpuDTO spu = orderCreateContext.getSpu();
        if ( spu == null ) {
            return null;
        }
        Long sellerId = spu.getSellerId();
        if ( sellerId == null ) {
            return null;
        }
        return sellerId;
    }

    private Long contextSpuId(OrderCreateContext orderCreateContext) {
        if ( orderCreateContext == null ) {
            return null;
        }
        ItemSpuDTO spu = orderCreateContext.getSpu();
        if ( spu == null ) {
            return null;
        }
        Long id = spu.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long contextSkuId(OrderCreateContext orderCreateContext) {
        if ( orderCreateContext == null ) {
            return null;
        }
        ItemSkuDTO sku = orderCreateContext.getSku();
        if ( sku == null ) {
            return null;
        }
        Long id = sku.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long contextReqOfferId(OrderCreateContext orderCreateContext) {
        if ( orderCreateContext == null ) {
            return null;
        }
        OrderCreateReqDTO req = orderCreateContext.getReq();
        if ( req == null ) {
            return null;
        }
        Long offerId = req.getOfferId();
        if ( offerId == null ) {
            return null;
        }
        return offerId;
    }
}
