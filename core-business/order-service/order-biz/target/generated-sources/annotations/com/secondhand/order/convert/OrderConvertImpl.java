package com.secondhand.order.convert;

import com.secondhand.item.dto.ItemSkuDTO;
import com.secondhand.item.dto.ItemSpuDTO;
import com.secondhand.mq.event.OrderSystemCancelEvent;
import com.secondhand.order.dto.OrderPayNotifyDTO;
import com.secondhand.order.dto.admin.OrderAdminRespDTO;
import com.secondhand.order.dto.resp.OrderDetailRespDTO;
import com.secondhand.order.dto.resp.OrderRespDTO;
import com.secondhand.order.enums.OrderStatusEnum;
import com.secondhand.order.pojo.context.OrderCreateContext;
import com.secondhand.order.pojo.context.OrderStateContext;
import com.secondhand.order.pojo.domain.TradeOrder;
import com.secondhand.order.pojo.dto.OrderCancelDTO;
import com.secondhand.order.pojo.dto.OrderCreateReqDTO;
import com.secondhand.order.pojo.dto.OrderDeliverDTO;
import com.secondhand.order.pojo.dto.OrderReceiveDTO;
import com.secondhand.user.dto.AddressDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-02T12:03:04+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.0.v20260407-0427, environment: Java 21.0.10 (Eclipse Adoptium)"
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
        tradeOrder.setSourceAddressId( contextAddressId( context ) );
        tradeOrder.setReceiverNameSnapshot( contextAddressReceiverName( context ) );
        tradeOrder.setReceiverPhoneSnapshot( contextAddressReceiverPhone( context ) );
        tradeOrder.setReceiverAddressSnapshot( contextAddressDetailAddress( context ) );

        tradeOrder.setStatus( OrderStatusEnum.WAIT_PAY );

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
    public OrderStateContext fromCancel(OrderSystemCancelEvent dto, TradeOrder tradeOrder) {
        if ( dto == null && tradeOrder == null ) {
            return null;
        }

        OrderStateContext.OrderStateContextBuilder orderStateContext = OrderStateContext.builder();

        if ( dto != null ) {
            orderStateContext.orderNo( dto.getOrderNo() );
            orderStateContext.cancelReason( dto.getCancelReason() );
        }
        if ( tradeOrder != null ) {
            orderStateContext.tradeOrder( tradeOrder );
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

    @Override
    public OrderRespDTO toOrderResp(TradeOrder entity) {
        if ( entity == null ) {
            return null;
        }

        OrderRespDTO orderRespDTO = new OrderRespDTO();

        orderRespDTO.setId( entity.getId() );
        orderRespDTO.setOrderNo( entity.getOrderNo() );
        orderRespDTO.setItemSpuId( entity.getItemSpuId() );
        orderRespDTO.setPayAmount( entity.getPayAmount() );
        orderRespDTO.setStatus( entity.getStatus() );
        orderRespDTO.setCreateTime( entity.getCreateTime() );

        return orderRespDTO;
    }

    @Override
    public List<OrderRespDTO> toOrderRespList(List<TradeOrder> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderRespDTO> list1 = new ArrayList<OrderRespDTO>( list.size() );
        for ( TradeOrder tradeOrder : list ) {
            list1.add( toOrderResp( tradeOrder ) );
        }

        return list1;
    }

    @Override
    public OrderDetailRespDTO toOrderDetailResp(TradeOrder entity) {
        if ( entity == null ) {
            return null;
        }

        OrderDetailRespDTO orderDetailRespDTO = new OrderDetailRespDTO();

        orderDetailRespDTO.setId( entity.getId() );
        orderDetailRespDTO.setOrderNo( entity.getOrderNo() );
        orderDetailRespDTO.setItemSpuId( entity.getItemSpuId() );
        orderDetailRespDTO.setPayAmount( entity.getPayAmount() );
        orderDetailRespDTO.setStatus( entity.getStatus() );
        orderDetailRespDTO.setCreateTime( entity.getCreateTime() );
        orderDetailRespDTO.setBuyerId( entity.getBuyerId() );
        orderDetailRespDTO.setSellerId( entity.getSellerId() );
        orderDetailRespDTO.setItemSkuId( entity.getItemSkuId() );
        orderDetailRespDTO.setTotalAmount( entity.getTotalAmount() );
        orderDetailRespDTO.setFreightAmount( entity.getFreightAmount() );
        orderDetailRespDTO.setOfferAmount( entity.getOfferAmount() );
        orderDetailRespDTO.setReceiverNameSnapshot( entity.getReceiverNameSnapshot() );
        orderDetailRespDTO.setReceiverPhoneSnapshot( entity.getReceiverPhoneSnapshot() );
        orderDetailRespDTO.setReceiverAddressSnapshot( entity.getReceiverAddressSnapshot() );
        orderDetailRespDTO.setPayTime( entity.getPayTime() );
        orderDetailRespDTO.setConsignTime( entity.getConsignTime() );
        orderDetailRespDTO.setFinishTime( entity.getFinishTime() );

        return orderDetailRespDTO;
    }

    @Override
    public OrderAdminRespDTO toOrderAdminResp(TradeOrder entity) {
        if ( entity == null ) {
            return null;
        }

        OrderAdminRespDTO orderAdminRespDTO = new OrderAdminRespDTO();

        orderAdminRespDTO.setId( entity.getId() );
        orderAdminRespDTO.setOrderNo( entity.getOrderNo() );
        orderAdminRespDTO.setBuyerId( entity.getBuyerId() );
        orderAdminRespDTO.setSellerId( entity.getSellerId() );
        orderAdminRespDTO.setItemSpuId( entity.getItemSpuId() );
        orderAdminRespDTO.setPayAmount( entity.getPayAmount() );
        orderAdminRespDTO.setCreateTime( entity.getCreateTime() );

        orderAdminRespDTO.setStatus( entity.getStatus() != null ? entity.getStatus().getCode() : null );

        return orderAdminRespDTO;
    }

    @Override
    public List<OrderAdminRespDTO> toOrderAdminRespList(List<TradeOrder> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderAdminRespDTO> list1 = new ArrayList<OrderAdminRespDTO>( list.size() );
        for ( TradeOrder tradeOrder : list ) {
            list1.add( toOrderAdminResp( tradeOrder ) );
        }

        return list1;
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

    private Long contextAddressId(OrderCreateContext orderCreateContext) {
        if ( orderCreateContext == null ) {
            return null;
        }
        AddressDTO address = orderCreateContext.getAddress();
        if ( address == null ) {
            return null;
        }
        Long id = address.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String contextAddressReceiverName(OrderCreateContext orderCreateContext) {
        if ( orderCreateContext == null ) {
            return null;
        }
        AddressDTO address = orderCreateContext.getAddress();
        if ( address == null ) {
            return null;
        }
        String receiverName = address.getReceiverName();
        if ( receiverName == null ) {
            return null;
        }
        return receiverName;
    }

    private String contextAddressReceiverPhone(OrderCreateContext orderCreateContext) {
        if ( orderCreateContext == null ) {
            return null;
        }
        AddressDTO address = orderCreateContext.getAddress();
        if ( address == null ) {
            return null;
        }
        String receiverPhone = address.getReceiverPhone();
        if ( receiverPhone == null ) {
            return null;
        }
        return receiverPhone;
    }

    private String contextAddressDetailAddress(OrderCreateContext orderCreateContext) {
        if ( orderCreateContext == null ) {
            return null;
        }
        AddressDTO address = orderCreateContext.getAddress();
        if ( address == null ) {
            return null;
        }
        String detailAddress = address.getDetailAddress();
        if ( detailAddress == null ) {
            return null;
        }
        return detailAddress;
    }
}
