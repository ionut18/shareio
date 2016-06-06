package org.licence.repository;

import org.licence.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ionut on 04.06.2016.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByIdRideAndPassenger(Long idRide, String passenger);

    List<Message> findByReceiverOrderByDateDesc(String username);

}
