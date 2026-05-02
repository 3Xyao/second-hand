package com.secondhand.item.convert;

import com.secondhand.item.dto.ItemDetailDTO;
import com.secondhand.item.dto.ItemSkuDTO;
import com.secondhand.item.dto.ItemSpuDTO;
import com.secondhand.item.dto.ItemSyncEsDTO;
import com.secondhand.item.dto.SaleAttrItem;
import com.secondhand.item.dto.admin.ItemAdminRespDTO;
import com.secondhand.item.dto.resp.ItemDetailRespDTO;
import com.secondhand.item.enums.SkuStatusEnum;
import com.secondhand.item.pojo.domain.Category;
import com.secondhand.item.pojo.domain.ItemSku;
import com.secondhand.item.pojo.domain.ItemSpu;
import com.secondhand.item.pojo.dto.ItemPublishDTO;
import com.secondhand.item.pojo.dto.ItemSkuPublishDTO;
import com.secondhand.item.pojo.dto.ItemSkuSyncEsDTO;
import com.secondhand.user.dto.UserForItemDetailRespDTO;
import com.secondhand.user.dto.UserSyncEsDTO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-02T12:03:07+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.0.v20260407-0427, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class ItemConvertImpl implements ItemConvert {

    @Override
    public ItemSyncEsDTO toFullSyncDTO(ItemSpu spu, ItemSkuSyncEsDTO itemSkuSyncEsDTO, Category category, UserSyncEsDTO user) {
        if ( spu == null && itemSkuSyncEsDTO == null && category == null && user == null ) {
            return null;
        }

        ItemSyncEsDTO itemSyncEsDTO = new ItemSyncEsDTO();

        if ( spu != null ) {
            itemSyncEsDTO.setId( spu.getId() );
            itemSyncEsDTO.setCreateTime( spu.getCreateTime() );
            itemSyncEsDTO.setUpdateTime( spu.getUpdateTime() );
            itemSyncEsDTO.setTitle( spu.getTitle() );
            itemSyncEsDTO.setImages( spu.getImages() );
            itemSyncEsDTO.setCity( spu.getCity() );
            itemSyncEsDTO.setConditionLevel( spu.getConditionLevel() );
            itemSyncEsDTO.setViewCount( spu.getViewCount() );
            itemSyncEsDTO.setFavoriteCount( spu.getFavoriteCount() );
            itemSyncEsDTO.setCommentCount( spu.getCommentCount() );
            itemSyncEsDTO.setCategoryId( spu.getCategoryId() );
            itemSyncEsDTO.setSellerId( spu.getSellerId() );
        }
        if ( itemSkuSyncEsDTO != null ) {
            itemSyncEsDTO.setPrice( itemSkuSyncEsDTO.getMinPrice() );
            itemSyncEsDTO.setMaxPrice( itemSkuSyncEsDTO.getMaxPrice() );
            itemSyncEsDTO.setPublishPrice( itemSkuSyncEsDTO.getMinPublishPrice() );
            itemSyncEsDTO.setStock( itemSkuSyncEsDTO.getTotalStock() );
        }
        if ( category != null ) {
            itemSyncEsDTO.setCategoryName( category.getName() );
        }
        if ( user != null ) {
            itemSyncEsDTO.setSellerName( user.getNickname() );
            itemSyncEsDTO.setSellerAvatar( user.getAvatarUrl() );
        }

        return itemSyncEsDTO;
    }

    @Override
    public ItemSpu toItemSpu(ItemPublishDTO dto, Long sellerId) {
        if ( dto == null && sellerId == null ) {
            return null;
        }

        ItemSpu itemSpu = new ItemSpu();

        if ( dto != null ) {
            itemSpu.setCategoryId( dto.getCategoryId() );
            itemSpu.setDescription( dto.getDescription() );
            List<SaleAttrItem> list = dto.getSaleAttrs();
            if ( list != null ) {
                itemSpu.setSaleAttrs( new ArrayList<SaleAttrItem>( list ) );
            }
            itemSpu.setTitle( dto.getTitle() );
        }
        itemSpu.setSellerId( sellerId );

        return itemSpu;
    }

    @Override
    public ItemDetailDTO toItemDetailDTO(ItemSpu spu, ItemSku sku) {
        if ( spu == null && sku == null ) {
            return null;
        }

        ItemDetailDTO itemDetailDTO = new ItemDetailDTO();

        itemDetailDTO.setSpu( itemSpuToItemSpuDTO( spu ) );
        itemDetailDTO.setSku( itemSkuToItemSkuDTO( sku ) );

        return itemDetailDTO;
    }

    @Override
    public ItemDetailRespDTO toItemDetailRespDTO(ItemSpu spu) {
        if ( spu == null ) {
            return null;
        }

        ItemDetailRespDTO itemDetailRespDTO = new ItemDetailRespDTO();

        itemDetailRespDTO.setId( spu.getId() );
        itemDetailRespDTO.setTitle( spu.getTitle() );
        itemDetailRespDTO.setDescription( spu.getDescription() );
        itemDetailRespDTO.setCategoryId( spu.getCategoryId() );
        itemDetailRespDTO.setSellerId( spu.getSellerId() );
        itemDetailRespDTO.setOriginalPrice( spu.getOriginalPrice() );
        itemDetailRespDTO.setFreight( spu.getFreight() );
        itemDetailRespDTO.setAllowOffer( spu.getAllowOffer() );
        itemDetailRespDTO.setConditionLevel( spu.getConditionLevel() );
        itemDetailRespDTO.setStatus( spu.getStatus() );
        itemDetailRespDTO.setViewCount( spu.getViewCount() );
        itemDetailRespDTO.setFavoriteCount( spu.getFavoriteCount() );
        itemDetailRespDTO.setCreateTime( spu.getCreateTime() );

        return itemDetailRespDTO;
    }

    @Override
    public void updateItemDetailRespDTOWithSeller(UserForItemDetailRespDTO seller, ItemDetailRespDTO respDTO) {
        if ( seller == null ) {
            return;
        }

        respDTO.setSellerId( seller.getSellerId() );
        respDTO.setSellerNickname( seller.getSellerNickname() );
        respDTO.setSellerAvatar( seller.getSellerAvatar() );
        respDTO.setFavorited( seller.isFavorited() );
    }

    @Override
    public List<ItemSkuDTO> toItemSkuDTOList(List<ItemSku> itemSkuList) {
        if ( itemSkuList == null ) {
            return null;
        }

        List<ItemSkuDTO> list = new ArrayList<ItemSkuDTO>( itemSkuList.size() );
        for ( ItemSku itemSku : itemSkuList ) {
            list.add( itemSkuToItemSkuDTO( itemSku ) );
        }

        return list;
    }

    @Override
    public ItemAdminRespDTO toItemAdminRespDTO(ItemSpu spu) {
        if ( spu == null ) {
            return null;
        }

        ItemAdminRespDTO itemAdminRespDTO = new ItemAdminRespDTO();

        itemAdminRespDTO.setId( spu.getId() );
        itemAdminRespDTO.setTitle( spu.getTitle() );
        itemAdminRespDTO.setSellerId( spu.getSellerId() );
        itemAdminRespDTO.setOriginalPrice( spu.getOriginalPrice() );
        itemAdminRespDTO.setProvince( spu.getProvince() );
        itemAdminRespDTO.setCity( spu.getCity() );
        itemAdminRespDTO.setImages( spu.getImages() );
        itemAdminRespDTO.setViewCount( spu.getViewCount() );
        itemAdminRespDTO.setFavoriteCount( spu.getFavoriteCount() );
        itemAdminRespDTO.setCommentCount( spu.getCommentCount() );
        itemAdminRespDTO.setCreateTime( spu.getCreateTime() );

        itemAdminRespDTO.setStatus( spu.getStatus() != null ? spu.getStatus().getCode() : null );

        return itemAdminRespDTO;
    }

    @Override
    public List<ItemAdminRespDTO> toItemAdminRespDTOList(List<ItemSpu> itemSpuList) {
        if ( itemSpuList == null ) {
            return null;
        }

        List<ItemAdminRespDTO> list = new ArrayList<ItemAdminRespDTO>( itemSpuList.size() );
        for ( ItemSpu itemSpu : itemSpuList ) {
            list.add( toItemAdminRespDTO( itemSpu ) );
        }

        return list;
    }

    @Override
    public ItemSku toItemSku(ItemSkuPublishDTO dto, ItemSpu spu) {
        if ( dto == null && spu == null ) {
            return null;
        }

        ItemSku itemSku = new ItemSku();

        if ( dto != null ) {
            itemSku.setPublishPrice( dto.getPrice() );
            itemSku.setPrice( dto.getPrice() );
            itemSku.setImages( dto.getImages() );
            Map<String, String> map = dto.getOwnSpec();
            if ( map != null ) {
                itemSku.setOwnSpec( new LinkedHashMap<String, String>( map ) );
            }
            itemSku.setStock( dto.getStock() );
        }
        if ( spu != null ) {
            itemSku.setSpuId( spu.getId() );
            itemSku.setCreateTime( spu.getCreateTime() );
            itemSku.setUpdateTime( spu.getUpdateTime() );
            itemSku.setIsDeleted( spu.getIsDeleted() );
        }
        itemSku.setLockedStock( 0 );
        itemSku.setVersion( 1 );
        itemSku.setStatus( SkuStatusEnum.ON_SHELVES );
        itemSku.setTitle( spu.getTitle() + " " + String.join(" ", dto.getOwnSpec().values()) );

        return itemSku;
    }

    protected ItemSpuDTO itemSpuToItemSpuDTO(ItemSpu itemSpu) {
        if ( itemSpu == null ) {
            return null;
        }

        ItemSpuDTO itemSpuDTO = new ItemSpuDTO();

        itemSpuDTO.setId( itemSpu.getId() );
        itemSpuDTO.setTitle( itemSpu.getTitle() );
        itemSpuDTO.setCategoryId( itemSpu.getCategoryId() );
        itemSpuDTO.setSellerId( itemSpu.getSellerId() );
        itemSpuDTO.setOriginalPrice( itemSpu.getOriginalPrice() );
        itemSpuDTO.setFreight( itemSpu.getFreight() );
        itemSpuDTO.setAllowOffer( itemSpu.getAllowOffer() );
        itemSpuDTO.setStatus( itemSpu.getStatus() );

        return itemSpuDTO;
    }

    protected ItemSkuDTO itemSkuToItemSkuDTO(ItemSku itemSku) {
        if ( itemSku == null ) {
            return null;
        }

        ItemSkuDTO itemSkuDTO = new ItemSkuDTO();

        itemSkuDTO.setId( itemSku.getId() );
        itemSkuDTO.setSpuId( itemSku.getSpuId() );
        itemSkuDTO.setTitle( itemSku.getTitle() );
        itemSkuDTO.setImages( itemSku.getImages() );
        itemSkuDTO.setPrice( itemSku.getPrice() );
        itemSkuDTO.setPublishPrice( itemSku.getPublishPrice() );
        itemSkuDTO.setStock( itemSku.getStock() );
        itemSkuDTO.setLockedStock( itemSku.getLockedStock() );
        itemSkuDTO.setVersion( itemSku.getVersion() );
        Map<String, String> map = itemSku.getOwnSpec();
        if ( map != null ) {
            itemSkuDTO.setOwnSpec( new LinkedHashMap<String, String>( map ) );
        }
        itemSkuDTO.setStatus( itemSku.getStatus() );

        return itemSkuDTO;
    }
}
