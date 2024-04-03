package com.belovstech.prjsalews.service;

import com.belovstech.prjsalews.config.CustomUserDetails;
import com.belovstech.prjsalews.controller.DefaultExceptionHandler;
import com.belovstech.prjsalews.entity.ClientEntity;
import com.belovstech.prjsalews.exception.AlreadyExistException;
import com.belovstech.prjsalews.exception.DataBaseException;
import com.belovstech.prjsalews.exception.NotFoundException;
import com.belovstech.prjsalews.repository.ClientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("clientService")
public class ClientService {

    private static final Logger logger = LogManager.getLogger(DefaultExceptionHandler.class);

    @Autowired
    private ClientRepository clientRepository;

    public List<ClientEntity> getAllClients(CustomUserDetails userDetails) {
        return clientRepository.findByOwner(userDetails.getId());
    }

    public ClientEntity getClientById(UUID clientId, CustomUserDetails userDetails) throws NotFoundException {
        ClientEntity clientData = clientRepository.findByOwnerAndId(userDetails.getId(), clientId);
        if (clientData == null) {
            throw new NotFoundException();
        }
        return clientData;
    }

    public UUID createClient(ClientEntity client, CustomUserDetails userDetails) throws AlreadyExistException {
        try {
            ClientEntity clientData = clientRepository.findByOwnerAndPhoneNumber(
                    userDetails.getId(),
                    client.getPhoneNumber()
            );
            if (clientData != null) {
                throw new AlreadyExistException(
                        String.format("Клиент с таким номером телефоном уже существует (%s [%s])",
                                clientData.getFullName(), clientData.getId()
                        ));
            }

            ClientEntity clientToSave = new ClientEntity();
            clientToSave.setFullName(client.getFullName());
            clientToSave.setPhoneNumber(client.getPhoneNumber());
            clientToSave.setEmail(client.getEmail());
            clientToSave.setBirthday(client.getBirthday());
            clientToSave.setOwner(userDetails.getId());
            clientToSave.setInsUser(userDetails.getId());
            clientToSave.setUpdUser(userDetails.getId());

            return clientRepository.save(clientToSave).getId();

        } catch (RuntimeException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }

    public UUID updateClient(ClientEntity client, CustomUserDetails userDetails) throws NotFoundException {
        ClientEntity clientToSave = clientRepository.findByOwnerAndId(userDetails.getId(), client.getId());

        if (Objects.isNull(clientToSave)) {
            throw new NotFoundException("Клиент не найден");
        }

        try {

            if (client.getFullName() != null && !client.getFullName().isEmpty())
                clientToSave.setFullName(client.getFullName());
            if (client.getPhoneNumber() != null && !client.getPhoneNumber().isEmpty())
                clientToSave.setPhoneNumber(client.getPhoneNumber());
            if (client.getEmail() != null && !client.getEmail().isEmpty()) clientToSave.setEmail(client.getEmail());
            if (client.getBirthday() == null) clientToSave.setBirthday(client.getBirthday());

            clientToSave.setUpdUser(userDetails.getId());
            clientToSave.setUpdDate(Calendar.getInstance().getTime());
            clientRepository.save(clientToSave);

            return client.getId();

        } catch (RuntimeException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }

    public UUID deleteClient(UUID clientId, CustomUserDetails userDetails) throws NotFoundException {
        ClientEntity clientData = clientRepository.findByOwnerAndId(userDetails.getId(), clientId);

        if (clientData == null) {
            throw new NotFoundException("Клиент не найден");
        }

        try {
            clientRepository.deleteById(clientData.getId());
            return clientData.getId();

        } catch (RuntimeException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }
}
