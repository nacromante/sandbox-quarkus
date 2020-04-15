package com.sandbox.entry;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/entries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntryResource {

    @Inject
    private EntryService entryService;

    @POST
    public void saveEntry(EntryDto entryDto)
    {
        entryService.save(entryDto);
    }

    @GET
    public Iterable<Entry> getEntries()
    {
        return  entryService.findAll();
    }


    @DELETE
    @Path("{id}")
    public void deleteEntry(@PathParam(value = "id") Integer entryId)
    {
        entryService.remove(entryId);
    }
}