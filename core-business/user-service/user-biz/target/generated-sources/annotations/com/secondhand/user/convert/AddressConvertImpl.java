package com.secondhand.user.convert;

import com.secondhand.user.dto.AddressDTO;
import com.secondhand.user.pojo.domain.Address;
import com.secondhand.user.pojo.dto.AddressAddReqDTO;
import com.secondhand.user.pojo.dto.AddressUpdateReqDTO;
import com.secondhand.user.pojo.vo.AddressRespDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-14T19:19:32+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class AddressConvertImpl implements AddressConvert {

    @Override
    public AddressDTO toAddressDTO(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setId( address.getId() );
        addressDTO.setUserId( address.getUserId() );
        addressDTO.setReceiverName( address.getReceiverName() );
        addressDTO.setReceiverPhone( address.getReceiverPhone() );
        addressDTO.setProvince( address.getProvince() );
        addressDTO.setCity( address.getCity() );
        addressDTO.setDistrict( address.getDistrict() );
        addressDTO.setDetailAddress( address.getDetailAddress() );
        addressDTO.setIsDefault( address.getIsDefault() );

        return addressDTO;
    }

    @Override
    public Address toAddress(AddressDTO addressDTO) {
        if ( addressDTO == null ) {
            return null;
        }

        Address address = new Address();

        address.setId( addressDTO.getId() );
        address.setUserId( addressDTO.getUserId() );
        address.setReceiverName( addressDTO.getReceiverName() );
        address.setReceiverPhone( addressDTO.getReceiverPhone() );
        address.setProvince( addressDTO.getProvince() );
        address.setCity( addressDTO.getCity() );
        address.setDistrict( addressDTO.getDistrict() );
        address.setDetailAddress( addressDTO.getDetailAddress() );
        address.setIsDefault( addressDTO.getIsDefault() );

        return address;
    }

    @Override
    public Address toAddress(AddressAddReqDTO reqDTO, Long userId) {
        if ( reqDTO == null && userId == null ) {
            return null;
        }

        Address address = new Address();

        if ( reqDTO != null ) {
            address.setReceiverName( reqDTO.getReceiverName() );
            address.setReceiverPhone( reqDTO.getReceiverPhone() );
            address.setProvince( reqDTO.getProvince() );
            address.setCity( reqDTO.getCity() );
            address.setDistrict( reqDTO.getDistrict() );
            address.setDetailAddress( reqDTO.getDetailAddress() );
            address.setIsDefault( reqDTO.getIsDefault() );
        }
        address.setUserId( userId );

        return address;
    }

    @Override
    public Address toAddress(AddressUpdateReqDTO reqDTO, Long userId) {
        if ( reqDTO == null && userId == null ) {
            return null;
        }

        Address address = new Address();

        if ( reqDTO != null ) {
            address.setReceiverName( reqDTO.getReceiverName() );
            address.setReceiverPhone( reqDTO.getReceiverPhone() );
            address.setProvince( reqDTO.getProvince() );
            address.setCity( reqDTO.getCity() );
            address.setDistrict( reqDTO.getDistrict() );
            address.setDetailAddress( reqDTO.getDetailAddress() );
        }
        address.setUserId( userId );

        return address;
    }

    @Override
    public List<AddressRespDTO> toRespList(List<Address> addressList) {
        if ( addressList == null ) {
            return null;
        }

        List<AddressRespDTO> list = new ArrayList<AddressRespDTO>( addressList.size() );
        for ( Address address : addressList ) {
            list.add( addressToAddressRespDTO( address ) );
        }

        return list;
    }

    protected AddressRespDTO addressToAddressRespDTO(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressRespDTO addressRespDTO = new AddressRespDTO();

        addressRespDTO.setId( address.getId() );
        addressRespDTO.setReceiverName( address.getReceiverName() );
        addressRespDTO.setReceiverPhone( address.getReceiverPhone() );
        addressRespDTO.setProvince( address.getProvince() );
        addressRespDTO.setCity( address.getCity() );
        addressRespDTO.setDistrict( address.getDistrict() );
        addressRespDTO.setDetailAddress( address.getDetailAddress() );
        addressRespDTO.setIsDefault( address.getIsDefault() );
        addressRespDTO.setUpdateTime( address.getUpdateTime() );

        return addressRespDTO;
    }
}
