package edu.upc.dsa.services;


import edu.upc.dsa.UserManager;
import edu.upc.dsa.UserManagerImpl;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/users", description = "Endpoint to User Service")
@Path("/users")
public class UserService {

    private UserManager Um;

    public UserService() {
        this.Um = UserManagerImpl.getInstance();

    }
    @POST
    @ApiOperation(value = "add user", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })
    @Path("/adduser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User u) {
        String name = u.getName();
        String surname = u.getSurname();
        String email = u.getEmail();
        String birthday = u.getBirthday();
        String password = u.getPassword();
        this.Um.addUser(name, surname, birthday, email, password);

        return Response.status(201).build();
    }
    @POST
    @ApiOperation(value = "Login", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Error")
    })
    @Path("/adduser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Login(String email, String password) {
        Boolean auth = this.Um.login(email,password);
        if (auth) {
            return Response.status(201).build();
        }
        else{
        return Response.status(404).build();}
    }
    @GET
    @ApiOperation(value = "Get usuarios por orden alfabetico", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/getusersbyorder")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersbyOrder() {

        List<User> users = this.Um.ListUsersAlf();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build()  ;
    }

    @GET
    @ApiOperation(value = "Get objects por orden descendente", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjects() {

        List<Object> objects = this.Um.ListUsersAlf();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build()  ;
    }

}