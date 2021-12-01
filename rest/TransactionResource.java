package org.acme.rest;

import org.acme.model.Transaction;
import org.acme.producer.TransactionProducer;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransactionResource {

    @Inject
    TransactionProducer producer;

    @POST
    public Response send(Transaction transaction) {
        producer.sendTransactionToKafka(transaction);
        // Return an 202 - Accepted response.
        return Response.accepted().entity(transaction).build();
    }
}