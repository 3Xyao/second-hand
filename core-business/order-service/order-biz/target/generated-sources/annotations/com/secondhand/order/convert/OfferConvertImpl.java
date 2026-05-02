package com.secondhand.order.convert;

import com.secondhand.item.dto.ItemDetailDTO;
import com.secondhand.item.dto.ItemSkuDTO;
import com.secondhand.item.dto.ItemSpuDTO;
import com.secondhand.order.dto.resp.OfferRespDTO;
import com.secondhand.order.enums.OfferStatusEnum;
import com.secondhand.order.pojo.context.OfferCreateContext;
import com.secondhand.order.pojo.domain.Offer;
import com.secondhand.order.pojo.dto.OfferCreateReqDTO;
import java.math.BigDecimal;
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

        offer.setStatus( OfferStatusEnum.PENDING );

        return offer;
    }

    @Override
    public OfferRespDTO toOfferResp(Offer entity) {
        if ( entity == null ) {
            return null;
        }

        OfferRespDTO offerRespDTO = new OfferRespDTO();

        offerRespDTO.setId( entity.getId() );
        offerRespDTO.setBuyerId( entity.getBuyerId() );
        offerRespDTO.setSellerId( entity.getSellerId() );
        offerRespDTO.setItemSkuId( entity.getItemSkuId() );
        offerRespDTO.setOriginalPrice( entity.getOriginalPrice() );
        offerRespDTO.setOfferPrice( entity.getOfferPrice() );
        offerRespDTO.setStatus( entity.getStatus() );
        offerRespDTO.setCreateTime( entity.getCreateTime() );

        return offerRespDTO;
    }

    @Override
    public List<OfferRespDTO> toOfferRespList(List<Offer> list) {
        if ( list == null ) {
            return null;
        }

        List<OfferRespDTO> list1 = new ArrayList<OfferRespDTO>( list.size() );
        for ( Offer offer : list ) {
            list1.add( toOfferResp( offer ) );
        }

        return list1;
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
