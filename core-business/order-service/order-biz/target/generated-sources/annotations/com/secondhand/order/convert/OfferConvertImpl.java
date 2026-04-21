package com.secondhand.order.convert;

import com.secondhand.item.dto.ItemDetailDTO;
import com.secondhand.item.dto.ItemSkuDTO;
import com.secondhand.item.dto.ItemSpuDTO;
import com.secondhand.order.pojo.context.OfferCreateContext;
import com.secondhand.order.pojo.domain.Offer;
import com.secondhand.order.pojo.dto.OfferCreateReqDTO;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-14T21:20:12+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class OfferConvertImpl implements OfferConvert {

    @Override
    public Offer toOffer(OfferCreateContext context) {
        if ( context == null ) {
            return null;
        }

        Offer offer = new Offer();

        offer.setOfferPrice( contextReqDTOOfferPrice( context ) );
        offer.setItemSkuId( contextReqDTOItemSkuId( context ) );
        offer.setBuyerId( context.getBuyerId() );
        offer.setSellerId( contextItemDetailSpuSellerId( context ) );
        offer.setOriginalPrice( contextItemDetailSkuPrice( context ) );

        offer.setStatus( com.secondhand.order.enums.OfferStatusEnum.PENDING );

        return offer;
    }

    private BigDecimal contextReqDTOOfferPrice(OfferCreateContext offerCreateContext) {
        if ( offerCreateContext == null ) {
            return null;
        }
        OfferCreateReqDTO reqDTO = offerCreateContext.getReqDTO();
        if ( reqDTO == null ) {
            return null;
        }
        BigDecimal offerPrice = reqDTO.getOfferPrice();
        if ( offerPrice == null ) {
            return null;
        }
        return offerPrice;
    }

    private Long contextReqDTOItemSkuId(OfferCreateContext offerCreateContext) {
        if ( offerCreateContext == null ) {
            return null;
        }
        OfferCreateReqDTO reqDTO = offerCreateContext.getReqDTO();
        if ( reqDTO == null ) {
            return null;
        }
        Long itemSkuId = reqDTO.getItemSkuId();
        if ( itemSkuId == null ) {
            return null;
        }
        return itemSkuId;
    }

    private Long contextItemDetailSpuSellerId(OfferCreateContext offerCreateContext) {
        if ( offerCreateContext == null ) {
            return null;
        }
        ItemDetailDTO itemDetail = offerCreateContext.getItemDetail();
        if ( itemDetail == null ) {
            return null;
        }
        ItemSpuDTO spu = itemDetail.getSpu();
        if ( spu == null ) {
            return null;
        }
        Long sellerId = spu.getSellerId();
        if ( sellerId == null ) {
            return null;
        }
        return sellerId;
    }

    private BigDecimal contextItemDetailSkuPrice(OfferCreateContext offerCreateContext) {
        if ( offerCreateContext == null ) {
            return null;
        }
        ItemDetailDTO itemDetail = offerCreateContext.getItemDetail();
        if ( itemDetail == null ) {
            return null;
        }
        ItemSkuDTO sku = itemDetail.getSku();
        if ( sku == null ) {
            return null;
        }
        BigDecimal price = sku.getPrice();
        if ( price == null ) {
            return null;
        }
        return price;
    }
}
