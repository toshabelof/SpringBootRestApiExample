package com.belovstech.prjsalews.service;

import com.belovstech.prjsalews.entity.ClientToAddressEntity;
import com.belovstech.prjsalews.exception.AlreadyExistException;
import com.belovstech.prjsalews.exception.DataBaseException;
import com.belovstech.prjsalews.exception.NotFoundException;
import com.belovstech.prjsalews.repository.ClientToAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientToAddressService {

    @Autowired
    ClientToAddressRepository clientToAddressRepository;

    public List<ClientToAddressEntity> getAddressByClientId(UUID clientId){
        return clientToAddressRepository.findByClientId(clientId);
    }

    public UUID saveAddress(ClientToAddressEntity address) throws AlreadyExistException {
        if(clientToAddressRepository.findByAddress(address.getAddress()) != null){
            throw new AlreadyExistException("Адрес уже существует");
        }

        try {
            clientToAddressRepository.save(address);
            return address.getId();
        }
        catch(RuntimeException exception){
            throw new DataBaseException(exception.getMessage());
        }
    }

    public UUID updateAddress(ClientToAddressEntity address) throws AlreadyExistException {
        if (clientToAddressRepository.findById(address.getId()).isEmpty()){
            throw new NotFoundException(String.format("Такого ID (%s) адреса нет в базе данных", address.getId()));
        }

        if(clientToAddressRepository.findByAddress(address.getAddress()) != null){
            throw new AlreadyExistException("Адрес уже существует");
        }
        try {
            clientToAddressRepository.save(address);
        }
        catch (RuntimeException exception){
            throw new DataBaseException(exception.getMessage());
        }
        return address.getId();
    }

    public UUID deleteAddress(UUID addressId){
        if (clientToAddressRepository.findById(addressId).isEmpty()){
            throw new NotFoundException(String.format("Такого ID (%s) адреса нет в базе данных", addressId));
        }

        try {
            clientToAddressRepository.deleteById(addressId);
        }
        catch (RuntimeException exception){
            throw new DataBaseException(exception.getMessage());
        }

        return addressId;
    }
}
