package com.spring.aftas.services.interfaces;

import java.util.List;
import java.util.Optional;

public interface IData<Response,Request,type> {

    List<Response> getAllService();

    Optional<Response> saveService(Request request);

    boolean deleteService(type Id);

    Optional<Response> updateService(Request request,type Id);

    Optional<Response> findByIdService(type Id);



}
