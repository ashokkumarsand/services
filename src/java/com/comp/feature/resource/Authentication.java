/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comp.feature.resource;

import com.comp.feature.model.Credentials;
import com.comp.feature.session.utils.SessionIdentifierGenerator;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author ashok
 */
@Path("auth")
public class Authentication {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Authentication
     */
    public Authentication() {
    }

    @POST
    @Consumes("application/json")
    public Response authenticateUser(Credentials credentials) {
        
        System.out.println("username :: "+credentials.getUsername());
        try {

            // Authenticate the user using the credentials provided
            authenticate(credentials);

            // Issue a token for the user
            String token = issueToken(credentials.getUsername());

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }      
    }

    private void authenticate(Credentials credentials) throws Exception {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
    }

    private String issueToken(String username) {
        
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
        
        return new SessionIdentifierGenerator().nextSessionId();
    }
}
